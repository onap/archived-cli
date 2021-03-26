#!/usr/bin/python
# Copyright 2020 Simran Singhal.
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

# Copyright 2020 Huawei Technologies Co., Ltd.
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#     http://www.apache.org/licenses/LICENSE-2.0
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

import os
import argparse
import uuid
from robot import run
from argparse import RawTextHelpFormatter

def execute_robot_run(request_id, test_suite, testcase, variables_file_path):
  output_dir = os.getenv('OPEN_CLI_HOME') + '/data/executions/' + request_id + '/logs'
  if env_file_path:
    product = os.getenv('OPEN_CLI_PRODUCT')
    data_list_file_path = data_list_file_path1 = ""
    with open(os.getenv('OPEN_CLI_HOME') + '/conf/vtp.properties') as fp:
      line = fp.readline()
      while line:
        key = line.split("=")[0]
        value = line.split("=")[-1]
        if key == "cmd." + product + ".envpath":
          data_list_file_path = value.replace("\n", "")
        if key == "cmd." + product + ".envpath1":
          data_list_file_path1 = value.replace("\n", "")
        line = fp.readline()
    if(data_list_file_path and data_list_file_path1):
      os.system("cat " + data_list_file_path + ">" + data_list_file_path1)
      cmd = 'sed -i -e  "s/sysdev/' + os.environ['SYSDEV'].replace('"', '') + '/g" -e "s/sysapp/' + os.environ['SYSAPP'].replace('"', '') + '/g" -e "s/sysmecm/' + os.environ['SYSMECM'].replace('"', '') + '/g" -e "s/schema/' + os.environ['SCHEMA'].replace('"', '') + '/g" -e "s/rhost/' + os.environ['HOST'].replace('"', '') + '/g" -e "s/Port/' + os.environ['PORT'].replace('"', '') + '/g" -e "s/H_Ip/' + os.environ['HOSTIP'].replace('"', '') + '/g" -e "s/apmPORT/' + os.environ['APMPORT'].replace('"', '') + '/g" -e "s/esrPORT/' + os.environ['ESRPORT'].replace('"', '') + '/g" -e "s/appoPORT/' + os.environ['APPOPORT'].replace('"', '') + '/g" -e "s/lcmPORT/' + os.environ['LCMPORT'].replace('"', '') + '/g" -e "s/righthip/' + os.environ['RIGHTHOSTIP'].replace('"', '') + '/g" -e "s/xsdkdate/' + os.environ['XSDKDATE'].replace('"', '') + '/g" -e "s/signature/' + os.environ['SIGNATURE'].replace('"', '') + '/g" -e "s/loginname/' + os.environ['LOGINNAME'].replace('"', '') + '/g" -e "s/loginpass/' + os.environ['LOGINPASS'].replace('"', '') + '/g" -e "s/loginphone/' + os.environ['LOGINPHONE'].replace('"', '') + '/g" -e "s/packagename/' + os.environ['APPNAME'].replace('"', '') + '/g" -e "s/industry/' + os.environ['INDUSTRY'].replace('"', '') + '/g" -e "s/apptype/' + os.environ['TYPE'].replace('"', '') + '/g" -e "s/affinity/' + os.environ['AFFINITY'].replace('"', '') + '/g" -e "s/yFile/' + os.environ['YAMLFile'].replace('"', '') + '/g" -e "s/servicename/' + os.environ['SERVICENAME'].replace('"', '') + '/g" ' + data_list_file_path1
      cmd = cmd.replace("\n", "")
      os.system(cmd)

  with open('run.txt', 'w') as output:
    if(variables_file_path):
      result = run(test_suite, test=testcase, variablefile=variables_file_path, stdout=output, stderr=output, outputdir=output_dir)
    else:
      result = run(test_suite, test=testcase, stdout=output, stderr=output, outputdir=output_dir)

    if result != 0:
      raise Exception('RUN TEST CASE FAILED return-code:', result)
  if os.path.exists('run.txt'):
    with open('run.txt', 'r') as log:
        print(log.read())
    os.remove('run.txt')

def main():
  text = 'This command helps to run a robot testcase\n' \
         'These python modules are need to be installed for running the tests\n' \
         'robotframework==3.1\n' \
         'RESTinstance==1.0.0rc4\n' \
         'robotframework-dependencylibrary==1.0.0.post1\n' \
         'robotframework-jsonlibrary==0.3\n' \
         'robotframework-jsonschemalibrary==1.0\n' \
         'robotframework-mockserver==0.0.4\n'

  parser = argparse.ArgumentParser(description=text, formatter_class=RawTextHelpFormatter)
  parser.add_argument('--request-id', action='store', dest='request_id',
                      help='Request Id to track the progress of running this script',
                      default=os.environ.get('OPEN_CLI_REQUEST_ID'))
  parser.add_argument('--test-suite', action='store', dest='test_suite',
                        help='Location to test suite file', required=True)
  parser.add_argument('--testcase', action='store', dest='testcase',
                        help='Name of the testcase', required=False, nargs='+')
  parser.add_argument('--variables-file-path', action='store', dest='variables_file_path',
                        help='Location to variable file', nargs='?', const='')
  parser.add_argument('--env-file-path', action='store', dest='env_file_path',
                        help='Location to env file', required=False)

  args = parser.parse_args()
  global env_file_path
  env_file_path = args.env_file_path

  if env_file_path:
    with open(env_file_path) as fp:
      line = fp.readline()
      while line:
        key = line.split("=")[0]
        value = line.split("=")[-1]
        os.environ[key] = value
        line = fp.readline()
  request_id = args.request_id
  test_suite = args.test_suite
  if args.testcase:
    testcase = ' '.join(args.testcase)
  else:
    testcase = '*'
  variables_file_path = ''

  if not request_id:
    request_id = str(uuid.uuid4())

  if args.variables_file_path:
    variables_file_path = args.variables_file_path

    if os.path.exists(variables_file_path):

      if not os.path.isfile(variables_file_path):
        raise Exception('Given variable file path is not a file\n')

    else:
      raise Exception('Given variable file path does not exist\n')

  if not os.path.exists(test_suite):
    raise Exception('Given api-tests folder location does not exist\n')

  execute_robot_run(request_id,test_suite, testcase, variables_file_path)

if __name__ == '__main__':
  main()
