/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 *
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 *
 */
package org.geosdi.geoplatform.gui.client.model;

import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class GPServerBeanModel extends GeoPlatformBeanModel {

    public enum GPServerKeyValue {

        URL_SERVER("urlServer"), NAME("name"), TITLE("title"),
        CONTACT_PERSON("contactPerson"),
        CONTACT_ORGANIZATION("contactOrganization");

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

    private long id;
    private String urlServer;
    private String name;
    private String title;
    private String contactPerson;
    private String contactOrganization;

     /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the urlServer
     */
    public String getUrlServer() {
        return urlServer;
    }

    /**
     * @param urlServer the urlServer to set
     */
    public void setUrlServer(String urlServer) {
        this.urlServer = urlServer;
        set(GPServerKeyValue.URL_SERVER.getValue(), this.urlServer);
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
     * @return the contactPerson
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * @param contactPerson the contactPerson to set
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        set(GPServerKeyValue.CONTACT_PERSON.getValue(), this.contactPerson);
    }

    /**
     * @return the contactOrganization
     */
    public String getContactOrganization() {
        return contactOrganization;
    }

    /**
     * @param contactOrganization the contactOrganization to set
     */
    public void setContactOrganization(String contactOrganization) {
        this.contactOrganization = contactOrganization;
        set(GPServerKeyValue.CONTACT_ORGANIZATION.getValue(), this.contactOrganization);
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
        int hash = 3;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return "GPServerDTO{" + "id = " + id + "urlServer = " + urlServer + "name =  "
                + name + "title=" + title + "contactPerson=" + contactPerson +
                "contactOrganization = " + contactOrganization + '}';
    }
}
