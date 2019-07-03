FROM mysql:8.0 AS mysql
# パッケージをダウンロードする
RUN mkdir /tmp/mysql-pkg \
  && cd /tmp/mysql-pkg \
  && apt-get update && apt-get download $(apt-cache depends --recurse --no-recommends --no-suggests \
  --no-conflicts --no-breaks --no-replaces --no-enhances \
  --no-pre-depends mysql-community-client="${MYSQL_VERSION}" mysql-community-server-core="${MYSQL_VERSION}" | grep "^\w") \
  && rm -rf /var/lib/apt/lists/*

# パッケージをダウンロードする
FROM eed3si9n/sbt:jdk8-alpine as sbt

RUN mkdir /tmp/sbt-play \
  && cd /tmp/sbt-play \
  && mkdir project \
  && echo 'addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.7.0")' > project/plugins.sbt \
  && echo 'sbt.version=1.2.8' > project/build.properties \
  && echo -e 'lazy val root = (project in file("."))\n.enablePlugins(PlayScala, JavaAppPackaging, DockerPlugin)\nlibraryDependencies ++= Seq(ws, guice)' \
  && sbt package \
  && cd / \
  && rm -rf /tmp/sbt-play



# テスト用。イメージは build.sbt のそれに合わせる
FROM openjdk:8-jdk-stretch

COPY --from=sbt /opt/sbt /opt/sbt
COPY --from=sbt /home/demiourgos1/.ivy2 /root/.ivy2
COPY --from=sbt /home/demiourgos1/.sbt /root/.sbt

ENV PATH="/opt/sbt/sbt/bin:$PATH" \
    JAVA_OPTS="-XX:+UseContainerSupport -Dfile.encoding=UTF-8" \
    SBT_OPTS="-Xmx2048M -Xss2M"

# --- mysql 8 ---------------
COPY --from=mysql /tmp/mysql-pkg /tmp/mysql-pkg

RUN groupadd -r mysql && useradd -r -g mysql mysql
RUN cd /tmp/mysql-pkg && dpkg -i *.deb

COPY --from=mysql /etc/mysql /etc/mysql

# ---load basic play jars------------------

ADD prj /tmp/prj
RUN cd /tmp/prj \
  && sbt package \
  && cd / \
  && rm -rf /tmp/prj

# ---load additional jars------------------
ADD prj /tmp/prj
ADD plugins.sbt /tmp/prj/project/
ADD build.sbt /tmp/prj/
RUN cd /tmp/prj \
  && sbt package \
  && cd / \
  && rm -rf /tmp/prj

