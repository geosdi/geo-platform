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
package org.geosdi.geoplatform.gui.client.model.tree;

import com.extjs.gxt.ui.client.data.ModelData;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.widget.wfs.tree.visitor.GPWFSCompositeVisitor;
import org.geosdi.geoplatform.gui.configuration.composite.GPTreeCompositeType;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.model.tree.TreeStatusEnum;
import org.geosdi.geoplatform.gui.model.tree.visitor.IVisitor;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class WFSRootLayerTreeNode extends GPBeanTreeModel implements GPWFSRootLayerTreeNode {

    private GPBeanTreeModel twin;

    /**
     * @param theTwin
     */
    @Override
    public void bind(GPBeanTreeModel theTwin) {
        checkArgument(theTwin != null, "The Parameter Twin must not be null.");
        this.twin = theTwin;
        super.setLabel(this.twin.getLabel());
        super.setId(this.twin.getId());
        super.setzIndex(this.twin.getzIndex());
    }

    /**
     * @param childrens
     */
    @Override
    public void addChildrens(List<GPLayerBean> childrens) {
        checkArgument(childrens != null, "The Parameter childrens must not be null.");
        for (GPLayerBean layer : childrens) {
            if (layer instanceof GPLayerTreeModel) {
                WFSLayerTreeNode child = new WFSLayerTreeNode((GPLayerTreeModel) layer);
                child.setParent(this);
                super.add(child);
            }
        }
    }

    @Override
    public AbstractImagePrototype getIcon() {
        return this.twin.getIcon();
    }

    @Override
    public GPTreeCompositeType getTreeCompositeType() {
        return this.twin.getTreeCompositeType();
    }

    @Override
    public TreeStatusEnum getTreeStatus() {
        return this.twin.getTreeStatus();
    }

    @Override
    public void accept(IVisitor visitor) {
    }

    /**
     * @param visitor
     */
    @Override
    public void accept(GPWFSCompositeVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * @return {@link Child}
     */
    @Override
    public <Child extends GPWFSLayerTreeNode> List<Child> getChildrens() {
        List<Child> childrens = Lists.newArrayList();
        for (ModelData child : super.getChildren()) {
            if ((child != null) && (child instanceof GPWFSLayerTreeNode))
                childrens.add((Child) child);
        }
        return childrens;
    }

    @Override
    public String toString() {
        return "WFSRootLayerTreeNode{" +
                "children = " + children +
                '}';
    }
}