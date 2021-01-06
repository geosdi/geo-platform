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
package org.geosdi.geoplatform.gui.client.widget.components.search;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Element;
import javax.inject.Inject;
import org.geosdi.geoplatform.gui.client.i18n.CatalogFinderConstants;
import org.geosdi.geoplatform.gui.client.i18n.CatalogFinderMessages;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.widget.components.GPCatalogFinderComponent;
import org.geosdi.geoplatform.gui.client.widget.components.search.pagination.RecordsContainer;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableEvent;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableHandler;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.responce.TextInfo;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CatalogSearchWidget extends LayoutContainer
        implements ActionEnableHandler, GPCatalogFinderComponent {

    private final TextInfo textInfo;
    private final RecordsContainer recordsContainer;
    private final GPEventBus bus;
    //
    private GPSecureStringTextField searchTextField;
    private Button searchButton;
    private CheckBox titleCheckbox;
    private CheckBox abstractCheckbox;
    private CheckBox subjectsCheckbox;
    private CheckBoxGroup optionsCheckboxgroup;
    private CheckBox allSelectedCheckbox;

    @Inject
    public CatalogSearchWidget(TextInfo theTextInfo,
            RecordsContainer theRecordsContainer,
            GPEventBus theBus) {
        this.textInfo = theTextInfo;
        recordsContainer = theRecordsContainer;
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

        Label searchLabel = new Label(
                CatalogFinderConstants.INSTANCE.CatalogSearchWidget_searchLabelText());
        searchLabel.setStyleName("catalogSearch-Label");

        left.add(searchLabel, new ColumnData(300.0));

        HorizontalPanel panel = new HorizontalPanel();
        panel.setStyleAttribute("padding-top", "8px");

        searchTextField = new GPSecureStringTextField();
        searchTextField.setWidth(250);
        searchTextField.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (searchButton.isEnabled()
                        && event.getKeyCode() == KeyCodes.KEY_ENTER) {
                    searchButton.fireEvent(Events.Select);
                }
            }

            @Override
            public void componentKeyUp(ComponentEvent event) {
                if (searchTextField.getValue() == null) {
                    if (event.getKeyCode() == KeyCodes.KEY_BACKSPACE
                            || event.getKeyCode() == KeyCodes.KEY_DELETE) {
                        resetSearch();
                    }
                }
            }

        });
        panel.add(searchTextField);

        searchButton = new Button(ButtonsConstants.INSTANCE.searchText(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        String searchText = searchTextField.getValue();
                        if (searchText != null && !optionsCheckboxgroup.isValid(
                                true)) {
                            GeoPlatformMessage.alertMessage(
                                    CatalogFinderConstants.INSTANCE.CatalogSearchWidget_errorSearchTitleText(),
                                    CatalogFinderMessages.INSTANCE.CatalogSearchWidget_errorSearchBodyMessage(
                                            searchText));
                            return;
                        }
                        // Manual binding
                        textInfo.setText(searchText);
                        textInfo.setSearchTitle(
                                titleCheckbox.getValue().booleanValue());
                        textInfo.setSearchAbstract(
                                abstractCheckbox.getValue().booleanValue());
                        textInfo.setSearchSubjects(
                                subjectsCheckbox.getValue().booleanValue());
                        // Performing the search
                        recordsContainer.searchRecords();
                    }

                });
        searchButton.setStyleAttribute("margin-left", "20px");
        searchButton.disable();
        panel.add(searchButton);

        left.add(panel);

        // Search Panel - Right
        LayoutContainer right = new LayoutContainer();
        right.setLayout(new ColumnLayout());

        Label optionsLabel = new Label(
                CatalogFinderConstants.INSTANCE.CatalogSearchWidget_optionsLabelText());
        optionsLabel.setStyleName("catalogOptions-Label");

        right.add(optionsLabel, new ColumnData(240.0));

        optionsCheckboxgroup = new CheckBoxGroup();
        optionsCheckboxgroup.setOrientation(Orientation.VERTICAL);
        optionsCheckboxgroup.setValidator(new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                CheckBoxGroup group = (CheckBoxGroup) field;
                if (group.getValue() == null) {
                    return CatalogFinderConstants.INSTANCE.CatalogSearchWidget_optionsCheckboxValidateText();
                }
                return null;
            }

        });

        Listener<FieldEvent> checkBoxListener = new Listener<FieldEvent>() {

            @Override
            public void handleEvent(FieldEvent fe) {
                manageAllSelectedCheckbox();
            }

        };

        titleCheckbox = new CheckBox();
        titleCheckbox.setBoxLabel(
                CatalogFinderConstants.INSTANCE.CatalogSearchWidget_titleLabelText());
        titleCheckbox.setValue(true); // Enabled by default
        titleCheckbox.addListener(Events.Change, checkBoxListener);
        optionsCheckboxgroup.add(titleCheckbox);

        abstractCheckbox = new CheckBox();
        abstractCheckbox.setBoxLabel(
                CatalogFinderConstants.INSTANCE.CatalogSearchWidget_abstractLabelText());
        abstractCheckbox.setValue(true); // Enabled by default
        abstractCheckbox.addListener(Events.Change, checkBoxListener);
        optionsCheckboxgroup.add(abstractCheckbox);

        subjectsCheckbox = new CheckBox();
        subjectsCheckbox.setBoxLabel(
                CatalogFinderConstants.INSTANCE.CatalogSearchWidget_keywordsLabelText());
        subjectsCheckbox.setValue(true); // Enabled by default
        subjectsCheckbox.addListener(Events.Change, checkBoxListener);
        optionsCheckboxgroup.add(subjectsCheckbox);

        right.add(optionsCheckboxgroup);

        allSelectedCheckbox = new CheckBox();
        allSelectedCheckbox.setBoxLabel(
                CatalogFinderConstants.INSTANCE.CatalogSearchWidget_allSelectLabelText());
        allSelectedCheckbox.setValue(true); // Enabled by default
        allSelectedCheckbox.addListener(Events.Change,
                new Listener<FieldEvent>() {

                    @Override
                    public void handleEvent(FieldEvent fe) {
                        Boolean allSelected = (Boolean) fe.getValue();

                        titleCheckbox.setValue(allSelected);
                        abstractCheckbox.setValue(allSelected);
                        subjectsCheckbox.setValue(allSelected);
                    }

                });
        right.add(allSelectedCheckbox);

        add(left, new ColumnData(0.6));
        add(right, new ColumnData(0.4));
    }

    private void manageAllSelectedCheckbox() {
        allSelectedCheckbox.setFireChangeEventOnSetValue(false); // Disable the firing of Events.Change
        if (titleCheckbox.getValue() && abstractCheckbox.getValue()
                && subjectsCheckbox.getValue()) {
            allSelectedCheckbox.setValue(true);
        } else {
            allSelectedCheckbox.setValue(false);
        }
        allSelectedCheckbox.setFireChangeEventOnSetValue(true); // Enable the firing of Events.Change
    }

    @Override
    public void onActionEnabled(ActionEnableEvent event) {
        searchButton.setEnabled(event.isEnabled());
    }

    @Override
    public void reset() {
        this.resetSearch();
        this.allSelectedCheckbox.setValue(true);
    }

    private void resetSearch() {
        searchTextField.clear();
        recordsContainer.reset();
    }

}
