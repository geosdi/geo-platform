package org.geosdi.geoplatform.gml.impl.v311.jts.parameter.bridge.implementor;

import org.geosdi.geoplatform.gml.api.parameter.ParameterValue;
import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface JTSParameterValue<V extends Object> extends ParameterValue<V>, GPImplementor<JTSParameterEnum> {
}
