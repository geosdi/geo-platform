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
package org.geosdi.geoplatform.responce;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Abstract Record DTO for CSW request.
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@XmlTransient
public abstract class AbstractRecordDTO implements Serializable {

    private static final long serialVersionUID = 8229785485262331251L;
    //
    protected Long idCatalog;
    private String identifier;
    private String title;
    private String catalogURL;
    private String type;
    private String abstractText;
    @XmlElementWrapper(name = "subjectList")
    @XmlElement(name = "subject")
    private List<String> subjects;

    /**
     * @return the idCatalog
     */
    public Long getIdCatalog() {
        return idCatalog;
    }

    /**
     * @param theIdCatalog the idCatalog to set
     */
    public void setIdCatalog(Long theIdCatalog) {
        this.idCatalog = theIdCatalog;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatalogURL() {
        return catalogURL;
    }

    public void setCatalogURL(String catalogURL) {
        this.catalogURL = catalogURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(String subject) {
        if (subjects == null) {
            subjects = new ArrayList<String>();
        }

        subjects.add(subject);
    }

    @Override
    public String toString() {
        return getClass().getName() + " {"
                + "idCatalog = " + idCatalog
                + ", identifier = " + identifier
                + ", title = " + title
                + ", catalogURL = " + catalogURL
                + ", type = " + type
                + ", abstractText = " + abstractText
                + ", subjects = " + subjects;
    }

}
