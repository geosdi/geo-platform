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
package org.geosdi.geoplatform.gui.client.widget.grid;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.google.common.collect.Lists;
import java.util.List;
import org.geosdi.geoplatform.gui.client.i18n.ServerModuleConstants;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.model.server.GPServerBeanModel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class ManageServerGrid extends GeoPlatformGridWidget<GPServerBeanModel> {

    private RowEditor<GPServerBeanModel> rowEditor = new RowEditor<GPServerBeanModel>();

    public ManageServerGrid(boolean lazy) {
        super(lazy);
    }

    @Override
    public void setGridProperties() {
        grid.setAutoExpandColumn("urlServer");
        grid.setBorders(true);
        grid.addPlugin(getRowEditor());
        grid.setHeight(300);
    }

    @Override
    public ColumnModel prepareColumnModel() {
        List<ColumnConfig> configs = Lists.<ColumnConfig>newArrayList();
        ColumnConfig aliasColumn = new ColumnConfig();
        aliasColumn.setId("alias");
        aliasColumn.setHeaderHtml(ServerModuleConstants.INSTANCE.
                serverAliasText());
        aliasColumn.setWidth(220);

        GPSecureStringTextField aliasTextfield = new GPSecureStringTextField();
        aliasTextfield.setAllowBlank(false);
        aliasColumn.setEditor(new CellEditor(aliasTextfield));
        configs.add(aliasColumn);

        ColumnConfig urlColumn = new ColumnConfig();
        urlColumn.setId("urlServer");
        urlColumn.setHeaderHtml(ServerModuleConstants.INSTANCE.
                serverURLText());
        urlColumn.setWidth(400);

        GPSecureStringTextField urlTextfield = new GPSecureStringTextField();
        urlTextfield.setAllowBlank(false);
//        urlTextfield.setEmptyText("http://");
        urlColumn.setEditor(new CellEditor(urlTextfield));
        configs.add(urlColumn);
        return new ColumnModel(configs);
    }

    @Override
    public void createStore() {
        this.store = new ListStore<GPServerBeanModel>();
    }

    /**
     * @return the rowEditor
     */
    public RowEditor<GPServerBeanModel> getRowEditor() {
        return rowEditor;
    }

    /**
     * @param rowEditor the rowEditor to set
     */
    public void setRowEditor(RowEditor<GPServerBeanModel> rowEditor) {
        this.rowEditor = rowEditor;
    }
}
