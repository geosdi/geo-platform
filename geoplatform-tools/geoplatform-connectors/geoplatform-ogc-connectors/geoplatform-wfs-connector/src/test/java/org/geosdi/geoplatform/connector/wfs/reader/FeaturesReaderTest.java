/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.wfs.reader;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.geosdi.geoplatform.connector.jaxb.GPConnectorJAXBContext;
import org.geosdi.geoplatform.connector.jaxb.JAXBContextConnectorRepository;
import org.geosdi.geoplatform.connector.jaxb.WFSConnectorJAXBContext;
import org.geosdi.geoplatform.stax.reader.AbstractStaxStreamReader;
import org.geosdi.geoplatform.xml.gml.v311.MultiSurfaceType;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class FeaturesReaderTest {
    
    static {
        wfsContext = JAXBContextConnectorRepository.getProvider(
                WFSConnectorJAXBContext.WFS_CONTEXT_KEY);
    }
    //
    private static final GPConnectorJAXBContext wfsContext;
    //
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private FeatureStaxReader featureReader = new FeatureStaxReader();
    
    @Test
    public void readGetFeature() throws IOException, XMLStreamException {
        String pathFile = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/getFeature.xml";
        
        StringBuilder read = featureReader.read(new File(pathFile));
        
        logger.info("Result GetFeature from File @@@@@@@@@@@@@@@@@@@@ {}",
                read.toString());
    }
    
    @Test
    public void readAllFeatures() throws IOException, XMLStreamException {
        String pathFile = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/states-getFeature-all.xml";
        
        StringBuilder read = featureReader.read(new File(pathFile));
        
        logger.info("Result GetAllFeature from File @@@@@@@@@@@@@@@@@@@@ {}",
                read.toString());
    }
    
    class FeatureStaxReader extends AbstractStaxStreamReader<StringBuilder> {
        
        private StringBuilder builder = new StringBuilder();
        
        @Override
        public StringBuilder read(Object o) throws XMLStreamException, IOException {
            super.acquireReader(o);
            
            String featureID = null;
            
            while (reader.hasNext()) {
                
                int evenType = reader.getEventType();
                
                if (evenType == XMLEvent.START_ELEMENT) {
                    if ("wfs".equals(reader.getPrefix()) && "FeatureCollection".equals(
                            reader.getLocalName())) {
                        String numberOfFeatures = reader.getAttributeValue(null,
                                "numberOfFeatures");
                        String timeStamp = reader.getAttributeValue(null,
                                "timeStamp");
                        logger.info("\n@@@@@@@@@@@ {} - {}", numberOfFeatures,
                                timeStamp);
                    }
                    
                    if ("topp".equals(reader.getPrefix()) && "states".equals(
                            reader.getLocalName())) {
                        featureID = reader.getAttributeValue(
                                "http://www.opengis.net/gml", "id");
                        logger.info("\n@@@@@@@@@@@ {}", featureID);
                    }
                    
                    if ("topp".equals(reader.getPrefix()) && "the_geom".equals(
                            reader.getLocalName())) {
                        readGeometry();
                    }
                    
                    logger.info("Event Type @@@@@@@@@@@@@@@@@@ " + reader.getLocalName());
                }

//                if (evenType == XMLEvent.CHARACTERS) {
//                    builder.append(reader.getText());
//                }

                reader.next();
            }
            
            return builder;
        }
        
        void readGeometry() throws XMLStreamException {
            int eventType = reader.nextTag();
            
            if (eventType == XMLEvent.START_ELEMENT) {
                
                Unmarshaller u;
                MultiSurfaceType geometry;
                try {
                    u = wfsContext.acquireUnmarshaller();
                    geometry = ((JAXBElement<MultiSurfaceType>) u.unmarshal(
                            reader)).getValue();
                    
                    logger.info("ECCOLA @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ "
                            + geometry);
                } catch (JAXBException ex) {
                    logger.error("ERROR @@@@@@@@@@@@@@@ " + ex);
                }
                
                super.goToEndTag("the_geom");
            }
        }
    }
}
