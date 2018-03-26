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
package com.huawei.openstack4j.openstack.vpc.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Model represent attributes of privateip
 *
 * @author ChangjunZhao
 * @date   2018-03-25
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("privateip")
public class Privateip implements ModelEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String id;

	/**
	 * subnet id
	 */
	@JsonProperty("subnet_id")
	String subnetId;
	
	/**
	 * tenant id
	 */
	@JsonProperty("tenant_id")
	String tenantId;
	
	@JsonProperty("device_owner")
	String deviceOwner;
	
	@JsonProperty("ip_address")
	String ipAddress;

	/**
	 * private ip status
	 */
	String status;
	
	@Setter
	public static class Privateips extends ListResult<Privateip> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("privateips")
		private List<Privateip> privateips;

		public List<Privateip> value() {
			return privateips;
		}

	}

}
