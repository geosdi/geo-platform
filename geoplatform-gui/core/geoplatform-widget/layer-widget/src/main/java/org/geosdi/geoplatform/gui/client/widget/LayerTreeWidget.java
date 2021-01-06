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

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.puregwt.timeout.IGPExpandTreeNodeHandler;
import org.geosdi.geoplatform.gui.client.widget.tree.GeoPlatformTreeWidget;
import org.geosdi.geoplatform.gui.client.widget.tree.builder.LayerTreeBuilder;
import org.geosdi.geoplatform.gui.client.widget.tree.menu.automator.GPTreeMenuAutomator;
import org.geosdi.geoplatform.gui.client.widget.tree.panel.GinTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.properties.GPTreeProperties;
import org.geosdi.geoplatform.gui.client.widget.tree.store.GinTreeStore;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.layers.IGPBuildTreeHandler;

import javax.inject.Inject;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class LayerTreeWidget extends GeoPlatformTreeWidget<GPBeanTreeModel> implements IGPBuildTreeHandler, IGPExpandTreeNodeHandler {

    public static final String LAYER_TREE_PANEL = "layerTreePanel";
    //
    @Inject
    private LayerTreeBuilder treeBuilder;
    private final GPTreeProperties treeProperties;
    private final GPTreeMenuAutomator menuAutomator;

    /**
     * @param theStore
     * @param theThree
     * @param theTreeProperties
     * @param theMenuAutomator
     */
    @Inject
    public LayerTreeWidget(GinTreeStore theStore, GinTreePanel theThree, GPTreeProperties theTreeProperties,
            GPTreeMenuAutomator theMenuAutomator) {
        super(theStore.get(), theThree.get());
        this.treeProperties = theTreeProperties;
        this.menuAutomator = theMenuAutomator;
        super.afterPropertiesSet();
    }

    @Override
    public void buildTree() {
        this.treeBuilder.buildTree();
    }

    @Override
    public final void setTreePanelProperties() {
        this.treeProperties.bind(this);
        this.menuAutomator.automateTreeMenuCreation();
    }

    @Override
    public void expandNode(FolderTreeNode node) {
        node.setLoading(Boolean.TRUE);
        super.tree.fireEvent(Events.BeforeExpand, new TreePanelEvent(super.tree, node));
    }

    @Override
    public void rebuildTree() {
        this.treeBuilder.rebuildTree();
    }

    @Override
    protected void registerTree() {
        Registry.register(LAYER_TREE_PANEL, this.tree);
    }
}
