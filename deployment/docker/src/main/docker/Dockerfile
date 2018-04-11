FROM openjdk:8-jre-slim

#Environments
ENV OPEN_CLI_HOME=/opt/oclip \
    OPEN_CLI_DEBUG=false \
    OPEN_CLI_DEBUG_PORT=5005 \
    OPEN_CLI_MODE=console \
    OPEN_CLI_PRODUCT_IN_USE=open-cli \
    GOTTY_TITLE_FORMAT="{{ .command }}"

#Copy CLI into docker
ADD ./STAGE $OPEN_CLI_HOME
WORKDIR $OPEN_CLI_HOME

RUN apt-get update && apt-get install -y lighttpd git curl pandoc vim && \
    cd /tmp && curl -O https://storage.googleapis.com/golang/go1.9.linux-amd64.tar.gz && \
    tar -xvf go1.9.linux-amd64.tar.gz && mkdir -p /tmp/gotty && \
    GOPATH=/tmp/gotty /tmp/go/bin/go get github.com/yudai/gotty && \
    mv /tmp/gotty/bin/gotty /usr/sbin/ && \
    pandoc -t plain $OPEN_CLI_HOME/docs/README.md > $OPEN_CLI_HOME/docs/oclip-readme.txt && \
    apt-get purge -y pandoc && apt-get autoremove -y && apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/go /tmp/gotty /tmp/* /var/tmp/* && \
    chmod +x  $OPEN_CLI_HOME/bin/oclip.sh  && \
    ln  $OPEN_CLI_HOME/bin/oclip.sh /usr/sbin/oclip  && \
    ln  $OPEN_CLI_HOME/bin/oclip.sh /usr/sbin/onap  && \
    if [ ! -d  $OPEN_CLI_HOME/data ]; then mkdir  $OPEN_CLI_HOME/data; fi  && \
    if [ ! -d  $OPEN_CLI_HOME/open-cli-schema ]; then mkdir  $OPEN_CLI_HOME/open-cli-schema; fi && \
    if [ ! -f /var/log/lighttpd/access.log ]; then touch /var/log/lighttpd/access.log; fi

#RTE: lighttpd
COPY ./STAGE/http/lighttpd/lighttpd.conf /etc/lighttpd/lighttpd.conf
COPY ./STAGE/http/web /var/www-data/servers/open-cli/
COPY ./STAGE/http/lighttpd/10-proxy.conf /etc/lighttpd/conf-enabled/
COPY ./STAGE/installer/cli-*.zip /var/www-data/servers/open-cli/oclip.zip

EXPOSE 80

#RTE: gotty
EXPOSE 8080

#Start
ENTRYPOINT if [ "$OPEN_CLI_MODE" = "daemon" ]; then service lighttpd start; gotty --permit-write --reconnect oclip; else oclip -v && /bin/bash; fi
