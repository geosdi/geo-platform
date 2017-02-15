package org.geosdi.geoplatform.gui.global.baselayer;

import com.google.gwt.user.client.ui.AbstractImagePrototype;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPBaseLayerValue {

    /**
     * @return {@link AbstractImagePrototype}
     */
    AbstractImagePrototype getIcon();

    /**
     * @return {@link String}
     */
    String getProjectionCode();
}
