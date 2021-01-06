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
package org.geosdi.geoplatform.services.util;

import java.util.Iterator;
import java.util.List;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.responce.GPCatalogMetadataDTO;
import org.geosdi.geoplatform.responce.GPCatalogMetadataOnlineResource;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.filter.ElementFilter;
import org.jdom.filter.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 */
@Configuration
public class GPCatalogMetadataLoader {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private static final Namespace gmdNamespace = Namespace.getNamespace("gmd", "http://www.isotc211.org/2005/gmd");
    private static final Namespace gcoNamespace = Namespace.getNamespace("gco", "http://www.isotc211.org/2005/gco");
    private static final Namespace gmlNamespace = Namespace.getNamespace("gml", "http://www.opengis.net/gml");
    private static final Namespace gtsNamespace = Namespace.getNamespace("gts", "http://www.isotc211.org/2005/gts");

    public GPCatalogMetadataDTO getGPCatalogMetadataDTO(Element rootElement) throws NumberFormatException {
        GPCatalogMetadataDTO gpCatalogMetadataDTO = new GPCatalogMetadataDTO();

        parseTitle(rootElement, gpCatalogMetadataDTO);
        parseAbstract(rootElement, gpCatalogMetadataDTO);
        parseKeywords(rootElement, gpCatalogMetadataDTO);
        parseOnlineElementsList(rootElement, gpCatalogMetadataDTO);
        parseUUID(rootElement, gpCatalogMetadataDTO);
        parseBoundingBox(rootElement, gpCatalogMetadataDTO);

        return gpCatalogMetadataDTO;
    }

    private void parseTitle(Element rootElement, GPCatalogMetadataDTO gpCatalogMetadataDTO) {
        Element titleElement = extractElementWithFilter(rootElement, "title", gmdNamespace);
        String title = extractTextFromCharacterString(titleElement);
        gpCatalogMetadataDTO.setTitle(title);
    }

    private void parseAbstract(Element rootElement, GPCatalogMetadataDTO gpCatalogMetadataDTO) {
        Element abstractElement = extractElementWithFilter(rootElement, "abstract", gmdNamespace);
        String abstractValue = extractTextFromCharacterString(abstractElement);
        gpCatalogMetadataDTO.setAbstractValue(abstractValue);
    }

    private void parseKeywords(Element rootElement, GPCatalogMetadataDTO gpCatalogMetadataDTO) {
        Element keywordsElement = extractElementWithFilter(rootElement, "MD_Keywords", gmdNamespace);
        if (keywordsElement != null) {
            List keywordsList = keywordsElement.getChildren();
            for (Object o : keywordsList) {
                Element keywordElement = (Element) o;
                String keyword = extractTextFromCharacterString(keywordElement);
                gpCatalogMetadataDTO.addKeywordValue(keyword);
            }
        }
    }

    private void parseOnlineElementsList(Element rootElement, GPCatalogMetadataDTO gpCatalogMetadataDTO) {
        Element digitalTransferOptionsElement = extractElementWithFilter(rootElement, "MD_DigitalTransferOptions", gmdNamespace);
        if (digitalTransferOptionsElement != null) {
            List onlineElementsList = digitalTransferOptionsElement.getChildren();
            for (Object o : onlineElementsList) {
                Element onlineElement = (Element) o;

                String url = parseOnlineElement(onlineElement, "CI_OnlineResource", gmdNamespace, "linkage", gmdNamespace, "URL", gmdNamespace);
                String protocol = parseOnlineElement(onlineElement, "CI_OnlineResource", gmdNamespace, "protocol", gmdNamespace, "CharacterString", gcoNamespace);
                String name = parseOnlineElement(onlineElement, "CI_OnlineResource", gmdNamespace, "name", gmdNamespace, "CharacterString", gcoNamespace);
                String description = parseOnlineElement(onlineElement, "CI_OnlineResource", gmdNamespace, "description", gmdNamespace, "CharacterString", gcoNamespace);

                GPCatalogMetadataOnlineResource gpCatalogMetadataOnlineResource = new GPCatalogMetadataOnlineResource();
                gpCatalogMetadataOnlineResource.setURL(url);
                gpCatalogMetadataOnlineResource.setProtocol(protocol);
                gpCatalogMetadataOnlineResource.setName(name);
                gpCatalogMetadataOnlineResource.setDescription(description);

                gpCatalogMetadataDTO.addOnlineResource(gpCatalogMetadataOnlineResource);
            }
        }
    }

