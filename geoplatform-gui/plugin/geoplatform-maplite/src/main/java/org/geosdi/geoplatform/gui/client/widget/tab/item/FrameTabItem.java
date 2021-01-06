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
package org.geosdi.geoplatform.gui.client.widget.tab.item;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.MapLiteModuleConstants;
import org.geosdi.geoplatform.gui.client.widget.tab.GeoPlatformTabItem;
import org.geosdi.geoplatform.gui.client.widget.tab.Utility;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;

import java.util.logging.Level;

/**
 *
 * @author Salvia Vito - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class FrameTabItem extends GeoPlatformTabItem  {

    private NumberField heightFrame;
    private NumberField widthFrame;
    private FormPanel formPanel;
    private TextArea codeTextArea;

    public FrameTabItem() {
        super(MapLiteModuleConstants.INSTANCE.GPMapLiteExportProjectWidget_frameText(),
                Boolean.TRUE);
    }

    @Override
    public void addComponents() {
//        setLayout(new FormLayout());
        this.formPanel = new FormPanel();
        this.formPanel.setHeaderVisible(Boolean.FALSE);
        this.formPanel.setAutoHeight(Boolean.TRUE);
//        vp.setLayoutData(new FormLayout());
        this.heightFrame = new NumberField() {

            @Override
            public void setValue(Number value) {
                super.setValue(value);
                if (value != null) {
                    NumberFormat nf = NumberFormat.getDecimalFormat();
                    String formattedValue = nf.format(value.floatValue()).replaceAll(",", "");
                    heightFrame.setRawValue(formattedValue);
                    logger.log(Level.INFO, "Updating heightFrame field: " + formattedValue);
                }
            }
        };
        this.heightFrame.setPropertyEditorType(Integer.class);
        this.heightFrame.setFieldLabel(MapLiteModuleConstants.INSTANCE.
                GPMapLiteExportProjectWidget_widthText());

        this.widthFrame = new NumberField() {

            @Override
            public void setValue(Number value) {
                super.setValue(value);
                if (value != null) {
                    NumberFormat nf = NumberFormat.getDecimalFormat();
                    String formattedValue = nf.format(value.floatValue()).replaceAll(",", "");
                    heightFrame.setRawValue(formattedValue);
                    logger.log(Level.INFO, "Updating heightFrame width: " + formattedValue);
                }
            }
        };
        this.widthFrame.setPropertyEditorType(Integer.class);
        this.widthFrame.setFieldLabel(MapLiteModuleConstants.INSTANCE.
                GPMapLiteExportProjectWidget_heightText());

        Button generateIFrameCode = new Button(MapLiteModuleConstants.INSTANCE.
                CreateMapLiteFrame_createText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.gear()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        StringBuilder code = new StringBuilder();
                        code.append("<iframe");
                        code.append(" src=\""+ Utility.generateMapLiteURL()+"\"");
                        if(widthFrame.getValue() != null){
                            code.append(" width=\""+widthFrame.getValue()+"\"");
                        }
                        if(heightFrame.getValue() != null){
                            code.append(" height=\""+heightFrame.getValue()+"\"");
                        }
                        code.append(">");
                        code.append(" </iframe>");
                        codeTextArea.setValue(code.toString());
                    }
                });


        this.codeTextArea = new TextArea();
        this.codeTextArea.setFieldLabel(MapLiteModuleConstants.INSTANCE.
                ExportMapLiteProjectAction_iframeCode());
        this.codeTextArea.setReadOnly(Boolean.TRUE);
        this.formPanel.add(this.heightFrame);
        this.formPanel.add(this.widthFrame);
        this.formPanel.add(generateIFrameCode);
        this.formPanel.add(this.codeTextArea);
        super.add(formPanel);
    }




    @Override
    public final void subclassCallToInit() {
        super.init();
    }

}
