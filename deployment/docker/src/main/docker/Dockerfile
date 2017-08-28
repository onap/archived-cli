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
RUN if [ ! -f /var/log/lighttpd/access.log ]; then touch /var/log/lighttpd/access.log; fi
RUN ln ./bin/onap.sh /usr/sbin/onap

EXPOSE 80

ENV ONAP_HOST_URL http://localhost:8080
ENV ONAP_USERNAME guest
ENV ONAP_PASSWORD guest
ENV ONAP_CLI_DEBUG false
ENV ONAP_CLI_DEBUG_PORT 5005
ENV MODE console

ENTRYPOINT cp /etc/lighttpd/conf-available/10-accesslog.conf /etc/lighttpd/conf-enabled/ && \
           service lighttpd start && \
           if [ "$MODE" = "deamon" ]; then tailf /var/log/lighttpd/access.log;else /bin/bash; fi

RUN echo ONAP CLI docker successfully created !!