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
package org.geosdi.geoplatform.gui.client.widget.wfs.toolbar.button.observer;

import com.google.common.collect.Maps;
import org.geosdi.geoplatform.gui.client.widget.wfs.toolbar.button.WFSToggleButton;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class EditToolbarBaseObserver implements WFSToolbarObserver {

    private final Map<String, WFSToggleButton> forceResetButtons = Maps.<String, WFSToggleButton>newHashMap();
    private WFSToggleButton buttonPressed;
    private WFSToggleButton lastButtonPressed;

    @Inject
    public EditToolbarBaseObserver(GPEventBus bus) {
        bus.addHandler(TYPE, this);
    }

    @Override
    public WFSToggleButton getButtonPressed() {
        return this.buttonPressed;
    }

    @Override
    public boolean isButtonPressed() {
        return this.buttonPressed != null;
    }

    @Override
    public void setButtonPressed(WFSToggleButton btnPressed) {
        this.buttonPressed = btnPressed;
        if ((buttonPressed.isForceReset()) && !(forceResetButtons.containsKey(
                buttonPressed.getId()))) {
            forceResetButtons.put(buttonPressed.getId(), buttonPressed);
        }
    }

    @Override
    public void changeButtonState() {
        if (isButtonPressed()) {
            this.buttonPressed.disableEditorControl();
            this.lastButtonPressed = buttonPressed;
            this.buttonPressed = null;
        }
    }

    @Override
    public boolean isSameButton(String buttonId) {
        return this.buttonPressed != null
                ? this.buttonPressed.getId().equalsIgnoreCase(buttonId) : false;
    }

    @Override
    public void resetButtonPressed() {
        if (isButtonPressed()) {
            this.buttonPressed.resetEditorControl();
            this.buttonPressed = null;
        } else {
            this.lastButtonPressed.resetEditorControl();
            this.lastButtonPressed = null;
        }
        this.forceResetButtons();
    }

    protected final void forceResetButtons() {
        for (Map.Entry<String, WFSToggleButton> entry : forceResetButtons.entrySet()) {
            entry.getValue().resetEditorControl();
        }
    }

}
