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

import java.util.Hashtable;
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
 * Model represent attributes of Port
 *
 * @author ChangjunZhao
 * @date   2018-03-25
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("port")
public class Port implements ModelEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String id;

	/**
	 * port name
	 */
	String name;
	
	/**
	 * network id
	 */
	@JsonProperty("network_id")
	String networkId;
	
	/**
	 * admin state up
	 */
	@JsonProperty("admin_state_up")
	Boolean adminStateUp;
	
	/**
	 * mac address
	 */
	@JsonProperty("mac_address")
	String macAddress;
	
	/**
	 * fixed ips
	 */
	@JsonProperty("fixed_ips")
	List<FixedIp> fixedIps;
	
	/**
	 * device id
	 */
	@JsonProperty("device_id")
	String deviceId;
	
	/**
	 * device owner
	 */
	@JsonProperty("device_owner")
	String deviceOwner;
	
	/**
	 * tenant id
	 */
	@JsonProperty("tenant_id")
	String tenantId;

	/**
	 * status
	 */
	String status;
	
	/**
	 * security groups
	 */
	@JsonProperty("security_groups")
	List<String> securityGroups;
	
	/**
	 * allowed address pairs
	 */
	@JsonProperty("allowed_address_pairs")
	List<AllowAddressPair> allowedAddressPairs;
	
	/**
	 * extra dhcp opts
	 */
	@JsonProperty("extra_dhcp_opts")
	List<ExtraDhcpOpt> extraDhcpOpts;
	
	/**
	 * binding:vif_type
	 */
	@JsonProperty("binding:vif_type")
	String bindingVifType;
	
	/**
	 * binding:vif_details
	 */
	@JsonProperty("binding:vif_details")
	Hashtable<String, Object> bindingVifDetails;
	
	/**
	 * binding:host_id
	 */
	@JsonProperty("binding:host_id")
	String bindingHostId;
	
	/**
	 * binding:profile
	 */
	@JsonProperty("binding:profile")
	Hashtable<String, Object> bindingProfile;
	
	/**
	 * binding:vnic_type
	 */
	@JsonProperty("binding:vnic_type")
	String bindingVnicType;
	
	
	public static class Ports extends ListResult<Port> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("ports")
		private List<Port> ports;

		public List<Port> value() {
			return ports;
		}

	}

}
