package org.geosdi.geoplatform.experimental.el.dao.store;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = PageStore.class)
@JsonSerialize(as = PageStore.class)
public interface IPageStore extends Serializable {

    /**
     * @return {@link Long}
     */
    Long getTotal();

    /**
     * @return {@link List}
     */
    List getResults();
}
