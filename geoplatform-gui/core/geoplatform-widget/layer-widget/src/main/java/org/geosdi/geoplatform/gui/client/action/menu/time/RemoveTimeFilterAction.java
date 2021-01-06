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
import com.google.gwt.event.shared.HandlerRegistration;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.action.menu.tree.TreeMenuCompositeAction;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.AbstractMementoOriginalProperties;
import org.geosdi.geoplatform.gui.client.puregwt.decorator.event.TreeChangeLabelEvent;
import org.geosdi.geoplatform.gui.client.puregwt.reset.event.GPResetComponentsEvent;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableEvent;
import org.geosdi.geoplatform.gui.impl.map.event.TimeFilterLayerMapEvent;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.decorator.event.GPTreeLabelEvent;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.tree.GPTreeMenuActionHandlerManager;

import java.util.List;

import static com.google.gwt.user.client.ui.AbstractImagePrototype.create;
import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.gui.client.LayerResources.ICONS;
import static org.geosdi.geoplatform.gui.client.widget.time.LayerTimeFilterWidget.LAYER_TIME_DELIMITER;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @author Vito Salvia <vito.salvia@gmail.com>
 * @email nazzareno.sileno@geosdi.org
 */
public class RemoveTimeFilterAction extends MenuBaseAction implements TreeMenuCompositeAction<GPBeanTreeModel> {

    private GPTreePanel<GPBeanTreeModel> treePanel;
    private final TimeFilterLayerMapEvent timeFilterLayerMapEvent = new TimeFilterLayerMapEvent();
    private final GPTreeLabelEvent labelEvent = new TreeChangeLabelEvent();

    /**
     * @param theTreePanel
     */
    public RemoveTimeFilterAction(GPTreePanel<GPBeanTreeModel> theTreePanel) {
        super("RemoveTimeFilter", create(ICONS.cqlFilterDelete()));
        this.treePanel = theTreePanel;
    }

    @Override
    public void componentSelected(MenuEvent ce) {
        GPBeanTreeModel itemSelected = this.treePanel.getSelectionModel().getSelectedItem();
        if (itemSelected instanceof FolderTreeNode) {
            throw new IllegalArgumentException("The Time Filter can't be applied to a folder");
        }
        GPLayerTreeModel layerSelected = (GPLayerTreeModel) treePanel.getSelectionModel().getSelectedItem();
        IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
        AbstractMementoOriginalProperties memento = mementoSave.copyOriginalProperties(layerSelected);
        layerSelected.setTimeFilter(null);
        layerSelected.setVariableTimeFilter(null);
        String layerName;
        if (layerSelected.getAlias() != null && layerSelected.getAlias().indexOf(LAYER_TIME_DELIMITER) != -1) {
            layerName = layerSelected.getAlias().substring(0, layerSelected.getAlias().indexOf(LAYER_TIME_DELIMITER));
        } else {
            layerName = layerSelected.getLabel();
        }
        layerSelected.setAlias(layerName);
        mementoSave.putOriginalPropertiesInCache(memento);
        WidgetPropertiesHandlerManager.fireEvent(labelEvent);
        WidgetPropertiesHandlerManager.fireEvent(new GPResetComponentsEvent());
        timeFilterLayerMapEvent.setLayerBean(layerSelected);
        GPHandlerManager.fireEvent(timeFilterLayerMapEvent);
        treePanel.refresh(layerSelected);
    }

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
        return (((theModel != null) && (theModel instanceof RasterTreeNode)) ?
                ((((RasterTreeNode) theModel).isTemporalLayer()) && (((RasterTreeNode) theModel).getTimeFilter() != null)) : TRUE);
    }
}
