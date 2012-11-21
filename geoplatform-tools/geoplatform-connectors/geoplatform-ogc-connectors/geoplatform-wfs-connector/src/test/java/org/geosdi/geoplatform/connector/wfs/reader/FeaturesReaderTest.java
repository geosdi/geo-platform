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
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.geosdi.geoplatform.stax.reader.AbstractStaxStreamReader;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class FeaturesReaderTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private FeatureStaxReader featureReader = new FeatureStaxReader();

    @Test
    public void readGetFeature() throws IOException, XMLStreamException {
        String pathFile = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/getFeature.xml";

        StringBuilder read = featureReader.read(new File(pathFile));

        logger.trace("Result GetFeature from File @@@@@@@@@@@@@@@@@@@@ {}",
                     read.toString());
    }

    class FeatureStaxReader extends AbstractStaxStreamReader<StringBuilder> {

        @Override
        public StringBuilder read(Object o) throws XMLStreamException, IOException {
            super.acquireReader(o);

            StringBuilder builder = new StringBuilder();
            StringBuilder test = new StringBuilder();
            boolean catching = false;
            Location start;
            Location end;
            int count = 1;

            while (reader.hasNext()) {

                int evenType = reader.getEventType();
//                logger.info("\n\n\n--- COUNT: {} --- EVENT: {}", count++, evenType);


                if (catching) {
                    if (reader.hasText()) {
                        test.append(reader.getText());
                    }
                }

                if (evenType == XMLEvent.START_ELEMENT) {
                    if ("wfs".equals(reader.getPrefix()) && "FeatureCollection".equals(reader.getLocalName())) {
                        String numberOfFeatures = reader.getAttributeValue(null, "numberOfFeatures");
                        String timeStamp = reader.getAttributeValue(null, "timeStamp");
                        logger.info("\n@@@@@@@@@@@ {} - {}", numberOfFeatures, timeStamp);
//                        continue;
                    }

                    if ("topp".equals(reader.getPrefix()) && "states".equals(reader.getLocalName())) {
//                        logger.info("\n*** {}", reader.getAttributeCount());
////                    logger.info("\n*** {}", reader.getCharacterEncodingScheme());
////                    logger.info("\n*** {}", reader.getEncoding());
//                        logger.info("\n*** {}", reader.getLocalName());
////                    logger.info("\n*** {}", reader.getLocation());
//                        logger.info("\n*** {}", reader.getName());
////                    logger.info("\n*** {}", reader.getNamespaceContext());
////                    logger.info("\n*** {}", reader.getNamespaceCount());
//                        logger.info("\n*** {}", reader.getNamespaceURI());
//                        logger.info("\n*** {}", reader.getPrefix());
////                    logger.info("\n*** {}", reader.getVersion());
//
//                        logger.info("\n+++ {}", reader.isStartElement());
//                        logger.info("\n+++ {}", reader.hasName());
//                        logger.info("\n+++ {}", reader.hasText());

                        String featureID = reader.getAttributeValue("http://www.opengis.net/gml", "id");
                        logger.info("\n@@@@@@@@@@@ {}", featureID);
//                        continue;
                    }

                    if ("topp".equals(reader.getPrefix()) && "the_geom".equals(reader.getLocalName())) {
                        catching = true;
                        start = reader.getLocation();
                        logger.info("################# TRUE #################\n{}", start);
//                        continue;
                    }
                }

                if (evenType == XMLEvent.END_ELEMENT) {
                    if ("topp".equals(reader.getPrefix()) && "the_geom".equals(reader.getLocalName())) {
                        catching = false;
                        end = reader.getLocation();
                        logger.info("################# FALSE #################\n{}", end);
//                        continue;
                    }
                }

                if (evenType == XMLEvent.CHARACTERS) {

//                    logger.info("\n*** {}", reader.getLocation());
//                    logger.info("\n*** {}", reader.getNamespaceURI());
//                    logger.info("\n*** {}", reader.getPrefix());
//                    logger.info("\n*** {}", reader.getText());
//                    logger.info("\n*** {}", reader.getTextCharacters());
//                    logger.info("\n*** {}", reader.getTextLength());
//                    logger.info("\n*** {}", reader.getTextStart());
//                    
//                    logger.info("\n+++ {}", reader.isStartElement());
//                    logger.info("\n+++ {}", reader.hasName());
//                    logger.info("\n+++ {}", reader.hasText());

                    builder.append(reader.getText());
                }

                reader.next();
            }


//            logger.info("\n\n\n{}\n\n\n", o.getClass());
//            File file;
//            if (o instanceof File) {
//                file = (File) o;
//                read(file.getPath());
////                logger.info("wuepa {}", toString.length());
//            }

//            logger.info("\n\n\n\n######################\n{}", test.toString());

            return builder;
        }
    }

    void read(String fileName) throws IOException {
        logger.info("Reading from file.");
        StringBuilder text = new StringBuilder();
        String NL = System.getProperty("line.separator");
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        try {
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine()).append(NL);
            }
        } finally {
            scanner.close();
        }
        logger.info("Text read in: " + text);
    }
}
