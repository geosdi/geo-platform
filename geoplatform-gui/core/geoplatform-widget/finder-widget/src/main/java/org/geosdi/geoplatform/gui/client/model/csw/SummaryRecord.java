/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gui.client.model.csw;

import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class SummaryRecord extends GeoPlatformBeanModel {

    private static final long serialVersionUID = 3666223900734438298L;
    //
    private String identifier;

    /**
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier the identifier to set
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return super.get(SummaryRecordKeyValue.title.toString());
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        super.set(SummaryRecordKeyValue.title.toString(), title);
    }

    /**
     * @return the keywords
     */
    public String getKeywords() {
        return super.get(SummaryRecordKeyValue.keywords.toString());
    }

    /**
     * @param keywords the keywords to set
     */
    public void setKeywords(String keywords) {
        super.set(SummaryRecordKeyValue.keywords.toString(), keywords);
    }

    /**
     * @return the abstractText
     */
    public String getAbstractText() {
        return super.get(SummaryRecordKeyValue.abstractText.toString());
    }

    /**
     * @param abstractText the abstractText to set
     */
    public void setAbstractText(String abstractText) {
        super.set(SummaryRecordKeyValue.abstractText.toString(), abstractText);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SummaryRecord other = (SummaryRecord) obj;
        if ((this.identifier == null) ? (other.identifier != null) : !this.identifier.equals(
                other.identifier)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.identifier != null ? this.identifier.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("SummaryBeanRecord {");
        str.append("identifier = ").append(identifier);
        str.append(", title = ").append(getTitle());
        str.append(", keywords = ").append(getKeywords());
        str.append(", abstract =").append(getAbstractText());

        return str.append('}').toString();
    }
}
