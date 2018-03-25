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

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.DateTimeUtils;
import com.huawei.openstack4j.openstack.common.ListResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Model represent attributes of publicip
 *
 * @author ChangjunZhao
 * @date   2018-03-25
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("publicip")
public class Publicip implements ModelEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String id;

	/**
	 * publicip type
	 */
	String type;
	
	/**
	 * publicip status
	 */
	String status;
	
	/**
	 * public ip address
	 */
	@JsonProperty("public_ip_address")
	String publicIpAddress;
	
	/**
	 * private ip address
	 */
	@JsonProperty("private_ip_address")
	String privateIpAddress;
	
	/**
	 * port id
	 */
	@JsonProperty("port_id")
	String portId;
	
	/**
	 * tenant id
	 */
	@JsonProperty("tenant_id")
	String tenantId;
	
	/**
	 * create time
	 */
	@JsonProperty("create_time")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMSZONE)
	Date createTime;
	
	/**
	 * bandwidth id
	 */
	@JsonProperty("bandwidth_id")
	String bandwidthId;
	
	/**
	 * bandwidth size
	 */
	@JsonProperty("bandwidth_size")
	Integer bandwidthSize;
	
	/**
	 * bandwidth share type
	 */
	@JsonProperty("bandwidth_share_type")
	String bandwidthShareType;
	
	/**
	 * bandwidth name
	 */
	@JsonProperty("bandwidth_name")
	String bandwidthName;
	
	public static class Publicips extends ListResult<Publicip> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("publicips")
		private List<Publicip> publicips;

		public List<Publicip> value() {
			return publicips;
		}

	}

}
