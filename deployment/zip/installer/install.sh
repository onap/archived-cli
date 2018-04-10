#!/bin/bash

#*******************************************************************************
# Copyright 2017 Huawei Technologies Co., Ltd.
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

export OPEN_CLI_HOME=/opt/oclip
export OPEN_CLI_PRODUCT_IN_USE=open-cli

cd $OPEN_CLI_HOME

if [ ! -d ./data ]; then mkdir ./data; fi
if [ ! -d ./open-cli-schema ]; then mkdir ./open-cli-schema; fi

chmod +x ./bin/oclip.sh

#Make oclip available in path
ln -sf ./bin/oclip.sh /usr/bin/oclip
ln -sf ./bin/oclip.sh /usr/bin/onap

#Print the version
oclip -v

onap -v

cd -