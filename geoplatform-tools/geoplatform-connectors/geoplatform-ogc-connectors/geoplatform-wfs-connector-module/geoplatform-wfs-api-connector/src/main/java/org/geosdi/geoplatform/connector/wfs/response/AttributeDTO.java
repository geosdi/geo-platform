/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.wfs.response;

import java.io.Serializable;

/**
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @email francesco.izzi@geosdi.org
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class AttributeDTO implements Serializable {

    private static final long serialVersionUID = 4000281402477022477L;
    //
    private String type;
    private String name;
    private String value;
    private int maxOccurs;
    private int minOccurs;
    private boolean nillable;

    public AttributeDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getMaxOccurs() {
        return maxOccurs;
    }

    public void setMaxOccurs(int maxOccurs) {
        this.maxOccurs = maxOccurs;
    }

    public int getMinOccurs() {
        return minOccurs;
    }

    public void setMinOccurs(int minOccurs) {
        this.minOccurs = minOccurs;
    }

    public boolean isNillable() {
        return nillable;
    }

    public void setNillable(boolean nillable) {
        this.nillable = nillable;
    }

    public boolean isDateType() {
        return (this.type.equalsIgnoreCase("dateTime") || (this.type.equalsIgnoreCase("date")));
    }

    public AttributeDTO copy() {
        AttributeDTO copy = new AttributeDTO();
        copy.setName(this.getName());
        copy.setValue(this.getValue());
        copy.setType(this.getType());
        copy.setMaxOccurs(this.getMaxOccurs());
        copy.setMinOccurs(this.getMinOccurs());
        copy.setNillable(this.isNillable());

        return copy;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("AttributeDTO {");
        str.append("name=").append(name);
        str.append(", value=").append(value);
        str.append(", type=").append(type);
        str.append(", maxOccurs=").append(maxOccurs);
        str.append(", minOccurs=").append(minOccurs);
        str.append(", nillable=").append(nillable);
        return str.append("}").toString();
    }

}
