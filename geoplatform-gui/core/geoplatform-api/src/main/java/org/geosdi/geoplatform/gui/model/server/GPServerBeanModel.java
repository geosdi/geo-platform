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

import java.util.ArrayList;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPServerBeanModel extends GeoPlatformBeanModel {

    public enum GPServerKeyValue {

        URL_SERVER("urlServer"), NAME("name"), TITLE("title"), ALIAS("alias");
        private String value;

        GPServerKeyValue(String theValue) {
            this.value = theValue;
        }

        public String getValue() {
            return value;
        }
    }
    /**
     *
     */
    private static final long serialVersionUID = 3734113612153640102L;
    private Long id;
    private String name;
    private String title;
    private String organization;
    private ArrayList<? extends GPLayerGrid> layers;

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
        return super.get(GPServerKeyValue.URL_SERVER.getValue());
    }

    /**
     * @param urlServer the urlServer to set
     */
    public void setUrlServer(String urlServer) {
        set(GPServerKeyValue.URL_SERVER.getValue(), urlServer);
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
        set(GPServerKeyValue.NAME.getValue(), this.name);
    }

    /**
     * @return the alias
     */
    public String getAlias() {
        return super.get(GPServerKeyValue.ALIAS.getValue());
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        set(GPServerKeyValue.ALIAS.getValue(), alias);
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
        set(GPServerKeyValue.TITLE.getValue(), this.title);
    }

    /**
     * @return the organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * @param organization the organization to set
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * @return the layers
     */
    public ArrayList<? extends GPLayerGrid> getLayers() {
        return this.layers;
    }

    /**
     * @param layers the layers to set
     */
    public void setLayers(ArrayList<? extends GPLayerGrid> layers) {
        this.layers = layers;
    }

    /**
     * @return the layersLoaded
     */
    public boolean isLayersLoaded() {
        return this.layers != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GPServerBeanModel other = (GPServerBeanModel) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "GPServerBeanModel{" + "id = " + id
                + ", alias = " + getAlias()
                + ", urlServer = " + getUrlServer()
                + ", name =  " + name
                + ", title = " + title
                + ", organization = " + organization + '}';
    }
}
