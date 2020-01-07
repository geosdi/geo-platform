package org.geosdi.geoplatform.rs.support.request.binder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.geosdi.geoplatform.rs.support.request.GPI18NRequest;

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlTransient
public abstract class GPRequestBinder<B extends Object> extends GPI18NRequest {

    private static final long serialVersionUID = 1508964835112867144L;

    /**
     * @return {@link B}
     * @throws Exception
     */
    @JsonIgnore
    public abstract B bind() throws Exception;
}