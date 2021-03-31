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
package org.geosdi.geoplatform.gui.client.widget.components.wms;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.WidgetListener;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import org.geosdi.geoplatform.gui.client.i18n.CatalogFinderConstants;
import org.geosdi.geoplatform.gui.client.model.FullRecord;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.responce.URIDTO;

import java.util.ArrayList;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CatalogWMSCapabilitiesWidget extends GeoPlatformWindow implements GPCatalogWMSCapabilitiesWidget {

    private CatalogGridLayersWidget catalogGridLayers;
    private final TreePanel tree;
    private final GPEventBus bus;
    private FullRecord fullRecord;

    public CatalogWMSCapabilitiesWidget(TreePanel theTree, GPEventBus theBus) {
        super(Boolean.TRUE);
        this.tree = theTree;
        this.bus = theBus;

        this.bus.addHandler(TYPE, this);
    }

    @Override
    public void addComponent() {
        this.catalogGridLayers = new CatalogGridLayersWidget(this.tree);
        super.add(this.catalogGridLayers.getFormPanel());
    }

    @Override
    public void reset() {
        this.catalogGridLayers.resetComponents();
//        this.fullRecord = null;
    }

    @Override
    public void initSize() {
        super.setSize(600, 400);
        super.setHeadingHtml(CatalogFinderConstants.INSTANCE.
                CatalogWMSCapabilitiesWidget_headingText());
    }

    @Override
    public void setWindowProperties() {
        super.setLayout(new FitLayout());
        super.setResizable(Boolean.FALSE);
        super.setModal(Boolean.TRUE);
        super.setCollapsible(Boolean.FALSE);
        super.setPlain(Boolean.TRUE);

        super.addWidgetListener(new WidgetListener() {

            @Override
            public void widgetAttached(ComponentEvent ce) {
                catalogGridLayers.getGrid().setHeight(300);
            }

        });
    }
    
    @Override
    public void show() {
        super.show();
        this.catalogGridLayers.maskGrid();
    }

    @Override
    public String getWMSGetCapabilitiesURL() {
        if (this.fullRecord == null) {
            throw new IllegalStateException("Full Record must not be null");
        }
        URIDTO uriDTO = this.fullRecord.getURIDtoForWMSGetCapabilities();
        return (uriDTO != null) ? uriDTO.getServiceURL() : null;
    }

    @Override
    public void maskGrid() {
        this.catalogGridLayers.maskGrid();
    }

    @Override
    public void unMaskGrid() {
        this.catalogGridLayers.unMaskGrid();
    }

    @Override
    public <L extends GPLayerBean> void fillStore(ArrayList<L> beans) {
        this.catalogGridLayers.fillStore(beans);
    }

    @Override
    public void injectFullRecord(FullRecord fullRecord) {
        this.fullRecord = fullRecord;
    }

}
