/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Date;
import org.geosdi.geoplatform.GPGenericMarshaller;
import org.geosdi.geoplatform.json.jaxb.GenericJsonJaxbMarshaller;
import org.geosdi.geoplatform.mock.ClassToXMLMap;
import org.geosdi.geoplatform.twitter.SearchResults;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:"
    + "applicationContext-Json-Test.xml"})
public class GeoPlatformJsonTest {

    private static final Logger logger = LoggerFactory.getLogger(
            GeoPlatformJsonTest.class);
    //
    @Autowired
    @Qualifier(value = "xtreamJson")
    private GPGenericMarshaller xtreamJson;
    //
    @Autowired
    @Qualifier(value = "jaxbJson")
    private GenericJsonJaxbMarshaller jaxbJson;
    //
    private ClassToXMLMap message;

    @Before
    public void onSetUp() throws Exception {
        message = new ClassToXMLMap();
        message.setData("I am JSON data");
        message.setHistory("in the past i was in JSON format");
    }

    @Test
    public void testXStreamJson() throws Exception {
        String xtreamJsonFile = "target/xtreamJson.json";
        File f = new File(xtreamJsonFile);

        xtreamJson.marshal(message, f);

        ClassToXMLMap xstreamMap = (ClassToXMLMap) xtreamJson.
                unmarshal(f);

        Assert.assertNotNull(xstreamMap);

        logger.info("XSTREAM JSON BEAN *************** {}", xstreamMap + "\n");
    }

    @Test
    public void testJaxbJson() throws Exception {
        String jaxbJsonFile = "target/jaxbJson.json";
        File f = new File(jaxbJsonFile);

        jaxbJson.marshal(message, f);

        ClassToXMLMap jaxbJsonMap = (ClassToXMLMap) jaxbJson.
                unmarshal(f);

        Assert.assertNotNull(jaxbJsonMap);

        logger.info("JAXB JSON BEAN *************** {}", jaxbJsonMap + "\n");
    }

    @Test
    @Ignore(value = "With api 1.1.0 Authentication required on all endpoints")
    public void testTwitterSearch() throws Exception {
        URL url = new URL("http://search.twitter.com/search.json?q=jenkins");

        JsonDeserializer<Date> dateDeserializer = new JsonDeserializer<Date>() {

            @Override
            public Date deserialize(JsonElement je, Type type,
                    JsonDeserializationContext jdc) throws JsonParseException {

                return je == null ? null : new Date(je.getAsString());
            }

        };

        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class,
                dateDeserializer).create();

        SearchResults r = gson.fromJson(new InputStreamReader(url.openStream()),
                SearchResults.class);

        logger.info("FOUND : {} Results  ", r.getResults().size());

        String googleJsonFile = "target/googleJson.json";
        File f = new File(googleJsonFile);
        
        String json = gson.toJson(r);
        
        FileWriter fileWriter = new FileWriter(f, true);
        BufferedWriter bufferFileWriter  = new BufferedWriter(fileWriter);
        
        bufferFileWriter.append(json);
        
        bufferFileWriter.close();
                
    }

}
