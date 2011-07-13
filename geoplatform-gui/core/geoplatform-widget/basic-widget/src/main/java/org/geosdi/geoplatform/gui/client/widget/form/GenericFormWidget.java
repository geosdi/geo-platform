/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.gui.client.widget.SaveStatus;
import org.geosdi.geoplatform.gui.client.widget.SaveStatus.EnumSaveStatus;

import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 * 
 */
public abstract class GenericFormWidget
        extends Window implements IGeoPlatformForm {

    protected FormPanel formPanel;
    protected FieldSet fieldSet;
    protected SaveStatus saveStatus;
    private boolean initialized;

    /**
     * Param lazy specifies whether the component should be initialized
     * at creation
     *
     * @param lazy
     */
    public GenericFormWidget(boolean lazy) {
        if (!lazy) {
            init();
        }
    }

    /**
     * Init the Widget
     */
    public void init() {
        if (!isInitialized()) {
            this.initializeWindow();
            this.initializeFormPanel();
            this.add(this.formPanel);
            this.initialized = true;
        }
    }

    private void initializeWindow() {
        initSize();
        setResizable(false);

        addWindowListener(new WindowListener() {

            @Override
            public void windowHide(WindowEvent we) {
                reset();
            }
        });

        setLayout(new FitLayout());
        setModal(true);
        setPlain(true);
    }

    private void initializeFormPanel() {
        this.formPanel = new FormPanel();
        this.formPanel.setFrame(true);
        this.formPanel.setLayout(new FlowLayout());

        initSizeFormPanel();
        addComponentToForm();
    }

    /**
     * Set the correct Status Iconn Style
     */
    public void setSaveStatus(EnumSaveStatus status, EnumSaveStatus message) {
        this.saveStatus.setIconStyle(status.getValue());
        this.saveStatus.setText(message.getValue());
    }

    public void setSaveStatus(EnumSaveStatus status, String message) {
        this.saveStatus.setIconStyle(status.getValue());
        this.saveStatus.setText(message);
    }

    public void reset() {
    }

    public abstract void addComponentToForm();

    public abstract void initSize();

    public abstract void initSizeFormPanel();

    /**
     * Specifies the state of the component initialization
     *
     * @return the initialized
     */
    public boolean isInitialized() {
        return initialized;
    }
}
