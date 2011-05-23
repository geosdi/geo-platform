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

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 * @author Michele Santomauro
 *
 */
@XmlTransient
public abstract class AbstractElementDTO implements IElementDTO,
        Comparable<IElementDTO> {

    private long id = -1; // Database identity

    private String name = "NULL";

    private int position = -1;

    private boolean shared = false;
    
    private boolean checked = false;

    //<editor-fold defaultstate="collapsed" desc="Constructor method">
    /**
     * Default constructor
     */
    public AbstractElementDTO() {
    }

    /**
     * Constructor with args
     * @param id
     * @param name
     * @param position
     * @param shared
     */
    public AbstractElementDTO(long id, String name, int position, boolean shared, boolean checked) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.shared = shared;
        this.checked = checked;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter and setter methods">
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * @param position
     *            the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * @return the shared state
     */
    public boolean isShared() {
        return shared;
    }

    /**
     * @param shared
     *            the shared state to set
     */
    public void setShared(boolean shared) {
        this.shared = shared;
    }
    
    /**
     * @return the checked
     */    
    public boolean isChecked() {
        return checked;
    }

    /**
     * @param checked
     *            the checked to set
     */    
    public void setChecked(boolean checked) {
        this.checked = checked;
    }    
    //</editor-fold>

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", position=" + position + ", shared=" + shared;
    }

    // For sort IElementDTO object in the TreeFolderElements
    @Override
    public int compareTo(IElementDTO element) {
        return ((AbstractElementDTO) element).getPosition() - getPosition();
    }
}
