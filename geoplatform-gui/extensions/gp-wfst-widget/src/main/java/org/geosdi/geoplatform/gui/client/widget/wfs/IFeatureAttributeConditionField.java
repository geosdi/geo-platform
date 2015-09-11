package org.geosdi.geoplatform.gui.client.widget.wfs;

import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.IDateSelectedHandler;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IFeatureAttributeConditionField extends IDateSelectedHandler {

    QueryRestrictionDTO getQueryRestriction();
}
