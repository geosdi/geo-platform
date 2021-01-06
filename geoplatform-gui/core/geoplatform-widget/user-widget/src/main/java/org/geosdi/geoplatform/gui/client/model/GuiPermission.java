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
package org.geosdi.geoplatform.gui.client.model;

import org.geosdi.geoplatform.gui.client.i18n.GuiPermissionConstants;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public enum GuiPermission {

    /**
     * GuiComponent will be not visible
     */
    NONE(GuiPermissionConstants.INSTANCE.NONE()),
    /**
     * GuiComponent will be read-only (visible and not enable)
     */
    READ(GuiPermissionConstants.INSTANCE.READ()),
    /**
     * GuiComponent will be enable (and visible)
     */
    WRITE(GuiPermissionConstants.INSTANCE.WRITE());
    //
    private String permission;

    private GuiPermission(String permission) {
        this.permission = permission;
    }

    public static GuiPermission fromString(String permission) {
        if (permission != null) {
            permission = permission.trim();
            if (permission.equals(WRITE.permission)) {
                return WRITE;
            } else if (permission.equals(READ.permission)) {
                return READ;
            } else if (permission.equals(NONE.permission)) {
                return NONE;
            }
        }
        return null;
    }

    public static GuiPermission fromBoolean(Boolean permission) {
        if (permission != null) {
            if (permission.booleanValue()) {
                return WRITE;
            } else {
                return READ;
            }
        }
        return NONE;
    }

    public Boolean toBoolean() {
        Boolean bool = null;
        if (this == GuiPermission.WRITE) {
            bool = true;
        } else if (this == GuiPermission.READ) {
            bool = false;
        }
        return bool;
    }

    public String toStringColor() {
        String color;
        if (this == GuiPermission.WRITE) {
            color = "green";
        } else if (this == GuiPermission.READ) {
            color = "gold";
        } else {
            color = "red";
        }
        return color;
    }

    @Override
    public String toString() {
        return permission;
    }
}
