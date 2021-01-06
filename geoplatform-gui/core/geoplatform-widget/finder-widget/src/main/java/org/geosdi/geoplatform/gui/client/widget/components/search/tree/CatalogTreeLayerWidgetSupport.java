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
package org.geosdi.geoplatform.gui.client.widget.components.search.tree;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.CatalogFinderWidgetResources;
import org.geosdi.geoplatform.gui.client.action.wms.CatalogWMSCapabilitiesAction;
import org.geosdi.geoplatform.gui.client.i18n.CatalogFinderConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.widget.components.search.pagination.RecordsContainer;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CatalogTreeLayerWidgetSupport extends GPTreeLayerWidgetSupport.TreeLayerWidgetSupport {

    public CatalogTreeLayerWidgetSupport(TreePanel<GPBeanTreeModel> theTree,
            RecordsContainer recordsContainer, GPEventBus bus) {
        super(theTree, recordsContainer, bus);
    }

    @Override
    public void createLabelComponent() {
        this.operationLabel = new Label(CatalogFinderConstants.INSTANCE.CatalogTreeLayerWidgetSupport_operationLabelText());
        this.operationLabel.setStyleName("searchOperation-Label");
    }

    @Override
    public void createLoadWMSGetCapabilitiesButton() {
        this.loadWMSGetCapabilities = new Button(ButtonsConstants.INSTANCE
                .loadWMSGetCapabilitiesText(), new CatalogWMSCapabilitiesAction(tree, bus));
        this.loadWMSGetCapabilities.setStyleAttribute("padding-left", "10px");
        this.loadWMSGetCapabilities.disable();
    }

    @Override
    public void createAddLayersTreeButton() {
        this.addLayersToTreeButton = new Button(ButtonsConstants.INSTANCE.addOnTreeText(),
                AbstractImagePrototype.create(CatalogFinderWidgetResources.ICONS.addLayer()));

        this.addLayersToTreeButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent be) {
                expander.executeActionRequest();
            }

        });

        this.addLayersToTreeButton.disable();
    }

    @Override
    public void enableAddOnTreeButton(boolean enable) {
        this.addLayersToTreeButton.setEnabled(enable);
    }

    @Override
    public void enableWMSGetCapabilitiesButton(boolean enable) {
        this.loadWMSGetCapabilities.setEnabled(enable);
    }

}
