package org.geosdi.geoplatform.gui.client.widget.time.panel;

import com.extjs.gxt.ui.client.widget.form.FormPanel;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class TimePeriodPanel extends GeoPlatformContentPanel {

    private FormPanel panel;

    public TimePeriodPanel(FormPanel panel) {
        super(Boolean.TRUE);
        this.panel = panel;
    }

    @Override
    public void addComponent() {
        super.add(this.panel);
    }

    @Override
    public void initSize() {
        setHeaderVisible(false);
        setBorders(false);
    }

    @Override
    public void setPanelProperties() {

    }
}
