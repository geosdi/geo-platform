package org.geosdi.geoplatform.connector.reader.stax;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.connector.reader.featuretype.GPFeatureType;
import org.geosdi.geoplatform.connector.reader.featuretype.GPFeatureTypeReader;
import org.geosdi.geoplatform.connector.reader.featuretype.IGPFeatureType;
import org.geosdi.geoplatform.stax.reader.AbstractStaxStreamReader;
import org.geosdi.geoplatform.stax.reader.builder.GPXmlStreamReaderBuilder;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

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
public abstract class GPGetFeatureGeoJsonStaxReader extends AbstractStaxStreamReader<FeatureCollection> {

    protected static final String WFS_PREFIX = "wfs";
    protected static final String FEATURE_COLLECTION_LOCAL_NAME = "FeatureCollection";
    protected static final String SCHEMA_LOCATION_NAMESPACE_URI = "http://www.w3.org/2001/XMLSchema-instance";
    protected static final String SCHEMA_LOCATION_KEY = "schemaLocation";
    protected static final String GML_PREFIX = "gml";
    protected static final String TYPE_NAME_KEY = "typeName=";
    protected static final String TYPES_NAME_SEPARATOR = ",";
    protected static final String TYPE_NAME_SEPARATOR = ":";
    protected static final String FEATURE_NAME_KEY = "FEATURE_NAME";
    private static final String BOUNDING_BY_PREFIX = "boundedBy";
    private static final GPFeatureTypeReader featureTypeReader = new GPFeatureTypeReader() {

        /**
         *
         * @param value
         * @return {@link Map<String, IGPFeatureType>}
         * @throws Exception
         */
        @Override
        public Map<String, IGPFeatureType> read(@Nonnull(when = NEVER) String value) throws Exception {
            checkArgument((value != null) && !(value.trim().isEmpty()), "The Parameter value must not be null or an empty string.");
            return compile(TYPES_NAME_SEPARATOR).splitAsStream(value)
                    .map(v -> v.split(TYPE_NAME_SEPARATOR))
                    .filter(Objects::nonNull)
                    .filter(values -> values.length == 2)
                    .filter(values -> ((values[0] != null) && !(values[0].trim().isEmpty()) && (values[1] != null) && !(values[1].trim().isEmpty())))
                    .map(values -> new GPFeatureType(values[0], values[1]))
                    .collect(toMap(k -> k.getName(), identity(), (oldVal, newVal) -> oldVal, LinkedHashMap::new));
        }
    };
    //
    protected final ThreadLocal<Map<String, IGPFeatureType>> typeNames = withInitial(() -> null);
    private final ThreadLocal<String> previousGeometry = withInitial(() -> null);
    private final String fidLocalName;

    /**
     * @param theXmlStreamBuilder
     * @param theFidLocalName
     */
    protected GPGetFeatureGeoJsonStaxReader(@Nonnull(when = NEVER) GPXmlStreamReaderBuilder theXmlStreamBuilder, @Nonnull(when = NEVER) String theFidLocalName) {
        super(theXmlStreamBuilder);
        checkArgument((theFidLocalName != null) && !(theFidLocalName.trim().isEmpty()), "The Parameter fidLocalName must not be null or an empty string.");
        this.fidLocalName = theFidLocalName;
    }

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
                this.typeNames.set((index != -1) ? featureTypeReader.read(typeName.substring(0, index)) : featureTypeReader.read(typeName));
                logger.trace("########################TYPES_NAME : {}", this.typeNames.get());
            } else {
                logger.debug("#####################TYPE_NAME NOT FOUND.\n");
                this.typeNames.set(new LinkedHashMap<>());
            }
        } else {
            logger.trace("#######################SCHEMA_LOCATION IS NULL.\n");
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
    protected void readFeatures(Feature feature) throws Exception {
        int eventType = xmlStreamReader().nextTag();
        if (eventType == XMLEvent.START_ELEMENT) {
            logger.debug("################TRYING TO READ XML.");
            Map<String, IGPFeatureType> featureTypes = this.typeNames.get();
            checkArgument((featureTypes != null), "Impossible Read XML featureTypes is null.");
            this.readFeatureID(featureTypes, feature);
        }
    }

    /**
     * @throws Exception
     */
    protected void readFeatureID(Map<String, IGPFeatureType> featureTypes, Feature feature) throws Exception {
        String prefix = xmlStreamReader().getPrefix();
        String name = xmlStreamReader().getLocalName();
        logger.trace("##############################PREFIX : {} - NAME : {}\n", prefix, name);
        IGPFeatureType featureType = featureTypes.computeIfAbsent(name, value -> new GPFeatureType(prefix, name));
        if ((featureType != null) && (super.isTagName(prefix, name))) {
            String featureID = xmlStreamReader().getAttributeValue(null, this.fidLocalName);
            feature.setId((featureID != null) && !(featureID.trim().isEmpty()) ? featureID : of(prefix, name).collect(joining(":")));
            logger.trace("#####################FEATURE_ID : {}", feature.getId());
            readInternal(featureType, feature);
        }
    }

    /**
     * @param featureType
     * @throws Exception
     */
    final void readInternal(IGPFeatureType featureType, Feature feature) throws Exception {
        logger.trace("#######################TRY TO READ ATTRIBUTES OF : {}\n", featureType.getName());
        Map<String, Object> featureProperties = new LinkedHashMap<>();
        featureProperties.put(FEATURE_NAME_KEY, featureType.getName());
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
                        if (super.isTagName(GML_PREFIX, BOUNDING_BY_PREFIX))
                            super.goToEndTag(BOUNDING_BY_PREFIX);
                        else
                            this.readGeometry(feature, featureProperties);
                    }
                } else if (super.isTagPrefix(GML_PREFIX)) {
                    if (super.isTagName(GML_PREFIX, BOUNDING_BY_PREFIX))
                        super.goToEndTag(BOUNDING_BY_PREFIX);
                    else
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
    final void readGeometry(Feature feature, Map<String, Object> featureProperties) throws Exception {
        String localName = xmlStreamReader().getLocalName();
        logger.trace("@@@@@@@@@@@@@@@@@@@@FOUND GEOMETRY : {} \n", localName);
        feature.setGeometry(this.internalReadGeometry(xmlStreamReader()));
        if (featureProperties.containsKey(this.previousGeometry.get())) {
            featureProperties.remove(this.previousGeometry.get());
        }
    }

    /**
     * @param streamReader
     * @return {@link GeoJsonObject}
     * @throws Exception
     */
    protected abstract GeoJsonObject internalReadGeometry(@Nonnull(when = NEVER) XMLStreamReader streamReader) throws Exception;
}