package org.geosdi.geoplatform.rs.support.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@AllArgsConstructor
@Getter
@ToString
@XmlTransient
public abstract class GPStore<V> implements Serializable {

    private static final long serialVersionUID = -8603598177950850051L;
    //
    private final Long total;
    private final List<V> values;
}