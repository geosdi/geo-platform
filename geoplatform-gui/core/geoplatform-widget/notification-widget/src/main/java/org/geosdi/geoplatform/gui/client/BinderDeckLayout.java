package org.geosdi.geoplatform.gui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckLayoutPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import org.geosdi.geoplatform.gui.client.event.IAvantiHandler;
import org.geosdi.geoplatform.gui.client.event.IIndietroHandler;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;

public class BinderDeckLayout extends Composite implements IIndietroHandler,
        //public class BinderDeckLayout extends PopupPanel implements IIndietroHandler,
        IAvantiHandler {

    private static BinderDeckLayoutUiBinder uiBinder = GWT
            .create(BinderDeckLayoutUiBinder.class);

    interface BinderDeckLayoutUiBinder extends
            UiBinder<Widget, BinderDeckLayout> {
    }
    @UiField
    VerticalPanel deckPanel;
//	@UiField
//	VerticalPanel root;
    DeckLayoutPanel deckLayoutPanel = new DeckLayoutPanel();
    ArrayList<SingleComponent> listComponent = new ArrayList<SingleComponent>();

    public BinderDeckLayout() {
        initWidget(uiBinder.createAndBindUi(this));
//		add(uiBinder.createAndBindUi(this));
        GPHandlerManager.addHandler(IIndietroHandler.TYPE, this);
        GPHandlerManager.addHandler(IAvantiHandler.TYPE, this);
        initComponent();
        LayoutPanel layoutPanel = new LayoutPanel();
        layoutPanel.setPixelSize(400, 300);
        layoutPanel.add(deckLayoutPanel);
        deckLayoutPanel.insert(new BinderFirstPanel(listComponent,
                deckLayoutPanel), 0);
//		deckPanel.setStyleName(style.border(), true);
//		deckPanel.getElement().getStyle().setRight(15, Unit.PX);
//		deckPanel.getElement().getStyle().setTop(70, Unit.PX);
//		deckPanel.getElement().getStyle().setPosition(Position.ABSOLUTE);
        deckPanel.add(layoutPanel);
//		System.out.println(".................."+deckPanel.getElement().getStyle().getRight());
//		deckPanel.getStyleName().
        deckLayoutPanel.showWidget(0);
    }

    private void initComponent() {
        SingleComponent component = new SingleComponent();
        component.setId(1);
        component.setLabel(new Label("Leggi la prima notizia"));
        component
                .setImage(new Image(
                "http://www.cucciolidolci.hellokittymania.net/wp-photos/cuccioli-immagini-dolci-635-20081116-201639-1.jpg"));
        component
                .setLabelTextNews(new Label(
                "Australia: foto di alcuni cuccioli di un animale che non sò di che razza sia che giocano affettuosamente"));
        listComponent.add(component);
        component = new SingleComponent();
        component.setId(2);
        component.setLabel(new Label("Leggi la seconda notizia"));
        component
                .setImage(new Image(
                "http://www.webwards.net/wp-content/uploads/2007/09/escher_droste_effect.jpg"));
        component
                .setLabelTextNews(new Label(
                "Grafica: l'arte di creare delle immagini grafiche utilizzando dei tools appositi"));
        listComponent.add(component);
        component = new SingleComponent();
        component.setId(3);
        component.setLabel(new Label("Leggi la seconda notizia"));
        component
                .setImage(new Image(
                "http://www.webwards.net/wp-content/uploads/2007/09/escher_droste_effect.jpg"));
        component
                .setLabelTextNews(new Label(
                "REPEAT: Grafica: l'arte di creare delle immagini grafiche utilizzando dei tools appositi"));
        listComponent.add(component);
    }

    public void onClickAvanti(int index) {
        deckLayoutPanel.showWidget(index);
    }

    public void onClickIndietro(int index) {
        deckLayoutPanel.showWidget(index);
    }
}
