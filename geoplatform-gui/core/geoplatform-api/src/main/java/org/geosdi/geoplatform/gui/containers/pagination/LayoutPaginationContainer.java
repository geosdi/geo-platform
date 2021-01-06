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
package org.geosdi.geoplatform.gui.containers.pagination;

import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class LayoutPaginationContainer<C extends Widget, T extends GeoPlatformBeanModel>
        extends LayoutContainer {

    protected ListStore<T> store;
    protected C widget;
    protected RpcProxy<PagingLoadResult<T>> proxy;
    protected PagingLoader<PagingLoadResult<T>> loader;
    protected ContentPanel panel;
    private GeoPlatformPagingToolBar toolBar;
    private boolean initialized;
    private int pageSize;

    public LayoutPaginationContainer(boolean lazy) {
        this(lazy, 25);
    }

    public LayoutPaginationContainer(boolean lazy, int thePageSize) {
        this.pageSize = thePageSize;
        if (!lazy) {
            this.init();
        }
    }

    protected final void init() {
        if (!initialized) {
            this.initPanel();
            createStore();
            this.createToolBar();
            this.setUpLoadListener();
            initWidget();
            this.addWidgets();
        }
    }

    @Override
    protected void beforeRender() {
        this.init();
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        super.add(this.panel);
    }

    /**
     * Create Store, RpcProxy and Loader
     *
     * Code snippet:
     *
     * <pre>
     *      super.proxy = new RpcProxy<PagingLoadResult<T>>() {
     *
     *             @Override
     *              protected void load(Object loadConfig,
     *                      AsyncCallback<PagingLoadResult<T>> callback) {
     *
     *              }
     *      };
     *
     *      super.loader = new BasePagingLoader<PagingLoadResult<T>>(proxy);
     *      super.loader.setRemoteSort(false);
     * </pre>
     */
    public abstract void createStore();

    public abstract void initWidget();

    protected abstract void onLoaderBeforeLoad(LoadEvent le);

    protected abstract void onLoaderLoad(LoadEvent le);

    protected abstract void onLoaderLoadException(LoadEvent le);

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @return the initialized
     */
    public boolean isInitialized() {
        return initialized;
    }

    /**
     *
     * @return The Widget
     */
    public C getWidget() {
        return widget;
    }

    private void initPanel() {
        this.panel = new ContentPanel();
        this.panel.setHeaderVisible(false);
        this.panel.setLayout(new FitLayout());
    }

    private void createToolBar() {
        if (loader == null) {
            throw new NullPointerException("The 'Loader' must not be null. "
                    + "Create it into 'createStore' method.");
        }

        this.toolBar = new GeoPlatformPagingToolBar(this.pageSize);
        this.toolBar.bind(loader);
    }

    private void addWidgets() {
        if (widget == null) {
            throw new NullPointerException("The 'Widget' must be not null. "
                    + "Create it into 'initWidget' method.");
        }

        this.panel.add(widget);
        this.panel.setBottomComponent(this.toolBar);
    }

    private void setUpLoadListener() {
        loader.addLoadListener(new LoadListener() {

            @Override
            public void loaderBeforeLoad(LoadEvent le) {
                toolBar.enableRefresh();
                onLoaderBeforeLoad(le);
            }

            @Override
            public void loaderLoad(LoadEvent le) {
                onLoaderLoad(le);
            }

            @Override
            public void loaderLoadException(LoadEvent le) {
                onLoaderLoadException(le);
            }
        });
    }

    public void reset() {
        this.store.removeAll();
        this.toolBar.clear();
    }
}
