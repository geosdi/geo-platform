package org.geosdi.geoplatform.gui.client.widget.wfs.binding;

import com.google.gwt.event.shared.HandlerRegistration;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.WFSFeatureBindingHandler;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface WFSFeatureBinding extends WFSFeatureBindingHandler {

    HandlerRegistration addFeatureBindingHandler();
}
