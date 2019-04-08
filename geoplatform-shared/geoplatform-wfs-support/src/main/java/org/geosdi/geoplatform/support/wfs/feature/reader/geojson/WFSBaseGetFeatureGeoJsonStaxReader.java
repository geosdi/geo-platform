package org.geosdi.geoplatform.support.wfs.feature.reader.geojson;

import org.geojson.Feature;
import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.connector.reader.stax.GPGetFeatureGeoJsonStaxReader;
import org.geosdi.geoplatform.gml.api.AbstractGeometry;
import org.geosdi.geoplatform.support.wfs.feature.reader.GPWFSGetFeatureStaxReader;
import org.geosdi.geoplatform.xml.gml.v311.AbstractGeometryType;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLStreamReader;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class WFSBaseGetFeatureGeoJsonStaxReader extends GPGetFeatureGeoJsonStaxReader implements GPWFSGetFeatureStaxReader {

    protected static final String FEATURE_MEMBERS_LOCAL_NAME = "featureMembers";
    private static final String ID_LOCAL_NAME = "id";

    protected WFSBaseGetFeatureGeoJsonStaxReader() {
        super(ID_LOCAL_NAME);
    }

    /**
     * @param feature
     * @throws Exception
     */
    void readFeatureID(Feature feature) throws Exception {
        this.readFeatureID(this.typeNames.get(), feature);
    }

    /**
     * @param streamReader
     * @return {@link GeoJsonObject}
     * @throws Exception
     */
    @Override
    protected GeoJsonObject internalReadGeometry(@Nonnull(when = NEVER) XMLStreamReader streamReader) throws Exception {
        try {
            AbstractGeometry geometry = jaxbContextBuilder.unmarshal(xmlStreamReader(), AbstractGeometryType.class);
            return sextanteParser.parseGeometryAsGeoJson(geometry);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("########################Parse Exception : {}", ex);
        } finally {
            xmlStreamReader().nextTag();
        }
        return null;
    }
}