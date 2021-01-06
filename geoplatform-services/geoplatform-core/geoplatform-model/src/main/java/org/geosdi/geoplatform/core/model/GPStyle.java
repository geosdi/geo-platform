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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @author Francesco Izzi - geoSDI
 * 
 * TODO: for future use
 */
//@XmlRootElement(name = "Style")
//@Entity(name = "Style")
//@Table(name = "gp_style")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "style")
public class GPStyle implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8623163339265164161L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "GP_STYLE_SEQ")
    @SequenceGenerator(name = "GP_STYLE_SEQ", sequenceName = "GP_STYLE_SEQ")
    private Long id;
    //
    @Column
    private String name;
    //
    @Column
    private String title;
    //
    @Column(name = "abstract")
    private String abstractText;
    //
    @Column(name = "legend_url")
    private String legendURL;
    //
    @ManyToOne(optional = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GPRasterLayer layer;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
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
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the abstractText
     */
    public String getAbstractText() {
        return abstractText;
    }

    /**
     * @param abstractText
     *            the abstractText to set
     */
    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    /**
     * @return the legendURL
     */
    public String getLegendURL() {
        return legendURL;
    }

    /**
     * @param legendURL
     *            the legendURL to set
     */
    public void setLegendURL(String legendURL) {
        this.legendURL = legendURL;
    }

    /**
     * @return the layer
     */
    public GPRasterLayer getLayer() {
        return layer;
    }

    /**
     * @param layer
     *            the layer to set
     */
    public void setLayer(GPRasterLayer layer) {
        this.layer = layer;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("GPStyle {");
        str.append("id=").append(id);
        str.append(", name=").append(name);
        str.append(", title=").append(title);
        str.append(", abstractText=").append(abstractText);
        str.append(", legendURL=").append(legendURL);
        str.append(", layer.id=").append(
                layer != null ? layer.getId() : "NULL").append('}');
        return str.toString();
    }
}
