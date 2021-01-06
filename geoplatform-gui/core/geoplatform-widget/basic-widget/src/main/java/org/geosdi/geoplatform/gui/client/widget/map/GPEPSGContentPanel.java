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
package org.geosdi.geoplatform.gui.client.widget.map;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.Element;
import org.geosdi.geoplatform.gui.client.i18n.BasicWidgetConstants;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.client.widget.map.util.EPSGTemplate;
import org.geosdi.geoplatform.gui.client.widget.map.util.EPSGUtility;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPEPSGContentPanel extends GeoPlatformContentPanel {

    private GPSecureStringTextField epsgTextField;
    private ListStore<EPSGTemplate> storeEPSG;
    private ComboBox<EPSGTemplate> comboEPSG;
    private SelectionChangedListener<EPSGTemplate> comboEpsgListener;

    public GPEPSGContentPanel(boolean lazy) {
        super(lazy);
    }

    @Override
    public void addComponent() {
        epsgTextField = new GPSecureStringTextField();
        epsgTextField.setAllowBlank(false);
        epsgTextField.setFieldLabel(
                BasicWidgetConstants.INSTANCE.GPEPSGContentPanel_EPSGTextFieldLabelText());

        this.storeEPSG = new ListStore<EPSGTemplate>();
        this.storeEPSG.add(EPSGUtility.getCommonEPSG());

        this.comboEPSG = new ComboBox<EPSGTemplate>();
        this.comboEPSG.setFieldLabel(
                BasicWidgetConstants.INSTANCE.GPEPSGContentPanel_EPSGComboFieldLabelText());
        this.comboEPSG.setEmptyText(
                BasicWidgetConstants.INSTANCE.EPSGUtility_chooseEPSGText());
        this.comboEPSG.setDisplayField(
                EPSGTemplate.EPSGEnum.EPSG_DESCRIPTION.getValue());
        this.comboEPSG.setEditable(false);
        this.comboEPSG.setAllowBlank(true);
        this.comboEPSG.setForceSelection(true);
        this.comboEPSG.setTypeAhead(true);
        this.comboEPSG.setTriggerAction(ComboBox.TriggerAction.ALL);

        this.comboEPSG.setStore(this.storeEPSG);

        this.comboEpsgListener = new SelectionChangedListener<EPSGTemplate>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<EPSGTemplate> se) {
                if (se.getSelectedItem() != null) {
                    epsgTextField.setValue(se.getSelectedItem().getEpsg());
                }
            }

        };

        super.add(this.comboEPSG, new FormData("100%"));

        epsgTextField = new GPSecureStringTextField();
        epsgTextField.setAllowBlank(false);
        epsgTextField.setFieldLabel(
                BasicWidgetConstants.INSTANCE.GPEPSGContentPanel_EPSGTextFieldLabelText());

        KeyListener keyListener = new KeyListener() {

            @Override
            public void componentKeyUp(ComponentEvent event) {
                comboEPSG.reset();
            }

        };

        epsgTextField.addKeyListener(keyListener);
        super.add(epsgTextField, new FormData("100%"));
    }

    @Override
    protected void onRemove(Component item) {
        super.onRemove(item);
        this.comboEPSG.removeSelectionListener(this.comboEpsgListener);
        this.comboEPSG.reset();
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        this.comboEPSG.removeSelectionListener(this.comboEpsgListener);
        this.comboEPSG.reset();
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        this.comboEPSG.addSelectionChangedListener(this.comboEpsgListener);
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);
        this.comboEPSG.addSelectionChangedListener(this.comboEpsgListener);
    }

    public GPSecureStringTextField getEpsgTextField() {
        return epsgTextField;
    }

    public ComboBox<EPSGTemplate> getComboEPSG() {
        return comboEPSG;
    }

    @Override
    public void reset() {
        this.epsgTextField.reset();
        this.comboEPSG.reset();
    }

    @Override
    public void initSize() {
    }

    @Override
    public void setPanelProperties() {
        super.setHeaderVisible(Boolean.FALSE);
        super.setLayout(new FormLayout());
    }

}
