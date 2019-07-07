package org.geosdi.geoplatform.connector.geoserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverLatLonBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverNativeBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.keyword.IGPGeoserverKeyword;
import org.geosdi.geoplatform.connector.geoserver.model.metadata.adapter.GPGeoserverMetadataMapAdapter;
import org.geosdi.geoplatform.connector.geoserver.model.namespace.IGPGeoserverNamespace;
import org.geosdi.geoplatform.connector.geoserver.model.srs.GPGeoserverResponseSRS;
import org.geosdi.geoplatform.connector.geoserver.model.store.GPGeoserverStoreInfo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlTransient
@Getter
@Setter
@ToString
public abstract class GPGeoserverResourceInfo implements IGPGeoserverResourceInfo {

    private static final long serialVersionUID = -2589320136857111299L;
    //
    private GPGeoserverStoreInfo store;
    @XmlElement(name = "abstract")
    private String abstractText;
    private String nativeName;
    private String name;
    private String title;
    private String srs;
    private IGPGeoserverNamespace namespace;
    private boolean enabled;
    @XmlJavaTypeAdapter(value = GPGeoserverMetadataMapAdapter.class)
    private Map<String, String> metadata;
    private IGPGeoserverKeyword keywords;
    private GPGeoserverLatLonBoundingBox latLonBoundingBox;
    private GPGeoserverNativeBoundingBox nativeBoundingBox;
    private GPGeoserverResponseSRS responseSRS;
}