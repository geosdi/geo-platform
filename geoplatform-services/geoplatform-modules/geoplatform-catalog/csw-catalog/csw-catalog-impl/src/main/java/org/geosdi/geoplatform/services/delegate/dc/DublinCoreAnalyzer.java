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
package org.geosdi.geoplatform.services.delegate.dc;

import org.geosdi.geoplatform.gui.responce.DCEsriScheme;
import org.geosdi.geoplatform.gui.responce.OnlineResourceProtocolType;
import org.geosdi.geoplatform.gui.responce.URIDTO;
import org.geosdi.geoplatform.responce.FullRecordDTO;
import org.geosdi.geoplatform.services.BindingUtility;
import org.geosdi.geoplatform.xml.csw.v202.RecordType;
import org.geosdi.geoplatform.xml.csw.v202.dc.elements.SimpleLiteral;
import org.geosdi.geoplatform.xml.csw.v202.dc.terms.URI;
import org.geosdi.geoplatform.xml.ows.v100.BoundingBoxType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBElement;
import java.util.List;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface DublinCoreAnalyzer {

    /**
     *
     * @param <F>
     * @param record
     * @param fullRecord
     */
    <F extends FullRecordDTO> void analyzeRecord(RecordType record, F fullRecord);

    class GPDublinCoreAnalyzer implements DublinCoreAnalyzer {

        private static final Logger logger = LoggerFactory.getLogger(GPDublinCoreAnalyzer.class);

        @Override
        public void analyzeRecord(RecordType record, FullRecordDTO dto) {
            if (record.isSetBoundingBox()) {
                BoundingBoxType bboxType = record.getBoundingBox().get(0).getValue();
                dto.setBBox(BindingUtility.convertBBoxTypeToBBox(bboxType));
                dto.setCrs(BindingUtility.convertEncodedCRS(bboxType.getCrs()));
            }

            for (JAXBElement<? extends SimpleLiteral> element : record.getDCElement()) {
                logger.trace("###########LOCAL_PART : {} - PREFIX: {} - SCHEME : {} "
                                + "- ELEMENTS : {}", element.getName().getLocalPart(),
                        element.getName().getNamespaceURI(), element.getValue()
                                .getScheme(), element.getValue().getContent());

                String localPartElement = element.getName().getLocalPart();
                List<String> contentElement = element.getValue().getContent();
                SimpleLiteral value = element.getValue();

                logger.trace("\n\n###################LOCAL_PART_NAME : {} "
                                + "- CONTENT_ELEMENT : {}\n\n", localPartElement,
                        contentElement);

                if ("identifier".equals(localPartElement)) {
                    dto.setIdentifier(
                            BindingUtility.convertStringListToString(
                                    contentElement));
                }

                if ("title".equals(localPartElement)) {
                    dto.setTitle(
                            BindingUtility.convertStringListToString(
                                    contentElement));
                }

                if ("type".equals(localPartElement)) {
                    dto.setType(
                            BindingUtility.convertStringListToString(
                                    contentElement));
                    if (value.isSetScheme()) {
                        String scheme = value.getScheme();
                        DCEsriScheme esriScheme = DCEsriScheme.fromValue(scheme);
                        if ((esriScheme != null) && (esriScheme == DCEsriScheme.CONTENT_TYPE)) {
                            for (String content : contentElement) {
                                if (OnlineResourceProtocolType.isForWMSGetCapabilities(content)) {
                                    URIDTO uriDTO = new URIDTO();
                                    uriDTO.setProtocol(content);
                                    dto.addUri(uriDTO);
                                    break;
                                }
                            }
                        }
                    }
                }

                if ("abstract".equals(localPartElement)) {
                    dto.setAbstractText(
                            BindingUtility.convertStringListToString(
                                    contentElement));
                }

                if ("subject".equals(localPartElement)) {
                    dto.addSubject(
                            BindingUtility.convertStringListToString(
                                    contentElement));
                }

                if ("references".equals(localPartElement)) {
                    if (value.isSetScheme()) {
                        String scheme = value.getScheme();
                        DCEsriScheme esriScheme = DCEsriScheme.fromValue(scheme);
                        if ((esriScheme != null) && ((esriScheme == DCEsriScheme.RESOURCE_URL)
                                || (esriScheme == DCEsriScheme.OGC_WMS))) {
                            for (String content : contentElement) {
                                if (content.contains("?")) {
                                    int i = content.indexOf("?");
                                    URIDTO uriDTO = dto.getURIDtoForWMSGetCapabilities();
                                    if (uriDTO != null) {
                                        uriDTO.setServiceURL(content.substring(0, i + 1));
                                    }
                                }
                            }
                        }
                    }
                }

                if ("URI".equals(localPartElement)) {
                    URI uri = (URI) element.getValue();
                    String protocol = uri.getProtocol();

                    URIDTO uriDTO = new URIDTO();
                    uriDTO.setProtocol(protocol);
                    uriDTO.setName(uri.getName());
                    uriDTO.setDescription(uri.getDescription());
                    uriDTO.setServiceURL(uri.getServiceURL());
                    dto.addUri(uriDTO);
                }
            }

            logger.debug("#####################{} found : {} : {}\n\n",
                    getClass().getSimpleName(), dto);
        }

    }

}
