package com.huawei.openstack4j.openstack.ecs.v1.internal;

import static com.google.common.base.Preconditions.*;

import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.compute.RebootType;
import com.huawei.openstack4j.model.compute.StopType;
import com.huawei.openstack4j.openstack.common.AsyncJobEntity;
import com.huawei.openstack4j.openstack.common.IdResourceEntity;
import com.huawei.openstack4j.openstack.ecs.v1.domain.CloudServer;
import com.huawei.openstack4j.openstack.ecs.v1.domain.CloudServer.CloudServers;
import com.huawei.openstack4j.openstack.ecs.v1.domain.ServerCreate;
import com.huawei.openstack4j.openstack.ecs.v1.domain.ResizeServer;

public class CloudServerService extends BaseElasticComputeServices {

	/**
	 * create one or multiple server
	 * 
	 * @param creation
	 * @return			job-id of the asynchronous create server task
	 */
	public String create(ServerCreate creation) {
		checkArgument(!Strings.isNullOrEmpty(creation.getImageRef()), "parameter `imageRef` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(creation.getFlavorRef()), "parameter `flavorRef` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(creation.getName()), "parameter `name` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(creation.getVpcId()), "parameter `vpcid` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(creation.getAvailabilityZone()), "parameter `availability_zone` should not be empty");
		checkArgument(!(creation.getPersonality() != null && creation.getPersonality().size() > 5),
				"size of parameter `personality` should not greate than 5");
		checkArgument(creation.getNetworks() != null && creation.getNetworks().size() > 0,
				"parameter `networks` should not be empty");
		checkArgument(creation.getRootVolume() != null, "parameter `root_volume` should not be empty");
		return post(AsyncJobEntity.class, "/cloudservers").entity(creation).execute().getId();
	}

	/**
	 * batch delete servers
	 * 
	 * @param serverIds			list of server identifier which to be deleted
	 * @param deletePublicIp	whether to delete public IP of the server
	 * @param deleteVolume		whether to delete volume of the server
	 * @return					job-id of the asynchronous delete server task
	 */
	public String delete(List<String> serverIds, boolean deletePublicIp, boolean deleteVolume) {
		checkArgument(serverIds != null && serverIds.size() > 0, "parameter `serverIds` should not be empty");
		DeleteServerRequest request = new DeleteServerRequest(serverIds, deletePublicIp, deleteVolume);
		return post(AsyncJobEntity.class, uri("/cloudservers/delete")).entity(request).execute().getId();
	}

	/**
	 * batch stop servers
	 * 
	 * @param serverIds		list of server identifier which to be stopped
	 * @param type			stop type - soft | hard
	 * @return				job-id of the asynchronous stop server task
	 */
	public String stop(List<String> serverIds, StopType type) {
		checkArgument(serverIds != null && serverIds.size() > 0, "parameter `serverIds` should not be empty");
		checkArgument(type != null, "parameter `type` should not be null");

		BatchStopAction action = new BatchStopAction(serverIds, type);
		return post(AsyncJobEntity.class, uri("/cloudservers/action")).entity(action).execute().getId();
	}

	/**
	 * batch reboot servers
	 * 
	 * @param serverIds		list of server identifier which to be reboot
	 * @param type			reboot type - soft | hard
	 * @return				job-id of the asynchronous stop server task
	 */
	public String reboot(List<String> serverIds, RebootType type) {
		checkArgument(serverIds != null && serverIds.size() > 0, "parameter `serverIds` should not be empty");
		checkArgument(type != null, "parameter `type` should not be null");

		BatchRebootAction action = new BatchRebootAction(serverIds, type);
		return post(AsyncJobEntity.class, uri("/cloudservers/action")).entity(action).execute().getId();
	}

	/**
	 * batch start servers
	 * 
	 * @param serverIds		list of server identifier which to be started
	 * @return				job-id of the asynchronous start server task
	 */
	public String start(List<String> serverIds) {
		checkArgument(serverIds != null && serverIds.size() > 0, "parameter `serverIds` should not be empty");
		BatchStartAction action = new BatchStartAction(serverIds);
		return post(AsyncJobEntity.class, uri("/cloudservers/action")).entity(action).execute().getId();
	}
	
	/**
	 * Change cloud server specifications
	 * @param resize
	 * @param serverId
	 * @return
	 */
	public String resize(ResizeServer resize,String serverId){
		checkArgument(!Strings.isNullOrEmpty(resize.getFlavorRef()), "parameter `flavorRef` should not be empty");
		return post(AsyncJobEntity.class, "/cloudservers/"+serverId+"/resize").entity(resize).execute().getId();
	}
	
	/**
	 * Query cloud server details list
	 * @return
	 */
	public List<CloudServer> list(){
		return get(CloudServers.class, uri("/cloudservers/detail")).execute().getList();
	}

	/**
	 * Query cloud server details list by filteringParams
	 * @return
	 */
	public List<CloudServer> list(Map<String, String> filteringParams) {
		Invocation<CloudServers> serverInvocation = get(CloudServers.class, "/cloudservers/detail");
		if (filteringParams != null) {
			for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
				serverInvocation = serverInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return serverInvocation.execute().getList();
	}
	/**
	 * Query cloud server details
	 * @param serverId
	 * @return
	 */
	public CloudServer get(String serverId){
		return get(CloudServer.class, uri("/cloudservers/")+serverId).execute();
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
