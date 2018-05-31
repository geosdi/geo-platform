package org.geosdi.geoplatform.gui.client.widget.wfs;

import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import org.geosdi.geoplatform.gui.client.puregwt.map.event.FeatureMapWidthEvent;
import org.geosdi.geoplatform.gui.client.puregwt.map.event.IncreaseWidthEvent;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

import javax.inject.Inject;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class WFSAccordionWidget extends GeoPlatformContentPanel {

    private final FeatureMapWidthEvent increaseWidthEvent = new IncreaseWidthEvent();
    //
    @Inject
    private FeatureSelectionWidget selectionWidget;
    @Inject
    private GeocodingWidget geocodingWidget;
    @Inject
    private GPEventBus bus;

    public WFSAccordionWidget() {
        super(Boolean.TRUE);
    }

    @Override
    public void addComponent() {
        this.add(this.geocodingWidget);
        this.add(this.selectionWidget);
    }

    @Override
    public void collapse() {
        this.increaseWidthEvent.setWidth(super.getWidth());
        this.bus.fireEvent(this.increaseWidthEvent);
        super.collapse();
    }

    public void reconfigureAttributes() {
        this.selectionWidget.reconfigureAttributes();
    }

    @Override
    public void initSize() {

    }

    @Override
    public void setPanelProperties() {
        this.setLayout(new AccordionLayout());
    }
}
