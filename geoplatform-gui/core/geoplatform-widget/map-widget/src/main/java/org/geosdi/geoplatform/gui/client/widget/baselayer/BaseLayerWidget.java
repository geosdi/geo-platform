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
package org.geosdi.geoplatform.gui.client.widget.baselayer;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Format;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import org.geosdi.geoplatform.gui.action.button.GPSecureButton;
import org.geosdi.geoplatform.gui.client.action.baselayer.SaveBaseLayerAction;
import org.geosdi.geoplatform.gui.client.event.ChangeBaseLayerEvent;
import org.geosdi.geoplatform.gui.client.i18n.MapModuleConstants;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.client.widget.baselayer.factory.GPMapBaseLayerFactory;
import org.geosdi.geoplatform.gui.client.widget.baselayer.model.GPBaseLayer;
import org.geosdi.geoplatform.gui.configuration.action.GeoPlatformSecureAction;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.MapHandlerManager;
import org.geosdi.geoplatform.gui.global.enumeration.GlobalRegistryEnum;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;

import static com.google.gwt.user.client.ui.AbstractImagePrototype.create;
import static org.geosdi.geoplatform.gui.client.BasicWidgetResources.ICONS;
import static org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants.INSTANCE;
import static org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class BaseLayerWidget extends GeoPlatformWindow {

    private static final short WIDGET_WIDTH = 485;
    private static final short WIDGET_HEIGHT = 385;
    //
    private ListStore<GPBaseLayer> store = new ListStore<GPBaseLayer>();
    private ListView<GPBaseLayer> listView;
    private ContentPanel centralPanel;
    private GPSecureButton saveButton;

    public BaseLayerWidget(boolean lazy) {
        super(lazy);
    }

    @Override
    public void addComponent() {
        this.store.add(GPMapBaseLayerFactory.getBaseLayerList());
        GeoPlatformSecureAction saveBaseLayerAction = new SaveBaseLayerAction(GPTrustedLevel.LOW, this);
        this.saveButton = new GPSecureButton(INSTANCE.saveText(), create(ICONS.save()),
                saveBaseLayerAction);
        this.saveButton.disable();
        Button applyButton = new Button(INSTANCE.applyCloseText(), create(ICONS.done()),
                new SelectionListener<ButtonEvent>() {
            
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        BaseLayerWidget.super.hide();
                    }

                });
        super.addButton(saveButton);
        super.addButton(applyButton);
    }

    @Override
    public void initSize() {
        setSize(WIDGET_WIDTH, WIDGET_HEIGHT);
    }

    @Override
    public void setWindowProperties() {
        super.setHeadingHtml(MapModuleConstants.INSTANCE.BaseLayerWidget_headingText());
        super.setScrollMode(Style.Scroll.NONE);
        super.setResizable(Boolean.FALSE);
    }

    @Override
    public void finalizeInitOperations() {
        super.finalizeInitOperations();
        this.centralPanel = new ContentPanel(new FlowLayout(0));
        this.centralPanel.setHeaderVisible(Boolean.FALSE);
        this.centralPanel.setFrame(Boolean.TRUE);
        this.centralPanel.setSize(WIDGET_WIDTH - 15, WIDGET_HEIGHT - 20);
        this.centralPanel.add(this.generateListView());
        this.centralPanel.setScrollMode(Style.Scroll.NONE);
        super.add(this.centralPanel);
    }

    @Override
    public void reset() {
        this.saveButton.disable();
    }

    private ListView<GPBaseLayer> generateListView() {
        listView = new ListView<GPBaseLayer>() {
            @Override
            protected GPBaseLayer prepareData(GPBaseLayer baseLayer) {
                baseLayer.set("shortName", Format.ellipse(baseLayer.getGwtOlBaseLayer().getName(), 30));
                return baseLayer;
            }

        };
        listView.addStyleName("overview-page");
        listView.setItemSelector(".project-box");
        listView.setOverStyle("sample-over");
        listView.setSelectStyle("none");
        listView.setBorders(Boolean.FALSE);
        listView.setStore(store);

        listView.getSelectionModel().addSelectionChangedListener(
                new SelectionChangedListener<GPBaseLayer>() {
                    ChangeBaseLayerEvent event;

                    @Override
                    public void selectionChanged(SelectionChangedEvent<GPBaseLayer> se) {
                        GPBaseLayer selectedBaseLayer = se.getSelectedItem();
                        if (selectedBaseLayer != null) {
                            event = new ChangeBaseLayerEvent(selectedBaseLayer);
                            MapHandlerManager.fireEvent(event);
                            IGPAccountDetail accountDetail = Registry.get(ACCOUNT_DETAIL_IN_SESSION.name());
                            accountDetail.setBaseLayer(selectedBaseLayer.getBaseLayerEnumName().toString());
                        }
                        listView.getSelectionModel().deselectAll();
                        BaseLayerWidget.this.saveButton.enable();
                    }

                });
        setListViewProperties();
        return listView;
    }

    private void setListViewProperties() {
        StringBuilder sb = new StringBuilder();
        sb.append("<tpl for=\".\">");
        sb.append("<div class='project-box' style='padding-top: 4px;border: none'>");
        sb.append("<div class='thumbd' title='{");
        sb.append(GlobalRegistryEnum.TOOLTIP);
        sb.append("}'>{");
        sb.append(GPBaseLayer.BaseLayerKey.IMAGE);
        sb.append("}</div>");
        sb.append("<div>{");
        sb.append(GlobalRegistryEnum.TOOLTIP);
        sb.append("}</div></div></tpl>");

        listView.setTemplate(sb.toString());

        listView.setSize(WIDGET_WIDTH - 25, WIDGET_HEIGHT - 75);
    }
}