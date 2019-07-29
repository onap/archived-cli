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
   * Cause: The input inside the command contains a special character(e.g. '!', '$','\\') and the console mandates to escape this kind of chars
   * Sample Exception:
     ::

        oclip:open-cli>policy-type-list-all -m http://10.12.7.6:30240 -u "healthcheck" -p "zb!XztG34"
             Exception in thread "main" java.lang.IllegalArgumentException: !XztG34": event not found
              at jline.console.ConsoleReader.expandEvents(ConsoleReader.java:507)
              at jline.console.ConsoleReader.finishBuffer(ConsoleReader.java:379)
              at jline.console.ConsoleReader.readLine(ConsoleReader.java:1327)
              at jline.console.ConsoleReader.readLine(ConsoleReader.java:1117)
              at jline.console.ConsoleReader.readLine(ConsoleReader.java:1105)
              at org.onap.cli.main.OnapCli.handleInteractive(OnapCli.java:369)
              at org.onap.cli.main.OnapCli.handle(OnapCli.java:645)
              at org.onap.cli.main.OnapCli.main(OnapCli.java:751)

   * Solution: User need to escape the special character while giving input(e.g. using single/double quote or backslash)

