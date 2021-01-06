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
package org.geosdi.geoplatform.gui.client.widget.wfs.tree;

import com.google.gwt.user.client.Timer;
import org.geosdi.geoplatform.gui.client.config.annotation.tree.WFSLayerTree;
import org.geosdi.geoplatform.gui.client.config.annotation.tree.WFSLayerTreeStore;
import org.geosdi.geoplatform.gui.client.model.tree.WFSRootLayerTreeNode;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreeStore;
import org.geosdi.geoplatform.gui.client.widget.wfs.tree.visitor.GPWFSCompositeVisitor;
import org.geosdi.geoplatform.gui.client.widget.wfs.tree.visitor.WFSVisitorCheck;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.Boolean.TRUE;

/**
 * @author Vito Salvia <vito.salvia@gmail.com>
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 */
@Singleton
public class WFSLayerTreeBuilder implements GPWFSLayerTreeBuilder {

    private final static Logger logger = Logger.getLogger("WFSLayerTreeBuilder");
    //
    private final GPTreeStore store;
    private final GPTreePanel tree;
    private final WFSRootLayerTreeNode root;
    private final GPWFSCompositeVisitor visitor;

    /**
     * @param theStore
     * @param theTree
     * @param theRoot
     */
    @Inject
    public WFSLayerTreeBuilder(@WFSLayerTreeStore GPTreeStore theStore, @WFSLayerTree GPTreePanel theTree,
            WFSRootLayerTreeNode theRoot) {
        this.store = theStore;
        this.tree = theTree;
        this.root = theRoot;
        this.visitor = new WFSVisitorCheck(this.tree);
    }

    /**
     * @param twin
     * @param childrens
     */
    @Override
    public void buildTree(GPBeanTreeModel twin, List<GPLayerBean> childrens) {
        this.root.bind(twin);
        this.root.addChildrens(childrens);
        this.store.add(this.root, TRUE);
        Timer t = new Timer() {

            @Override
            public void run() {
                enableTreeCheck();
                tree.setExpanded(root, TRUE);
            }

        };
        t.schedule(500);
    }

    @Override
    public void rebuildTree() {
        this.root.removeAll();
        this.store.removeAll();
    }

    /**
     *
     */
    private void enableTreeCheck() {
        this.root.accept(this.visitor);
    }
}