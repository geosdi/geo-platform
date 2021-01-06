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
package org.geosdi.geoplatform.connector.wfs;

import org.geosdi.geoplatform.connector.server.request.WFSDescribeFeatureTypeRequest;
import org.geosdi.geoplatform.xml.xsd.v2001.*;
import org.junit.Test;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSDescribeFeatureTypeTest extends WFSTestConfigurator {

    @Test
    public void testV110() throws Exception {
        WFSDescribeFeatureTypeRequest<Schema> request = super.serverConnector.createDescribeFeatureTypeRequest();
        QName name = new QName("sf:streams");
        request.setTypeName(Arrays.asList(name));
        Schema s = request.getResponse();
        logger.info("TARGET NAMESPACE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", s.getTargetNamespace());
        for (OpenAttrs o : s.getSimpleTypeOrComplexTypeOrGroup()) {
            if (o instanceof TopLevelElement) {
                logger.info("TopLevelElement @@@@@@@@@@@@@@@@ {}\n", o);
            }

            if (o instanceof TopLevelComplexType) {
                List<Object> particles = ((TopLevelComplexType) o).getComplexContent().getExtension().getSequence().getParticle();
                for (Object p : particles) {

                    LocalElement l = ((JAXBElement<LocalElement>) p).getValue();
                    logger.info("TopLevelComplexType @@@@@@@@@@@@@@@@ {}\n", l.getType().getLocalPart());
                }
            }
        }

        String wfsDescribeFeatureFile = "target/wfsDescribeFeaturev110.xml";
        try (FileOutputStream fos = new FileOutputStream(wfsDescribeFeatureFile)) {
            request.getMarshaller().marshal(s, fos);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testSecureV110() throws Exception {
        WFSDescribeFeatureTypeRequest<Schema> request = super.secureServerConnector.createDescribeFeatureTypeRequest();
        QName name = new QName("tiger:poi");
        request.setTypeName(Arrays.asList(name));
        Schema s = request.getResponse();
        logger.info("TARGET SECURE NAMESPACE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {}", s.getTargetNamespace());
        for (OpenAttrs o : s.getSimpleTypeOrComplexTypeOrGroup()) {
            if (o instanceof TopLevelElement) {
                logger.info("SECURE TopLevelElement @@@@@@@@@@@@@@@@ {}\n\n", o);
            }

            if (o instanceof TopLevelComplexType) {
                List<Object> particles = ((TopLevelComplexType) o).getComplexContent().getExtension().getSequence().getParticle();
                for (Object p : particles) {

                    LocalElement l = ((JAXBElement<LocalElement>) p).getValue();
                    logger.info("SECURE TopLevelComplexType @@@@@@@@@@@@@@@@ {}", l.getType().getLocalPart());
                }
            }
        }

        String wfsDescribeFeatureFile = "target/wfsSecureDescribeFeaturev110.xml";
        try (FileOutputStream fos = new FileOutputStream(wfsDescribeFeatureFile)) {
            request.getMarshaller().marshal(s, fos);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
