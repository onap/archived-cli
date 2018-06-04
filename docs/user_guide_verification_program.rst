.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2018 Huawei Technologies Co., Ltd.

.. user_guide_verification_program:


CLI verification program
==============================================

Verification program helps the author to setup the mocking environment for the HTTP profile (and
in future it could be extended to other profile) for authoring the testing the CLI OCS YAMLs.

It provides the model based test environment where author needs to setup following aspects:

# Write the moco json which capture the HTTP request and response in moco style

# Write the sample YAML which capture the one or more functional test case(s)

# Place them under open-cli-sample directory under OPEN_CLI_HOME

Once this setup is done, author can verify the OCS YAML by running the following CLI ::

 oclip <command name> --verify | -V

It will list out the success/failure of each test cases written in sample YAML.
