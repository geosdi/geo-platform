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
package org.geosdi.geoplatform.gui.client.widget.components.filters.spatial;

import org.geosdi.geoplatform.gui.client.widget.i18n.GPSimpleComboI18N;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import java.util.Arrays;
import org.geosdi.geoplatform.gui.client.i18n.CatalogFinderConstants;
import org.geosdi.geoplatform.gui.client.widget.components.GPCatalogFinderComponent;
import org.geosdi.geoplatform.gui.responce.AreaInfo;
import org.geosdi.geoplatform.gui.responce.AreaInfo.AreaSearchType;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CatalogComboBoxComponent implements GPCatalogFinderComponent {

    private AreaInfo areaInfo;
    private FlexTable table;
    private GPSimpleComboI18N<ClientAreaSearchType> combo;

    public CatalogComboBoxComponent(AreaInfo theAreaInfo) {
        this.areaInfo = theAreaInfo;
    }

    public FlexTable getComboBoxComponent() {
        this.table = new FlexTable();
        table.setCellSpacing(4);
        table.setCellPadding(1);

        table.getElement().getStyle().setMarginLeft(80, Unit.PX);

        this.addLabel();
        this.addComboBox();

        return table;
    }

    private void addLabel() {
        table.getCellFormatter().setHorizontalAlignment(1, 1,
                HasHorizontalAlignment.ALIGN_CENTER);
        Label typeLabel = new Label(CatalogFinderConstants.INSTANCE.CatalogComboBoxComponent_typeLabelText());
        typeLabel.setStyleName("comboType-Label");

        table.setWidget(1, 1, typeLabel);
    }

    private void addComboBox() {
        table.getCellFormatter().setHorizontalAlignment(1, 2,
                HasHorizontalAlignment.ALIGN_CENTER);

        combo = new GPSimpleComboI18N<ClientAreaSearchType>();
        combo.setForceSelection(true);
        combo.setEditable(false);

        combo.setWidth(120);
        combo.setTriggerAction(TriggerAction.ALL);
        combo.add(Arrays.asList(ClientAreaSearchType.values()));

        combo.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<ClientAreaSearchType>>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<SimpleComboValue<ClientAreaSearchType>> se) {
                AreaSearchType areaSearchType = AreaSearchType.valueOf(se.getSelectedItem().getValue().name());
                areaInfo.setAreaSearchType(areaSearchType);
            }
        });

        combo.setSimpleValue(ClientAreaSearchType.OVERLAP);

//        ListModelPropertyEditor<SimpleComboValue<AreaSearchType>> propEditor =
//                new ListModelPropertyEditor<SimpleComboValue<AreaSearchType>>() {
//            public String getStringValue(SimpleComboValue<AreaSearchType> value) {
//                return value.getValue().getLabel();
//            }
//        };
//
//        propEditor.setDisplayProperty("label");
//
//        combo.setPropertyEditor(propEditor);
//        
//        combo.getView().setModelProcessor(new ModelProcessor<SimpleComboValue<AreaSearchType>>(){
//
//            @Override
//            public SimpleComboValue<AreaSearchType> prepareData(SimpleComboValue<AreaSearchType> model) {
//                model.set("label", model.getValue().getLabel());
//                return model;
//            }
//            
//        });

        table.setWidget(1, 2, combo);
    }

    @Override
    public void reset() {
        combo.setSimpleValue(ClientAreaSearchType.OVERLAP);
    }
}
