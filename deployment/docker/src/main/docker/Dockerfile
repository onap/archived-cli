FROM openjdk:8-jre-slim

#Setup default software
RUN apt-get update && apt-get install -y lighttpd git curl pandoc vim

#Setup gotty
RUN cd /tmp && curl -O https://storage.googleapis.com/golang/go1.9.linux-amd64.tar.gz && \
    tar -xvf go1.9.linux-amd64.tar.gz && mkdir -p /tmp/gotty && \
    GOPATH=/tmp/gotty /tmp/go/bin/go get github.com/yudai/gotty && \
    mv /tmp/gotty/bin/gotty /usr/sbin/

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

#Setup the run time environment (RTE)
#RTE: CLI
RUN chmod +x ./bin/oclip.sh  && \
     ln ./bin/oclip.sh /usr/sbin/oclip  && \
     if [ ! -d ./data ]; then mkdir ./data; fi  && \
     if [ ! -d ./open-cli-schema ]; then mkdir ./open-cli-schema; fi

#Create the readable README
RUN pandoc -t plain $OPEN_CLI_HOME/docs/README.md > $OPEN_CLI_HOME/docs/oclip-readme.txt

#RTE: lighttpd
COPY ./STAGE/http/lighttpd/lighttpd.conf /etc/lighttpd/lighttpd.conf
COPY ./STAGE/http/web /var/www-data/servers/open-cli/
COPY ./STAGE/http/lighttpd/10-proxy.conf /etc/lighttpd/conf-enabled/
COPY ./STAGE/installer/cli-*.zip /var/www-data/servers/open-cli/oclip.zip

RUN if [ ! -f /var/log/lighttpd/access.log ]; then touch /var/log/lighttpd/access.log; fi
RUN cp /etc/lighttpd/conf-available/10-accesslog.conf /etc/lighttpd/conf-enabled/

EXPOSE 80

#RTE: gotty
EXPOSE 8080

#Start
ENTRYPOINT if [ "$OPEN_CLI_MODE" = "daemon" ]; then service lighttpd start; gotty --permit-write --reconnect oclip; else oclip -v && /bin/bash; fi

#Cleanup
RUN apt-get purge -y pandoc && apt-get autoremove -y && apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/go /tmp/gotty /tmp/* /var/tmp/*

RUN echo Open CLI docker successfully created !!
