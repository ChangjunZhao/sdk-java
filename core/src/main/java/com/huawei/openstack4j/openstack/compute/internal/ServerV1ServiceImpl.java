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
package com.huawei.openstack4j.openstack.compute.internal;

import static com.google.common.base.Preconditions.*;

import java.util.List;

import com.google.common.collect.Lists;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.api.compute.ServerV1Service;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.compute.RebootType;
import com.huawei.openstack4j.model.compute.StopType;
import com.huawei.openstack4j.openstack.common.AsyncJobEntity;
import com.huawei.openstack4j.openstack.common.IdResourceEntity;
import com.huawei.openstack4j.openstack.common.functions.ReplaceVersionOfURL;

public class ServerV1ServiceImpl extends BaseComputeServices implements ServerV1Service {

	public ServerV1ServiceImpl() {
		super(ServiceType.COMPUTE, ReplaceVersionOfURL.instance("/v1"));
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public String delete(List<String> serverIds, boolean deletePublicIp, boolean deleteVolume) {
		checkArgument(serverIds != null && serverIds.size() > 0, "parameter `serverIds` should not be empty");
		DeleteServerRequest request = new DeleteServerRequest(serverIds, deletePublicIp, deleteVolume);
		return post(AsyncJobEntity.class, uri("/cloudservers/delete")).entity(request).execute().getId();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public String stop(List<String> serverIds, StopType type) {
		checkArgument(serverIds != null && serverIds.size() > 0, "parameter `serverIds` should not be empty");
		checkArgument(type != null, "parameter `type` should not be null");

		BatchStopAction action = new BatchStopAction(serverIds, type);
		return post(AsyncJobEntity.class, uri("/cloudservers/action")).entity(action).execute().getId();
	}
	
	/*
	 * {@inheritDoc}
	 */
	@Override
	public String reboot(List<String> serverIds, RebootType type) {
		checkArgument(serverIds != null && serverIds.size() > 0, "parameter `serverIds` should not be empty");
		checkArgument(type != null, "parameter `type` should not be null");
		
		BatchRebootAction action = new BatchRebootAction(serverIds, type);
		return post(AsyncJobEntity.class, uri("/cloudservers/action")).entity(action).execute().getId();
	}
	
	/*
	 * {@inheritDoc}
	 */
	@Override
	public String start(List<String> serverIds) {
		checkArgument(serverIds != null && serverIds.size() > 0, "parameter `serverIds` should not be empty");
		
		BatchStartAction action = new BatchStartAction(serverIds);
		return post(AsyncJobEntity.class, uri("/cloudservers/action")).entity(action).execute().getId();
	}
	
	public static class BatchAction implements ModelEntity {

		private static final long serialVersionUID = -3993352728410832732L;

		@JsonProperty("type")
		public String type;

		@JsonProperty("servers")
		List<IdResourceEntity> servers = Lists.newArrayList();

		public BatchAction(List<String> serverIds, String type) {
			for (String serverId : serverIds) {
				servers.add(new IdResourceEntity(serverId));
			}
			this.type = type;
		}
	}
	
	@JsonRootName("os-start")
	public static class BatchStartAction extends BatchAction {
		private static final long serialVersionUID = -8311243025930452903L;

		public BatchStartAction(List<String> serverIds) {
			super(serverIds, null);
		}
	}

	
	@JsonRootName("reboot")
	public static class BatchRebootAction extends BatchAction {
		private static final long serialVersionUID = 3151059333050510631L;

		public BatchRebootAction(List<String> serverIds, RebootType type) {
			super(serverIds, type.name().toLowerCase());
		}
	}

	@JsonRootName("os-stop")
	public static class BatchStopAction extends BatchAction {
		private static final long serialVersionUID = 9217647120271727241L;

		public BatchStopAction(List<String> serverIds, StopType type) {
			super(serverIds, type.name().toLowerCase());
		}
	}

	public static class DeleteServerRequest implements ModelEntity {

		private static final long serialVersionUID = -3993352728410832732L;

		@JsonProperty("delete_publicip")
		public boolean deletePublicIp;
		@JsonProperty("delete_volume")
		public boolean deleteVolume;

		@JsonProperty("servers")
		List<IdResourceEntity> servers = Lists.newArrayList();

		public DeleteServerRequest(List<String> serverIds, boolean deletePublicIp, boolean deleteVolume) {
			for (String serverId : serverIds) {
				servers.add(new IdResourceEntity(serverId));
			}

			this.deletePublicIp = deletePublicIp;
			this.deleteVolume = deleteVolume;
		}
	}

}
