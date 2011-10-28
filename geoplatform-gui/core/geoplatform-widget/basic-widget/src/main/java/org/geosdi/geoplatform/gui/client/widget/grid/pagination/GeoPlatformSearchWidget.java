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
package org.geosdi.geoplatform.gui.client.widget.grid.pagination;

import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.toolbar.LabelToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public abstract class GeoPlatformSearchWidget<C extends Widget, T extends GeoPlatformBeanModel>
        extends Window {

    private VerticalPanel vp;
    protected FormPanel formPanel;
    protected ListStore<T> store;
    protected C widget;
    protected TextField<String> search;
    protected RpcProxy<PagingLoadResult<T>> proxy;
    protected PagingLoader<PagingLoadResult<ModelData>> loader;
    protected PagingToolBar toolBar;
    protected Button selectButton;
    protected Button cancelButton;
    protected SearchStatus searchStatus;
    protected String searchText;
    private boolean initialized;
    private int pageSize = 25;

    /**
     *
     * @param lazy
     */
    public GeoPlatformSearchWidget(boolean lazy) {
        if (!lazy) {
            init();
        }
    }

    public GeoPlatformSearchWidget(boolean lazy, int pageSize) {
        this.pageSize = pageSize;
        if (!lazy) {
            init();
        }
    }

    protected void init() {
        if (!isInitialized()) {
            initWindow();
            initVerticalPanel();
            initFormPanel();
            this.finalizeInitOperations();
            this.initialized = true;
        }
    }

    /**
     * Remember to call super.finalizeInitOperations when override this method
     */
    public void finalizeInitOperations() {
        add(vp);
    }

    private void initWindow() {
        setModal(true);
        setResizable(false);
        setLayout(new FlowLayout());
        setPlain(true);
        setMaximizable(false);

        addWindowListener(new WindowListener() {

            @Override
            public void windowHide(WindowEvent we) {
                executeCancel();
            }
        });

        setWindowProperties();
    }

    private void initVerticalPanel() {
        vp = new VerticalPanel();
        vp.setSpacing(10);
        createStore();
        initWidget();
    }

    private void initFormPanel() {
        formPanel = new FormPanel();
        formPanel.setHeaderVisible(false);
        formPanel.setFrame(true);
        formPanel.setLayout(new FlowLayout());

        FieldSet searchFieldSet = new FieldSet();
        searchFieldSet.setHeading("Search");

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(80);
        searchFieldSet.setLayout(layout);

        search = new TextField<String>();
        search.setFieldLabel("Find");

        search.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyUp(ComponentEvent event) {
                if (((event.getKeyCode() == KeyCodes.KEY_BACKSPACE)
                        || (event.getKeyCode() == KeyCodes.KEY_DELETE))
                        && (search.getValue() == null)) {
                    reset();
                }
            }

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if ((event.getKeyCode() == KeyCodes.KEY_ENTER)) {
                    searchText = search.getValue() == null ? "" : search.getValue();
                    loader.load(0, 25);
                }
            }
        });

        BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
        data.setMargins(new Margins(5, 5, 5, 5));

        searchFieldSet.add(search, data);

        formPanel.add(searchFieldSet);

        formPanel.add(this.initWidget());

        this.searchStatus = new SearchStatus();
        searchStatus.setAutoWidth(true);

        formPanel.getButtonBar().add(this.searchStatus);

        formPanel.getButtonBar().add(new LabelToolItem("    "));

        formPanel.setButtonAlign(HorizontalAlignment.RIGHT);

        selectButton = new Button("Select", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                executeSelect();
            }
        });

        selectButton.setIcon(BasicWidgetResources.ICONS.select());
        selectButton.disable();

        formPanel.addButton(this.selectButton);

        cancelButton = new Button("Cancel", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                executeCancel();
            }
        });

        cancelButton.setIcon(BasicWidgetResources.ICONS.cancel());

        formPanel.addButton(cancelButton);

        formPanel.setBottomComponent(this.toolBar);

        vp.add(formPanel);
    }

    /**
     * Remove all beans from the Store and after Hide the window
     */
    public void executeCancel() {
        super.hide();
        reset();
    }

    public void reset() {
        this.search.reset();
        this.store.removeAll();
        this.toolBar.clear();
        this.selectButton.disable();
        this.searchStatus.clearStatus("");
    }

    public void clearWidgetElements() {
        this.store.removeAll();
        this.toolBar.clear();
    }

    /**
     * 
     * @param position
     * @param button 
     */
    public void addButton(int position, Button button) {
        this.formPanel.getButtonBar().getItems().add(position, button);
    }

    /**
     * Set the correct Status Icon Style
     */
    public void setSearchStatus(EnumSearchStatus status,
            EnumSearchStatus message) {
        this.searchStatus.setIconStyle(status.toString());
        this.searchStatus.setText(message.toString());
    }

    public abstract void setWindowProperties();

    public abstract void createStore();

    public abstract C initWidget();

    public abstract void executeSelect();

    /**
     * @return the initialized
     */
    public boolean isInitialized() {
        return initialized;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }
}
