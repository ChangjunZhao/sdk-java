package com.huawei.openstack4j.openstack.evs.v2.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Metadata{
	
	/**
	 * metadata中的表示加密功能的字段，0代表不加密，1代表加密。
		该字段不存在时，云硬盘默认为不加密。
	 */
	@JsonProperty("__system__encrypted")
	private String systemEncrypted;
	
	/**
	 * 	metadata中的加密cmkid字段，与__system__encrypted配合表示需要加密，cmkid长度固定为36个字节。
	 */
	@JsonProperty("__system__cmkid")
	private String systemCmkid;
	
	/**
	 * true表示云硬盘的设备类型为SCSI类型，即允许ECS操作系统直接访问底层存储介质。支持SCSI锁命令。
		false表示云硬盘的设备类型为VBD (虚拟块存储设备 , Virtual Block Device)类型，即为默认类型，VBD只能支持简单的SCSI读写命令。
		该字段不存在时，云硬盘默认为VBD类型
	 */
	@JsonProperty("hw:passthrough")
	private Boolean hwPassthrough;
}
