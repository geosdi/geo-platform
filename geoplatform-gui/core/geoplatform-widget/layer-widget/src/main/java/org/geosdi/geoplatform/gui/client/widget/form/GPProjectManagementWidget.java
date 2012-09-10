/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 *
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 *
 */
package org.geosdi.geoplatform.gui.client.widget.form;

import com.extjs.gxt.ui.client.widget.Component;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProject;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.client.widget.pagination.projects.GPProjectSearchPanel;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPProjectManagementWidget extends GeoPlatformWindow {

    public final static int COMPONENT_WIDTH = 686;
    public final static int COMPONENT_HEIGHT = 533;
    private GPProjectSearchPanel projectSearchPanel;
    private ShareProjectPanel shareProjectPanel;

    public GPProjectManagementWidget(boolean lazy) {
        super(lazy);
    }

    @Override
    public void show() {
        super.init();
        this.showSearchProjectPanel();
        super.show();
    }

    @Override
    public void setWindowProperties() {
        super.setHeading("GeoPlatform Project Management");
    }

    public void showSharingPanel(GPClientProject clientProject) {
//        if (this.indexOf(shareProjectPanel) != -1) {
//            this.remove(projectSearchPanel);
//            System.out.println("Removed elemento");
//        }
//        super.removeAll();
        Component itemToRemove = super.getItemByItemId(this.projectSearchPanel.getItemId());
        if (itemToRemove != null) {
            super.remove(itemToRemove);
        }
        super.add(this.shareProjectPanel);
        this.shareProjectPanel.loadData(clientProject);
        super.doLayout(Boolean.TRUE);
    }

    public void showSearchProjectPanel() {
//        if (this.indexOf(shareProjectPanel) != -1) {
//            this.remove(shareProjectPanel);
//            System.out.println("Removed elemento");
//        }
//        super.removeAll();
        Component itemToRemove = super.getItemByItemId(this.shareProjectPanel.getItemId());
        if (itemToRemove != null) {
            super.remove(itemToRemove);
        }
        super.add(this.projectSearchPanel);
        this.projectSearchPanel.loadData();
        super.doLayout(Boolean.TRUE);
    }

    @Override
    public void addComponent() {
        this.shareProjectPanel = new ShareProjectPanel(this, Boolean.TRUE);
        this.projectSearchPanel = new GPProjectSearchPanel(this);
    }

    @Override
    public void finalizeInitOperations() {
        super.finalizeInitOperations();
    }

    @Override
    public void initSize() {
        super.setSize(700, 565);
    }
}
