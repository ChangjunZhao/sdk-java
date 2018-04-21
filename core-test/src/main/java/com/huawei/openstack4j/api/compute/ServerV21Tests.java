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

import static org.testng.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.api.exceptions.ServerResponseException;
import com.huawei.openstack4j.model.compute.Server;
import com.huawei.openstack4j.model.compute.Server.HostStatus;
import com.huawei.openstack4j.model.compute.Server.Status;
import com.huawei.openstack4j.model.compute.ServerCreate;
import com.huawei.openstack4j.model.compute.ServerPassword;
import com.huawei.openstack4j.model.compute.VolumeAttachment;
import com.huawei.openstack4j.model.compute.actions.EvacuateOptions;

import okhttp3.mockwebserver.RecordedRequest;

/**
 * Test cases for V2.1 Server based Services
 * 
 * @author ChangjunZhao
 */
@Test(suiteName = "ServersV21")
public class ServerV21Tests extends AbstractTest {

	private static final String JSON_SERVERS = "/compute/serversv21.json";

	@Test
	public void listServer() throws Exception {
		respondWith(JSON_SERVERS);

		List<? extends Server> servers = osv3().compute().servers().list();
		assertEquals(1, servers.size());

		takeRequest();

		Server s = servers.get(0);
		assertEquals("/dev/vda", s.getRootDeviceName());
		assertEquals(HostStatus.UP, s.getHostStatus());
	}

	@Override
	protected Service service() {
		return Service.COMPUTE;
	}

}
