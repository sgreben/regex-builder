FROM alpine:3.8 AS download-maven
RUN apk add --no-cache curl
ARG MAVEN_VERSION=3.5.4
ARG SHA=ce50b1c91364cb77efe3776f756a6d92b76d9038b0a0782f7d53acf1e997a14d
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries
RUN mkdir -p /app /app/ref \
  && curl -fsSL -o /maven.tar.gz ${BASE_URL}/apache-maven-"${MAVEN_VERSION}"-bin.tar.gz \
  && echo "${SHA}  /maven.tar.gz" | sha256sum -c - \
  && tar -xzf /maven.tar.gz -C /app --strip-components=1

FROM alpine:3.8 AS generate-gpg-key
RUN apk add --no-cache gnupg1
ENV GNUPGHOME=/key
RUN mkdir -p "${GNUPGHOME}"
COPY gpg-params /gpg-params
RUN gpg --batch --gen-key /gpg-params

FROM openjdk:9-jdk-slim AS build-jar
RUN ln -s /etc/java-9-openjdk /usr/lib/jvm/java-9-openjdk-$(dpkg --print-architecture)/conf
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "${HOME}/.m2"
COPY --from=download-maven /app "${MAVEN_HOME}"
RUN ln -s "${MAVEN_HOME}"/bin/mvn /usr/bin/mvn
# Build app
ENV APP_SOURCE /app
WORKDIR /app
COPY pom.xml pom.xml
RUN mvn validate dependency:go-offline
COPY src src
RUN mvn clean compile test
RUN mvn -DskipTests=true package

FROM build-jar AS build-signed-jar
RUN apt-get update && apt-get install -y gnupg1 && rm -rf /var/lib/apt/lists/*
ENV GNUPGHOME=/key
COPY --from=generate-gpg-key /key "${GNUPGHOME}"
COPY settings.xml /root/.m2/
RUN mvn -o -DskipTests=true verify

FROM build-signed-jar AS deploy-snapshot
RUN mvn -DskipTests=true deploy

FROM build-signed-jar AS deploy-release
RUN mvn -DskipTests=true deploy
