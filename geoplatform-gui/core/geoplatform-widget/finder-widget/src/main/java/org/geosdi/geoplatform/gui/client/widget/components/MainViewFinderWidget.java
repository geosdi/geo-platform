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
package org.geosdi.geoplatform.gui.client.widget.components;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.CheckBoxGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import java.util.Collections;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MainViewFinderWidget extends GeoPlatformContentPanel {

    public MainViewFinderWidget() {
        super(false);
    }

    @Override
    public void initSize() {
        super.setSize(600, 650);
    }

    @Override
    public void setPanelProperties() {
        super.setHeaderVisible(false);
    }

    @Override
    public void addComponent() {
        TabPanel tabPanel = new TabPanel();
        super.add(tabPanel);

        TabItem searchTab = new TabItem("Search");
        searchTab.setSize(600, 650);
        tabPanel.add(searchTab);

        searchTab.add(this.addSearchPanel());
        searchTab.add(this.addGridPanel());

        TabItem metadataTab = new TabItem("Full Metadata");
        tabPanel.add(metadataTab);
    }

    private LayoutContainer addSearchPanel() {
        // Search Panel
        LayoutContainer searchPanel = new LayoutContainer();
        searchPanel.setLayout(new ColumnLayout());

        // Search Panel - Left
        LayoutContainer left = new LayoutContainer();
        left.setLayout(new ColumnLayout());
//        left.setStyleAttribute("paddingRight", "10px");

        Label searchLabel = new Label("Search Text");
        left.add(searchLabel, new ColumnData(300.0));

        TextField<String> searchTextField = new TextField<String>();
        searchTextField.setWidth(250);
        left.add(searchTextField);

        Button searchButton = new Button("Search");
        left.add(searchButton);

        // Search Panel - Right
        LayoutContainer right = new LayoutContainer();
        right.setLayout(new ColumnLayout());
//        right.setStyleAttribute("paddingLeft", "10px");

        Label optionsLabel = new Label("Search Options");
        right.add(optionsLabel, new ColumnData(240.0));

        CheckBoxGroup optionsCheckboxgroup = new CheckBoxGroup();
        optionsCheckboxgroup.setOrientation(Orientation.VERTICAL);
        right.add(optionsCheckboxgroup, new ColumnData(100.0));

        CheckBox TitleCheckbox = new CheckBox();
        TitleCheckbox.setBoxLabel("Title");
        TitleCheckbox.setHideLabel(true);
        optionsCheckboxgroup.add(TitleCheckbox);

        CheckBox abstractCheckbox = new CheckBox();
        abstractCheckbox.setBoxLabel("Abstract");
        abstractCheckbox.setHideLabel(true);
        optionsCheckboxgroup.add(abstractCheckbox);

        CheckBox keywordsCheckbox = new CheckBox();
        keywordsCheckbox.setBoxLabel("Keywords");
        keywordsCheckbox.setHideLabel(true);
        optionsCheckboxgroup.add(keywordsCheckbox);

        CheckBox allCheckbox = new CheckBox();
        allCheckbox.setBoxLabel("Select/Deselect all");
        allCheckbox.setHideLabel(true);
        right.add(allCheckbox, new ColumnData(Style.DEFAULT));

        searchPanel.add(left, new ColumnData(0.6));
        searchPanel.add(right, new ColumnData(0.4));

        return searchPanel;
    }

    private LayoutContainer addGridPanel() {
        // Grid Panel
        LayoutContainer gridPanel = new LayoutContainer();

        Text resultLabel = new Text("Search Result");
        gridPanel.add(resultLabel);

        Grid grid = new Grid(new ListStore(), new ColumnModel(Collections.<ColumnConfig>emptyList()));
        grid.setBorders(true);
        gridPanel.add(grid);

        Text operationLabel = new Text("Operations with selected");
        gridPanel.add(operationLabel);

        Button addLayerButton = new Button("Add To Layer Tree");
        gridPanel.add(addLayerButton);

        return gridPanel;
    }
}
