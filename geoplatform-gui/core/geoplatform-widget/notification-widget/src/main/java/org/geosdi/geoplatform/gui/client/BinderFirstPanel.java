package org.geosdi.geoplatform.gui.client;

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
import java.util.ArrayList;
import org.geosdi.geoplatform.gui.client.event.EventViewNews;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;

public class BinderFirstPanel extends Composite {

	private static BinderFirstPanelUiBinder uiBinder = GWT
			.create(BinderFirstPanelUiBinder.class);

	interface BinderFirstPanelUiBinder extends
			UiBinder<Widget, BinderFirstPanel> {
	}

	@UiField VerticalPanel centerPanel;
	@UiField Hyperlink plusLabel;
	@UiField Hyperlink viewLabel;
	@UiField
	NotifyStyle style;
	private ArrayList<SingleComponent> listComponent;
	
	public BinderFirstPanel(final ArrayList<SingleComponent> listComponent, final DeckLayoutPanel deckLayoutPanel) {
		this.listComponent = listComponent;
		initWidget(uiBinder.createAndBindUi(this));
		ScrollPanel scrollPanel = new ScrollPanel();
		HTMLPanel newsPanel = new HTMLPanel("");
		newsPanel.setWidth("100%");
		newsPanel.setHeight("100%");
		scrollPanel.setSize("400px", "250px");
		for (int i = 0; i < listComponent.size(); i++) {
			final int j = i + 1;
			VerticalPanel singlePanel = new VerticalPanel();
			singlePanel.setWidth("100%");
			singlePanel.setHeight("100px");
			singlePanel.add(listComponent.get(i).getLabel());
			singlePanel.add(listComponent.get(i).getIcon());
			singlePanel.setStyleName(style.singleNews(), true);
			deckLayoutPanel.insert(new BinderOtherPanel(listComponent.get(i)), j);
			singlePanel.addDomHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					deckLayoutPanel.setAnimationDuration(300);
					deckLayoutPanel.showWidget((j));
				}
			}, ClickEvent.getType());
			newsPanel.add(singlePanel);
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
