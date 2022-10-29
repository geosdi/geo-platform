package org.geosdi.geoplatform.connector.geowebcache.model.seed.entry.entry;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GeowebcacheEntryValue.class)
public interface GPGeowebcacheEntryValue extends Serializable {

    /**
     * @return {@link List <String>}
     */
    List<String> getValues();

    /**
     * @param theValues
     */
    void setValues(List<String> theValues);

    /**
     * @param theValue
     */
    void addValue(@Nonnull(when = NEVER) String theValue);
}
