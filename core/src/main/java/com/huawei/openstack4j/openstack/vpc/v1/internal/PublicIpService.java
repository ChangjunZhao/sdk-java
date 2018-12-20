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
package com.huawei.openstack4j.openstack.vpc.v1.internal;

import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PublicIp;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PublicIpApply;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PublicIpUpdate;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PublicIp.Publicips;
/**
 * The implementation of manipulation of Publicip
 * 
 * @author ChangjunZhao
 * @date   2018-03-25
 */
public class PublicIpService extends BaseVirtualPrivateCloudService{
	
	/**
	 * Querying Elastic IP Addresses
	 * @return
	 */
	public List<? extends PublicIp> list(){
		return list(null);
	}
	
	/**
	 * Querying Elastic IP Addresses with filter
	 * @param filteringParams
	 * @return
	 */
	public List<? extends PublicIp> list(Map<String, String> filteringParams) {
		Invocation<Publicips> flavorInvocation = get(Publicips.class, uri("/publicips"));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	flavorInvocation = flavorInvocation.param(entry.getKey(), entry.getValue());
            }
        }
		
		return flavorInvocation.execute().getList();
	}
	
	/**
	 * Assigning an Elastic IP Address
	 * @param apply
	 * @return
	 */
	public PublicIp apply(PublicIpApply apply){
		Preconditions.checkNotNull(apply, "parameter `apply` should not be null");
		Preconditions.checkNotNull(apply.getPublicip(), "parameter `apply.publicip` should not be null");
		Preconditions.checkNotNull(apply.getBandwidth(), "parameter `apply.bandwidth` should not be null");
		Preconditions.checkNotNull(apply.getPublicip().getType(), "parameter `apply.publicip.type` should not be empty");
		Preconditions.checkNotNull(apply.getBandwidth().getShareType(), "parameter `apply.bandwidth.shareType` should not be empty");
		return post(PublicIp.class, uri("/publicips")).entity(apply).execute();
	}
	
	/**
	 * Querying Elastic IP Address Details
	 * @param publicipId
	 * @return
	 */
	public PublicIp get(String publicIpId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(publicIpId), "parameter `publicIpId` should not be empty");
		return get(PublicIp.class, uri("/publicips/%s",publicIpId)).execute();
	}
	
	/**
	 * Updating(Binding/unbinding) Elastic IP Address Information
	 * @param publicip
	 * @return
	 */
	public PublicIp update(String publicIpId,PublicIpUpdate publicIpUpdate){
		Preconditions.checkNotNull(publicIpUpdate, "parameter `publicIpUpdate` should not be null");
		Preconditions.checkNotNull(publicIpId, "parameter `publicIpId` should not be null");
		return put(PublicIp.class, uri("/publicips/%s",publicIpId)).entity(publicIpUpdate).execute();
	}
	
	/**
	 * Deleting an Elastic IP Address
	 * @param publicIpId
	 * @return
	 */
	public ActionResponse delete(String publicIpId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(publicIpId), "parameter `publicIpId` should not be empty");
		return deleteWithResponse(uri("/publicips/%s", publicIpId)).execute();
	}

}
