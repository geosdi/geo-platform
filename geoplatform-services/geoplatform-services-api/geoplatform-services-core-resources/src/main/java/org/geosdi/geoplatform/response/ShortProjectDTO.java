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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
//@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShortProjectDTO implements Serializable {

    private static final long serialVersionUID = -2692298561415328918L;
    //
    private Integer version;
    private Integer numberOfElements;
    private boolean internalPublic;
    private boolean externalPublic;

    public ShortProjectDTO() {
    }

    public ShortProjectDTO(Integer theVersion, Integer theNumberOfElements, boolean theInternalPublic,
            boolean theExternalPublic) {
        this.version = theVersion;
        this.numberOfElements = theNumberOfElements;
        this.internalPublic = theInternalPublic;
        this.externalPublic = theExternalPublic;
    }

    /**
     * @return {@link Integer}
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return {@link Integer}
     */
    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    /**
     * @param numberOfElements
     */
    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
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

    @Override
    public String toString() {
        return getClass().getName() + "{"
                + " version = " + version
                + ", numberOfElements = " + numberOfElements +
                ", internalPublic = " + internalPublic +
                ", externalPublic = " + externalPublic
                + '}';
    }
}
