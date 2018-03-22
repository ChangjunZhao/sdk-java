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

import com.huawei.openstack4j.model.compute.RebootType;
import com.huawei.openstack4j.model.compute.StopType;
import com.huawei.openstack4j.openstack.compute.v1.domain.ServerCreate;

public interface ServerV1Service {
	
	
	
	/**
	 * create one or multiple server
	 * 
	 * @param creation
	 * @return			job-id of the asynchronous create server task
	 */
	String create(ServerCreate creation);

	/**
	 * batch delete servers
	 * 
	 * @param serverIds			list of server identifier which to be deleted
	 * @param deletePublicIp	whether to delete public IP of the server
	 * @param deleteVolume		whether to delete volume of the server
	 * @return					job-id of the asynchronous delete server task
	 */
	String delete(List<String> serverIds, boolean deletePublicIp, boolean deleteVolume);
	
	
	/**
	 * batch stop servers
	 * 
	 * @param serverIds		list of server identifier which to be stopped
	 * @param type			stop type - soft | hard
	 * @return				job-id of the asynchronous stop server task
	 */
	String stop(List<String> serverIds, StopType type);


	/**
	 * batch reboot servers
	 * 
	 * @param serverIds		list of server identifier which to be reboot
	 * @param type			reboot type - soft | hard
	 * @return				job-id of the asynchronous stop server task
	 */
	String reboot(List<String> serverIds, RebootType type);
	
	/**
	 * batch start servers
	 * 
	 * @param serverIds		list of server identifier which to be started
	 * @return				job-id of the asynchronous start server task
	 */
	String start(List<String> serverIds);

}
