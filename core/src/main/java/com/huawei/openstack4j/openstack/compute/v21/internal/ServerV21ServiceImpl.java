/*******************************************************************************
 * 	Copyright 2018 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.openstack.compute.v21.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.openstack4j.api.compute.ServerV21Service;
import com.huawei.openstack4j.model.compute.Server;
import com.huawei.openstack4j.openstack.compute.domain.NovaServer;
import com.huawei.openstack4j.openstack.compute.domain.NovaServer.Servers;

/**
 * Server Operation API V2.1 implementation
 *
 * @author Changjun Zhao
 */
public class ServerV21ServiceImpl extends BaseComputeServices implements ServerV21Service {

	private static final Logger LOG = LoggerFactory.getLogger(ServerV21ServiceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Server> list() {
		return list(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Server> list(boolean detail) {
		return list(detail, Boolean.FALSE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Server> listAll(boolean detail) {
		return list(detail, Boolean.TRUE);
	}

	private List<? extends Server> list(boolean detail, boolean allTenants) {
		Invocation<Servers> req = get(Servers.class, uri("/servers" + ((detail) ? "/detail" : "")));
		if (allTenants)
			req.param("all_tenants", 1);
		return req.execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Server> list(Map<String, String> filteringParams) {
		Invocation<Servers> serverInvocation = get(Servers.class, "/servers/detail");
		if (filteringParams != null) {
			for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
				serverInvocation = serverInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return serverInvocation.execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Server get(String serverId) {
		checkNotNull(serverId);
		return get(NovaServer.class, uri("/servers/%s", serverId)).execute();
	}
	
}
