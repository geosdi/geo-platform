package org.geosdi.geoplatform.gui.client.config.composite.menu.tools;

import org.geosdi.geoplatform.gui.client.i18n.WFSTWidgetConstants;
import org.geosdi.geoplatform.gui.configuration.composite.menu.tools.TreeMenuClientTool;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class WFSWidgetTreeMenuClientTool extends TreeMenuClientTool {

    private static final long serialVersionUID = -3008896019031340209L;

    @Override
    public String getText() {
        return ((this.text == null) && (super.getTextKey() != null))
                ? WFSTWidgetConstants.INSTANCE.getString(super.getTextKey())
                : text;
    }

}
