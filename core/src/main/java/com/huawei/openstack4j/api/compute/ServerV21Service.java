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
package com.huawei.openstack4j.api.compute;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.huawei.openstack4j.api.compute.ext.InstanceActionsService;
import com.huawei.openstack4j.api.compute.ext.InterfaceService;
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

/**
 * Server Operations API
 * 
 * @author Jeremy Unruh
 */
public interface ServerV21Service {

    /**
     * List all servers (detailed) that the current tenant has access to
     *
     * @return list of all servers
     */
    List<? extends Server> list();

    /**
     * List all servers (detailed / brief) that the current tenant has access to
     *
     * @param detail if true all attributes will be populated, false (brief) will be ID, Name and Links
     * @return list of all servers
     */
    List<? extends Server> list(boolean detail);

    /**
     * Returns list of servers filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return 
     */
    List<? extends Server> list(Map<String, String> filteringParams);

    /**
     * List all servers for all tenants (detailed / brief) 
     *
     * @param detail if true all attributes will be populated, false (brief) will be ID, Name and Links
     * @return list of all servers
     */
    List<? extends Server> listAll(boolean detail);

    /**
     * Get the specified server by ID
     *
     * @param serverId the server id
     * @return the server or null if not found
     */
    Server get(String serverId);
}
