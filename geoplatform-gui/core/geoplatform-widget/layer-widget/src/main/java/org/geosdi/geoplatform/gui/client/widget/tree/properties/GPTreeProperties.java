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
package org.geosdi.geoplatform.gui.client.widget.tree.properties;

import org.geosdi.geoplatform.gui.client.widget.LayerTreeWidget;
import org.geosdi.geoplatform.gui.client.widget.tree.properties.basic.GPTreeBasicProperties;
import org.geosdi.geoplatform.gui.client.widget.tree.properties.check.GPTreeCheckChange;
import org.geosdi.geoplatform.gui.client.widget.tree.properties.dnd.GPTreeDndManager;
import org.geosdi.geoplatform.gui.client.widget.tree.properties.expand.GPTreeExpandListener;
import org.geosdi.geoplatform.gui.client.widget.tree.properties.handler.GPTreeHandlerManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Singleton
public class GPTreeProperties implements TreeProperties {

    private final GPTreeExpandListener treeExpandListener;
    private final GPTreeDndManager treeDndManager;
    private final GPTreeCheckChange treeCheckChange;
    private final GPTreeBasicProperties treeBasicProperties;
    private final GPTreeHandlerManager treeHandlerManager;
    private LayerTreeWidget layerTreeWidget;

    /**
     * @param theTreeExpandListener
     * @param theTreeDndManager
     * @param theTreeCheckChange
     * @param theTreeBasicProperties
     * @param theTreeHandlerManager
     */
    @Inject
    public GPTreeProperties(GPTreeExpandListener theTreeExpandListener, GPTreeDndManager theTreeDndManager,
            GPTreeCheckChange theTreeCheckChange, GPTreeBasicProperties theTreeBasicProperties,
            GPTreeHandlerManager theTreeHandlerManager) {
        this.treeExpandListener = theTreeExpandListener;
        this.treeDndManager = theTreeDndManager;
        this.treeCheckChange = theTreeCheckChange;
        this.treeBasicProperties = theTreeBasicProperties;
        this.treeHandlerManager = theTreeHandlerManager;
    }

    protected void setTreeProperties() {
        this.treeExpandListener.addExpandListener();
        this.treeDndManager.enableDndSupport();
        this.treeCheckChange.enableCheckChange();
        this.treeBasicProperties.setTreeBasicProperties();
        this.treeHandlerManager.addHandlers(layerTreeWidget);
    }

    @Override
    public void bind(LayerTreeWidget theLayerTreeWidget) {
        if (theLayerTreeWidget == null) {
            throw new IllegalArgumentException("The LayerTreeWidget to Bind "
                    + "must not be null.");
        }

        this.layerTreeWidget = theLayerTreeWidget;
        this.setTreeProperties();
    }
}
