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
package org.geosdi.geoplatform.gui.client.widget.tree;

import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.data.TreeLoader;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel.CheckCascade;
import java.util.ArrayList;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public abstract class GeoPlatformAsyncTreeWidget<T extends GPBeanTreeModel> {

    protected TreeStore<T> store;
    protected TreePanel<T> tree;
    protected RpcProxy<ArrayList<T>> proxy;
    private TreeLoader<T> loader;

    /**
     * @Constructor
     *
     */
    public GeoPlatformAsyncTreeWidget() {
        this.proxy = this.generateRpcProxy();
        this.loader = this.generateTreeLoader();
        this.store = new TreeStore<T>(this.loader);
        this.tree = new TreePanel<T>(this.store);
    }

    /**
     * True to highlight nodes when the mouse is over (defaults to true).
     *
     * @param trackMouseOver
     *            true to highlight nodes on mouse over
     */
    public void setTrackMouseOver(boolean trackMouseOver) {
        this.tree.setTrackMouseOver(trackMouseOver);
    }

    /**
     * Sets whether check boxes are used in the tree.
     *
     * @param checkable
     *            true for check boxes
     */
    public void setCheckable(boolean checkable) {
        this.tree.setCheckable(checkable);
    }

    /**
     * Sets the cascading behavior for check tree (defaults to PARENTS). When
     * using CHILDREN, it is important to note that the cascade will only be
     * applied to rendered nodes. {@link #setAutoLoad(boolean)} can be used to
     * fully render the tree on render.
     * <p>
     * Valid values are:
     * <ul>
     * <li>NONE - no cascading</li>
     * <li>PARENTS - cascade to parents</li>
     * <li>CHILDREN - cascade to children</li>
     * </ul>
     *
     * @param checkStyle
     *            the child style
     */
    public void setCheckStyle(CheckCascade checkStyle) {
        this.tree.setCheckStyle(checkStyle);
    }

    public abstract void setTreePanelProperties();

    /**
     * @return the store
     */
    public TreeStore<T> getStore() {
        return store;
    }

    /**
     * @param store
     *            the store to set
     */
    public void setStore(TreeStore<T> store) {
        this.store = store;
    }

    /**
     * @return the tree
     */
    public TreePanel<T> getTree() {
        return tree;
    }

    /**
     * @param tree
     *            the tree to set
     */
    public void setTree(TreePanel<T> tree) {
        this.tree = tree;
    }

    public abstract RpcProxy<ArrayList<T>> generateRpcProxy();

    public abstract TreeLoader<T> generateTreeLoader();

}
