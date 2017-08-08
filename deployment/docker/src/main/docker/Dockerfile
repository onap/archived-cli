FROM ubuntu:14.04

RUN apt-get update
RUN apt-get update && apt-get install -y software-properties-common
RUN sudo -E add-apt-repository ppa:openjdk-r/ppa && apt-get update && apt-get install -y openjdk-8-jdk
ENV JAVA_HOME usr/lib/jvm/java-8-openjdk-amd64

ENV ONAP_CLI_HOME /opt/onap/cli
ADD ./STAGE $ONAP_CLI_HOME

WORKDIR $ONAP_CLI_HOME
RUN chmod +x ./bin/onap.sh
RUN mkdir ./data
RUN mkdir ./onap-cli-schema
RUN ln ./bin/onap.sh /usr/sbin/onap

ENTRYPOINT /bin/bash

RUN echo ONAP CLI successfully installed !!