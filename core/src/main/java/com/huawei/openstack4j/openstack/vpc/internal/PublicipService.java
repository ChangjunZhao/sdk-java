/*******************************************************************************
 * 	Copyright 2018 HuaWei and OTC                                       
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
package com.huawei.openstack4j.openstack.vpc.internal;

import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.vpc.domain.Publicip;
import com.huawei.openstack4j.openstack.vpc.domain.Publicip.Publicips;
import com.huawei.openstack4j.openstack.vpc.domain.PublicipApply;
/**
 * The implementation of manipulation of Publicip
 * 
 * @author ChangjunZhao
 * @date   2018-03-25
 */
public class PublicipService extends BaseVpcServices{
	
	/**
	 * Querying Elastic IP Addresses
	 * @return
	 */
	public List<? extends Publicip> list(){
		return list(null);
	}
	
	/**
	 * Querying Elastic IP Addresses with filter
	 * @param filteringParams
	 * @return
	 */
	public List<? extends Publicip> list(Map<String, String> filteringParams) {
		Invocation<Publicips> flavorInvocation = get(Publicips.class, uri("/publicips"));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	flavorInvocation = flavorInvocation.param(entry.getKey(), entry.getValue());
            }
        }
		
		return flavorInvocation.execute().getList();
	}
	
	/**
	 * Applying for an Elastic IP Address
	 * @param creation
	 * @return
	 */
	public Publicip apply(PublicipApply apply){
		Preconditions.checkNotNull(apply, "parameter `apply` should not be null");
		Preconditions.checkNotNull(apply.getPublicip(), "parameter `apply.publicip` should not be null");
		Preconditions.checkNotNull(apply.getBandwidth(), "parameter `apply.bandwidth` should not be null");
		Preconditions.checkNotNull(apply.getPublicip().getType(), "parameter `apply.publicip.type` should not be empty");
		Preconditions.checkNotNull(apply.getBandwidth().getShareType(), "parameter `apply.bandwidth.shareType` should not be empty");
		return post(Publicip.class, uri("/publicips")).entity(apply).execute();
	}
	
	/**
	 * Querying Elastic IP Address Details
	 * @param publicipId
	 * @return
	 */
	public Publicip get(String publicipId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(publicipId), "parameter `publicipId` should not be empty");
		return get(Publicip.class, uri("/publicips/%s",publicipId)).execute();
	}
	
	/**
	 * Updating(Binding/unbinding) Elastic IP Address Information
	 * @param publicip
	 * @return
	 */
	public Publicip update(Publicip publicip){
		Preconditions.checkNotNull(publicip, "parameter `apply` should not be null");
		Preconditions.checkNotNull(publicip.getPortId(), "parameter `publicip.portId` should not be null");
		return put(Publicip.class, uri("/publicips/%s",publicip.getId())).entity(publicip).execute();
	}
	
	/**
	 * Deleting an Elastic IP Address
	 * @param publicipId
	 * @return
	 */
	public ActionResponse delete(String publicipId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(publicipId), "parameter `publicipId` should not be empty");
		return deleteWithResponse(uri("/publicips/%s", publicipId)).execute();
	}

}
