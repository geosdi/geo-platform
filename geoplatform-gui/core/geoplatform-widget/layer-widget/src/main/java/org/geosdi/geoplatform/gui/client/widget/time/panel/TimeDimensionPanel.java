package org.geosdi.geoplatform.gui.client.widget.time.panel;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.puregwt.filter.event.GPHideFilterWidgetEvent;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class TimeDimensionPanel extends GeoPlatformContentPanel {

    private FormPanel panel;

    public TimeDimensionPanel(FormPanel panel) {
        super(Boolean.TRUE);
        this.panel = panel;
    }

    @Override
    public void addComponent() {
        super.add(this.panel);
        Button close = new Button(ButtonsConstants.INSTANCE.closeText(),
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        WidgetPropertiesHandlerManager.fireEvent(new GPHideFilterWidgetEvent());
                    }
                });
        this.panel.addButton(close);
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
