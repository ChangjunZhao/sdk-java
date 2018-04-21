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
package com.huawei.openstack4j.openstack.compute.v21.internal;

import static com.google.common.base.Preconditions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.compute.ServerService;
import com.huawei.openstack4j.api.compute.ServerV21Service;
import com.huawei.openstack4j.api.compute.ext.InstanceActionsService;
import com.huawei.openstack4j.api.compute.ext.InterfaceService;
import com.huawei.openstack4j.core.transport.ExecutionOptions;
import com.huawei.openstack4j.core.transport.HttpResponse;
import com.huawei.openstack4j.core.transport.propagation.PropagateOnStatus;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.compute.Action;
import com.huawei.openstack4j.model.compute.RebootType;
import com.huawei.openstack4j.model.compute.Server;
import com.huawei.openstack4j.model.compute.Server.Status;
import com.huawei.openstack4j.model.compute.ServerCreate;
import com.huawei.openstack4j.model.compute.ServerPassword;
import com.huawei.openstack4j.model.compute.ServerUpdateOptions;
import com.huawei.openstack4j.model.compute.VNCConsole;
import com.huawei.openstack4j.model.compute.VNCConsole.Type;
import com.huawei.openstack4j.model.compute.VolumeAttachment;
import com.huawei.openstack4j.model.compute.actions.BackupOptions;
import com.huawei.openstack4j.model.compute.actions.EvacuateOptions;
import com.huawei.openstack4j.model.compute.actions.LiveMigrateOptions;
import com.huawei.openstack4j.model.compute.actions.RebuildOptions;
import com.huawei.openstack4j.model.compute.builder.ServerCreateBuilder;
import com.huawei.openstack4j.openstack.common.Metadata;
import com.huawei.openstack4j.openstack.compute.domain.AdminPass;
import com.huawei.openstack4j.openstack.compute.domain.ConsoleOutput;
import com.huawei.openstack4j.openstack.compute.domain.ConsoleOutputOptions;
import com.huawei.openstack4j.openstack.compute.domain.MetadataItem;
import com.huawei.openstack4j.openstack.compute.domain.NovaPassword;
import com.huawei.openstack4j.openstack.compute.domain.NovaServer;
import com.huawei.openstack4j.openstack.compute.domain.NovaServer.Servers;
import com.huawei.openstack4j.openstack.compute.domain.NovaServerCreate;
import com.huawei.openstack4j.openstack.compute.domain.NovaServerUpdate;
import com.huawei.openstack4j.openstack.compute.domain.NovaVNCConsole;
import com.huawei.openstack4j.openstack.compute.domain.NovaVolumeAttachment;
import com.huawei.openstack4j.openstack.compute.domain.NovaVolumeAttachment.NovaVolumeAttachments;
import com.huawei.openstack4j.openstack.compute.domain.actions.BackupAction;
import com.huawei.openstack4j.openstack.compute.domain.actions.BasicActions;
import com.huawei.openstack4j.openstack.compute.domain.actions.BasicActions.ChangePassword;
import com.huawei.openstack4j.openstack.compute.domain.actions.BasicActions.ConfirmResize;
import com.huawei.openstack4j.openstack.compute.domain.actions.BasicActions.Migrate;
import com.huawei.openstack4j.openstack.compute.domain.actions.BasicActions.Reboot;
import com.huawei.openstack4j.openstack.compute.domain.actions.BasicActions.Resize;
import com.huawei.openstack4j.openstack.compute.domain.actions.BasicActions.RevertResize;
import com.huawei.openstack4j.openstack.compute.domain.actions.CreateSnapshotAction;
import com.huawei.openstack4j.openstack.compute.domain.actions.EvacuateAction;
import com.huawei.openstack4j.openstack.compute.domain.actions.LiveMigrationAction;
import com.huawei.openstack4j.openstack.compute.domain.actions.RebuildAction;
import com.huawei.openstack4j.openstack.compute.domain.actions.ResetStateAction;
import com.huawei.openstack4j.openstack.compute.domain.actions.SecurityGroupActions;
import com.huawei.openstack4j.openstack.compute.domain.actions.ServerAction;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.compute.functions.WrapServerIfApplicableFunction;

/**
 * Server Operation API implementation
 *
 * @author Jeremy Unruh
 */
public class ServerV21ServiceImpl extends BaseComputeServices implements ServerV21Service {

	private static final Logger LOG = LoggerFactory.getLogger(ServerV21ServiceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Server> list() {
		return list(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Server> list(boolean detail) {
		return list(detail, Boolean.FALSE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Server> listAll(boolean detail) {
		return list(detail, Boolean.TRUE);
	}

	private List<? extends Server> list(boolean detail, boolean allTenants) {
		Invocation<Servers> req = get(Servers.class, uri("/servers" + ((detail) ? "/detail" : "")));
		if (allTenants)
			req.param("all_tenants", 1);
		return req.execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Server> list(Map<String, String> filteringParams) {
		Invocation<Servers> serverInvocation = get(Servers.class, "/servers/detail");
		if (filteringParams != null) {
			for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
				serverInvocation = serverInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return serverInvocation.execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Server get(String serverId) {
		checkNotNull(serverId);
		return get(NovaServer.class, uri("/servers/%s", serverId)).execute();
	}
	
}
