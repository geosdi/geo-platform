/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
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
