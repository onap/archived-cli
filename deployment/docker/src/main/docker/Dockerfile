FROM openjdk:8-jre-slim

#Setup default software
RUN apt-get update && apt-get install -y lighttpd git curl pandoc vim

#Setup gotty
RUN cd /tmp && curl -O https://storage.googleapis.com/golang/go1.9.linux-amd64.tar.gz && \
    tar -xvf go1.9.linux-amd64.tar.gz && mkdir -p /tmp/gotty && \
    GOPATH=/tmp/gotty /tmp/go/bin/go get github.com/yudai/gotty && \
    mv /tmp/gotty/bin/gotty /usr/sbin/

#Environments
ENV ONAP_CLI_HOME /opt/onap/cli
ENV ONAP_CLI_DEBUG false
ENV ONAP_CLI_DEBUG_PORT 5005
ENV CLI_MODE console
ENV CLI_PRODUCT_VERSION onap-1.1

ENV HOST_URL http://localhost:8080
ENV ONAP_USERNAME guest
ENV ONAP_PASSWORD guest

#Copy CLI into docker
ADD ./STAGE $ONAP_CLI_HOME
WORKDIR $ONAP_CLI_HOME

#Setup the run time environment (RTE)
#RTE: CLI
RUN chmod +x ./bin/onap.sh  && \
     ln ./bin/onap.sh /usr/sbin/onap  && \
     if [ ! -d ./data ]; then mkdir ./data; fi  && \
     if [ ! -d ./onap-cli-schema ]; then mkdir ./onap-cli-schema; fi

#Create the readable README
RUN pandoc -t plain $ONAP_CLI_HOME/docs/README.md > $ONAP_CLI_HOME/docs/onap-readme.txt

#RTE: lighttpd
COPY ./STAGE/http/lighttpd/lighttpd.conf /etc/lighttpd/lighttpd.conf
COPY ./STAGE/http/web /var/www-data/servers/onap-cli/
COPY ./STAGE/http/lighttpd/10-proxy.conf /etc/lighttpd/conf-enabled/
COPY ./STAGE/installer/cli-*.zip /var/www-data/servers/onap-cli/onap-cli.zip

RUN if [ ! -f /var/log/lighttpd/access.log ]; then touch /var/log/lighttpd/access.log; fi
RUN cp /etc/lighttpd/conf-available/10-accesslog.conf /etc/lighttpd/conf-enabled/

EXPOSE 80

#RTE: gotty
EXPOSE 8080

#Start
ENTRYPOINT if [ "$CLI_MODE" = "daemon" ]; then service lighttpd start; gotty --permit-write --reconnect onap; else onap -v && /bin/bash; fi

#Cleanup
RUN apt-get purge -y pandoc && apt-get autoremove -y && apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/go /tmp/gotty /tmp/* /var/tmp/*

RUN echo ONAP CLI docker successfully created !!