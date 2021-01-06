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
package org.geosdi.geoplatform.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "Project")
@Table(name = "gp_project")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "project")
public class GPProject implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8397860970222813277L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GP_PROJECT_SEQ")
    @SequenceGenerator(name = "GP_PROJECT_SEQ", sequenceName = "GP_PROJECT_SEQ")
    private Long id;
    //
    @Column(nullable = false)
    @Index(name = "PROJECT_NAME_INDEX")
    private String name;
    //
    @Column(name = "number_of_elements")
    private int numberOfElements = 0;
    //
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date(System.currentTimeMillis());
    //
    @Column
    @Version
    private int version;
    //
    @Column
    private boolean shared = false;
    //
    @Column(name = "project_description", columnDefinition = "Text")
    private String description;
    //
    @Column(name = "image_project_path")
    private String imagePath;
    @Column(name = "internal_public")
    private boolean internalPublic;
    @Column(name = "external_public")
    private boolean externalPublic;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the numberOfElements
     */
    public int getNumberOfElements() {
        return numberOfElements;
    }

    /**
     * @param numberOfElements the numberOfElements to set
     */
    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    /**
     * @param delta the delta to set
     */
    public void deltaToNumberOfElements(int delta) {
        this.numberOfElements += delta;
    }

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * @return the shared
     */
    public boolean isShared() {
        return shared;
    }

    /**
     * @param shared the shared to set
     */
    public void setShared(boolean shared) {
        this.shared = shared;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath the imagePath to set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @return {@link Boolean}
     */
    public boolean isInternalPublic() {
        return internalPublic;
    }

    /**
     * @param internalPublic
     */
    public void setInternalPublic(boolean internalPublic) {
        this.internalPublic = internalPublic;
    }

    /**
     * @return {@link Boolean}
     */
    public boolean isExternalPublic() {
        return externalPublic;
    }

    /**
     * @param externalPublic
     */
    public void setExternalPublic(boolean externalPublic) {
        this.externalPublic = externalPublic;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName()).append(" {");
        str.append("id = ").append(id);
        str.append(", name = ").append(name);
        str.append(", numberOfElements = ").append(numberOfElements);
        str.append(", creationDate = ").append(creationDate);
        str.append(", version = ").append(version);
        str.append(", shared = ").append(shared);
        str.append(", description = ").append(description);
        str.append(", imagePath = ").append(imagePath);
        str.append(", internalPublic = ").append(internalPublic);
        str.append(", externalPublic = ").append(externalPublic);
        return str.append('}').toString();
    }
}