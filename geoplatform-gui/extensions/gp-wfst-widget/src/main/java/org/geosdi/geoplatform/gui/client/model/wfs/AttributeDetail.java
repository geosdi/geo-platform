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
package org.geosdi.geoplatform.gui.client.model.wfs;

import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class AttributeDetail extends GeoPlatformBeanModel {

    private static final long serialVersionUID = 7214348729445121136L;

    public String getName() {
        return super.get(AttributeDetailKeyValue.NAME.name());
    }

    public void setName(String name) {
        super.set(AttributeDetailKeyValue.NAME.name(), name);
    }

    public String getValue() {
        return super.get(AttributeDetailKeyValue.VALUE.name());
    }

    public void setValue(String value) {
        super.set(AttributeDetailKeyValue.VALUE.name(), value);
    }

    public String getType() {
        return super.get(AttributeDetailKeyValue.TYPE.name());
    }

    public void setType(String type) {
        super.set(AttributeDetailKeyValue.TYPE.name(), type);
    }

    public Integer getMaxOccurs() {
        return super.get(AttributeDetailKeyValue.MAX.name());
    }

    public void setMaxOccurs(Integer maxOccurs) {
        super.set(AttributeDetailKeyValue.MAX.name(), maxOccurs);
    }

    public Integer getMinOccurs() {
        return super.get(AttributeDetailKeyValue.MIN.name());
    }

    public void setMinOccurs(Integer minOccurs) {
        super.set(AttributeDetailKeyValue.MIN.name(), minOccurs);
    }

    public Boolean isNillable() {
        return super.get(AttributeDetailKeyValue.NILLABLE.name());
    }

    public void setNillable(Boolean nillable) {
        super.set(AttributeDetailKeyValue.NILLABLE.name(), nillable);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("AttributeDetail {");
        str.append("name=").append(this.getName());
        str.append(", value=").append(this.getValue());
        str.append(", type=").append(this.getType());
        str.append(", max=").append(this.getMaxOccurs());
        str.append(", min=").append(this.getMinOccurs());
        str.append(", nillable=").append(this.isNillable());
        return str.append('}').toString();
    }

    public enum AttributeDetailKeyValue {

        NAME, VALUE, TYPE, MAX, MIN, NILLABLE;
    }
}
