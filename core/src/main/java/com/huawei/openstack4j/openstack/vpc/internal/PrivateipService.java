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
import com.huawei.openstack4j.openstack.vpc.domain.Privateip;
import com.huawei.openstack4j.openstack.vpc.domain.Privateip.Privateips;
/**
 * The implementation of manipulation of Privateip
 * 
 * @author ChangjunZhao
 * @date   2018-03-25
 */
public class PrivateipService extends BaseVpcServices{
	
	/**
	 * Querying Private IP Addresses
	 * @param subnetId
	 * @return
	 */
	public List<? extends Privateip> list(String subnetId){
		return list(subnetId,null);
	}
	
	/**
	 * Querying Private IP Addresses with filter
	 * @param subnetId
	 * @param filteringParams
	 * @return
	 */
	public List<? extends Privateip> list(String subnetId,Map<String, String> filteringParams) {
		Invocation<Privateips> flavorInvocation = get(Privateips.class, uri("/subnets/%s/privateips",subnetId));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	flavorInvocation = flavorInvocation.param(entry.getKey(), entry.getValue());
            }
        }
		
		return flavorInvocation.execute().getList();
	}
	
	/**
	 * Applying for Private IP Addresses
	 * @param privateips
	 * @return
	 */
	public List<? extends Privateip> apply(Privateips privateips){
		Preconditions.checkNotNull(privateips, "parameter `privateips` should not be null");
		return post(Privateips.class, uri("/privateips")).entity(privateips).execute().getList();
	}
	
	/**
	 * Querying Elastic IP Address Details
	 * @param publicipId
	 * @return
	 */
	public Privateip get(String privateipId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(privateipId), "parameter `privateipId` should not be empty");
		return get(Privateip.class, uri("/privateips/%s",privateipId)).execute();
	}
	
	/**
	 * Deleting an Elastic IP Address
	 * @param publicipId
	 * @return
	 */
	public ActionResponse delete(String privateipId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(privateipId), "parameter `privateipId` should not be empty");
		return deleteWithResponse(uri("/privateips/%s", privateipId)).execute();
	}

}
