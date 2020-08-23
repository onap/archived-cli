#!/bin/bash

#*******************************************************************************
# Copyright 2017-18 Huawei Technologies Co., Ltd.
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
#*******************************************************************************

CLI_LATEST_BINARY="https://nexus.onap.org/service/local/artifact/maven/redirect?r=releases&g=org.onap.cli&a=cli-zip&e=zip&v=LATEST"
CLI_INSTALL_DIR=/opt/oclip
CLI_ZIP=cli.zip
CLI_BIN=/usr/bin/oclip

#create install dir
if [ -d $CLI_INSTALL_DIR ]
then
    mv $CLI_INSTALL_DIR $CLI_INSTALL_DIR/../cli_`date +"%m-%d-%y-%H-%M-%S"`
    rm $CLI_BIN
fi

mkdir -p $CLI_INSTALL_DIR
cd $CLI_INSTALL_DIR

#Download and unzip CLI
apt-get install -y -qq --no-install-recommends wget unzip

#check for java
java -version
if [ $? == 127 ]
then
    apt-get install -y -qq --no-install-recommends openjdk-8-jre
fi

wget -O $CLI_ZIP $CLI_LATEST_BINARY

unzip $CLI_ZIP

source ./install.sh
