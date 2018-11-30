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
package com.huawei.openstack4j.api.compute;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.domain.NovaServerTag;

/**
 * Server tag service for v2.1
 * @author ChangjunZhao
 */
public interface ServerTagV21Service extends RestService {
	
	/**
	 * Querying Tags of an ECS
	 * @param serverId
	 * @return
	 */
	NovaServerTag list(String serverId);
	
	/**
	 * Adding Tags of an ECS
	 * @param serverId
	 * @param tag
	 * @return
	 */
	NovaServerTag addTags(String serverId, NovaServerTag tag);
	
	/**
	 * Deleting Tags from an ECS
	 * @param serverId
	 * @return
	 */
	ActionResponse deleteAll(String serverId);
	
	/**
	 * Deleting a Specified Tag from an ECS
	 * @param serverId
	 * @param tag
	 * @return
	 */
	ActionResponse delete(String serverId, String tag);
	
	/**
	 * Querying a Specified Tag for an ECS
	 * @param serverId
	 * @param tag
	 * @return
	 */
	ActionResponse check(String serverId, String tag);
	
	/**
	 * Adding a Tag to an ECS
	 * @param serverId
	 * @param tag
	 * @return
	 */
	ActionResponse addSingle(String serverId, String tag);

}
