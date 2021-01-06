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

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class GuiComponentDetail extends GeoPlatformBeanModel {

    private static final long serialVersionUID = -4382463793116702562L;
    //
    private String componentId; // For performance purpose: used for equals() and hashCode() methods
    private AbstractImagePrototype image; // For manual binding

    public enum GuiComponentDetailKeyValue {

        COMPONENT_ID,
        PERMISSION,
        DESCRIPTION;
    }

    /**
     * @return the componentId
     */
    public String getComponentId() {
        return componentId;
    }

    /**
     * @param componentId the componentId to set
     */
    public void setComponentId(String componentId) {
        this.componentId = componentId;
        super.set(GuiComponentDetailKeyValue.COMPONENT_ID.toString(), this.componentId);
    }

    /**
     * @return the image
     */
    public AbstractImagePrototype getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(AbstractImagePrototype image) {
        this.image = image;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return super.get(GuiComponentDetailKeyValue.DESCRIPTION.toString());
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        super.set(GuiComponentDetailKeyValue.DESCRIPTION.toString(), description);
    }

    /**
     * @return the permission
     */
    public GuiPermission getPermission() {
        return super.get(GuiComponentDetailKeyValue.PERMISSION.toString());
    }

    /**
     * @param permission the permission to set
     */
    public void setPermission(GuiPermission permission) {
        super.set(GuiComponentDetailKeyValue.PERMISSION.toString(), permission);
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GuiComponentDetail other = (GuiComponentDetail) obj;
        if ((this.componentId == null) ? (other.componentId != null)
                : !this.componentId.equals(other.componentId)) {
            return false;
        }
        return true;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.componentId != null ? this.componentId.hashCode() : 0);
        return hash;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("GuiComponentDetail {");
        str.append("componentId=").append(componentId);
        str.append(", permission=").append(this.getPermission());
        if (image != null) {
            String simpleName = image.getClass().toString();
            str.append(", image=").append(simpleName.substring(simpleName.lastIndexOf(".") + 1));
        } else {
            str.append(", image=NULL");
        }
        str.append(", description=").append(this.getDescription());
        return str.append('}').toString();
    }
}
