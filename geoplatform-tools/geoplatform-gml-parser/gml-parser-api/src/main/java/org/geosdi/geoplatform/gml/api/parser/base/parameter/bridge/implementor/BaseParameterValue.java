package org.geosdi.geoplatform.gml.api.parser.base.parameter.bridge.implementor;

import org.geosdi.geoplatform.gml.api.parameter.ParameterValue;
import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface BaseParameterValue<V extends Object> extends ParameterValue<V>, GPImplementor<BaseParameterEnum> {
}
