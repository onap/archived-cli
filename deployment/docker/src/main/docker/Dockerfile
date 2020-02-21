# Copyright 2018 Huawei Technologies Co., Ltd.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

FROM openjdk:11.0.5-jre-slim

ENV OPEN_CLI_HOME=/opt/oclip \
    OPEN_CLI_DEBUG=false \
    OPEN_CLI_DEBUG_PORT=5005 \
    OPEN_CLI_MODE=shell \
    OPEN_CLI_PRODUCT_IN_USE=onap-dublin \
    GOTTY_TITLE_FORMAT="{{ .command }}" \
    OCLIP_GRPC_SERVER="http://oclip_ocs_grpc:50051"

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
    chmod +x  $OPEN_CLI_HOME/bin/oclip-rcli.sh  && \
    chmod +x  $OPEN_CLI_HOME/bin/oclip-grpc-server.sh  && \
    ln  $OPEN_CLI_HOME/bin/oclip.sh /usr/sbin/oclip  && \
    ln  $OPEN_CLI_HOME/bin/oclip.sh /usr/sbin/onap  && \
    ln  $OPEN_CLI_HOME/bin/oclip-grpc-server.sh /usr/sbin/oclip-grpc  && \
    ln  $OPEN_CLI_HOME/bin/oclip-rcli.sh /usr/sbin/oclipr  && \
    if [ ! -d  $OPEN_CLI_HOME/data ]; then mkdir  $OPEN_CLI_HOME/data; fi  && \
    if [ ! -d  $OPEN_CLI_HOME/open-cli-schema ]; then mkdir  $OPEN_CLI_HOME/open-cli-schema; fi && \
    if [ ! -f /var/log/lighttpd/access.log ]; then touch /var/log/lighttpd/access.log; fi && \
    cp /etc/lighttpd/conf-available/10-accesslog.conf /etc/lighttpd/conf-enabled/ && \
    cp $OPEN_CLI_HOME/http/lighttpd/lighttpd.conf /etc/lighttpd/lighttpd.conf && \
    if [ ! -d  /var/www-data/servers/open-cli/ ]; then mkdir -p /var/www-data/servers/open-cli/; fi  && \
    cp $OPEN_CLI_HOME/http/web/*.* /var/www-data/servers/open-cli/ && \
    cp $OPEN_CLI_HOME/http/lighttpd/10-proxy.conf /etc/lighttpd/conf-enabled/ && \
    cp $OPEN_CLI_HOME/installer/cli-*.zip /var/www-data/servers/open-cli/oclip.zip && \
    cp $OPEN_CLI_HOME/http/web/ocomp.crt ~/.gotty.crt && \
    cp $OPEN_CLI_HOME/http/web/ocomp.key ~/.gotty.key
    #openssl req -x509 -nodes -days 9999 -newkey rsa:2048 -keyout ~/.ocomp.key -out ~/.ocomp.crt -subj "/C=IN/ST=KA/L=BLR/O=CLI/CN=onap"
EXPOSE 80
EXPOSE 8080
EXPOSE 50051
ENTRYPOINT if [ "$OPEN_CLI_MODE" = "daemon" ]; then service lighttpd start; gotty -t --permit-write --reconnect oclip; \
           elif [ "$OPEN_CLI_MODE" = "ocs-web" ]; then echo "****** OCLIP Web Command Server ******"; service lighttpd start; \
           elif [ "$OPEN_CLI_MODE" = "ocs-grpc" ]; then echo "****** OCLIP gRPC Command Server ******"; oclip-grpc; \
           elif [ "$OPEN_CLI_MODE" = "occ" ]; then echo "****** OCLIP Web Command Console (OCC) ******"; gotty --permit-write --reconnect oclip; \
           else echo "****** OCLIP Command Shell******"; oclip -v && /bin/bash; fi
