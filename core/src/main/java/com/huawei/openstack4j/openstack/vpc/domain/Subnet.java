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
 * Model represent attributes of vpc subnet
 *
 * @author ChangjunZhao
 * @date   2018-03-25
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("subnet")
public class Subnet implements ModelEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String id;

	/**
	 * vpc name
	 */
	String name;
	
	/**
	 * vpc subnet cidr
	 */
	String cidr;
	
	@JsonProperty("gateway_ip")
	String gatewayIp;
	
	@JsonProperty("dhcp_enable")
	boolean dhcpEnable;
	
	@JsonProperty("primary_dns")
	String primaryDns;
	
	@JsonProperty("secondary_dns")
	String secondaryDns;
	
	List<String> dnsList;
	
	@JsonProperty("availability_zone")
	String availabilityZone;
	
	@JsonProperty("vpc_id")
	String vpcId;

	/**
	 * vpc status
	 */
	String status;
	
	@JsonProperty("neutron_network_id")
	String neutronNetworkId;
	
	@JsonProperty("neutron_subnet_id")
	String neutronSubnetId;
	
	public static class Subnets extends ListResult<Subnet> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("subnets")
		private List<Subnet> subnets;

		public List<Subnet> value() {
			return subnets;
		}

	}

}
