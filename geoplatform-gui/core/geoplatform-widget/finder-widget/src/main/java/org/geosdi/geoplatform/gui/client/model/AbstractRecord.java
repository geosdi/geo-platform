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

import java.util.List;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 * Abstract Record for CSW request.
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class AbstractRecord extends GeoPlatformBeanModel {

    private static final long serialVersionUID = -715748241959974761L;
    //
    protected Long idCatalog; // Only to optimize the request GetRecordById
    protected String identifier; // For performance purpose: used for equals() and hashCode() methods
    private String catalogURL; // It isn't for binding model-view
    private String type; // It isn't for binding model-view

    public enum RecordKeyValue {

        TITLE, ABSTRACT_TEXT, SUBJECTS;
    }

    /**
     * @return the idCatalog
     */
    public Long getIdCatalog() {
        return idCatalog;
    }

    /**
     * @param idCatalog the idCatalog to set
     */
    public void setIdCatalog(Long idCatalog) {
        this.idCatalog = idCatalog;
    }

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
        return super.get(RecordKeyValue.TITLE.toString());
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        super.set(RecordKeyValue.TITLE.toString(), title);
    }

    /**
     * @return the catalogURL
     */
    public String getCatalogURL() {
        return catalogURL;
    }

    /**
     * @param catalogURL the catalogURL to set
     */
    public void setCatalogURL(String catalogURL) {
        this.catalogURL = catalogURL;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the abstractText
     */
    public String getAbstractText() {
        return super.get(RecordKeyValue.ABSTRACT_TEXT.toString());
    }

    /**
     * @param abstractText the abstractText to set
     */
    public void setAbstractText(String abstractText) {
        super.set(RecordKeyValue.ABSTRACT_TEXT.toString(), abstractText);
    }

    /**
     * @return the subjects
     */
    public List<String> getSubjects() {
        return super.get(RecordKeyValue.SUBJECTS.toString());
    }

    /**
     * @param subjects the subjects to set
     */
    public void setSubjects(List<String> subjects) {
        super.set(RecordKeyValue.SUBJECTS.toString(), subjects);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.idCatalog != null
                ? this.idCatalog.hashCode() : 0);
        hash = 83 * hash + (this.identifier != null
                ? this.identifier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractRecord other = (AbstractRecord) obj;
        if (this.idCatalog != other.idCatalog && (this.idCatalog == null
                || !this.idCatalog.equals(other.idCatalog))) {
            return false;
        }
        if ((this.identifier == null) ? (other.identifier != null)
                : !this.identifier.equals(other.identifier)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("identifier = ").append(identifier);
        str.append(", title = ").append(this.getTitle());
        str.append(", catalogURL = ").append(catalogURL);
        str.append(", type = ").append(type);
        str.append(", abstract = ").append(this.getAbstractText());
        str.append(", subjects = ").append(this.getSubjects());
        return str.toString();
    }
}
