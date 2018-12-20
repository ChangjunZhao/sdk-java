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
package com.huawei.openstack4j.openstack.vpc.v1.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
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
public class SubnetCreate implements ModelEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * vpc name
	 */
	private String name;
	
	/**
	 * vpc subnet cidr
	 */
	private String cidr;
	
	/**
	 *  The gateway of the subnet
	 */
	@JsonProperty("gateway_ip")
	private String gatewayIp;
	
	/**
	 * Specifies whether DHCP is enabled for the subnet
	 */
	@JsonProperty("dhcp_enable")
	private boolean dhcpEnable;
	
	/**
	 * Specifies the IP address of DNS server 1 on the subnet
	 */
	@JsonProperty("primary_dns")
	private String primaryDns;
	
	/**
	 * Specifies the IP address of DNS server 2 on the subnet
	 */
	@JsonProperty("secondary_dns")
	private String secondaryDns;
	
	/**
	 * Specifies the DNS server address list of a subnet
	 */
	private List<String> dnsList;
	
	/**
	 * Specifies the ID of the AZ to which the subnet belongs
	 */
	@JsonProperty("availability_zone")
	private String availabilityZone;
	
	/**
	 * Specifies the ID of the VPC to which the subnet belongs
	 */
	@JsonProperty("vpc_id")
	private String vpcId;
	
}
