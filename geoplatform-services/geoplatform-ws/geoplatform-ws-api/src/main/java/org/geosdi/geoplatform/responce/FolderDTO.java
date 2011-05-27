//<editor-fold defaultstate="collapsed" desc="License">
/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
//</editor-fold>
package org.geosdi.geoplatform.responce;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.geosdi.geoplatform.core.model.GPFolder;

/**
 * @author giuseppe
 * 
 */
@XmlRootElement(name = "FolderDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class FolderDTO extends AbstractElementDTO {

    private int numberOfDescendants;

    //<editor-fold defaultstate="collapsed" desc="Constructor method">
    /**
     * Default constructor
     */
    public FolderDTO() {
        super();
    }

    /**
     * Constructor with GPFolder as arg
     * @param folder
     */
    public FolderDTO(GPFolder folder) {
        super(folder.getId(), folder.getName(), folder.getPosition(), 
                folder.isShared(), folder.isChecked());
        this.numberOfDescendants = folder.getNumberOfDescendants(); // DEL?
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter and setter methods">
    /**
     * @return the numberOfChilds
     */
    public int getNumberOfChilds() {
        return numberOfDescendants;
    }

    /**
     * @param numberOfChilds to set
     */
    public void setNumberOfChilds(int numberOfChilds) {
        this.numberOfDescendants = numberOfChilds;
    }
    //</editor-fold>

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FolderDTO [" + super.toString()
                + ", numberOfDescendants=" + numberOfDescendants + "]";
    }
}
