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
from robot import run
from argparse import RawTextHelpFormatter

def execute_robot_run(test_suite, testcase, variables_file_path):

  with open('run.txt', 'w') as output:
    if(variables_file_path):
      run(test_suite, report=None, log=None, test=testcase, variablefile=variables_file_path, stdout=output, stderr=output)
    else:
      run(test_suite, report=None, log=None, test=testcase, stdout=output, stderr=output)

  if os.path.exists('output.xml'):
    os.remove('output.xml')

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
  parser.add_argument('--test-suite', action='store', dest='test_suite',
                        help='Location to test suite file', required=True)
  parser.add_argument('--testcase', action='store', dest='testcase',
                        help='Name of the testcase', required=True, nargs='+')
  parser.add_argument('--variables-file-path', action='store', dest='variables_file_path',
                        help='Location to variable file', nargs='?', const='')

  args = parser.parse_args()

  test_suite = args.test_suite
  testcase = ' '.join(args.testcase)
  variables_file_path = ''

  if args.variables_file_path:
    variables_file_path = args.variables_file_path

    if os.path.exists(variables_file_path):

      if not os.path.isfile(variables_file_path):
        raise Exception('Given variable file path is not a file\n')

    else:
      raise Exception('Given variable file path does not exist\n')

  if not os.path.exists(test_suite):
    raise Exception('Given api-tests folder location does not exist\n')

  execute_robot_run(test_suite, testcase, variables_file_path)

if __name__ == '__main__':
  main()
