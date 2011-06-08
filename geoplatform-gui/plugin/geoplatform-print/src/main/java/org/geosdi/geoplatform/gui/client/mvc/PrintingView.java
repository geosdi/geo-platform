/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.mvc;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import org.geosdi.geoplatform.gui.client.form.GPPrintWidget;
import org.geosdi.geoplatform.gui.configuration.mvc.GeoPlatformView;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class PrintingView extends GeoPlatformView {
    
    private GPPrintWidget printWidget;
    
    
    public PrintingView(Controller controller) {
        super(controller);
        this.printWidget = new GPPrintWidget(true);
    }
    
    @Override
    protected void handleEvent(AppEvent event) {
        if (event.getType() == GeoPlatformEvents.SHOW_PRINTING_WIDGET) {
            onShowPrintingWidget();
        }
    }

    private void onShowPrintingWidget() {
        this.printWidget.show();
    }
}
