/*******************************************************************************
 *  Copyright 2017 Huawei TLD
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
/*******************************************************************************
 *******************************************************************************/
package org.openstack4j.sample;

import java.util.List;

import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.dns.v2.PTR;
import org.openstack4j.model.dns.v2.Recordset;
import org.openstack4j.model.dns.v2.Zone;
import org.openstack4j.model.dns.v2.builder.ZoneBuilder;
import org.openstack4j.openstack.dns.v2.domain.DesignatePTR;
import org.openstack4j.openstack.dns.v2.domain.DesignatePTR.DesignatePTRBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-12 10:45:22
 */
public class DNSSample extends AbstractSample {

	private static final Logger logger = LoggerFactory.getLogger(DNSSample.class);

	@Test
	public void testListZones() {
		List<? extends Zone> list = osclient.dns().zones().list();
		logger.info("{}", list);
	}

	@Test
	public void testCreateZones() {
		ZoneBuilder builder = Builders.zone();
		Zone zone = builder.name("").build();
		osclient.dns().zones().create(zone);
		logger.info("{}");
	}

	@Test
	public void testGetZone() {
		Zone zone = osclient.dns().zones().get("123445");
		logger.info("{}", zone);
	}

	@Test
	public void testDeleteZones() {
		ActionResponse response = osclient.dns().zones().delete("zone-id");
		if (response.isSuccess()) {
			//
		}
	}

	@Test
	public void testCreateRecordset() {
		// create with recordset model
		Recordset recordset = Builders.recordset().name("").type("A").build();
		Recordset created = osclient.dns().recordsets().create("zone-id", recordset);
		logger.info("{}", created);

		// create directly
		Recordset created2 = osclient.dns().recordsets().create("zone-id", "name", "dns-type",
				Lists.newArrayList("record1", "records2", "..."));
		logger.info("{}", created2);
	}

	@Test
	public void listRecordsets() {
		List<? extends Recordset> recordsetsOfZone = osclient.dns().recordsets().list("zone-id");
		logger.info("recordsets of zone: {}", recordsetsOfZone);

		List<? extends Recordset> allRecordsets = osclient.dns().recordsets().list();
		logger.info("all recordsets", allRecordsets);
	}

	@Test
	public void getRecordset() {
		Recordset recordset = osclient.dns().recordsets().get("zone-id", "recordset-id");
		logger.info("recordset", recordset);
	}

	@Test
	public void delRecordset() {
		ActionResponse response = osclient.dns().recordsets().delete("zone-id", "recordset-id");
		if (response.isSuccess()) {
			//
		}
	}
	

	@Test
	public void getReverseRecord() {
		DesignatePTR ptr = osclient.dns().ptrs().get("eu-de",
				"9e9c6d33-51a6-4f84-b504-c13301f1cc8c");
		logger.info("PTR: {}", ptr);
	}
	@Test
	public void listPTR() {
		List<? extends PTR> list = osclient.dns().ptrs().list();
		logger.info("PTR list: {}", list);
	}


	public static void main(String[] args) {
		DesignatePTRBuilder builder = DesignatePTR.builder().id("1").ptrdname("example.com");
		DesignatePTR reverseRecord = builder.build();
		System.out.println(reverseRecord);
	}

}
