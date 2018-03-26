/*******************************************************************************
 * 	Copyright 2018 HuaWei Tld                                     
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
package com.huawei.openstack4j.api.vpc;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.vpc.domain.Bandwidth;

import okhttp3.mockwebserver.RecordedRequest;

/**
 * Test The implementation of manipulation of Bandwidth
 * @author ChangjunZhao
 * @date   2018-03-26
 */
@Test(suiteName = "Vpc/Bandwidth", enabled = true)
public class BandwidthTest extends AbstractTest {

	@Test
	public void testListBandwidths() throws IOException, InterruptedException {
		respondWith("/vpc/list_bandwidth_response.json");

		List<? extends Bandwidth> list = osv3().vpc().bandwidths().list();
		
		Bandwidth bandwidth = list.get(0);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/bandwidths");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 3);
		Assert.assertEquals(bandwidth.getId(), "a79fd11a-047b-4f5b-8f12-99c178cc780a");
		Assert.assertEquals(bandwidth.getBandwidthType(),"bgp");
		Assert.assertEquals(bandwidth.getSize().intValue(),5);
		Assert.assertEquals(bandwidth.getPublicipInfo().get(0).getPublicipAddress(),"161.17.101.10");
	}
	
	@Test
	public void testListBandwidthsWithFilter() throws IOException, InterruptedException {
		respondWith("/vpc/list_bandwidth_response.json");

		Map<String,String> filteringParams = new HashMap<String,String>();
		filteringParams.put("limit", "3");
		filteringParams.put("marker", "a79fd11a-047b-4f5b-8f12-99c178cc780a");
		List<? extends Bandwidth> list = osv3().vpc().bandwidths().list(filteringParams);
		
		Bandwidth bandwidth = list.get(0);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/bandwidths?marker=a79fd11a-047b-4f5b-8f12-99c178cc780a&limit=3");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertNotNull(list);
		Assert.assertEquals(list.size(), 3);
		Assert.assertEquals(bandwidth.getId(), "a79fd11a-047b-4f5b-8f12-99c178cc780a");
		Assert.assertEquals(bandwidth.getBandwidthType(),"bgp");
		Assert.assertEquals(bandwidth.getSize().intValue(),5);
		Assert.assertEquals(bandwidth.getPublicipInfo().get(0).getPublicipAddress(),"161.17.101.10");
	}
	
	@Test
	public void testGetBandwidth() throws IOException, InterruptedException {
		respondWith("/vpc/get_update_bandwidth_response.json");

		Bandwidth bandwidth = osv3().vpc().bandwidths().get("3fa5b383-5a73-4dcb-a314-c6128546d855");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/bandwidths/3fa5b383-5a73-4dcb-a314-c6128546d855");
		Assert.assertEquals(request.getMethod(), "GET");
		Assert.assertEquals(bandwidth.getId(), "3fa5b383-5a73-4dcb-a314-c6128546d855");
		Assert.assertEquals(bandwidth.getName(),"2222");
		Assert.assertEquals(bandwidth.getSize().intValue(),5);
		Assert.assertEquals(bandwidth.getPublicipInfo().get(0).getPublicipAddress(),"161.17.101.9");
		Assert.assertEquals(bandwidth.getShareType(),"PER");
	}
	
	@Test
	public void testUpdateBandwidth() throws IOException, InterruptedException {
		respondWith("/vpc/get_update_bandwidth_response.json");
		
		Bandwidth bandwidth = Bandwidth.builder().id("3fa5b383-5a73-4dcb-a314-c6128546d855").name("2222").size(5).build();

		bandwidth = osv3().vpc().bandwidths().update(bandwidth);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1/project-id/bandwidths/3fa5b383-5a73-4dcb-a314-c6128546d855");
		Assert.assertEquals(request.getMethod(), "PUT");
		Assert.assertEquals(bandwidth.getId(), "3fa5b383-5a73-4dcb-a314-c6128546d855");
		Assert.assertEquals(bandwidth.getName(),"2222");
		Assert.assertEquals(bandwidth.getSize().intValue(),5);
		Assert.assertEquals(bandwidth.getPublicipInfo().get(0).getPublicipAddress(),"161.17.101.9");
		Assert.assertEquals(bandwidth.getShareType(),"PER");
	}

	@Override
	protected Service service() {
		return Service.NETWORK;
	}

}
