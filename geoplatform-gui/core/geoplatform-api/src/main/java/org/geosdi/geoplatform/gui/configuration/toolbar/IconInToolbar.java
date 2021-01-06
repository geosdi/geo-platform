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
package org.geosdi.geoplatform.gui.configuration.toolbar;

import org.geosdi.geoplatform.gui.client.i18n.IconInToolbarConstants;
import org.geosdi.geoplatform.gui.configuration.WidgetGenericTool;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 *
 */
public class IconInToolbar extends WidgetGenericTool<IGeoPlatformToolbar> {

    private static final long serialVersionUID = 1982533388514947046L;
    //
    private String textKey;
    private String text;

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
            return IconInToolbarConstants.INSTANCE.getString(this.textKey);
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
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "IconInToolbar{" + "textKey=" + textKey + ", text=" + text + '}';
    }

    @Override
    protected void create(IGeoPlatformToolbar toolbar) {
        toolbar.addIconInToolbar(this);
    }
}
