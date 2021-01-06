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
package org.geosdi.geoplatform.gui.client.widget.form;

import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import org.geosdi.geoplatform.gui.client.widget.StatusWidget;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GenericFormWidget extends Window implements IGeoPlatformForm {

    protected FormPanel formPanel;
    protected FieldSet fieldSet;
    protected StatusWidget saveStatus;
    private boolean initialized;

    /**
     * Param lazy specifies whether the component should be initialized at
     * creation
     *
     * @param lazy
     */
    public GenericFormWidget(boolean lazy) {
        if (!lazy) {
            init();
        }
    }

    @Override
    protected void beforeRender() {
        this.init();
    }

    /**
     * Init the Widget
     */
    protected final void init() {
        if (!initialized) {
            this.initializeWindow();
            this.initializeFormPanel();
            this.add(formPanel);
            initialized = true;
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
        formPanel = new FormPanel();
        formPanel.setFrame(true);
        formPanel.setLayout(new FlowLayout());

        initSizeFormPanel();
        addComponentToForm();
    }

    /**
     * Set the correct Status Icon Style
     */
    public void setStatus(String status, String message) {
        saveStatus.setIconStyle(status);
        saveStatus.setText(message);
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

    /**
     * @return the formPanel
     */
    public FormPanel getFormPanel() {
        return formPanel;
    }
}
