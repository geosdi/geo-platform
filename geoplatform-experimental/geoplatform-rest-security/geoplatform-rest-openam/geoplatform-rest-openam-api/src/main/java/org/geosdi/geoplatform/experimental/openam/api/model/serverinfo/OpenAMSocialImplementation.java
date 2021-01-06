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
package org.geosdi.geoplatform.experimental.openam.api.model.serverinfo;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class OpenAMSocialImplementation implements IOpenAMSocialImplementation {

    private static final long serialVersionUID = 8894194481849660528L;
    //
    private String iconPath;
    private String authnChain;
    private String displayName;
    private Boolean valid;

    public OpenAMSocialImplementation() {
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getIconPath() {
        return this.iconPath;
    }

    /**
     * @param theIconPath
     */
    @Override
    public void setIconPath(String theIconPath) {
        this.iconPath = theIconPath;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getAuthnChain() {
        return this.authnChain;
    }

    /**
     * @param theAuthnChain
     */
    @Override
    public void setAuthnChain(String theAuthnChain) {
        this.authnChain = theAuthnChain;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * @param theDisplayName
     */
    @Override
    public void setDisplayName(String theDisplayName) {
        this.displayName = theDisplayName;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isValid() {
        return this.valid;
    }

    /**
     * @param theValid
     */
    @Override
    public void setValid(Boolean theValid) {
        this.valid = theValid;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " iconPath = '" + iconPath + '\'' +
                ", authnChain = '" + authnChain + '\'' +
                ", displayName = '" + displayName + '\'' +
                ", valid = " + valid +
                '}';
    }
}
