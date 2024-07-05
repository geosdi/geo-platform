package org.geosdi.geoplatform.experimental.el.dao.scroll;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FunctionalInterface
public interface GPScrollElasticSearchCallback {

    GPScrollElasticSearchCallback DEFAULT_SCROOL_CALBACK = () -> System.out.println("##################DEFAULT_SCROOL_ELASTIC_SEARCH_CALLBACK terminated its work.");

    void doOnCompleteScrool();
}