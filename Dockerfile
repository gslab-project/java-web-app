FROM ubuntu:latest
MAINTAINER Santosh Yadav

RUN apt-get update && \
    apt-get install -y openjdk-8-jdk && \
    apt-get install -y ant && \
    apt-get clean;

# Fix certificate issues
RUN apt-get update && \
    apt-get install ca-certificates-java && \
    apt-get clean && \
    update-ca-certificates -f;

# Setup JAVA_HOME -- useful for docker commandline
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
ENV POSTGRES_DB_HOST jdbc:postgresql://localhost
ENV POSTGRES_DB_PORT 5432
ENV POSTGRES_DB postgres
ENV POSTGRES_USER postgress
ENV POSTGRES_PASSWORD gslab@123
RUN export JAVA_HOME
RUN export POSTGRES_DB_HOST
RUN export POSTGRES_DB_PORT
RUN export POSTGRES_DB
RUN export POSTGRES_USER
RUN export POSTGRES_PASSWORD

COPY ./target/ems_demo-0.0.1.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8081
CMD ["echo", "Hello EMS!!!"]
ENTRYPOINT ["java", "-jar", "ems_demo-0.0.1.jar"]



