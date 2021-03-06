/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
package com.huawei.openstack4j.model.network;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * IP Address Version Type (V4/V6)
 * 
 * @author Jeremy Unruh
 */
public enum IPVersionType {
	V4(4),
	V6(6)
	;
	private final int version;
	
	private IPVersionType(int version) {
		this.version = version;
	}
	
	/**
	 * Gets the version in Integer form
	 *
	 * @return the version as int
	 */
	@JsonValue
	public int getVersion() {
		return version;
	}

	@JsonCreator
	public static IPVersionType valueOf(int value) {
		for (IPVersionType v : IPVersionType.values()) {
			if (v.version == value) {
				return v;
			}
		}
		return V4;
	}
	
}
