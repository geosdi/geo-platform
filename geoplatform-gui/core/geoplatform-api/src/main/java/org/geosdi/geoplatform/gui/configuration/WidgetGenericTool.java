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
package org.geosdi.geoplatform.gui.configuration;

import org.geosdi.geoplatform.gui.global.security.GPAccountLogged;

import java.util.logging.Logger;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class WidgetGenericTool<C> extends GenericTool {

    protected static final Logger logger = Logger.getLogger("WidgetGenericTool");
    //
    private static final long serialVersionUID = -436437354541234763L;
    //
    protected String text;

    /**
     * @return {@link String}
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Check the permission of the user logged and call the method for creation.
     * If the permission was not found the tool will not be created.
     *
     * @param container
     */
    public void buildTool(C container) {
        if (secure) {
            logger.fine("################SECURITY_ENABLED for Tool : " + super.getId());
            checkSecurity(container);
        } else {
            logger.fine("################BY_PASS_SECURITY : " + super.getId());
            byPassSecurity(container);
        }
    }

    protected final void checkSecurity(C container) {
        Boolean permission = GPAccountLogged.getInstance().
                hasComponentPermission(this.getId());
        if (permission != null) {
            super.enabled &= permission;
            this.create(container);
        }
    }

    protected final void byPassSecurity(C Container) {
        this.create(Container);
    }

    /**
     * Each component will be added into Container itself
     *
     * @param container
     */
    protected abstract void create(C container);

}
