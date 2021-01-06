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
package org.geosdi.geoplatform.gui.model.server;

import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class GPCSWServerBeanModel extends GeoPlatformBeanModel {

    private static final long serialVersionUID = 1326228959299732490L;
    //
    private Long id; // For performance purpose: used for equals() and hashCode() methods

    public enum GPCSWServerKeyValue {

        ID, URL_SERVER, TITLE, ALIAS;
    }

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
     * @return the urlServer
     */
    public String getUrlServer() {
        return super.get(GPCSWServerKeyValue.URL_SERVER.toString());
    }

    /**
     * @param urlServer the urlServer to set
     */
    public void setUrlServer(String urlServer) {
        super.set(GPCSWServerKeyValue.URL_SERVER.toString(), urlServer);
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return super.get(GPCSWServerKeyValue.TITLE.toString());
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        set(GPCSWServerKeyValue.TITLE.toString(), title);
    }

    /**
     * @return the alias
     */
    public String getAlias() {
        return super.get(GPCSWServerKeyValue.ALIAS.toString());
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        super.set(GPCSWServerKeyValue.ALIAS.toString(), alias);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GPCSWServerBeanModel other = (GPCSWServerBeanModel) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "GPServerDTO{" + "id = " + id
                + ", urlServer = " + getUrlServer()
                + ", alias = " + getAlias()
                + ", title = " + getTitle() + '}';
    }
}
