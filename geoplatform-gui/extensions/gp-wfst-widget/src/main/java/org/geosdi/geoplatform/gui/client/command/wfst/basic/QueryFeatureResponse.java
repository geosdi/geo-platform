package org.geosdi.geoplatform.gui.client.command.wfst.basic;

import org.geosdi.geoplatform.connector.wfs.response.FeatureCollectionDTO;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class QueryFeatureResponse extends GetAllFeatureResponse {

    private static final long serialVersionUID = 4474304355942338064L;
    
    public QueryFeatureResponse() {
    }
    public QueryFeatureResponse(FeatureCollectionDTO theFeatureCollectionDTO) {
        super(theFeatureCollectionDTO);
    }
}
