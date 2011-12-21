/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.services;

import it.geosolutions.geonetwork.GNClient;
import it.geosolutions.geonetwork.exception.GNLibException;
import it.geosolutions.geonetwork.exception.GNServerException;
import it.geosolutions.geonetwork.util.GNSearchResponse;
import org.geosdi.geoplatform.exception.GPCatalogException;
import org.geosdi.geoplatform.exception.GPCatalogLoginException;
import org.geosdi.geoplatform.responce.GPCatalogMetadataDTO;
import org.geosdi.geoplatform.services.util.GPCatalogClient;
import org.geosdi.geoplatform.services.util.GPCatalogMetadataLoader;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class GPCatalogTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Autowired
    private GPCatalogClient gpCatalogClient;
    //
    @Autowired
    private GPCatalogFinderService gpCatalogFinderService;
    //
    @Autowired
    private GPCatalogMetadataLoader gpCatalogMetadataLoader;

    @Test
    public void testGPCatalogClientReferences() {
        Assert.assertNotNull("gpCatalogClient is null", gpCatalogClient);

        Assert.assertNotNull("URL of GeoNetwork is null", gpCatalogClient.getGeoNetworkServiceURL());
        Assert.assertNotNull("username of GeoNetwork is null", gpCatalogClient.getGeoNetworkUsername());
        Assert.assertNotNull("password of GeoNetwork is null", gpCatalogClient.getGeoNetworkPassword());
    }

    @Test
    public void testGPCatalogFinderServiceReferences() {
        Assert.assertNotNull("gpCatalogFinderService is null", gpCatalogFinderService);
    }

    @Test
    public void testGPCatalogMetadataLoader() {
        Assert.assertNotNull("gpCatalogMetadataLoader is null", gpCatalogMetadataLoader);
    }

    @Test
    public void testGPCatalogClient() {
        try {
            gpCatalogClient.createClientWithCredentials();
        } catch (GPCatalogLoginException ex) {
            Assert.fail(ex.getMessage());
        }
    }
    
