package org.geosdi.geoplatform.connector.reader.stax;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geosdi.geoplatform.connector.parser.GPWMSGml2GeoJsonParser;
import org.geosdi.geoplatform.connector.parser.WMSGml2GeoJsonParser;
import org.geosdi.geoplatform.connector.reader.featuretype.GPWMSFeatureType;
import org.geosdi.geoplatform.connector.reader.featuretype.GPWMSFeatureTypeReader;
import org.geosdi.geoplatform.connector.reader.featuretype.WMSFeatureType;
import org.geosdi.geoplatform.stax.reader.AbstractStaxStreamReader;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.function.Function.identity;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class WMSGetFeatureInfoStaxReader extends AbstractStaxStreamReader<FeatureCollection> {

    protected static final String WFS_PREFIX = "wfs";
    protected static final String FEATURE_COLLECTION_LOCAL_NAME = "FeatureCollection";
    protected static final String GML_PREFIX = "gml";
    protected static final String FEATURE_MEMBER_LOCAL_NAME = "featureMember";
    private static final String FID_LOCAL_NAME = "fid";
    private static final String SCHEMA_LOCATION_NAMESPACE_URI = "http://www.w3.org/2001/XMLSchema-instance";
    private static final String SCHEMA_LOCATION_KEY = "schemaLocation";
    private static final String TYPE_NAME_KEY = "typeName=";
    private static final String TYPES_NAME_SEPARATOR = ",";
    private static final String TYPE_NAME_SEPARATOR = ":";
    private static final GPWMSGml2GeoJsonParser GML2_GEO_JSON_PARSER = new WMSGml2GeoJsonParser();
    private static final GPWMSFeatureTypeReader featureTypeReader = new GPWMSFeatureTypeReader() {

        /**
         *
         * @param value
         * @return {@link Map<String, GPWMSFeatureType>}
         * @throws Exception
         */
        @Override
        public Map<String, GPWMSFeatureType> read(@Nonnull(when = NEVER) String value) throws Exception {
            checkArgument((value != null) && !(value.trim().isEmpty()), "The Parameter value must not be null or an empty string.");
            return compile(TYPES_NAME_SEPARATOR).splitAsStream(value)
                    .map(v -> v.split(TYPE_NAME_SEPARATOR))
                    .filter(values -> ((values[0] != null) && !(values[0].trim().isEmpty()) && (values[1] != null) && !(values[1].trim().isEmpty())))
                    .map(values -> new WMSFeatureType(values[0], values[1]))
                    .collect(toMap(k -> k.getName(), identity(), (oldVal, newVal) -> oldVal, LinkedHashMap::new));
        }
    };
    //
    private final ThreadLocal<Map<String, GPWMSFeatureType>> typeNames = withInitial(() -> null);
    private final ThreadLocal<String> previousGeometry = withInitial(() -> null);

    /**
     * @throws Exception
     */
    protected void loadTypeNames() throws Exception {
        String schemaLocation = xmlStreamReader().getAttributeValue(SCHEMA_LOCATION_NAMESPACE_URI, SCHEMA_LOCATION_KEY);
        if ((schemaLocation != null) && !(schemaLocation.trim().isEmpty())) {
            schemaLocation = URLDecoder.decode(schemaLocation, UTF_8.name());
            int index = schemaLocation.indexOf(TYPE_NAME_KEY);
            if (index != -1) {
                String typeName = schemaLocation.substring(index + TYPE_NAME_KEY.length());
                index = typeName.indexOf(" ");
                if (index != -1) {
                    typeName = typeName.substring(0, index);
                    this.typeNames.set(featureTypeReader.read(typeName));
                } else {
                    this.typeNames.set(featureTypeReader.read(typeName));
                }
                logger.debug("########################TYPES_NAME : {}", this.typeNames.get());
            } else {
                logger.debug("#####################TYPE_NAME NOT FOUND.\n");
                this.typeNames.set(new LinkedHashMap<>());
            }
        } else {
            logger.debug("#######################SCHEMA_LOCATION IS NULL.\n");
        }
    }

    /**
     * @throws Exception
     */
    protected void readFeatures(Feature feature) throws Exception {
        int eventType = xmlStreamReader().nextTag();
        if (eventType == XMLEvent.START_ELEMENT) {
            logger.debug("################TRYING TO READ XML.");
            Map<String, GPWMSFeatureType> featureTypes = this.typeNames.get();
            checkArgument((featureTypes != null), "Impossible Read XML featureTypes is null.");
            readFeatureID(featureTypes, feature);
            super.goToEndTag(FEATURE_MEMBER_LOCAL_NAME);
        }
    }

    /**
     * Close both {@link javax.xml.stream.XMLStreamReader} xmlStreamReader and {@link java.io.InputStream} stream
     * used to build the xmlStreamReader
     *
     * @throws XMLStreamException
     * @throws IOException
     */
    @Override
    protected void reset() throws XMLStreamException, IOException {
        super.reset();
        this.previousGeometry.set(null);
    }

    /**
     * @throws Exception
     */
    void readFeatureID(Map<String, GPWMSFeatureType> featureTypes, Feature feature) throws Exception {
        if (super.isTagName(GML_PREFIX, FEATURE_MEMBER_LOCAL_NAME)) {
            xmlStreamReader().nextTag();
        }
        String prefix = xmlStreamReader().getPrefix();
        String name = xmlStreamReader().getLocalName();
        logger.trace("##############################PREFIX : {} - NAME : {}\n", prefix, name);
        GPWMSFeatureType featureType = featureTypes.computeIfAbsent(name, value -> new WMSFeatureType(prefix, name));
        if ((featureType != null) && (super.isTagName(prefix, name))) {
            String featureID = xmlStreamReader().getAttributeValue(null, FID_LOCAL_NAME);
            feature.setId((featureID != null) && !(featureID.trim().isEmpty()) ? featureID : of(prefix, name).collect(joining(":")));
            logger.trace("#####################FEATURE_ID : {}", feature.getId());
            readInternal(featureType, feature);
        }
    }

    /**
     * @param featureType
     * @throws Exception
     */
    void readInternal(GPWMSFeatureType featureType, Feature feature) throws Exception {
        logger.trace("#######################TRY TO READ ATTRIBUTES OF : {}\n", featureType.getName());
        Map<String, Object> featureProperties = new LinkedHashMap<>();
        int eventType = xmlStreamReader().nextTag();
        while (xmlStreamReader().hasNext()) {
            if (eventType == XMLEvent.END_ELEMENT) {
                String localName = xmlStreamReader().getLocalName();
                if (localName.equals(featureType.getName())) {
                    break;
                }
            } else if (eventType == XMLEvent.START_ELEMENT) {
                String localName = xmlStreamReader().getLocalName();
                if ((featureType != null) && (super.isTagPrefix(featureType.getPrefix()))) {
                    eventType = xmlStreamReader().next();
                    if (eventType == XMLEvent.CHARACTERS) {
                        this.previousGeometry.set(localName);
                        String attributeValue = xmlStreamReader().getText();
                        featureProperties.put(localName, attributeValue);
                        logger.trace("##########################ATTRIBUTE_NAME : {} - ATTRIBUTE_VALUE : {}\n", localName, attributeValue);
                    } else if (eventType == XMLEvent.END_ELEMENT) {
                        featureProperties.put(localName, null);
                    } else if (super.isTagPrefix(GML_PREFIX)) {
                        this.readGeometry(feature, featureProperties);
                    }
                } else if (super.isTagPrefix(GML_PREFIX)) {
                    this.readGeometry(feature, featureProperties);
                }
            }
            eventType = xmlStreamReader().next();
        }
        feature.setProperties(featureProperties);
    }

    /**
     * @param feature
     * @throws Exception
     */
    void readGeometry(Feature feature, Map<String, Object> featureProperties) throws Exception {
        String localName = xmlStreamReader().getLocalName();
        logger.trace("@@@@@@@@@@@@@@@@@@@@FOUND GEOMETRY : {} \n", localName);
        feature.setGeometry(GML2_GEO_JSON_PARSER.parse(xmlStreamReader()));
        if (featureProperties.containsKey(this.previousGeometry.get())) {
            featureProperties.remove(this.previousGeometry.get());
        }
    }
}