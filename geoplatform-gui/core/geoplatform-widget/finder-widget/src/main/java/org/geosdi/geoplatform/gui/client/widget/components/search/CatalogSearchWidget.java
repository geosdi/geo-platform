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
package org.geosdi.geoplatform.gui.client.widget.components.search;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.CheckBoxGroup;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Element;
import javax.inject.Inject;
import org.geosdi.geoplatform.gui.client.widget.components.search.pagination.SummaryRecordsContainer;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableEvent;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableHandler;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.gui.responce.SearchInfo;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class CatalogSearchWidget extends LayoutContainer
        implements ActionEnableHandler {

    private SearchInfo searchInfo;
    private SummaryRecordsContainer summaryRecordsContainer;
    private EventBus bus;
    //
    private CheckBox titleCheckbox;
    private CheckBox abstractCheckbox;
    private CheckBox keywordsCheckbox;
    private CheckBox allSelectedCheckbox;
    //
    private Button searchButton;
    private boolean validSelectedServer;
    private boolean validCheckBoxGroup;
    private boolean validSearchText;

    @Inject
    public CatalogSearchWidget(CatalogFinderBean theCatalogFinder,
                               SummaryRecordsContainer theSummaryRecordsContainer,
                               EventBus theBus) {
        theCatalogFinder.setSearchInfo(searchInfo = new SearchInfo());
        summaryRecordsContainer = theSummaryRecordsContainer;
        bus = theBus;
        bus.addHandler(ActionEnableEvent.TYPE, this);

        super.setLayout(new ColumnLayout());
        super.setStyleAttribute("padding", "10px");
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        LayoutContainer left = new LayoutContainer();
        left.setLayout(new ColumnLayout());

        Label searchLabel = new Label("Search Text");
        searchLabel.setStyleAttribute("color", "#4169E1");
        searchLabel.setStyleAttribute("font",
                                      "normal 14px tahoma, arial, helvetica, sans-serif");

        left.add(searchLabel, new ColumnData(300.0));

        HorizontalPanel panel = new HorizontalPanel();
        panel.setStyleAttribute("padding-top", "8px");

        final TextField<String> searchTextField = new TextField<String>();
        searchTextField.setWidth(250);
        searchTextField.setAutoValidate(true);
        searchTextField.setValidator(new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (value.trim().length() < 3) {
                    validSearchText = false;
                    manageSearchButton();
                    return "The search text must be at least 3 characters";
                }
                validSearchText = true;
                manageSearchButton();
                return null;
            }
        });
        searchTextField.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (searchButton.isEnabled()
                        && event.getKeyCode() == KeyCodes.KEY_ENTER) {
                    searchButton.fireEvent(Events.Select);
                }
            }
        });
        panel.add(searchTextField);

        searchButton = new Button("Search",
                                  new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                // Manual binding
                searchInfo.setSearchText(searchTextField.getValue().trim());
                searchInfo.setSearchTitle(titleCheckbox.getValue().booleanValue());
                searchInfo.setSearchAbstract(abstractCheckbox.getValue().booleanValue());
                searchInfo.setSearchKeywords(keywordsCheckbox.getValue().booleanValue());
                // Performing the search
                summaryRecordsContainer.searchSummaryRecords();
            }
        });
        searchButton.setStyleAttribute("padding-left", "20px");
        searchButton.disable();
        panel.add(searchButton);

        left.add(panel);

        // Search Panel - Right
        LayoutContainer right = new LayoutContainer();
        right.setLayout(new ColumnLayout());

        Label optionsLabel = new Label("Search Options");
        optionsLabel.setStyleAttribute("color", "#4169E1");
        optionsLabel.setStyleAttribute("font",
                                       "normal 14px tahoma, arial, helvetica, sans-serif");

        right.add(optionsLabel, new ColumnData(240.0));

        final CheckBoxGroup optionsCheckboxgroup = new CheckBoxGroup();
        optionsCheckboxgroup.setOrientation(Orientation.VERTICAL);
        optionsCheckboxgroup.setValidator(new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                CheckBoxGroup group = (CheckBoxGroup) field;
                if (group.getValue() == null) {
                    return "Select at least one option";
                }
                return null;
            }
        });

        Listener<FieldEvent> checkBoxListener = new Listener<FieldEvent>() {

            @Override
            public void handleEvent(FieldEvent fe) {
                manageAllSelectedCheckbox();
                validCheckBoxGroup = optionsCheckboxgroup.validate(true); // TODO false for display error tooltip message
                manageSearchButton();
            }
        };
        validCheckBoxGroup = true; // At least one checkbox of group is enabled by default

        titleCheckbox = new CheckBox();
        titleCheckbox.setBoxLabel("Title");
        titleCheckbox.setValue(true); // Enabled by default
        titleCheckbox.addListener(Events.Change, checkBoxListener);
        optionsCheckboxgroup.add(titleCheckbox);

        abstractCheckbox = new CheckBox();
        abstractCheckbox.setBoxLabel("Abstract");
        abstractCheckbox.setValue(true); // Enabled by default
        abstractCheckbox.addListener(Events.Change, checkBoxListener);
        optionsCheckboxgroup.add(abstractCheckbox);

        keywordsCheckbox = new CheckBox();
        keywordsCheckbox.setBoxLabel("Keywords");
        keywordsCheckbox.addListener(Events.Change, checkBoxListener);
        optionsCheckboxgroup.add(keywordsCheckbox);

        right.add(optionsCheckboxgroup);

        allSelectedCheckbox = new CheckBox();
        allSelectedCheckbox.setBoxLabel("Select/Deselect all");
        allSelectedCheckbox.addListener(Events.Change, new Listener<FieldEvent>() {

            @Override
            public void handleEvent(FieldEvent fe) {
                System.out.println("@@@ Change"); // TODO FIX select/deselect all!
//                Boolean checked = (Boolean) fe.getValue();
//                System.out.println("### checked " + checked);
                Boolean checkedRaw = Boolean.valueOf(((CheckBox) fe.getField()).getRawValue());
//                System.out.println("### checkedRaw " + checkedRaw);

                boolean allSelected = false;
                if (checkedRaw) {
                    allSelected = true;
                }
                System.out.println("*** allSelected " + allSelected);

                titleCheckbox.setValue(allSelected);
                abstractCheckbox.setValue(allSelected);
                keywordsCheckbox.setValue(allSelected);
            }
        });
        right.add(allSelectedCheckbox);

        add(left, new ColumnData(0.6));
        add(right, new ColumnData(0.4));
    }

    private void manageAllSelectedCheckbox() {
        if (titleCheckbox.getValue() && abstractCheckbox.getValue()
                && keywordsCheckbox.getValue()) {
            System.out.println("+++ set all TRUE");
//            allSelectedCheckbox.setValue(true);
            allSelectedCheckbox.setRawValue("true");
        } else {
            System.out.println("+++ set all FALSE");
//            allSelectedCheckbox.setValue(false);
            allSelectedCheckbox.setRawValue("false");
        }
    }

    @Override
    public void onActionEnabled(ActionEnableEvent event) {
        validSelectedServer = event.isEnabled();
        this.manageSearchButton();
    }

    private void manageSearchButton() {
        if (validSelectedServer && validCheckBoxGroup && validSearchText) {
            searchButton.enable();
        } else {
            searchButton.disable();
        }
    }
}
