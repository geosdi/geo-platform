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
package org.geosdi.geoplatform.responce;

import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.gui.responce.URIDTO;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.geosdi.geoplatform.gui.responce.BBox;

/**
 * Full Record DTO for CSW request.
 * 
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@XmlRootElement(name = "FullRecordDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class FullRecordDTO extends AbstractRecordDTO {

    private static final long serialVersionUID = -4843440136860067550L;
    //
    private BBox bBox;
    //
    @XmlElementWrapper(name = "uriList")
    @XmlElement(name = "uri")
    private List<URIDTO> uriList = new ArrayList<URIDTO>();

    public BBox getBBox() {
        return bBox;
    }

    public void setBBox(BBox bBox) {
        this.bBox = bBox;
    }

    public List<URIDTO> getUriList() {
        return uriList;
    }

    public void setUriList(List<URIDTO> uriList) {
        this.uriList = uriList;
    }

    public void addUri(URIDTO uri) {
        this.uriList.add(uri);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("FullRecordDTO {");
        str.append(super.toStringBuilder(str));
        str.append(", bBox=").append(bBox);
        str.append(", uri=").append(uriList);
        return str.append('}').toString();
    }
}
