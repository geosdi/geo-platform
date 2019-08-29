package org.geosdi.geoplatform.connector.geoserver.model.security.catalog;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElement;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ToString
public class GPGeoserverCatalog implements IGPGeoserverCatalog {

    private static final long serialVersionUID = -3429079095854095441L;
    //
    private final IGPGeoserverCatalogMode catalogMode;

    /**
     * @param theCatalogMode
     */
    @JsonCreator
    public GPGeoserverCatalog(@Nonnull(when = NEVER) @JsonProperty(value = "mode") IGPGeoserverCatalogMode theCatalogMode) {
        checkArgument(theCatalogMode != null, "The Parameter catologMode must not be null");
        this.catalogMode = theCatalogMode;
    }

    /**
     * @return {@link CatalogMode}
     */
    @XmlElement(name = "mode")
    @Override
    public <CatalogMode extends IGPGeoserverCatalogMode> CatalogMode getCatalogMode() {
        return (CatalogMode) this.catalogMode;
    }
}