Now, CLI provide generic-search cmd and node type search:

Generic-search will return all related nodes of the target node:
	
	usage: oclip generic-search 
	
	
	generic-search
	
	Product: onap-beijing
	Service: aai
	Author: ONAP CLI Team onap-discuss@lists.onap.org
	
	Options::
	
		 [-m | --host-url] [-r | --start-node-type] [-C | --no-catalog]
		 [-h | --help] [-f | --format] [-t | --no-title]
		 [-V | --verify] [-v | --version] [-d | --debug]
		 [-k | --key] [-z | --value] [-s | --long]
		 [-D | --context] [-e | --depth] [-u | --host-username]
		 [-a | --no-auth] [-p | --host-password]
		 
	 
	 
cmd	sample:
	      generic-search -u AAI -p AAI -m https://172.19.44.123:8443 -k customer.global-customer-id -z Orange -r customer -e 6

	 output:
			+----------------------+----------------------------------------------------+
			|resource-type         |resource-link                                       |
			+----------------------+----------------------------------------------------+
			|pnf                   |/aai/v11/network/pnfs/pnf/pnf_template              |
			+----------------------+----------------------------------------------------+
			|pnf                   |/aai/v11/network/pnfs/pnf/MME-0001                  |
			+----------------------+----------------------------------------------------+
			|pnf                   |/aai/v11/network/pnfs/pnf/SP%20GW-0001              |
			+----------------------+----------------------------------------------------+
			|p-interface           |/aai/v11/network/pnfs/pnf/pnf_template/p-interface  |
			|                      |s/p-interface/pnf_template-p-interface              |
			+----------------------+----------------------------------------------------+
			|l-interface           |/aai/v11/network/pnfs/pnf/MME-0001/p-interfaces/p-  |
			|                      |interface/MME-Eth-0001/l-interfaces/l-interface/MM  |
			|                      |E-Eth-0001-1                                        |
			+----------------------+----------------------------------------------------+
			|l-interface           |/aai/v11/network/pnfs/pnf/pnf_template/p-interface  |
			|                      |s/p-interface/pnf_template-p-interface/l-interface  |
			|                      |s/l-interface/pnf_template-i-interface              |
			+----------------------+----------------------------------------------------+
			|l-interface           |/aai/v11/network/pnfs/pnf/MME-0001/p-interfaces/p-  |
			|                      |interface/MME-Eth-0002/l-interfaces/l-interface/MM  |
			|                      |E-Eth-0002-1                                        |
			+----------------------+----------------------------------------------------+
			|customer              |/aai/v11/business/customers/customer/Orange         |
			+----------------------+----------------------------------------------------+
			|service-subscription  |/aai/v11/business/customers/customer/Orange/servic  |
			|                      |e-subscriptions/service-subscription/EPC            |
			+----------------------+----------------------------------------------------+
			|l-interface           |/aai/v11/network/pnfs/pnf/SP%20GW-0001/p-interface  |
			|                      |s/p-interface/SP%20GW-Eth-0001/l-interfaces/l-inte  |
			|                      |rface/SP%20GW-Eth-0001-1                            |
			+----------------------+----------------------------------------------------+
			|p-interface           |/aai/v11/network/pnfs/pnf/MME-0001/p-interfaces/p-  |
			|                      |interface/MME-Eth-0002                              |
			+----------------------+----------------------------------------------------+
			|pnf                   |/aai/v11/network/pnfs/pnf/testcmdpnfname            |
			+----------------------+----------------------------------------------------+
			|service-instance      |/aai/v11/business/customers/customer/Orange/servic  |
			|                      |e-subscriptions/service-subscription/EPC/service-i  |
			|                      |nstances/service-instance/176d9eba-1662-4289-8396-  |
			|                      |0097b50fd486                                        |
			+----------------------+----------------------------------------------------+
			|service-subscription  |/aai/v11/business/customers/customer/Orange/servic  |
			|                      |e-subscriptions/service-subscription/EEC            |
			+----------------------+----------------------------------------------------+
			|p-interface           |/aai/v11/network/pnfs/pnf/SP%20GW-0001/p-interface  |
			|                      |s/p-interface/SP%20GW-Eth-0001                      |
			+----------------------+----------------------------------------------------+
			|service-instance      |/aai/v11/business/customers/customer/Orange/servic  |
			|                      |e-subscriptions/service-subscription/EPC/service-i  |
			|                      |nstances/service-instance/176d9eba-1662-4289-8396-  |
			|                      |0097b50fd485                                        |
			+----------------------+----------------------------------------------------+
			|logical-link          |/aai/v11/network/logical-links/logical-link/S11-00  |
			|                      |001                                                 |
			+----------------------+----------------------------------------------------+
			|p-interface           |/aai/v11/network/pnfs/pnf/MME-0001/p-interfaces/p-  |
			|                      |interface/MME-Eth-0001                              |
			+----------------------+----------------------------------------------------+
			
Node type search will return the nodes match the filter condition:

		usage: oclip type-search

		type-search
		
		Product: onap-beijing
		Service: aai
		Author: ONAP CLI Team onap-discuss@lists.onap.org
		
		Options::
		
		 [-m | --host-url] [-C | --no-catalog] [-h | --help]
		 [-f | --format] [-t | --no-title] [-V | --verify]
		 [-v | --version] [-d | --debug] [-s | --long]
		 [-D | --context] [-n | --node-type-name] [-c | --filter]
		 [-u | --host-username] [-a | --no-auth] [-p | --host-password]

Sample cmd:
	type-search -u AAI -p AAI -m https://172.19.44.123:8443  -n customer --filter global-customer-id:EQUALS:Orange	
	
	output:
	
			+----------------+----------------------------------------------+
			|resource-type   |resource-link                                 |
			+----------------+----------------------------------------------+
			|customer        |/aai/v11/business/customers/customer/Orange   |
			+----------------+----------------------------------------------+
