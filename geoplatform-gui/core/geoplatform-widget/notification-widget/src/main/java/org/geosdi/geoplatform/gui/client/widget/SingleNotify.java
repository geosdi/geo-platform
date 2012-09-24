package org.geosdi.geoplatform.gui.client.widget;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.geosdi.geoplatform.gui.client.config.BasicGinInjector;
import org.geosdi.geoplatform.gui.model.message.ICommandAction;
import org.geosdi.geoplatform.gui.model.message.IGPClientMessage;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class SingleNotify extends VerticalPanel {

    private Label shortNotifyText;
    private Label expandedNotifyText;
    private Image imageNotify;
    private Image iconNotify;
    private IGPClientMessage message;
    private int index;

//    public SingleNotify(String shortNotifyText, String expandedNotifyText,
//            Image imageNotify) {
//        this.shortNotifyText = new Label(shortNotifyText);
//        this.expandedNotifyText = new Label(expandedNotifyText);
//        this.setImageNotify(imageNotify);
//        this.initializePanel();
//    }
    public SingleNotify(IGPClientMessage message) {
        this.message = message;
        this.shortNotifyText = new Label(message.getSubject());
        this.expandedNotifyText = new Label(message.getText());
//        System.out.println("Elementi ce ne sono: " + BasicGinInjector.MainInjector.getInstance().getCommandActionMediator());
        ICommandAction commandAction = (ICommandAction) BasicGinInjector.MainInjector.getInstance().getCommandActionMediator().get(message.getCommand());
        this.setImageNotify(commandAction.getCommandImage());
        this.initializePanel();
    }

    public ICommandAction getCommandAction() {
        return (ICommandAction) BasicGinInjector.MainInjector.
                getInstance().getCommandActionMediator().get(message.getCommand());
    }

    public IGPClientMessage getMessage() {
        return this.message;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Image getIconNotify() {
        return this.iconNotify;
    }

    public void setIconNotify(Image icon) {
        this.iconNotify = icon;
        this.iconNotify.setPixelSize(50, 50);
    }

    public Image getImageNotify() {
        return imageNotify;
    }

    public final void setImageNotify(Image image) {
        this.imageNotify = image;
        this.imageNotify.setPixelSize(400, 200);
        this.iconNotify = new Image(image.getUrl());
        this.iconNotify.setPixelSize(50, 50);
    }

    public Label getShortNotifyText() {
        return shortNotifyText;
    }

    public void setShortNotifyText(String shortNotifyText) {
        this.shortNotifyText.setText(shortNotifyText);
    }

    public Label getLabelTextNews() {
        return expandedNotifyText;
    }

    public void setLabelTextNews(String labelTextNews) {
        this.expandedNotifyText.setText(labelTextNews);
    }

    private void initializePanel() {
        super.setWidth("100%");
        super.setHeight("100px");
        super.add(this.shortNotifyText);
        super.add(this.iconNotify);
//        super.setStyleName(style.singleNews(), Boolean.TRUE);
//        deckLayoutPanel.insert(new BinderOtherPanel(listComponent.get(i)), j);
//        super.addDomHandler(new ClickHandler() {
//            public void onClick(ClickEvent event) {
//                deckLayoutPanel.setAnimationDuration(300);
//                deckLayoutPanel.showWidget((j));
//            }
//        }, ClickEvent.getType());
    }
}
