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

if [ -z "$OPEN_CLI_HOME" ]
then
    echo "There is no OPEN_CLI_HOME"
    exit 1
fi

CLASSPATH=$OPEN_CLI_HOME:$OPEN_CLI_HOME/conf:$OPEN_CLI_HOME/docs
for entry in "$OPEN_CLI_HOME/lib"/*
do
  CLASSPATH=$CLASSPATH:$entry
done

if [ "$OPEN_CLI_DEBUG" = "true" ]
then
    java -Xdebug -Xrunjdwp:transport=dt_socket,address=${OPEN_CLI_DEBUG_PORT:-5005},server=y -classpath $CLASSPATH -DOPEN_CLI_HOME=$OPEN_CLI_HOME org.onap.cli.main.OnapCli "$@"
else
    java -classpath $CLASSPATH -DOPEN_CLI_HOME=$OPEN_CLI_HOME org.onap.cli.main.OnapCli "$@"
fi
