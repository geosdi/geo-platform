package org.geosdi.geoplatform.connector.geoserver.model;

import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverLatLonBoundingBox;
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
public interface IGPGeoserverResourceInfo<NativeBoundingBox extends GPGeoserverBoundingBox<?>> extends Serializable {

    /**
     * @return {@link GPGeoserverStoreInfo}
     */
    GPGeoserverStoreInfo getStore();

    /**
     * @param theStore
     */
    void setStore(GPGeoserverStoreInfo theStore);

    /**
     * @return {@link String}
     */
    String getAbstractText();

    void setAbstractText(String theAbstractText);

    /**
     * @return {@link String}
     */
    String getNativeName();

    /**
     * @param theNativeName
     */
    void setNativeName(String theNativeName);

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @param theName
     */
    void setName(String theName);

    /**
     * @return {@link String}
     */
    String getTitle();

    /**
     * @param theTitle
     */
    void setTitle(String theTitle);

    /**
     * @return {@link String}
     */
    String getSrs();

    /**
     * @param theSrs
     */
    void setSrs(String theSrs);

    /**
     * @return {@link Namespace}
     */
    <Namespace extends IGPGeoserverNamespace> Namespace getNamespace();

    /**
     * @param theNamespace
     * @param <Namespace>
     */
    <Namespace extends IGPGeoserverNamespace> void setNamespace(Namespace theNamespace);

    /**
     * @return {@link Boolean}
     */
    boolean isEnabled();

    /**
     * @param theEnabled
     */
    void setEnabled(boolean theEnabled);

    /**
     * @return {@link Map<String, String>}
     */
    Map<String, String> getMetadata();

    /**
     * @param theMetadata
     */
    void setMetadata(Map<String, String> theMetadata);

    /**
     * @return {@link IGPGeoserverKeyword}
     */
    IGPGeoserverKeyword getKeywords();

    /**
     * @param theKeywords
     */
    void setKeywords(IGPGeoserverKeyword theKeywords);

    /**
     * @return {@link GPGeoserverLatLonBoundingBox}
     */
    GPGeoserverLatLonBoundingBox getLatLonBoundingBox();

    /**
     * @param theLatLonBoundingBox
     */
    void setLatLonBoundingBox(GPGeoserverLatLonBoundingBox theLatLonBoundingBox);

    /**
     * @return {@link NativeBoundingBox}
     */
    NativeBoundingBox getNativeBoundingBox();

    /**
     * @param theNativeBoundingBox
     */
    void setNativeBoundingBox(NativeBoundingBox theNativeBoundingBox);

    /**
     * @return {@link GPGeoserverResponseSRS}
     */
    GPGeoserverResponseSRS getResponseSRS();

    /**
     * @param theResponseSRS
     */
    void setResponseSRS(GPGeoserverResponseSRS theResponseSRS);
}