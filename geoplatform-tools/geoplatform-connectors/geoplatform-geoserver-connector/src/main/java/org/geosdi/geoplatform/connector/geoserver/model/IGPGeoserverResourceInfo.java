package org.geosdi.geoplatform.connector.geoserver.model;

import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverLatLonBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverNativeBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.keyword.IGPGeoserverKeyword;
import org.geosdi.geoplatform.connector.geoserver.model.namespace.IGPGeoserverNamespace;
import org.geosdi.geoplatform.connector.geoserver.model.srs.GPGeoserverResponseSRS;
import org.geosdi.geoplatform.connector.geoserver.model.store.GPGeoserverStoreInfo;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverResourceInfo extends Serializable {

    /**
     * @return {@link GPGeoserverStoreInfo}
     */
    GPGeoserverStoreInfo getStore();

    /**
     * @return {@link String}
     */
    String getAbstractText();

    /**
     * @return {@link String}
     */
    String getNativeName();

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @return {@link String}
     */
    String getTitle();

//    /**
//     * @param <MetadataLink>
//     * @return {@link List<MetadataLink>}
//     */
//    <MetadataLink extends IGPGeoserverMetadataLink> List<MetadataLink> getMetadataLinks();

    /**
     * @return {@link String}
     */
    String getSrs();

    /**
     * @return {@link Namespace}
     */
    <Namespace extends IGPGeoserverNamespace> Namespace getNamespace();

    /**
     * @return {@link Boolean}
     */
    boolean isEnabled();

    /**
     * @return {@link Map<String, String>}
     */
    Map<String, String> getMetadata();

    /**
     * @return {@link IGPGeoserverKeyword}
     */
    IGPGeoserverKeyword getKeywords();

    /**
     * @return {@link GPGeoserverLatLonBoundingBox}
     */
    GPGeoserverLatLonBoundingBox getLatLonBoundingBox();

    /**
     * @return {@link GPGeoserverNativeBoundingBox}
     */
    GPGeoserverNativeBoundingBox getNativeBoundingBox();

    /**
     * @return {@link GPGeoserverResponseSRS}
     */
    GPGeoserverResponseSRS getResponseSRS();
}