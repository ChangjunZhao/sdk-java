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
import com.huawei.openstack4j.openstack.vpc.domain.SecurityGroup;
import com.huawei.openstack4j.openstack.vpc.domain.SecurityGroup.SecurityGroups;
/**
 * The implementation of manipulation of Security Group
 * 
 * @author ChangjunZhao
 * @date   2018-03-25
 */
public class SecurityGroupService extends BaseVpcServices{
	
	/**
	 * Querying Security Groups
	 * @return
	 */
	public List<? extends SecurityGroup> list(){
		return list(null);
	}
	
	/**
	 * Querying Security Groups with filter
	 * @param filteringParams
	 * @return
	 */
	public List<? extends SecurityGroup> list(Map<String, String> filteringParams) {
		Invocation<SecurityGroups> flavorInvocation = get(SecurityGroups.class, uri("/security-groups"));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	flavorInvocation = flavorInvocation.param(entry.getKey(), entry.getValue());
            }
        }
		
		return flavorInvocation.execute().getList();
	}
	
	/**
	 * Creating a Security Group
	 * @param securityGroup
	 * @return
	 */
	public SecurityGroup create(SecurityGroup securityGroup){
		Preconditions.checkNotNull(securityGroup, "parameter `securityGroup` should not be null");
		Preconditions.checkNotNull(securityGroup.getName(), "parameter `securityGroup.name should not be empty");
		return post(SecurityGroup.class, uri("/security-groups")).entity(securityGroup).execute();
	}
	
	/**
	 * Querying Security Group Details
	 * @param securityGroupId
	 * @return
	 */
	public SecurityGroup get(String securityGroupId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(securityGroupId), "parameter `privateipId` should not be empty");
		return get(SecurityGroup.class, uri("/security-groups/%s",securityGroupId)).execute();
	}

}
