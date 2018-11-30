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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.openstack4j.api.compute.ServerTagV21Service;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.domain.NovaServerTag;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
/**
 * Server Tag Operations API implementation
 * @author ChangjunZhao
 * @date   2018-11-30
 */
public class ServerTagV21ServiceImpl extends BaseComputeServices implements ServerTagV21Service {
	
	private static final Logger LOG = LoggerFactory.getLogger(ServerTagV21ServiceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NovaServerTag list(String serverId) {
		checkNotNull(serverId);
		return get(NovaServerTag.class, uri("/servers/%s/tags", serverId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NovaServerTag addTags(String serverId, NovaServerTag tags) {
		checkNotNull(serverId);
		checkNotNull(tags);
		return put(NovaServerTag.class, uri("/servers/%s/tags", serverId)).entity(tags).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse deleteAll(String serverId) {
		checkNotNull(serverId);
		return ToActionResponseFunction.INSTANCE.apply(
				delete(Void.class, uri("/servers/%s/tags", serverId)).executeWithResponse());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String serverId, String tag) {
		checkNotNull(serverId);
		checkNotNull(tag);
		return ToActionResponseFunction.INSTANCE.apply(
				delete(Void.class, uri("/servers/%s/tags/%s", serverId, tag)).executeWithResponse());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse check(String serverId, String tag) {
		checkNotNull(serverId);
		checkNotNull(tag);
		return ToActionResponseFunction.INSTANCE.apply(
				get(Void.class, uri("/servers/%s/tags/%s", serverId, tag)).executeWithResponse());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse addSingle(String serverId, String tag) {
		checkNotNull(serverId);
		checkNotNull(tag);
		return ToActionResponseFunction.INSTANCE.apply(
				put(ActionResponse.class, uri("/servers/%s/tags/%s", serverId, tag)).executeWithResponse());
	}

}
