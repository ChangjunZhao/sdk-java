package com.huawei.openstack4j.openstack.vpc.v1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VirtualPublicIpResp implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6776710501544914385L;
	
	@JsonProperty("publicip_id")
	private String publicipId;
	@JsonProperty("type")
	private String type;
	@JsonProperty("public_ip_address")
	private String publicIpAddress;
	
}
