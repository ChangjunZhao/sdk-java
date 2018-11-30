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
import com.huawei.openstack4j.openstack.vpc.v1.domain.BandWidth;
import com.huawei.openstack4j.openstack.vpc.v1.domain.BandWidth.Bandwidths;

/**
 * The implementation of manipulation of Bandwidth
 * 
 * @author ChangjunZhao
 * @date   2018-03-25
 */
public class BandWidthService extends BaseVirtualPrivateCloudService{

	/**
	 * Querying Bandwidths
	 * @return Bandwidth List
	 */
	public List<? extends BandWidth> list(){
		return list(null);
	}
	
	/**
	 * Querying Bandwidths with filter
	 * @param filteringParams
	 * @return
	 */
	public List<? extends BandWidth> list(Map<String, String> filteringParams) {
		Invocation<Bandwidths> flavorInvocation = get(Bandwidths.class, uri("/bandwidths"));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	flavorInvocation = flavorInvocation.param(entry.getKey(), entry.getValue());
            }
        }
		
		return flavorInvocation.execute().getList();
	}
	
	/**
	 * Querying Bandwidth Details
	 * @param bandwidthId
	 * @return Bandwidth
	 */
	public BandWidth get(String bandwidthId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(bandwidthId), "parameter `bandwidthId` should not be empty");
		return get(BandWidth.class, uri("/bandwidths/%s",bandwidthId)).execute();
	}
	
	/**
	 * Updating Bandwidth
	 * @param bandwidth
	 * @return bandwidth
	 */
	public BandWidth update(BandWidth bandwidth){
		Preconditions.checkNotNull(bandwidth, "parameter `bandwidth` should not be null");
		return put(BandWidth.class, uri("/bandwidths/%s",bandwidth.getId())).entity(bandwidth).execute();
	}

}
