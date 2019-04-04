package org.geosdi.geoplatform.support.wfs.feature.reader.geojson;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.connector.wfs.response.collection.FeatureAttributesMap;
import org.geosdi.geoplatform.gml.api.AbstractGeometry;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.geosdi.geoplatform.support.wfs.feature.reader.WFSBaseGetFeatureStaxReader;
import org.geosdi.geoplatform.xml.gml.v311.AbstractGeometryType;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSGetFeatureGeoJsonStaxReader extends WFSBaseGetFeatureStaxReader<Feature, GeoJsonObject, FeatureCollection> {

    /**
     * @param theLayerSchema
     */
    public WFSGetFeatureGeoJsonStaxReader(@Nonnull(when = NEVER) LayerSchemaDTO theLayerSchema) {
        super(theLayerSchema);
    }

    /**
     * @param reader
     * @param geometryName
     * @param attributeNames
     * @return {@link FeatureCollection}
     * @throws Exception
     */
    @Override
    protected FeatureCollection internalRead(XMLStreamReader reader, String geometryName, List<String> attributeNames) throws Exception {
        String typeName = layerSchema.getTypeName();
        checkArgument((typeName != null) && (typeName.contains(":")), "Type Name must not be null or must be WORKSPACE:LAYER.");
        String prefix = typeName.substring(0, typeName.indexOf(":"));
        String name = typeName.substring(typeName.indexOf(":") + 1);
        checkArgument((prefix != null) && !(prefix.trim().isEmpty()), "The Parameter Prefix must not be null or an empty string.");
        checkArgument((name != null) && !(name.trim().isEmpty()), "The Parameter Name must not be null or an empty string.");
        logger.trace("\n@@@@@@ Read feature {}:{} @@@@@@", prefix, name);
        FeatureCollection featureCollection = new FeatureCollection();
        Feature feature = null;
        try {
            while (reader.hasNext()) {
                int evenType = reader.getEventType();
                if (evenType == XMLEvent.START_ELEMENT) {
                    if (super.isTagName(prefix, name)) {
                        feature = this.readFID();
                        featureCollection.add(feature);
                    } else if (super.isTagName(prefix, geometryName)) {
                        feature.setGeometry(this.readGeometry());
                        super.goToEndTag(geometryName);
                        this.readAttributes(feature, name, attributeNames, TRUE, prefix, geometryName);
                    } else if ((attributeNames != null) && (attributeNames.contains(reader.getLocalName()))) {
                        this.readAttributes(feature, name, attributeNames, FALSE, prefix, geometryName);
                    }
                }
                reader.next();
            }
            return featureCollection;
        } finally {
            super.dispose();
        }
    }

    /**
     * @return {@link Feature}
     * @throws Exception
     */
    protected Feature readFID() throws Exception {
        String featureID = xmlStreamReader().getAttributeValue("http://www.opengis.net/gml", "id");
        logger.trace("@@@@@@@@@@@@@@@ FEATURE_ID: {} @@@", featureID);
        Feature feature = new Feature();
        feature.setId(featureID);
        return feature;
    }

    /**
     * @return {@link GeoJsonObject}
     * @throws Exception
     */
    protected GeoJsonObject readGeometry() throws Exception {
        GeoJsonObject geoJsonGeometry = null;
        int eventType = xmlStreamReader().nextTag();
        if (eventType == XMLEvent.START_ELEMENT) {
            AbstractGeometry geometry = jaxbContextBuilder.unmarshal(xmlStreamReader(), AbstractGeometryType.class);
            try {
                geoJsonGeometry = this.sextanteParser.parseGeometryAsGeoJson(geometry);
                logger.trace("@@@@@@@@@@@@@@@@@@@@@@GEO_JSON_GEOMETRY : {}\n" + geometry);
            } catch (ParserException ex) {
                ex.printStackTrace();
                logger.error("########################Parse Exception : " + ex);
            }
        }
        return geoJsonGeometry;
    }

    /**
     * @param featureName
     * @param attributeNames
     * @param nextTag
     * @return {@link FeatureAttributesMap}
     * @throws Exception
     */
    protected void readAttributes(Feature feature, String featureName, List<String> attributeNames, Boolean nextTag,
            String prefix, String geometryName) throws Exception {
        checkArgument(feature != null, "The Parameter Feature must not be null.");

        if (attributeNames == null) {
            attributeNames = new ArrayList<>();
        }
        Map<String, Object> properties = new LinkedHashMap<>();
        for (String name : attributeNames) {
            properties.put(name, null);
        }
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
                    feature.setGeometry(this.readGeometry());
                    super.goToEndTag(geometryName);
                } else {
                    String localName = xmlStreamReader().getLocalName();
                    logger.trace("################LOCAL_NAME : {}\n", localName);

                    eventType = xmlStreamReader().next();
                    if (eventType == XMLEvent.CHARACTERS) {
                        String attributeValue = xmlStreamReader().getText();
                        properties.put(localName, attributeValue);
                        logger.trace("@@@@@@@@@@@@@@@@@@Attribute : LocalName :'{}': AttributeValue : {} @@@\n",
                                localName, attributeValue);
                        super.goToEndTag(localName);
                    }
                }
                eventType = xmlStreamReader().nextTag();
            }
        }
        feature.setProperties(properties);
    }
}