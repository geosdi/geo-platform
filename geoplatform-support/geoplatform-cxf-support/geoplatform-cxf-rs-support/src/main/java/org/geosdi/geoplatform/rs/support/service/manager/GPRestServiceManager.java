package org.geosdi.geoplatform.rs.support.service.manager;

import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPRestServiceManager extends InitializingBean {

    /**
     * @return {@link List <Object>}
     */
    List<Object> getServiceBeans();
}