////    @Test
////    public void testWrongURL() {
////        gpCatalogClient.setGeoNetworkServiceURL("http://localhost:8282/geonetwork");
////        try {
////            gpCatalogClient.login();
////            Assert.fail("Impossible to make login because GeoNetwork service URL is wrong");
////        } catch (GPCatalogLoginException ex) {
////        }
////    }
////
////    @Test
////    public void testWrongCredentials() {
////        gpCatalogClient.setGeoNetworkUsername("admin");
////        gpCatalogClient.setGeoNetworkPassword("aadminn");
////        try {
////            gpCatalogClient.login();
////            Assert.fail("Impossible to make login because password is wrong");
////        } catch (GPCatalogLoginException ex) {
////            Assert.fail(ex.getMessage());
////        }
////    }
    
    @Test
    public void testAnonymousGPCatalogFinderService() {
        try {
            GNClient client = gpCatalogClient.createClientWithoutCredentials();
            Assert.assertNotNull("GeoNetwork client is null", client);

            GNSearchResponse searchResponse = this.gpCatalogFinderService.searchMetadata(client, "strade romane");

            if (searchResponse.getCount() != 0) {
                logger.debug("Items found: " + searchResponse.getCount());
                // loop on all metadata
                for (GNSearchResponse.GNMetadata metadata : searchResponse) {
                    Long id = metadata.getId();
                    // and this is the full metadata document, as a JDOM element.
                    Element md = client.get(id);
                    XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
//                    logger.info("Metadata -> " + out.outputString(md));
                }
            } else {
                logger.info("No data retrieved");
            }
        } catch (GNLibException ex) {
            Assert.fail(ex.getMessage());
        } catch (GNServerException ex) {
            Assert.fail(ex.getMessage());
        } catch (GPCatalogException ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void testPrivateGPCatalogFinderService() {
        try {
            GNClient client = gpCatalogClient.createClientWithCredentials();
            Assert.assertNotNull("GeoNetwork client is null", client);

            GNSearchResponse searchResponse = this.gpCatalogFinderService.searchMetadata(client, "strade romane"); // TODO [Michele]: retrieve a non public layer from geonetwork

            if (searchResponse.getCount() != 0) {
                logger.debug("Items found: " + searchResponse.getCount());
                // loop on all metadata
                for (GNSearchResponse.GNMetadata metadata : searchResponse) {
                    Long id = metadata.getId();
                    // and this is the full metadata document, as a JDOM element.
                    Element md = client.get(id);
                    XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
//                    logger.info("Metadata -> " + out.outputString(md));
                }
            } else {
                logger.info("No data retrieved");
            }
        } catch (GNLibException ex) {
            Assert.fail(ex.getMessage());
        } catch (GNServerException ex) {
            Assert.fail(ex.getMessage());
        } catch (GPCatalogException ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void testExtractDatasFromMetadata() {
        try {
            GNClient client = gpCatalogClient.createClientWithCredentials();
            Assert.assertNotNull("GeoNetwork client is null", client);

            GNSearchResponse searchResponse = this.gpCatalogFinderService.searchMetadata(client, "strade romane");
            Assert.assertTrue("Items found not equals 3", searchResponse.getCount() == 3);

            if (searchResponse.getCount() != 0) {
                // loop on all metadata
                for (GNSearchResponse.GNMetadata metadata : searchResponse) {
                    Long id = metadata.getId();
                    // and this is the full metadata document, as a JDOM element.
                    Element md = client.get(id);
                    GPCatalogMetadataDTO gpCatalogMetadataDTO = this.gpCatalogMetadataLoader.getGPCatalogMetadataDTO(md);
                    logger.info(gpCatalogMetadataDTO.toString());
//
//                    Filter titleFilter = new ElementFilter("title", this.gmdNamespace);
//                    Element titleElement = (Element) (md.getDescendants(titleFilter).next());
//                    if (titleElement != null) {
//                        logger.info("\tTitle value: " + titleElement.getChildText("CharacterString", this.gcoNamespace));
//                    }
//
//                    Filter abstractFilter = new ElementFilter("abstract", this.gmdNamespace);
//                    Element abstractElement = (Element) (md.getDescendants(abstractFilter).next());
//                    if (abstractElement != null) {
//                        logger.info("\tAbstract value: " + abstractElement.getChildText("CharacterString", this.gcoNamespace));
//                    }
//
//                    Filter keywordsFilter = new ElementFilter("MD_Keywords", this.gmdNamespace);
//                    Element keywordsElement = (Element) (md.getDescendants(keywordsFilter).next());
//                    List keywordsList = keywordsElement.getChildren();
//                    for (Object o : keywordsList) {
//                        Element keywordElement = (Element) o;
//                        logger.info("\tKeyword value: " + keywordElement.getChildText("CharacterString", this.gcoNamespace));
//                    }
//
//                    Filter digitalTransferOptionsFilter = new ElementFilter("MD_DigitalTransferOptions", this.gmdNamespace);
//                    Element digitalTransferOptionsElement = (Element) (md.getDescendants(digitalTransferOptionsFilter).next());
//                    List onlineElementsList = digitalTransferOptionsElement.getChildren();
//                    for (Object o : onlineElementsList) {
//                        Element onlineElement = (Element) o;
//                        logger.info("\tURL value: " + onlineElement.getChild("CI_OnlineResource", this.gmdNamespace).getChild("linkage", gmdNamespace).getChildText("URL", gmdNamespace));
//                        logger.info("\tProtocol value: " + onlineElement.getChild("CI_OnlineResource", this.gmdNamespace).getChild("protocol", gmdNamespace).getChildText("CharacterString", gcoNamespace));
//                        logger.info("\tName value: " + onlineElement.getChild("CI_OnlineResource", this.gmdNamespace).getChild("name", gmdNamespace).getChildText("CharacterString", gcoNamespace));
//                        logger.info("\tDescription value: " + onlineElement.getChild("CI_OnlineResource", this.gmdNamespace).getChild("description", gmdNamespace).getChildText("CharacterString", gcoNamespace));
//                        logger.info("\n\n");
//                    }
//
//                    Element uuidElement = (Element) (md.getChild("uuid"));
//                    if (uuidElement != null) {
//                        logger.info("\tUUID value: " + uuidElement.getText());
//                    }
//
//                    Filter geographicBoundingBoxFilter = new ElementFilter("EX_GeographicBoundingBox", this.gmdNamespace);
//                    Element geographicBoundingBoxElement = (Element) (md.getDescendants(geographicBoundingBoxFilter).next());
//                    if (geographicBoundingBoxElement != null) {
//                        Element westBoundLongitudeElement = geographicBoundingBoxElement.getChild("westBoundLongitude", gmdNamespace);
//                        if (westBoundLongitudeElement != null) {
//                            logger.info("\tWest value: " + westBoundLongitudeElement.getChildText("Decimal", gcoNamespace));
//                        }
//
//                        Element eastBoundLongitudeElement = geographicBoundingBoxElement.getChild("eastBoundLongitude", gmdNamespace);
//                        if (eastBoundLongitudeElement != null) {
//                            logger.info("\tEast value: " + eastBoundLongitudeElement.getChildText("Decimal", gcoNamespace));
//                        }
//
//                        Element southBoundLatitudeElement = geographicBoundingBoxElement.getChild("southBoundLatitude", gmdNamespace);
//                        if (southBoundLatitudeElement != null) {
//                            logger.info("\tSouth value: " + southBoundLatitudeElement.getChildText("Decimal", gcoNamespace));
//                        }
//
//                        Element northBoundLatitudeElement = geographicBoundingBoxElement.getChild("westBoundLongitude", gmdNamespace);
//                        if (northBoundLatitudeElement != null) {
//                            logger.info("\tNorth value: " + northBoundLatitudeElement.getChildText("Decimal", gcoNamespace));
//                        }
//                    }

                    logger.info("-----------------------------------------------------------");
                }
            } else {
                logger.info("No data retrieved");
            }
        } catch (GNLibException ex) {
            Assert.fail(ex.getMessage());
        } catch (GNServerException ex) {
            Assert.fail(ex.getMessage());
        } catch (GPCatalogException ex) {
            Assert.fail(ex.getMessage());
        }
    }
}
