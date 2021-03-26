#!/usr/bin/python3
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

import os
import argparse
from argparse import RawTextHelpFormatter
from robot.parsing.model import TestData
import yaml
from yaml.representer import SafeRepresenter
from yaml import Dumper
from collections import OrderedDict
import re
import subprocess
import sys

class OcompException(Exception):
    def __init__(self, code, message):
        super(OcompException, self).__init__()
        self.code = code;
        self.message = message;

class OCOMP:
    def run(self, command, params={}):
        if data_list_file_path and data_list_file_path1:
          CMD_NAME = 'ocomp'
        else:
          CMD_NAME = 'oclip'
        CMD = [CMD_NAME]

        CMD.append(command)

        for name, value in params.items():
            CMD.append('--{}'.format(name))
            CMD.append(value)

        cmd_string = ' '.join(CMD)

        try:
            res = subprocess.Popen(CMD, stdout=subprocess.PIPE)
            res.wait()
            return res

        except OSError as e:
            sys.stderr.write(str(e))
            msg = 'failed to executed the command {}'.format(cmd_string)
            raise OcompException(9999, msg)


class LiteralString(str):
    pass

class LineBreakDumper(Dumper):
  def write_line_break(self, data=None):
    super().write_line_break(data)
    if len(self.indents) == 1:
      super().write_line_break()

def create_testcase_yaml(testcase_name, description, path_test_suite, testsuite_name, test_suite_folder_path, api_tests_folder_path):

  def change_style(style, representer):
    def new_representer(dumper, data):
      scalar = representer(dumper, data)
      scalar.style = style
      return scalar
    return new_representer

  represent_literal_str = change_style('|', SafeRepresenter.represent_str)
  yaml.add_representer(LiteralString, represent_literal_str)

  def ordered_dict_presenter(dumper, data):
      return dumper.represent_dict(data.items())
  yaml.add_representer(OrderedDict, ordered_dict_presenter)

  open_cli_schema_version = 1.0
  name = re.sub(re.compile('(?:\-){2,}'), '-', testcase_name.replace(' ', '-').lower())
  if(not description):
    description = name
  else:
    description = LiteralString(description.replace(r'\n', '\n') + '\n')

  product = os.getenv('OPEN_CLI_PRODUCT')
  if not product:
    product = "etsi-mano"
  robotName = testsuite_name.replace(' ', '_')
  service = test_suite_folder_path.split('/')[0] + '_' + robotName

  if not test_suite_folder_path.split('/')[0]:
    service = service[1:]
  author = 'edgeT team'
  info = OrderedDict(product = product, service = service, author = author)

  if(data_list_file_path and data_list_file_path1):
    param_name = "envFilePath"
    description = "env File Path"
    type = "string"
    short_option = "x"
    long_option = "envFilePath"
    is_optional = 'false'
    parameters = [OrderedDict(name = param_name,  description = description, type = type, short_option = short_option, long_option = long_option, is_optional = is_optional)]

    rdirection = 'landscape'
    rname = 'reports_path'
    rdescription = 'reports path'
    rscope = 'short'
    rtype = 'string'
    attributes = [OrderedDict(name=rname, description=rdescription, scope=rscope, type=rtype)]
    results = OrderedDict(direction=rdirection, attributes=attributes)

  if path_test_suite.split('.')[-1] != "robot":
    tcappend = ""
  else:
    tcappend = " --testcase " + testcase_name
  if (data_list_file_path and data_list_file_path1):
    openCliHome= os.getenv('OPEN_CLI_HOME')
    command = ['python3 '+openCliHome+'/script/run-robot-testcase.py --test-suite ' +
               path_test_suite + tcappend + ' --env-file-path  ${envFilePath}']
  else:
    test_suite_path = '${api-tests-folder-path}/api-tests/' + test_suite_folder_path + '/' + testsuite_name + '.robot'
    command = ['python3 $s{env:OPEN_CLI_HOME}/script/run-robot-testcase.py --variables-file-path ${variables-file-path} --test-suite ' +
              test_suite_path + tcappend]

  success_codes = [0]
  working_directory = '.'
  output = '$stdout'
  if(data_list_file_path and data_list_file_path1):
    reports_path = '$s{env:OPEN_CLI_REQUEST_ID}/logs/'
    result_map = OrderedDict(reports_path=reports_path)
    robot = OrderedDict(command=command, success_codes=success_codes, working_directory=working_directory, output=output, result_map=result_map)
  else:
      robot = OrderedDict(command=command, success_codes=success_codes, working_directory=working_directory,
                          output=output)
  if(data_list_file_path and data_list_file_path1):
    data = OrderedDict(open_cli_schema_version=open_cli_schema_version, name=name, description=description, info=info, cmd=robot, parameters=parameters, results=results)
    yaml_path = openCliHome + '/open-cli-schema/robot' + test_suite_folder_path + testsuite_name
  else:
    data = OrderedDict(open_cli_schema_version=open_cli_schema_version, name=name, description=description, info=info,
                     robot=robot)
    yaml_path = os.getenv('OPEN_CLI_HOME') + '/open-cli-schema/robot/' + test_suite_folder_path + '/' + testsuite_name

  os.makedirs(yaml_path, exist_ok=True)
  with open(yaml_path + '/' + name +'.yaml', 'w') as file:
    yaml.dump(data, file, Dumper=LineBreakDumper, default_flow_style=False)

  ocomp = OCOMP()
  res = ocomp.run(command='schema-validate', params={'schema-location': yaml_path + '/' + name + '.yaml'})
  result = res.stdout.read().strip()

  if res.returncode != 0:
      if os.path.exists(yaml_path + '/' + name + '.yaml'):
        os.remove(yaml_path + '/' + name + '.yaml')

