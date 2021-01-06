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
package org.geosdi.geoplatform.gui.responce;

import java.io.Serializable;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class TextInfo implements Serializable {

    private static final long serialVersionUID = 4543828151776154751L;
    //
    private String text;
    //
    private boolean searchTitle;
    private boolean searchAbstract;
    private boolean searchSubjects;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(boolean searchTitle) {
        this.searchTitle = searchTitle;
    }

    public boolean isSearchAbstract() {
        return searchAbstract;
    }

    public void setSearchAbstract(boolean searchAbstract) {
        this.searchAbstract = searchAbstract;
    }

    public boolean isSearchSubjects() {
        return searchSubjects;
    }

    public void setSearchSubjects(boolean searchSubjects) {
        this.searchSubjects = searchSubjects;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("TextInfo {");
        str.append("text = ").append(text);
        str.append(", searchTitle = ").append(searchTitle);
        str.append(", searchAbstract = ").append(searchAbstract);
        str.append(", searchSubjects = ").append(searchSubjects);
        return str.append('}').toString();
    }
}
