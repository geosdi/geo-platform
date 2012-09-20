package org.geosdi.geoplatform.gui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.geosdi.geoplatform.gui.client.event.EventAvanti;
import org.geosdi.geoplatform.gui.client.event.EventIndietro;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;

public class BinderOtherPanel extends Composite {

    private static BinderOtherPanelUiBinder uiBinder = GWT.create(BinderOtherPanelUiBinder.class);

    interface BinderOtherPanelUiBinder extends
            UiBinder<Widget, BinderOtherPanel> {
    }
    @UiField
    VerticalPanel centerPanel;
    @UiField
    Hyperlink indietroLabel;
    @UiField
    Hyperlink vecchieLabel;
    SingleComponent singleComponent;

    public BinderOtherPanel(SingleComponent singleComponent) {
        this.singleComponent = singleComponent;
        initWidget(uiBinder.createAndBindUi(this));
        VerticalPanel bodyPanel = new VerticalPanel();
        bodyPanel.setPixelSize(400, 250);
        bodyPanel.add(this.singleComponent.getLabelTextNews());
        bodyPanel.add(this.singleComponent.getImage());
        centerPanel.add(bodyPanel);
    }

    @UiHandler("indietroLabel")
    void onIndietroLabelClick(ClickEvent event) {
        System.out.println("indietro Indice: " + this.singleComponent.getId());
        EventIndietro eventIndietro = new EventIndietro();
        eventIndietro.setIndex(0);
        GPHandlerManager.fireEvent(eventIndietro);
    }

    @UiHandler("vecchieLabel")
    void onVecchieLabelClick(ClickEvent event) {
        System.out.println("Avanti Indice: " + this.singleComponent.getId());
        if ((singleComponent.getId() % 3) != 0) {
            EventAvanti eventAvanti = new EventAvanti();
            eventAvanti.setIndex((this.singleComponent.getId() + 1));
            GPHandlerManager.fireEvent(eventAvanti);
        }
//		if((singleComponent.getId()%3) != 2) vecchieLabel.setStyleName("gwt-Hyperlink-disabled", true);
    }

    @UiHandler("nuoveLabel")
    void onNuoveLabelClick(ClickEvent event) {
        System.out.println("indietro Indice: " + this.singleComponent.getId());
        EventIndietro eventIndietro = new EventIndietro();
        eventIndietro.setIndex((this.singleComponent.getId() - 1));
        GPHandlerManager.fireEvent(eventIndietro);
    }
}
