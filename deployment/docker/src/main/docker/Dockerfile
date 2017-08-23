FROM ubuntu:14.04

RUN apt-get update
RUN apt-get update && apt-get install -y software-properties-common
RUN sudo -E add-apt-repository ppa:openjdk-r/ppa && apt-get update && apt-get install -y openjdk-8-jdk && apt-get install -y lighttpd
ENV JAVA_HOME usr/lib/jvm/java-8-openjdk-amd64

ENV ONAP_CLI_HOME /opt/onap/cli
ADD ./STAGE $ONAP_CLI_HOME
COPY ./STAGE/http/lighttpd/lighttpd.conf /etc/lighttpd/lighttpd.conf
COPY ./STAGE/http/web /var/www-data/servers/onap-cli/
COPY ./STAGE/installer/*.zip /var/www-data/servers/onap-cli/

WORKDIR $ONAP_CLI_HOME
RUN chmod +x ./bin/onap.sh
RUN if [ ! -d ./data ]; then mkdir ./data; fi
RUN if [ ! -d ./onap-cli-schema ]; then mkdir ./onap-cli-schema; fi
RUN ln ./bin/onap.sh /usr/sbin/onap

EXPOSE 80
ENTRYPOINT service lighttpd start && /bin/bash

RUN echo ONAP CLI docker successfully created !!