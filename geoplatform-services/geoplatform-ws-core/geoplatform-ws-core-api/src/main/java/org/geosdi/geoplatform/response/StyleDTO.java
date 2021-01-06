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
package org.geosdi.geoplatform.response;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.geosdi.geoplatform.core.model.GPStyle;
import org.geotools.data.ows.StyleImpl;

/**
 * @todo for future use
 * 
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @author Michele Santomauro
 */
@XmlRootElement(name = "StyleDTO")
@XmlType(propOrder = {"id", "name", "title", "abstractText", "legendURL"})
public class StyleDTO {

    private Long id;
    private String name;
    private String title;
    private String abstractText;
    private String legendURL;

    //<editor-fold defaultstate="collapsed" desc="Constructor method">
    /**
     * Default constructor
     */
    public StyleDTO() {
    }

    /**
     * Constructor with GPLayer as arg
     */
    public StyleDTO(GPStyle style) {
        this.id = style.getId();
        this.name = style.getName();
        this.title = style.getTitle();
        this.abstractText = style.getAbstractText();
        this.legendURL = style.getLegendURL();
    }

    public StyleDTO(StyleImpl style) {
        assert (style != null) : "StyleImpl is NULL";

        this.name = style.getName();
        this.title = style.getTitle() == null ? "" : style.getTitle().toString();
        this.abstractText = style.getAbstract() == null ? "" : style.getAbstract().toString();
        // TODO: FIX StyleDTO(StyleImpl style) and DEL
//        this.legendURL = style.getLegendURLs() == null ? "" : style.getLegendURLs().toArray().toString();
//        // 
//        if (style.getLegendURLs() != null) {
//            System.out.println("\n\nURL#K\n" + style.getLegendURLs().size());
//            for (Object o : style.getLegendURLs()) {
//                System.out.println("\n\nURL#K\n" + o);
//            }
//        }
//        if (style.getStyleSheetURL() != null) {
//            System.out.println("\n\nURL#1\n" + style.getStyleSheetURL().toString());
//        }
//        if (style.getStyleURL() != null) {
//            System.out.println("\n\nURL#2\n" + style.getStyleURL().toString());
//        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter and setter methods">
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
    //</editor-fold>

    @Override
    public String toString() {
        return "StyleDTO [" + "id=" + id + ", name=" + name
                + ", title=" + title + ", abstractText=" + abstractText
                + ", legendURL=" + legendURL + ']';
    }

    public static List<StyleDTO> convertToStyleDTOList(List<GPStyle> styles) {
        List<StyleDTO> stylesDTO = new ArrayList<StyleDTO>(styles.size());

        for (GPStyle style : styles) {
            stylesDTO.add(new StyleDTO(style));
        }

        return stylesDTO;
    }
}
