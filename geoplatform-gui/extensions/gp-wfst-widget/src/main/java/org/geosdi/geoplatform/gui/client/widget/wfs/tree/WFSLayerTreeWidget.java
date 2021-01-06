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

import org.geosdi.geoplatform.gui.client.config.annotation.tree.WFSLayerTree;
import org.geosdi.geoplatform.gui.client.config.annotation.tree.WFSLayerTreeStore;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.LayerTreeHandler;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreeStore;
import org.geosdi.geoplatform.gui.client.widget.tree.GeoPlatformTreeWidget;
import org.geosdi.geoplatform.gui.client.widget.wfs.tree.properties.WFSTreeBasicProperties;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Vito Salvia <vito.salvia@gmail.com>
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 */
public class WFSLayerTreeWidget extends GeoPlatformTreeWidget<GPBeanTreeModel> implements LayerTreeHandler {

    private final WFSTreeBasicProperties treeProperties;
    private final GPEventBus bus;
    @Inject
    private WFSLayerTreeBuilder treeBuilder;

    /**
     * @param theStore
     * @param theTree
     * @param theTreeProperties
     */
    @Inject
    public WFSLayerTreeWidget(GPEventBus theBus, @WFSLayerTreeStore GPTreeStore theStore,
            @WFSLayerTree GPTreePanel theTree, WFSTreeBasicProperties theTreeProperties) {
        super(theStore, theTree);
        checkArgument(theBus != null, "The Parameter bus must not be null.");
        checkArgument(theTreeProperties != null, "The Parameter treeProperties must not be null.");
        this.bus = theBus;
        this.treeProperties = theTreeProperties;
        super.afterPropertiesSet();
        this.bus.addHandler(TYPE, this);
    }

    /**
     * @param twin
     * @param childres
     */
    @Override
    public void buildLayerTree(GPBeanTreeModel twin, List<GPLayerBean> childres) {
        this.treeBuilder.buildTree(twin, childres);
    }

    @Override
    public final void setTreePanelProperties() {
        this.treeProperties.setTreeBasicProperties();
    }

    @Override
    protected void registerTree() {
    }

    public void reset() {
        this.treeBuilder.rebuildTree();
    }
}