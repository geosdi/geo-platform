/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 *
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 *
 */
package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import org.geosdi.geoplatform.gui.client.ServerWidgetResources;
import org.geosdi.geoplatform.gui.client.model.GPServerBeanModel;
import org.geosdi.geoplatform.gui.client.model.GPServerBeanModel.GPServerKeyValue;
import org.geosdi.geoplatform.gui.client.service.GeoPlatformOGCRemote;
import org.geosdi.geoplatform.gui.client.service.GeoPlatformOGCRemoteAsync;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class DisplayServerWidget {

    private GeoPlatformOGCRemoteAsync service = GeoPlatformOGCRemote.Util.getInstance();

    private ToolBar toolbar;
    private ComboBox<GPServerBeanModel> comboServer;
    private ListStore<GPServerBeanModel> store;
    private SearchStatus searchStatus;
    private Button addServer;

    /**
     * @Constructor
     */
    public DisplayServerWidget() {
        init();
    }

    private void init() {
        this.createComponents();
        this.createToolBar();
    }

    private void createComponents() {
        this.store = new ListStore<GPServerBeanModel>();
        this.comboServer = new ComboBox<GPServerBeanModel>();

        comboServer.setEmptyText("Select a Server...");
        comboServer.setDisplayField(GPServerKeyValue.URL_SERVER.getValue());
        comboServer.setTemplate(getTemplate());
        comboServer.setWidth(250);
        comboServer.setStore(this.store);
        comboServer.setTypeAhead(true);
        comboServer.setTriggerAction(TriggerAction.ALL);

        this.addServer = new Button("Add Server",
                ServerWidgetResources.ICONS.addServer(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        /** HERE THE CODE TO OPEN ADD SERVER WINDOW **/
                    }
                });
    }

    private void createToolBar() {
        this.toolbar = new ToolBar();

        this.searchStatus = new SearchStatus();
        searchStatus.setAutoWidth(true);

        this.toolbar.add(this.searchStatus);

        this.toolbar.add(this.comboServer);
        this.toolbar.add(new SeparatorToolItem());

        toolbar.add(new FillToolItem());

        this.toolbar.add(this.addServer);
    }

    /**
     *
     * @return String
     */
    private native String getTemplate() /*-{
        return  [
            '<tpl for=".">',
                '<div class="x-combo-list-item" qtip="{slogan}" qtitle="Server">{urlServer}</div>',
            '</tpl>'
            ].join("");
    }-*/;

     /**
     * Set the correct Status Iconn Style
     */
    public void setSearchStatus(EnumSearchStatus status,
            EnumSearchStatus message) {
        this.searchStatus.setIconStyle(status.getValue());
        this.searchStatus.setText(message.getValue());
    }

    /**
     * Load All Server from WS
     */
    public void loadServers() {
        this.searchStatus.setBusy("Loading Server...");
    }

    /**
     * @return the toolbar
     */
    public ToolBar getToolbar() {
        return toolbar;
    }
}
