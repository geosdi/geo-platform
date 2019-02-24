/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2019 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.support.wfs.feature.reader;

import org.geosdi.geoplatform.connector.wfs.response.FeatureCollectionDTO;
import org.geosdi.geoplatform.connector.wfs.response.FeatureDTO;
import org.geosdi.geoplatform.connector.wfs.response.GeometryAttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.connector.wfs.response.collection.FeatureAttributesMap;
import org.geosdi.geoplatform.gml.api.AbstractGeometry;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.sextante.GMLBaseSextanteParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.stax.reader.AbstractStaxStreamReader;
import org.geosdi.geoplatform.xml.gml.v311.AbstractGeometryType;
import org.locationtech.jts.io.WKTWriter;

import javax.xml.bind.DatatypeConverter;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Giuseppe La Scaleia - <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @TODO Try to have this class @ThreadSafe
 */
public class WFSGetFeatureStaxReader extends AbstractStaxStreamReader<FeatureCollectionDTO> {

    static {
        jaxbContextBuilder = GPJAXBContextBuilder.newInstance();
    }

    //
    private static final GPJAXBContextBuilder jaxbContextBuilder;
    //
    private final LayerSchemaDTO layerSchema;
    //
    private final GMLBaseSextanteParser sextanteParser = GMLBaseParametersRepo.getDefaultSextanteParser();

    /**
     * @param layerSchema
     */
    public WFSGetFeatureStaxReader(LayerSchemaDTO layerSchema) {
        checkArgument(layerSchema != null, "The LayerSchema must not be null.");
        this.layerSchema = layerSchema;
    }

    /**
     * @param o
     * @return {@link FeatureCollectionDTO}
     * @throws Exception
     */
    @Override
    public FeatureCollectionDTO read(Object o) throws Exception {
        XMLStreamReader reader = super.acquireReader(o);
        String typeName = layerSchema.getTypeName();
        if ((typeName == null) || !(typeName.contains(":"))) {
            throw new IllegalStateException("Type Name must not be null or must be WORKSPACE:LAYER.");
        }
        String prefix = typeName.substring(0, typeName.indexOf(":"));
        String name = typeName.substring(typeName.indexOf(":") + 1);
        logger.trace("\n@@@@@@ Read feature {}:{} @@@@@@", prefix, name);

        GeometryAttributeDTO geometryAtt = layerSchema.getGeometry();
        String geometryName = geometryAtt.getName();
        if ((geometryAtt == null) || (geometryName == null) || (geometryName.isEmpty())) {
            throw new IllegalStateException("The Geometry Attribute must not be null or " +
                    "Geometry Name must not be null or an Empty String.");
        }
        List<String> attributeNames = layerSchema.getAttributeNames();
        FeatureCollectionDTO fc = new FeatureCollectionDTO();
        FeatureDTO feature = null;
        while (reader.hasNext()) {
            int evenType = reader.getEventType();
            if (evenType == XMLEvent.START_ELEMENT) {
                if (super.isTagName("wfs", "FeatureCollection")) {
                    this.readInfo(fc);
                } else if (super.isTagName(prefix, name)) {
                    feature = this.readFID();
                    fc.addFeature(feature);
                } else if (super.isTagName(prefix, geometryName)) {
                    String geometryWKT = this.readGeometry();
                    feature.setGeometry(geometryWKT);

                    super.goToEndTag(geometryName);
                    this.readAttributes(feature, name, attributeNames, TRUE, prefix, geometryName);
                } else if ((attributeNames != null) && (attributeNames.contains(reader.getLocalName()))) {
                    this.readAttributes(feature, name, attributeNames, FALSE, prefix, geometryName);
                }
            }
            reader.next();
        }
        super.dispose();
        return fc;
    }

