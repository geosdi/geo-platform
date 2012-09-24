package org.geosdi.geoplatform.gui.client.widget;

import org.geosdi.geoplatform.gui.client.style.NotifyStyle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;
import org.geosdi.geoplatform.gui.client.event.EventViewNews;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class NotifyListPanel extends Composite {

    private static NotifyListPanelUiBinder uiBinder = GWT
            .create(NotifyListPanelUiBinder.class);

    interface NotifyListPanelUiBinder extends
            UiBinder<Widget, NotifyListPanel> {
    }
    @UiField
    VerticalPanel centerPanel;
    @UiField
    Hyperlink plusLabel;
    @UiField
    Hyperlink viewLabel;
    @UiField
    NotifyStyle style;
    private List<SingleNotify> listComponent;

    public NotifyListPanel(final List<SingleNotify> listComponent, final DeckLayoutPanel deckLayoutPanel) {
        this.listComponent = listComponent;
        initWidget(uiBinder.createAndBindUi(this));
        ScrollPanel scrollPanel = new ScrollPanel();
        HTMLPanel newsPanel = new HTMLPanel("");
        newsPanel.setWidth("100%");
        newsPanel.setHeight("100%");
        scrollPanel.setSize("400px", "250px");
        for (int i = 0; i < listComponent.size(); i++) {
            final int j = i + 1;
            SingleNotify singleNotify = listComponent.get(i);
            singleNotify.setIndex(j);
            singleNotify.setStyleName(style.singleNews(), true);
            deckLayoutPanel.insert(new NotifyMessagePanel(listComponent.get(i)), j);
            singleNotify.addDomHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    deckLayoutPanel.setAnimationDuration(300);
                    deckLayoutPanel.showWidget((j));
                }
            }, ClickEvent.getType());
            newsPanel.add(singleNotify);
        }
        scrollPanel.add(newsPanel);
        centerPanel.add(scrollPanel);
    }

    @UiHandler("viewLabel")
    void onViewLabelClick(ClickEvent event) {
        System.out.println("VIEW: ");
        EventViewNews eventViewNews = new EventViewNews();
        eventViewNews.setListComponent(listComponent);
        GPHandlerManager.fireEvent(eventViewNews);
    }
}