def discover_testcases(api_tests_folder_path):
  product = os.getenv('OPEN_CLI_PRODUCT')
  if data_list_file_path and data_list_file_path1:
    with open(os.getenv('OPEN_CLI_HOME') + "/conf/vtp.properties", 'w') as file:
        file.write("cmd." + product + ".envpath=" + data_list_file_path + "\n")
    with open(os.getenv('OPEN_CLI_HOME') + "/conf/vtp.properties", 'a') as file:
        file.write("cmd." + product + ".envpath1=" + data_list_file_path1 + "\n")

  #create yaml at file level
  for root, dirs, files in os.walk(api_tests_folder_path):
    for file in files:

      if file.endswith(".robot"):
        path_to_test_suite = os.path.join(root, file)
        try:
          suite = TestData(parent=None, source=path_to_test_suite)
          for testcase in suite.testcase_table:
            test_suite_folder_path = root[len(api_tests_folder_path):]
            create_testcase_yaml(testcase.name, testcase.doc.value, path_to_test_suite, suite.name, test_suite_folder_path, api_tests_folder_path)

        except Exception as e:
          pass
  #create yaml at folder level
  for root, dirs, files in os.walk(api_tests_folder_path):
    test_suite_folder_path = api_tests_folder_path
    for dir in dirs:
        try:
            create_testcase_yaml(dir, "run " + test_suite_folder_path + "/" + dir + " suite", os.path.join(root, dir),
                                 dir, api_tests_folder_path.split('/')[-1], api_tests_folder_path)
        except Exception as e:
            pass
    break

  ocomp = OCOMP()
  res = ocomp.run(command='schema-refresh')
  result = res.stdout.read().strip()
  if res.returncode != 0:
      raise OcompException(9999, result)

def main():
  text = 'This command helps to discover all robot testcases\n' \
         'These python modules are need to be installed for running the tests\n' \
         'robotframework==3.1\n' \
         'RESTinstance==1.0.0rc4\n' \
         'robotframework-dependencylibrary==1.0.0.post1\n' \
         'robotframework-jsonlibrary==0.3\n' \
         'robotframework-jsonschemalibrary==1.0\n' \
         'robotframework-mockserver==0.0.4\n'

  parser = argparse.ArgumentParser(description=text, formatter_class=RawTextHelpFormatter)
  parser.add_argument('--api-tests-folder-path', action='store', dest='api_tests_folder_path',
                        help='Location to api-tests folder', required=True)
  parser.add_argument('--data_list_file_path', action='store', dest='data_list_file_path',
                      help='Location to data list file path')
  parser.add_argument('--data_list_file_path1', action='store', dest='data_list_file_path1',
                      help='Location to data list1 file path')

  args = parser.parse_args()
  api_tests_folder_path = args.api_tests_folder_path
  global data_list_file_path,data_list_file_path1
  data_list_file_path = args.data_list_file_path
  data_list_file_path1 = args.data_list_file_path1

  if not (data_list_file_path and data_list_file_path1):
    api_tests_folder_path = args.api_tests_folder_path + '/api-tests/'

  if os.path.exists(api_tests_folder_path):

    if not os.path.isdir(api_tests_folder_path):
      raise Exception('Given api-tests folder location is not a directory\n')

  else:
    raise Exception('Given api-tests folder location does not exist\n')

  discover_testcases(api_tests_folder_path)

if __name__ == '__main__':
  main()