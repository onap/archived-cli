FROM ubuntu:14.04

#installation
RUN apt-get update
RUN apt-get update && apt-get install -y software-properties-common
RUN sudo -E add-apt-repository ppa:openjdk-r/ppa && apt-get update && apt-get install -y openjdk-8-jdk && apt-get install -y lighttpd && apt-get install -y git curl
RUN cd /tmp && curl -O https://storage.googleapis.com/golang/go1.9.linux-amd64.tar.gz && tar -xvf go1.9.linux-amd64.tar.gz
RUN mkdir -p /tmp/gotty && \
  GOPATH=/tmp/gotty /tmp/go/bin/go get github.com/yudai/gotty && \
  mv /tmp/gotty/bin/gotty /usr/sbin/ && rm /tmp/go1.9.linux-amd64.tar.gz && rm -rf /tmp/go /tmp/gotty && cd -

ENV JAVA_HOME usr/lib/jvm/java-8-openjdk-amd64
ENV ONAP_CLI_HOME /opt/onap/cli
ENV ONAP_CLI_DEBUG false
ENV ONAP_CLI_DEBUG_PORT 5005
ENV CLI_MODE console
ENV CLI_PRODUCT_VERSION cli-1.0

ENV ONAP_HOST_URL http://localhost:8080
ENV ONAP_USERNAME guest
ENV ONAP_PASSWORD guest

ADD ./STAGE $ONAP_CLI_HOME
WORKDIR $ONAP_CLI_HOME

RUN chmod +x ./bin/onap.sh
RUN ln ./bin/onap.sh /usr/sbin/onap
RUN if [ ! -d ./data ]; then mkdir ./data; fi
RUN if [ ! -d ./onap-cli-schema ]; then mkdir ./onap-cli-schema; fi

COPY ./STAGE/http/lighttpd/lighttpd.conf /etc/lighttpd/lighttpd.conf
COPY ./STAGE/http/web /var/www-data/servers/onap-cli/
COPY ./STAGE/installer/*.zip /var/www-data/servers/onap-cli/
RUN if [ ! -f /var/log/lighttpd/access.log ]; then touch /var/log/lighttpd/access.log; fi
COPY ./STAGE/http/lighttpd/10-proxy.conf /etc/lighttpd/conf-enabled/
RUN cp /etc/lighttpd/conf-available/10-accesslog.conf /etc/lighttpd/conf-enabled/

#Lighttpd
EXPOSE 80

#gotty
EXPOSE 8080

ENTRYPOINT if [ "$CLI_MODE" = "daemon" ]; then service lighttpd start; gotty --permit-write --reconnect onap; else onap -v && /bin/bash; fi

RUN echo ONAP CLI docker successfully created !!