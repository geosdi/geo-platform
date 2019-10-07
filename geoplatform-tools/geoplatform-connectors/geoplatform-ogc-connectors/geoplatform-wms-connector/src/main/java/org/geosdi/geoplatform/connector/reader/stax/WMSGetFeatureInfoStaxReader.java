package org.geosdi.geoplatform.connector.reader.stax;

import org.geojson.Feature;
import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.connector.parser.GPWMSGml2GeoJsonParser;
import org.geosdi.geoplatform.connector.parser.WMSGml2GeoJsonParser;
import org.geosdi.geoplatform.stax.reader.builder.GPXmlStreamReaderBuilder;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLStreamReader;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class WMSGetFeatureInfoStaxReader extends GPGetFeatureGeoJsonStaxReader {

    protected static final String FEATURE_MEMBER_LOCAL_NAME = "featureMember";
    private static final String FID_LOCAL_NAME = "fid";
    private static final GPWMSGml2GeoJsonParser GML2_GEO_JSON_PARSER = new WMSGml2GeoJsonParser();

    /**
     * @param theXmlStreamBuilder
     */
    WMSGetFeatureInfoStaxReader(@Nonnull(when = NEVER) GPXmlStreamReaderBuilder theXmlStreamBuilder) {
        super(theXmlStreamBuilder, FID_LOCAL_NAME);
    }

    /**
     * @throws Exception
     */
    @Override
    protected void readFeatures(Feature feature) throws Exception {
        super.readFeatures(feature);
        super.goToEndTag(FEATURE_MEMBER_LOCAL_NAME);
    }

    /**
     * @param streamReader
     * @return {@link GeoJsonObject}
     * @throws Exception
     */
    @Override
    protected GeoJsonObject internalReadGeometry(@Nonnull(when = NEVER) XMLStreamReader streamReader) throws Exception {
        checkArgument(streamReader != null, "The Parameter streamReader must not be null.");
        return GML2_GEO_JSON_PARSER.parse(xmlStreamReader());
    }

    /**
     * @param object
     * @return {@link GPStaxFeatureStore}
     * @throws Exception
     */
    public abstract <FeatureStore extends GPStaxFeatureStore> FeatureStore readAsStore(@Nonnull(when = NEVER) Object object) throws Exception;
}