    private String parseOnlineElement(Element referenceElement,
                                      String firstLevelElementName, Namespace firstLevelNamespace,
                                      String secondLevelElementName, Namespace secondLevelNamespace,
                                      String thirdLevelElementName, Namespace thirdLevelNamespace) {
        Element firstLevelElement = referenceElement.getChild(firstLevelElementName, firstLevelNamespace);
        if (firstLevelElement != null) {
            Element secondLevelElement = firstLevelElement.getChild(secondLevelElementName, secondLevelNamespace);

            if (secondLevelElement != null) {
                String value = secondLevelElement.getChildText(thirdLevelElementName, thirdLevelNamespace);
                return value;
            }
        }
        return "";
    }

    private void parseUUID(Element rootElement, GPCatalogMetadataDTO gpCatalogMetadataDTO) {
        Element uuidElement = extractElement(rootElement, "uuid");
        if (uuidElement != null) {
            gpCatalogMetadataDTO.setUuid(uuidElement.getText());
        }
    }

    private void parseBoundingBox(Element rootElement, GPCatalogMetadataDTO gpCatalogMetadataDTO) {
        Element geographicBoundingBoxElement = extractElementWithFilter(rootElement, "EX_GeographicBoundingBox", gmdNamespace);
        if (geographicBoundingBoxElement != null) {
            GPBBox bbox = new GPBBox();

            Element westBoundLongitudeElement = extractElement(geographicBoundingBoxElement, "westBoundLongitude", gmdNamespace);
            if (westBoundLongitudeElement != null) {
                bbox.setMinX(Double.parseDouble(westBoundLongitudeElement.getChildText("Decimal", gcoNamespace)));
            }

            Element eastBoundLongitudeElement = extractElement(geographicBoundingBoxElement, "eastBoundLongitude", gmdNamespace);
            if (eastBoundLongitudeElement != null) {
                bbox.setMaxX(Double.parseDouble(eastBoundLongitudeElement.getChildText("Decimal", gcoNamespace)));
            }

            Element southBoundLatitudeElement = extractElement(geographicBoundingBoxElement, "southBoundLatitude", gmdNamespace);
            if (southBoundLatitudeElement != null) {
                bbox.setMinY(Double.parseDouble(southBoundLatitudeElement.getChildText("Decimal", gcoNamespace)));
            }

            Element northBoundLatitudeElement = extractElement(geographicBoundingBoxElement, "northBoundLongitude", gmdNamespace);
            if (northBoundLatitudeElement != null) {
                bbox.setMaxY(Double.parseDouble(northBoundLatitudeElement.getChildText("Decimal", gcoNamespace)));
            }

            gpCatalogMetadataDTO.setBbox(bbox);
        }
    }

    private Element extractElement(Element referenceElement, String elementName) {
        Element element = (Element) (referenceElement.getChild(elementName));

        return element;
    }

    private Element extractElement(Element referenceElement, String elementName, Namespace namespace) {
        Element element = (Element) (referenceElement.getChild(elementName, namespace));

        return element;
    }

    private Element extractElementWithFilter(Element rootElement, String elementName, Namespace namespace) {
        Filter filter = new ElementFilter(elementName, namespace);
        Iterator it = rootElement.getDescendants(filter);

        return it.hasNext() ? (Element) it.next() : null;
    }

    private String extractTextFromCharacterString(Element element) {
        if (element != null) {
            return element.getChildText("CharacterString", gcoNamespace);
        }
        return "";
    }
}
