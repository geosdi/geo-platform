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
package org.geosdi.geoplatform.gui.client.widget.cql.button;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public abstract class AdvancedCQLButton extends Button {

    private TextArea filterTextArea;
    protected boolean initialized = false;
    protected GeoPlatformWindow window;

    public AdvancedCQLButton(TextArea textArea, String text) {
        super(text);
        this.filterTextArea = textArea;
    }

    @Override
    protected void onClick(ComponentEvent ce) {
        super.onClick(ce);
        this.getMessageToShow().show();
    }

    public GeoPlatformWindow getMessageToShow() {
        if (!this.isInitialized()) {
            initialize();
        }
        return window;
    }

    protected abstract void initialize();

    protected boolean isInitialized() {
        return initialized;
    }

    protected void insertTextIntoFilterArea(String text) {
        String oldText = filterTextArea.getValue();
        StringBuilder newText = new StringBuilder();
        if (oldText != null && !oldText.isEmpty()) {
            newText.append(oldText.substring(0, filterTextArea.getCursorPos()));
            newText.append(text);
            newText.append(oldText.substring(filterTextArea.getCursorPos()));
        } else {
            newText.append(text);
        }
        filterTextArea.setValue(newText.toString());
    }
}
