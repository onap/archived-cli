FROM openjdk:8-jre-slim

ENV OPEN_CLI_HOME=/opt/oclip \
    OPEN_CLI_DEBUG=false \
    OPEN_CLI_DEBUG_PORT=5005 \
    OPEN_CLI_MODE=console \
    OPEN_CLI_PRODUCT_IN_USE=open-cli \
    GOTTY_TITLE_FORMAT="{{ .command }}"

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
    if [ ! -f /var/log/lighttpd/access.log ]; then touch /var/log/lighttpd/access.log; fi && \
    cp /etc/lighttpd/conf-available/10-accesslog.conf /etc/lighttpd/conf-enabled/ && \
    cp $OPEN_CLI_HOME/http/lighttpd/lighttpd.conf /etc/lighttpd/lighttpd.conf && \
    if [ ! -d  /var/www-data/servers/open-cli/ ]; then mkdir -p /var/www-data/servers/open-cli/; fi  && \
    cp $OPEN_CLI_HOME/http/web/*.* /var/www-data/servers/open-cli/ && \
    cp $OPEN_CLI_HOME/http/lighttpd/10-proxy.conf /etc/lighttpd/conf-enabled/ && \
    cp $OPEN_CLI_HOME/installer/cli-*.zip /var/www-data/servers/open-cli/oclip.zip
EXPOSE 80
EXPOSE 8080

ENTRYPOINT if [ "$OPEN_CLI_MODE" = "daemon" ]; then service lighttpd start; gotty --permit-write --reconnect oclip; else oclip -v && /bin/bash; fi
