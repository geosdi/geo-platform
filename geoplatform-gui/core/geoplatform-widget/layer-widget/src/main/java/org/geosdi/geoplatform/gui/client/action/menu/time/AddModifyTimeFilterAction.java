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
package org.geosdi.geoplatform.gui.client.action.menu.time;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.action.menu.tree.TreeMenuCompositeAction;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.widget.time.LayerTimeFilterWidget;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableEvent;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.tree.GPTreeMenuActionHandlerManager;

import java.util.List;
import java.util.logging.Logger;

import static java.lang.Boolean.TRUE;

/**
 * @author Nazzareno Sileno <nazzareno.sileno@geosdi.org>
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vito Salvia <vito.salvia@gmail.com>
 */
public class AddModifyTimeFilterAction extends MenuBaseAction implements TreeMenuCompositeAction<GPBeanTreeModel> {

    private static final Logger logger = Logger.getLogger("AddModifyTimeFilterAction");
    //
    private GPTreePanel<GPBeanTreeModel> treePanel;
    private LayerTimeFilterWidget timeFilterWidget;

    /**
     * @param theTreePanel
     */
    public AddModifyTimeFilterAction(GPTreePanel<GPBeanTreeModel> theTreePanel) {
        super("AddModifyCQLFilter", AbstractImagePrototype.create(LayerResources.ICONS.cqlFilter()));
        this.treePanel = theTreePanel;
        this.timeFilterWidget = new LayerTimeFilterWidget(theTreePanel);
    }

    @Override
    public void componentSelected(MenuEvent ce) {
        Menu parentMenu = ce.getMenu().getParentItem().getParentMenu();
        timeFilterWidget.setPagePosition(parentMenu.getPosition(true).x, parentMenu.getPosition(true).y);
        timeFilterWidget.show();
    }

    /**
     * @return {@link HandlerRegistration}
     */
    @Override
    public HandlerRegistration addTreeMenuSelectionHandler() {
        return GPTreeMenuActionHandlerManager.addHandler(TYPE, this);
    }

    /**
     * @param selection
     */
    @Override
    public void manageTreeSelection(List<GPBeanTreeModel> selection) {
        boolean selectionEnabled = ((selection != null) && (selection.size() == 1) ? this.manageTreeInternalSelection(selection.get(0)) : TRUE);
        this.handlerManager.fireEvent(new ActionEnableEvent((this.isEnabled() ? selectionEnabled : this.isEnabled())));
    }

    /**
     * @param theModel
     * @return {@link Boolean}
     */
    boolean manageTreeInternalSelection(GPBeanTreeModel theModel) {
        return (((theModel != null) && (theModel instanceof RasterTreeNode)) ? ((RasterTreeNode) theModel).isTemporalLayer() : TRUE);
    }
}