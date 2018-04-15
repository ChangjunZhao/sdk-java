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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.domain.NovaServerTag;

/**
 * Test cases for Server Tag based Services V2.1
 * 
 * @author ChangjunZhao
 */
@Test(suiteName="ServerTagsV21")
public class ServerTagV21Tests extends AbstractTest {

    private static final String JSON_SERVER_TAGS = "/compute/tags.json";

    @Test
    public void listTags() throws Exception {
        respondWith(JSON_SERVER_TAGS);
        NovaServerTag novaTags = osv3().compute().serverTagsV21().list("1");
        List<String> tags = novaTags.getTags();
        assertEquals(2, tags.size());
        assertEquals("tag1", tags.get(0));
    }
    
    @Test
    public void replaceTags() {
    	String jsonResponse = "{\"tags\": [\"new1\", \"new2\"]}";
        respondWith(200, jsonResponse);
        NovaServerTag novaTags = new NovaServerTag();
        novaTags.addTag("new1");
        novaTags.addTag("new2");
        NovaServerTag newTags = osv3().compute().serverTagsV21().replace("1", novaTags);
        
        assertEquals(novaTags.getTags(), newTags.getTags());
    }
    
    @Test
    public void deleteAllTags() {
    	respondWith(204);
    	ActionResponse delete = osv3().compute().serverTagsV21().deleteAll("1");
    	System.out.println(delete.getCode());
    	assertTrue(delete.isSuccess());
    }
    
    @Test
    public void checkTag() {
    	respondWith(204);
    	ActionResponse check = osv3().compute().serverTagsV21().check("1", "tag1");
    	assertTrue(check.isSuccess());
    }
    
    @Test
    public void addTag() {
    	respondWith(204);
    	ActionResponse check = osv3().compute().serverTagsV21().addSingle("1", "tag");
    	assertTrue(check.isSuccess());
    }
    
    @Test
    public void deleteTag() {
    	respondWith(204);
    	ActionResponse delete = osv3().compute().serverTagsV21().delete("1", "tag1");
    	assertTrue(delete.isSuccess());
    }
    
    @Override
    protected Service service() {
        return Service.COMPUTE;
    }

}
