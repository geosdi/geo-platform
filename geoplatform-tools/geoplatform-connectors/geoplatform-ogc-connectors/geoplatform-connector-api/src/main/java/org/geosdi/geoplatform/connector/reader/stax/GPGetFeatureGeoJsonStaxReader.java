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
                this.typeNames.set((index != -1) ? featureTypeReader.read(typeName.substring(0, index).replaceAll(TYPE_NAME_KEY, "")) : featureTypeReader.read(typeName.replaceAll(TYPE_NAME_KEY, "")));
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
                        String attributeValue = xmlStreamReader().getText();
                        featureProperties.put(localName, attributeValue);
                        if ((attributeValue != null) && (attributeValue.trim().isEmpty()))
                            this.previousGeometry.set(localName);
                        logger.trace("##########################ATTRIBUTE_NAME : {} - ATTRIBUTE_VALUE : {}\n", localName, attributeValue);
                    } else if (eventType == XMLEvent.END_ELEMENT) {
                        featureProperties.put(localName, null);
                    } else if (super.isTagPrefix(GML_PREFIX)) {
                        if (super.isTagName(GML_PREFIX, BOUNDING_BY_PREFIX)) {
                            super.goToEndTag(BOUNDING_BY_PREFIX);
                        } else {
                            this.readGeometry(feature, featureProperties);
                        }
                    }
                } else if (super.isTagPrefix(GML_PREFIX)) {
                    if (super.isTagName(GML_PREFIX, BOUNDING_BY_PREFIX)) {
                        super.goToEndTag(BOUNDING_BY_PREFIX);
                    } else {
                        this.readGeometry(feature, featureProperties);
                    }
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
        logger.trace("@@@@@@@@@@@@@@@@@@@@FOUND GEOMETRY : {} - Properties : {}\n", localName, featureProperties);
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