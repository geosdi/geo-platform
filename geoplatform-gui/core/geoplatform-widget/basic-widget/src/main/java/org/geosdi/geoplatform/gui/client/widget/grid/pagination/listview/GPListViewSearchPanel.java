/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.widget.grid.pagination.listview;

import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.ListViewSelectionModel;
import org.geosdi.geoplatform.gui.client.widget.grid.pagination.GeoPlatformSearchPanel;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public abstract class GPListViewSearchPanel<T extends GeoPlatformBeanModel>
        extends GeoPlatformSearchPanel<ListView<T>, T> {
    
    public GPListViewSearchPanel(boolean lazy) {
        super(lazy);
    }
    
    public GPListViewSearchPanel(boolean lazy, int pageSize) {
        super(lazy, pageSize);
    }
    
    @Override
    public void initWidget() {
        super.widget = new ListView<T>();
        super.widget.addStyleName("overview-page");
        super.widget.setItemSelector(".project-box");
        super.widget.setOverStyle("sample-over");
        super.widget.setSelectStyle("project-box-select");
        super.widget.setBorders(Boolean.FALSE);
        super.widget.setStore(store);
        super.widget.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<T>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<T> se) {
                changeSelection(se);
            }
        });
        
        setListViewProperties();
    }

    /**
     *
     * @param T element
     */
    public void addElement(T element) {
        this.store.insert(element, 0);
    }

    /**
     *
     * @return ListViewSelectionModel<T>
     */
    public ListViewSelectionModel<T> getSelectionModel() {
        return super.widget.getSelectionModel();
    }

    /**
     *
     * @return ListStore<T>
     */
    public ListStore<T> getStore() {
        return this.store;
    }

    /**
     * @return the listView
     */
    public ListView<T> getListView() {
        return super.widget;
    }
    
    public abstract void changeSelection(SelectionChangedEvent<T> se);
    
    public abstract void setListViewProperties();
}
