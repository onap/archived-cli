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
            print (msg)
            raise OcompException(9999, msg)


class LiteralString(str):
    pass

class LineBreakDumper(Dumper):
  def write_line_break(self, data=None):
    super().write_line_break(data)
    if len(self.indents) == 1:
      super().write_line_break()

def create_testcase_yaml(testcase_name, description, testsuite_name, test_suite_folder_path):

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
  description =  LiteralString(description.replace(r'\n', '\n') + '\n')

  product = "etsi-mano"
  service = test_suite_folder_path.split('/')[0]
  info = OrderedDict(product = product, service = service)

  test_suite_path = '${api-tests-folder-path}/api-tests/' + test_suite_folder_path + '/' + testsuite_name + '.robot'
  command = ['python3 $s{env:OPEN_CLI_HOME}/script/run-robot-testcase.py --variables-file-path ${variables-file-path} --test-suite ' +
              test_suite_path + ' --testcase ' + testcase_name]
  success_codes = [0]
  working_directory = '.'
  output = '$stdout'
  robot = OrderedDict(command=command, success_codes=success_codes, working_directory=working_directory, output=output)

  data = OrderedDict(open_cli_schema_version=open_cli_schema_version, name=name, description=description,
                      info=info, robot=robot)

  yaml_path = os.getenv('OPEN_CLI_HOME') + '/open-cli-schema/robot/' + test_suite_folder_path + '/' + testsuite_name
  os.makedirs(yaml_path, exist_ok=True)

  with open(yaml_path + '/' + name + '.yaml', 'w') as file:
    yaml.dump(data, file, Dumper=LineBreakDumper, default_flow_style=False)

  ocomp = OCOMP()
  res = ocomp.run(command='schema-validate', params={'schema-location': yaml_path + '/' + name + '.yaml'})
  result = res.stdout.read().strip()

  if res.returncode != 0:
      if os.path.exists(yaml_path + '/' + name + '.yaml'):
        os.remove(yaml_path + '/' + name + '.yaml')
      print (yaml_path + '/' + name + '.yaml')
      print(result)
      print()

def discover_testcases(api_tests_folder_path):

  for root, dirs, files in os.walk(api_tests_folder_path):
    for file in files:

      if file.endswith(".robot"):
        path_to_test_suite = os.path.join(root, file)
        try:

          suite = TestData(parent=None, source=path_to_test_suite)
          for testcase in suite.testcase_table:
            test_suite_folder_path = root[len(api_tests_folder_path):]
            create_testcase_yaml(testcase.name, testcase.doc.value, suite.name, test_suite_folder_path)

        except Exception as e:
          pass

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

  args = parser.parse_args()
  api_tests_folder_path = args.api_tests_folder_path + '/api-tests/'

  if os.path.exists(api_tests_folder_path):

    if not os.path.isdir(api_tests_folder_path):
      raise Exception('Given api-tests folder location is not a directory\n')

  else:
    raise Exception('Given api-tests folder location does not exist\n')

  discover_testcases(api_tests_folder_path)

if __name__ == '__main__':
  main()