    /**
     * @param fc
     * @return {@link FeatureCollectionDTO}
     * @throws Exception
     */
    private FeatureCollectionDTO readInfo(FeatureCollectionDTO fc) throws Exception {
        String numberOfFeatures = xmlStreamReader().getAttributeValue(null, "numberOfFeatures");
        fc.setNumberOfFeatures(Integer.parseInt(numberOfFeatures));
        String timeStamp = xmlStreamReader().getAttributeValue(null, "timeStamp");
        fc.setTimeStamp(DatatypeConverter.parseDate(timeStamp).getTime());
        logger.trace("@@@@@@@@@@@@@@@@@@ Read info @@@\nfeature: {} - time: {}\n", numberOfFeatures, timeStamp);
        return fc;
    }

    /**
     * @return {@link FeatureDTO}
     * @throws Exception
     */
    private FeatureDTO readFID() throws Exception {
        String featureID = xmlStreamReader().getAttributeValue("http://www.opengis.net/gml", "id");
        logger.trace("@@@@@@@@@@@@@@@ FEATURE_ID: {} @@@", featureID);
        return new FeatureDTO(featureID);
    }

    /**
     * @return {@link String}
     * @throws Exception
     */
    private String readGeometry() throws Exception {
        String geometryWKT = null;

        int eventType = xmlStreamReader().nextTag();
        if (eventType == XMLEvent.START_ELEMENT) {
            AbstractGeometry geometry = jaxbContextBuilder.unmarshal(xmlStreamReader(), AbstractGeometryType.class);
            /**@TODO The Geometry will always must 2d Dimension??
             * otherwise the code must be :
             * <code>WKTWriter wktWriter = new WKTWriter(geometry.isSetSrsDimension() ? geometry.getSrsDimension().intValue() : 2)</code>
             **/
            WKTWriter wktWriter = new WKTWriter(2);
            logger.trace("@@@@@@@@@@@@@@Geometry : {}\n", geometry);
            wktWriter.setFormatted(TRUE);
            try {
                geometryWKT = wktWriter.writeFormatted(this.sextanteParser.parseGeometry(geometry));
                logger.trace("@@@@@@@@@@@@@@@@@@@@@@WKT_GEOMETRY : {}\n" + geometryWKT);
            } catch (ParserException ex) {
                ex.printStackTrace();
                logger.error("########################Parse Exception : " + ex);
            }
        }
        return geometryWKT;
    }

    /**
     * @param featureName
     * @param attributeNames
     * @param nextTag
     * @return {@link FeatureAttributesMap}
     * @throws Exception
     */
    private void readAttributes(FeatureDTO feature, String featureName, List<String> attributeNames, Boolean nextTag,
            String prefix, String geometryName) throws Exception {
        checkArgument(feature != null, "The Parameter Feature must not be null.");

        if (attributeNames == null) {
            attributeNames = new ArrayList<>();
        }
        Map<String, String> map = new HashMap<>();
        for (String name : attributeNames) {
            map.put(name, null);
        }
        FeatureAttributesMap fMap = new FeatureAttributesMap();
        fMap.setAttributesMap(map);

        int eventType = ((nextTag) ? xmlStreamReader().nextTag() : xmlStreamReader().getEventType());
        while (xmlStreamReader().hasNext()) {
            if (eventType == XMLEvent.END_ELEMENT) {
                String localName = xmlStreamReader().getLocalName();
                if (localName.equals(featureName)) {
                    break;
                }
            }

            if (eventType == XMLEvent.START_ELEMENT) {
                if (super.isTagName(prefix, geometryName)) {
                    String geometryWKT = this.readGeometry();
                    feature.setGeometry(geometryWKT);
                    super.goToEndTag(geometryName);
                } else {
                    String localName = xmlStreamReader().getLocalName();
                    logger.trace("################LOCAL_NAME : {}\n", localName);

                    eventType = xmlStreamReader().next();
                    if (eventType == XMLEvent.CHARACTERS) {
                        String attributeValue = xmlStreamReader().getText();
                        map.put(localName, attributeValue);
                        logger.trace("@@@@@@@@@@@@@@@@@@Attribute : LocalName :'{}': AttributeValue : {} @@@\n",
                                localName, attributeValue);
                        super.goToEndTag(localName);
                    }
                }
                eventType = xmlStreamReader().nextTag();
            }
        }
        feature.setAttributes(fMap);
    }
}
