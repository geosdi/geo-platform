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
package org.geosdi.geoplatform.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
@XmlRootElement(name = "PaginatedSearchRequest")
public class PaginatedSearchRequest extends SearchRequest {

    private static final long serialVersionUID = -5688971381043223245L;

    private int num;
    private int page;

    public PaginatedSearchRequest() {
    }

    public PaginatedSearchRequest(int num, int page) {
        this.num = num;
        this.page = page;
    }

    public PaginatedSearchRequest(String nameLike, int num, int page) {
        super(nameLike);
        this.num = num;
        this.page = page;
    }

    public PaginatedSearchRequest(String nameLike, LikePatternType likeType,
            int num, int page) {
        super(nameLike, likeType);
        this.num = num;
        this.page = page;
    }

    /**
     * @param num the number of entries per page (you may get less entries in
     * the last page)
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * @return the number of entries per page (you may get less entries in the
     * last page)
     */
    @XmlElement(required = true, nillable = false)
    public int getNum() {
        return num;
    }

    /**
     * @param page the page number
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the page number
     */
    @XmlElement(required = true, nillable = false)
    public int getPage() {
        return page;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getClass().getSimpleName());
        builder.append(" [num=").append(num);
        builder.append(", page=").append(page);
        builder.append(", like=").append(super.getNameLike()).append(']');
        return builder.toString();
    }
}
