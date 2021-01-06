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

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.core.XTemplate;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.StoreFilterField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.RowExpander;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.common.collect.Lists;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.i18n.CatalogFinderConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.widget.expander.CatalogWMSCapabilitiesExpander;
import org.geosdi.geoplatform.gui.client.widget.grid.GeoPlatformGridWidget;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.server.GPLayerGrid;
import org.geosdi.geoplatform.gui.puregwt.grid.event.DeselectGridElementHandler;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.DisplayLayersProgressBarEvent;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * @param <L>
 */
public class CatalogGridLayersWidget<L extends GPLayerBean> extends GeoPlatformGridWidget<L>
        implements DeselectGridElementHandler {

    private FormPanel formPanel;
    private final TreePanel tree;
    private Button done;
    private RowExpander rowExpander;
    private final CatalogWMSCapabilitiesExpander expander;
    private final DisplayLayersProgressBarEvent hideProgressBar = new DisplayLayersProgressBarEvent(false);

    public CatalogGridLayersWidget(TreePanel theTree) {
        super(Boolean.FALSE);
        this.initFormPanel();
        this.tree = theTree;
        this.expander = new CatalogWMSCapabilitiesExpander(this);
        LayerHandlerManager.addHandler(DeselectGridElementHandler.TYPE, this);
    }

    @Override
    public void setGridProperties() {
        grid.setAutoExpandColumn(GPLayerGrid.GPLayerBeanKeyValue.GPLAYER_NAME.getValue());
        grid.setBorders(false);

        grid.getView().setForceFit(true);
        grid.setLoadMask(true);

        grid.addPlugin(this.rowExpander);

        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.MULTI);

        grid.addListener(Events.CellClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                done.enable();
            }

        });
    }

    @Override
    public ColumnModel prepareColumnModel() {
        List<ColumnConfig> configs = Lists.<ColumnConfig>newArrayList();

        XTemplate tpl = XTemplate.create(
                "<p><b>Abstract:</b> {abstractText}</p>");

        rowExpander = new RowExpander(tpl);

        configs.add(rowExpander);

        ColumnConfig name = new ColumnConfig();
        name.setId(GPLayerGrid.GPLayerBeanKeyValue.GPLAYER_NAME.getValue());
        name.setHeaderHtml(CatalogFinderConstants.INSTANCE.CatalogGridLayersWidget_layerNameHeaderText());
        name.setWidth(200);
        configs.add(name);

        ColumnConfig title = new ColumnConfig();
        title.setId(GPLayerGrid.GPLayerBeanKeyValue.GPLAYER_LABEL.getValue());
        title.setHeaderHtml(CatalogFinderConstants.INSTANCE.CatalogGridLayersWidget_titleHeaderText());
        title.setWidth(150);
        configs.add(title);

        return new ColumnModel(configs);
    }

    @Override
    public void createStore() {
        this.store = new ListStore<L>();
    }

    /**
     * Clean The Store
     */
    @Override
    public void cleanUpTheStore() {
        if (this.grid.getView().getBody().isMasked()) {
            unMaskGrid();
        }
        this.store.removeAll();
    }

    /**
     * Create mask effect on Grid
     */
    public void maskGrid() {
        this.grid.getView().getBody()
                .mask(WindowsConstants.INSTANCE.loadingLayersText());
    }

    /**
     * Remove mask effect from the Grid
     */
    public void unMaskGrid() {
        this.grid.getView().getBody().unmask();
    }

    /**
     *
     * @param beans {@link ArrayList} of GPLayerBean to fill the Store
     */
    public void fillStore(ArrayList<L> beans) {
        this.store.add(beans);
    }

    /**
     *
     */
    public void cleanStore() {
        this.store.removeAll();
    }

    public void resetComponents() {
        this.store.removeAll();
        unMaskGrid();
        this.grid.getSelectionModel().deselectAll();
        this.done.disable();
    }

    public List<L> getSelectedItems() {
        return this.grid.getSelectionModel().getSelectedItems();
    }

    /**
     * @return the tree
     */
    public TreePanel getTree() {
        return tree;
    }

    /**
     *
     * @return the FormPanel
     */
    public FormPanel getFormPanel() {
        return this.formPanel;
    }

    @Override
    public void deselectElements() {
        this.grid.getSelectionModel().deselectAll();
        this.done.disable();
        LayerHandlerManager.fireEvent(hideProgressBar);
    }

    private void initFormPanel() {
        this.formPanel = new FormPanel();
        this.formPanel.setHeaderVisible(false);
        this.formPanel.setFrame(true);
        this.formPanel.setLayout(new FlowLayout());

        this.formPanel.add(this.grid);

        StoreFilterField<L> filter = this.createFilter();
        filter.setToolTip(CatalogFinderConstants.INSTANCE.CatalogGridLayersWidget_filterTooltipText());
        filter.bind(super.store);

        this.formPanel.setButtonAlign(Style.HorizontalAlignment.LEFT);
        this.formPanel.getButtonBar().add(filter);
        this.formPanel.getButtonBar().add(new FillToolItem());

        this.done = new Button(ButtonsConstants.INSTANCE.doneText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.done()));

        this.done.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                expander.executeActionRequest();
            }

        });

        this.done.disable();

        this.formPanel.getButtonBar().add(this.done);
    }

    private StoreFilterField<L> createFilter() {
        return new StoreFilterField<L>() {

            @Override
            protected boolean doSelect(Store<L> store, L parent, L record,
                    String property, String filter) {
                RegExp expression = RegExp.compile(filter, "i"); // Ignore Case
                if (expression.test(record.getName())
                        || expression.test(record.getTitle())) {
                    return true;
                }
                String abstractText = record.getAbstractText() == null
                        ? null : record.getAbstractText();
                if (abstractText != null) {
                    if (expression.test(abstractText)) {
                        return true;
                    }
                }
                return false;
            }

        };
    }

}
