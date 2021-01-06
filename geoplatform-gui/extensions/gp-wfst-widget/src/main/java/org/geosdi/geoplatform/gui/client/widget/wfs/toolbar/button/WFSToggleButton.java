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
package org.geosdi.geoplatform.gui.client.widget.wfs.toolbar.button;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ToggleButton;
import org.geosdi.geoplatform.gui.client.action.wfs.WFSToggleAction;
import org.geosdi.geoplatform.gui.client.puregwt.togglebutton.event.EnableToggleStateEvent;
import org.geosdi.geoplatform.gui.client.puregwt.togglebutton.event.ToggleStateEvent;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.puregwt.GPEventBusImpl;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSToggleButton extends ToggleButton implements WFSEditorButton {

    static final GPEventBus toggleStateBus = new GPEventBusImpl();
    static final WFSClickEvent wfsClickEvent = new WFSClickEvent();
    static final ToggleStateEvent toggleStateEvent = new ToggleStateEvent();
    //
    final String id;
    final boolean forceReset;
    WFSToggleAction action;

    public WFSToggleButton(String theId, boolean isForceReset) {
        super();
        this.id = theId;
        this.forceReset = isForceReset;
    }

    public WFSToggleButton(Image upImage, WFSToggleAction theAction,
                           String theId, boolean isForceReset) {
        super(upImage, theAction);
        this.id = theId;
        this.forceReset = isForceReset;
        this.action = theAction;
    }

    public static void fireToggleStateEvent(String source) {
        toggleStateBus.fireEventFromSource(toggleStateEvent, source);
    }

    public static void fireEnableToggleStateEvent(EnableToggleStateEvent event) {
        toggleStateBus.fireEventFromSource(event, EnableToggleEnum.ENABLE_STATE);
    }

    public HandlerRegistration addWFSToggleAction(WFSToggleAction theAction) {
        this.action = theAction;
        return super.addClickHandler(action);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    @Override
    public void disableEditorControl() {
        super.setDown(false);
        if (action != null) {
            action.disableEditorControl();
        }
    }

    @Override
    public void enableToggleDown() {
        super.setDown(true);

        if (this.action != null) {
            wfsClickEvent.setSource(this);
            action.onClick(wfsClickEvent);
        }
    }

    @Override
    public void enableToggleState(boolean state) {
        super.setEnabled(state);
    }

    @Override
    public HandlerRegistration addToggleStateHandler() {
        return toggleStateBus.addHandlerToSource(TYPE, id, this);
    }

    @Override
    public HandlerRegistration addEnableToggleStateHandler() {
        return toggleStateBus.addHandlerToSource(TYPE,
                EnableToggleEnum.ENABLE_STATE, this);
    }

    @Override
    public void resetEditorControl() {
        if (isDown()) {
            super.setDown(false);
        }
        if (action != null) {
            action.resetEditorControl();
        }
    }

    /**
     * @return the forceReset
     */
    public boolean isForceReset() {
        return forceReset;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WFSToggleButton other = (WFSToggleButton) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WFSToggleButton{ " + "id = " + id + ", forceReset = "
                + forceReset + '}';
    }

    static class WFSClickEvent extends ClickEvent {

        protected WFSClickEvent() {
        }

        @Override
        public void setSource(Object theSource) {
            super.setSource(theSource);
        }

    }

}
