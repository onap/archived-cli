@echo off
REM #*******************************************************************************
REM # Copyright 2017 Huawei Technologies Co., Ltd.
REM #
REM # Licensed under the Apache License, Version 2.0 (the "License");
REM # you may not use this file except in compliance with the License.
REM # You may obtain a copy of the License at
REM #
REM #     http://www.apache.org/licenses/LICENSE-2.0
REM #
REM # Unless required by applicable law or agreed to in writing, software
REM # distributed under the License is distributed on an "AS IS" BASIS,
REM # WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
REM # See the License for the specific language governing permissions and
REM # limitations under the License.
REM #*******************************************************************************

if "%OPEN_CLI_HOME%"=="" (
	echo "OPEN_CLI_HOME is not set."
	exit 1
)

if "%OPEN_CLI_DEBUG_PORT%"=="" (
	set OPEN_CLI_DEBUG_PORT=5005
)

set CLASSPATH=%OPEN_CLI_HOME%\conf;%OPEN_CLI_HOME%\docs;%OPEN_CLI_HOME%;%OPEN_CLI_HOME%\lib\*
set PATH=%OPEN_CLI_HOME%\bin;%PATH%

::SETLOCAL EnableDelayedExpansion
::for /f %%e in ('dir /b %OPEN_CLI_HOME%\lib') do (
::	set "CLASSPATH=!CLASSPATH!;%OPEN_CLI_HOME%\lib\%%e"
::)

if "%OPEN_CLI_DEBUG%"=="true" (
	java -Xdebug -Xrunjdwp:transport=dt_socket,address=%OPEN_CLI_DEBUG_PORT%,server=y -classpath %CLASSPATH% -DOPEN_CLI_HOME=%OPEN_CLI_HOME% org.onap.cli.main.OnapCli %*
) else (
	java -classpath %CLASSPATH% -DOPEN_CLI_HOME=%OPEN_CLI_HOME% org.onap.cli.main.OnapCli %*
)
