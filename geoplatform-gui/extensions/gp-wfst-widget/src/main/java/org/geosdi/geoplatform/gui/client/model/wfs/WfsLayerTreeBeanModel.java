package org.geosdi.geoplatform.gui.client.model.wfs;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.tree.grid.GPTreeGridBeanModel;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class WfsLayerTreeBeanModel extends GPTreeGridBeanModel {

    /**
     *
     * @param theLayer
     */
    public WfsLayerTreeBeanModel(GPRasterBean theLayer){
        super(null);
    }

    @Override
    public Widget getWidget() {
        return new Label("cess");
    }

    @Override
    public AbstractImagePrototype getIcon() {
        return null;
    }

    @Override
    public String toString() {
        return "WfsLayerTreeBeanModel{} " + super.toString();
    }
}
