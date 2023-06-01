.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright 2017 Huawei Technologies Co., Ltd.
.. _architecture:

.. _cli_architecture:

CLI architecture
================

Following diagram shows the high-level architecture of OCLIP.

.. image:: images/portal-cli-arch.png

* **Command Registrar** :  Registrar keeps track of the commands registered in OCLIP and when user invokes the command,
  it identifies the corresponding command and helps to invoke the corresponding command plug-in.

* **Command Discoverer** : Discoverer discovers the commands both in plug-in format and YAML and automatically register
  them into Registrar.

* **Command Plug-in** : Implement the command as plug-in. More details :ref:`cli_developer_guide`

* **HTTP Command** : Plug-in for implementing all Rest based command as YAML without any coding.

* **SNMP Command** : Plug-in for implementing all SNMP based command as YAML without any coding.

* **Command Shell** : Provides the interactive command line console from Linux

* **Web Command Console** : Provides the interactive command line console from web-browser

* **Cache** : Provides persistent storage for discoverer to store the meta-data about the discovered Commands.

* **open cli schemas** : Set of command YAML provides the CLI definitions.

