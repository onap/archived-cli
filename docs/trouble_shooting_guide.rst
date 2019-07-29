.. This work is licensed under a Creative Commons Attribution 4.0 International License.
.. http://creativecommons.org/licenses/by/4.0
.. Copyright © Intel Corporation 2019

.. _cli_trouble_shooting_guide:

CLI Trouble Shooting Guide
==========================

This part provides the trouble shooting guide for ONAP CLI. It aims to provide guidance on the known issues you may encounter.

Details
--------
1. IllegalArgumentException

   * Problem: Get an IllegalArgumentException after executing the command.
   * Cause: The input inside the command contains a special character.
   * Solution: User need to escape the special character while giving input
