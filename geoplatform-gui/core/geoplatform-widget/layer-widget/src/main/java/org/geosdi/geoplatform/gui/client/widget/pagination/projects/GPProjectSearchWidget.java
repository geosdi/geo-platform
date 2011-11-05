/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
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
package org.geosdi.geoplatform.gui.client.widget.pagination.projects;

import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.action.projects.AddProjectAction;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProject;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.client.widget.grid.pagination.listview.GPListViewSearchWidget;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.puregwt.layers.projects.event.GPDefaultProjectTreeEvent;
import org.geosdi.geoplatform.gui.puregwt.session.TimeoutHandlerManager;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class GPProjectSearchWidget extends GPListViewSearchWidget<GPClientProject> {

    private GPDefaultProjectTreeEvent defaultProjectEvent = new GPDefaultProjectTreeEvent();
    private GPDefaultProjectSelector selector;
    private Button deleteButton;

    public GPProjectSearchWidget() {
        super(true, 10);
        this.selector = new GPDefaultProjectSelector();
    }

    @Override
    public void finalizeInitOperations() {
        super.finalizeInitOperations();
        selectButton.setText("Set Default Project");
        super.search.setFieldLabel("Find Project");

        super.addButton(1, new Button("Add", LayerResources.ICONS.projectAdd(),
                new AddProjectAction(this)));

        this.deleteButton = new Button("Delete", LayerResources.ICONS.projectDelete(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                    }
                });

        this.deleteButton.disable();

        super.addButton(2, this.deleteButton);
    }

    @Override
    public void show() {
        this.init();
        super.show();
    }

    @Override
    public void setListViewProperties() {
        StringBuilder sb = new StringBuilder();
        sb.append("<tpl for=\".\">");
        sb.append("<div class='project-box' style='padding-top: 4px;border: none'>");
        sb.append("<div class='thumbd'>{image}</div>");
        sb.append("<div>Name : {name}</div>");
        sb.append("<div>Elements : {numberOfElements}</div>");
        sb.append("<div>{message}</div>");
        sb.append("</div></tpl>");

        listView.setTemplate(sb.toString());

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        listView.setSize(630, 340);
    }

    @Override
    public void setWindowProperties() {
        super.setHeading("GeoPlatform Project Management");
        super.setSize(700, 565);

        super.addWindowListener(new WindowListener() {

            @Override
            public void windowShow(WindowEvent we) {
                searchText = "";
                loader.load(0, getPageSize());
            }
        });
    }

    @Override
    public void createStore() {
        super.toolBar = new PagingToolBar(super.getPageSize());

        super.proxy = new RpcProxy<PagingLoadResult<GPClientProject>>() {

            @Override
            protected void load(Object loadConfig,
                    AsyncCallback<PagingLoadResult<GPClientProject>> callback) {

                LayerRemote.Util.getInstance().searchProjects(
                        (PagingLoadConfig) loadConfig,
                        searchText, LayerResources.ICONS.gpProject().getHTML(),
                        callback);
            }
        };

        super.loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
        super.loader.setRemoteSort(false);

        super.store = new ListStore<GPClientProject>(loader);

        super.toolBar.bind(loader);
    }

    @Override
    public void executeSelect() {
        if (listView.getSelectionModel().getSelectedItem().isDefaultProject()) {
            GeoPlatformMessage.alertMessage("GeoPlatform Alert Message",
                    "Default Project "
                    + "has already selected.");
            listView.getSelectionModel().deselectAll();
        } else {
            selector.selectDefaultProject();
        }
    }

    @Override
    public void changeSelection(SelectionChangedEvent<GPClientProject> se) {
        if (se.getSelectedItem() != null) {
            selectButton.enable();
            deleteButton.enable();
        } else {
            selectButton.disable();
            deleteButton.disable();
        }
    }

    /**
     * Internal Class Delegate to Select Default Project and Rebuild GPTreePanel
     * 
     */
    private class GPDefaultProjectSelector {

        private void selectDefaultProject() {
            searchStatus.setBusy("Setting Default Project");

            LayerRemote.Util.getInstance().setDefaultProject(listView.getSelectionModel().
                    getSelectedItem().getId(),
                    new AsyncCallback<Object>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            throw new UnsupportedOperationException("Not supported yet.");
                        }

                        @Override
                        public void onSuccess(Object result) {
                            setSearchStatus(EnumSearchStatus.STATUS_SEARCH,
                                    EnumProjectMessage.DEFAUTL_PROJECT_MESSAGE);
                            hide();
                            TimeoutHandlerManager.fireEvent(defaultProjectEvent);
                        }
                    });
        }
    }
}
