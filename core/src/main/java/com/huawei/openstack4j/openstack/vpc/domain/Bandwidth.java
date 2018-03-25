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
import lombok.ToString;

/**
 * Model represent attributes of bandwidth
 * 
 * @author ChangjunZhao
 * @date   2018-03-25
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("bandwidth")
public class Bandwidth implements ModelEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String id;

	/**
	 * bandwidth name
	 */
	String name;
	
	/**
	 * bandwidth size
	 */
	Integer size;

	/**
	 * bandwidth share type
	 */
	@JsonProperty("share_type")
	String shareType;
	
	/**
	 * bandwidth share type
	 */
	@JsonProperty("publicip_info")
	List<PublicipInfo> publicipInfo;
	
	/**
	 * bandwidth share type, PER or WHOLE
	 */
	@JsonProperty("tenant_id")
	String tenantId;
	
	/**
	 * bandwidth bandwidth type, bgp/union/double/telcom
	 */
	@JsonProperty("bandwidth_type")
	String bandwidthType;
	
	/**
	 * bandwidth charge mode, bandwidth or traffic
	 */
	@JsonProperty("charge_mode")
	String chargeMode;
	
	public static class Bandwidths extends ListResult<Bandwidth> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("bandwidths")
		private List<Bandwidth> bandwidths;

		public List<Bandwidth> value() {
			return bandwidths;
		}

	}

}
