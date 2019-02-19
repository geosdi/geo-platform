package org.geosdi.geoplatform.connector.reader.stax;

import net.jcip.annotations.ThreadSafe;
import org.geojson.FeatureCollection;
import org.geosdi.geoplatform.connector.reader.featuretype.GPWMSFeatureType;
import org.geosdi.geoplatform.connector.reader.featuretype.GPWMSFeatureTypeReader;
import org.geosdi.geoplatform.connector.reader.featuretype.WMSFeatureType;
import org.geosdi.geoplatform.stax.reader.AbstractStaxStreamReader;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.net.URLDecoder;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.toList;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPWMSGetFeatureInfoStaxReader extends AbstractStaxStreamReader<FeatureCollection> {

    private static final String SCHEMA_LOCATION_KEY = "schemaLocation";
    private static final String TYPE_NAME_KEY = "typeName=";
    private static final String TYPES_NAME_SEPARATOR = ",";
    private static final String TYPE_NAME_SEPARATOR = ":";
    private static final GPWMSFeatureTypeReader featureTypeReader = new GPWMSFeatureTypeReader() {

        /**
         *
         * @param value
         * @return {@link List<GPWMSFeatureType>}
         * @throws Exception
         */
        @Override
        public List<GPWMSFeatureType> read(@Nonnull(when = NEVER) String value) throws Exception {
            checkArgument((value != null) && !(value.trim().isEmpty()), "The Parameter value must not be null or an empty string.");
            return compile(TYPES_NAME_SEPARATOR).splitAsStream(value)
                    .map(v -> v.split(TYPE_NAME_SEPARATOR))
                    .filter(values -> ((values[0] != null) && !(values[0].trim().isEmpty()) && (values[1] != null) && !(values[1].trim().isEmpty())))
                    .map(values -> new WMSFeatureType(values[0], values[1]))
                    .collect(toList());
        }
    };
    //
    private final ThreadLocal<List<GPWMSFeatureType>> typeNames = withInitial(() -> null);

    /**
     * @param o
     * @return {@link FeatureCollection}
     * @throws Exception
     */
    @Override
    public FeatureCollection read(Object o) throws Exception {
        XMLStreamReader reader = super.acquireReader(o);
        while (reader.hasNext()) {
            int evenType = reader.getEventType();
            if (evenType == XMLEvent.START_ELEMENT) {
                if (super.isTagName("wfs", "FeatureCollection")) {
                    try {
                        loadTypeNames();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (super.isTagName("gml", "featureMember")) {
                    int eventType = reader().nextTag();
                    if (eventType == XMLEvent.START_ELEMENT) {
                        logger.info("################TRYING TO READ XML.");
                        List<GPWMSFeatureType> featureTypes = this.typeNames.get();
                        checkArgument((featureTypes != null) && !(featureTypes.isEmpty()), "Impossible Read XML featureTypes is null or empty.");
                        for (GPWMSFeatureType featureType : featureTypes) {
                            String prefix = featureType.getPrefix();
                            String name = featureType.getName();
                            logger.info("##############################PREFIX : {} - NAME : {}\n", reader().getPrefix(), reader().getName());
                            if (super.isTagName(prefix, name)) {
                                String featureID = reader().getAttributeValue(null, "fid");
                                logger.info("#####################FEATURE_ID : {}", featureID);
                            } else if (super.isTagPrefix(prefix)) {
                                logger.info("####################Prefix : {} - Name : {}\n", reader().getPrefix(), reader().getLocalName());
                            }
                            super.goToEndTag("featureMember");
                        }
                    }
                }
            }
            reader.next();
        }
        super.dispose();
        return null;
    }

    /**
     * @throws Exception
     */
    private void loadTypeNames() throws Exception {
        String schemaLocation = reader().getAttributeValue(null, SCHEMA_LOCATION_KEY);
        if ((schemaLocation != null) && !(schemaLocation.trim().isEmpty())) {
            schemaLocation = URLDecoder.decode(schemaLocation, UTF_8.name());
            int index = schemaLocation.indexOf(TYPE_NAME_KEY);
            if (index != -1) {
                String typeName = schemaLocation.substring(index + TYPE_NAME_KEY.length());
                this.typeNames.set(featureTypeReader.read(typeName));
                logger.debug("########################TYPES_NAME : {}", this.typeNames.get());
            }
        }
    }
}