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
package org.geosdi.geoplatform.gui.impl;

import org.geosdi.geoplatform.gui.configuration.FolderStore;
import org.geosdi.geoplatform.gui.configuration.menubar.IMenuBarContainerTool;
import org.geosdi.geoplatform.gui.configuration.toolbar.IToolbarContainerTool;
import org.geosdi.geoplatform.gui.configuration.startup.IStartupConfigurationStrategy;
import org.geosdi.geoplatform.gui.global.GeoPlatformInfo;
import org.geosdi.geoplatform.gui.global.IGeoPlatformGlobal;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
//@Component("geoPlatformGlobal")
public class GeoPlatformGlobal implements IGeoPlatformGlobal {

    /**
     *
     */
    private static final long serialVersionUID = -8229961575424418806L;

    @Autowired
    private GeoPlatformInfo geoPlatformInfo;

    private IToolbarContainerTool toolbarContainerTool;

    private IMenuBarContainerTool menuBarContainerTool;

    private FolderStore folderStore;
    
    private IStartupConfigurationStrategy strategy;

    /**
     * @return the geoPlatformInfo
     */
    @Override
    public GeoPlatformInfo getGeoPlatformInfo() {
        return geoPlatformInfo;
    }

    /**
     * @param geoPlatformInfo
     *            the geoPlatformInfo to set
     */
    public void setGeoPlatformInfo(GeoPlatformInfo geoPlatformInfo) {
        this.geoPlatformInfo = geoPlatformInfo;
    }

    /**
     * (non-Javadoc)
     *
     * @see org.geosdi.geoplatform.gui.global.IGeoPlatformGlobal#getToolbarContainerTool()
     */
    @Override
    public IToolbarContainerTool getToolbarContainerTool() {
        return toolbarContainerTool;
    }

    /**
     * @param toolbarContainerTool
     *            the toolbarContainerTool to set
     */
    public void setToolbarClientTool(IToolbarContainerTool toolbarContainerTool) {
        this.toolbarContainerTool = toolbarContainerTool;
    }

    @Override
    public IMenuBarContainerTool getMenuBarContainerTool() {
        return menuBarContainerTool;
    }

    /**
     * @param menuBarContainerTool
     *            the menuBarContainerTool to set
     */
    public void setMenuBarContainerTool(
            IMenuBarContainerTool menuBarContainerTool) {
        this.menuBarContainerTool = menuBarContainerTool;
    }

    @Override
    public FolderStore getFolderStore() {
        return this.folderStore;
    }

    public void setFolderStore(FolderStore folserStore) {
        this.folderStore = folserStore;
    }

    @Override
    public IStartupConfigurationStrategy getStrategy() {
        return this.strategy;
    }

    /**
     * @param strategy the strategy to set
     */
    public void setStrategy(IStartupConfigurationStrategy strategy) {
        this.strategy = strategy;
    }

}
