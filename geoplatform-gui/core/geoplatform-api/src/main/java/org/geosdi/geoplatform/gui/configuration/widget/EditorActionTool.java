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
package org.geosdi.geoplatform.gui.configuration.widget;

import org.geosdi.geoplatform.gui.configuration.ActionToolType;
import org.geosdi.geoplatform.gui.configuration.WidgetGenericTool;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class EditorActionTool extends WidgetGenericTool<IGeoPlatformEditor> {

    private static final long serialVersionUID = 2955451820569371624L;
    //
    private ActionToolType type;

    /**
     * @return the type
     */
    public ActionToolType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(ActionToolType type) {
        this.type = type;
    }

    @Override
    protected void create(IGeoPlatformEditor container) {
        switch (type) {
            case BUTTON:
                container.addButton(this);
                break;
            case TOGGLE:
                container.addToogleButton(this);
                break;
        }
    }

    @Override
    public String toString() {
        return "PanelActionTool {" + super.toString()
                + "type = " + type + '}';
    }
}
