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
package org.geosdi.geoplatform.gui.configuration.menubar;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.geosdi.geoplatform.gui.client.i18n.MenuBarCategoryConstants;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MenuBarCategory implements Serializable,
        Comparable<MenuBarCategory> {

    private static final long serialVersionUID = -6392463898405375954L;
    //
    private boolean enabled;
    private int order;
    private String textKey;
    private String text;
    private List<MenuBarClientTool> tools;

    public String getTextKey() {
        return textKey;
    }

    /**
     * Sets the i18n textKey to generate the proper menu text
     *
     * @param textKey
     */
    public void setTextKey(String textKey) {
        this.textKey = textKey;
    }

    /**
     * @return the text
     */
    public String getText() {
        if (this.text == null && this.textKey != null) {
            return MenuBarCategoryConstants.INSTANCE.getString(this.textKey);
        }
        return text;
    }

    /**
     * @param text the text to set
     * @deprecated to support i18n use setTextKey(String textKey) instead
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the enable
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enable the enable to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the order
     */
    public int getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * @return the tools
     */
    public List<MenuBarClientTool> getTools() {
        return tools;
    }

    /**
     * @param tools the tools to set
     */
    public void setTools(List<MenuBarClientTool> tools) {
        Collections.sort(tools);
        this.tools = tools;
    }

    @Override
    public int compareTo(MenuBarCategory o) {
        return getOrder() - o.getOrder();
    }

    @Override
    public String toString() {
        return "MenuBarCategory {" + "enabled = " + enabled
                + ", order = " + order + ", textKey = " + textKey
                + ", text = " + text + ", tools = " + tools + '}';
    }

}
