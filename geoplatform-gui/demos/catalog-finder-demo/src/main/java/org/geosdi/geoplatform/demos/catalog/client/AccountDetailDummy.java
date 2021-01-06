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
package org.geosdi.geoplatform.demos.catalog.client;

import org.geosdi.geoplatform.gui.configuration.map.client.GPClientViewport;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.global.security.IGPTreeOptions;
import org.geosdi.geoplatform.gui.model.message.IGPClientMessage;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;

import java.util.List;
import java.util.Map;

/**
 * Dummy Account, only for Catalog Finder demo purpose. The save catalog server
 * operation require an Organization name, fetch from Account logged.
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class AccountDetailDummy implements IGPAccountDetail {

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getUsername() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getEmail(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getOrganization() {
        return "geoSDI"; // Organization "geoSDI" must be into DB
    }

    @Override
    public String getAuthkey() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getHostXmppServer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getBaseLayer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public GPTrustedLevel getTrustedLevel() {
        return GPTrustedLevel.NONE;
    }

    @Override
    public String getAuthority() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setBaseLayer(String baseLayer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public GPClientViewport getViewport() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IGPTreeOptions getTreeOptions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setComponentPermission(Map<String, Boolean> componentPermission) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, Boolean> getComponentPermission() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean hasComponentPermission(String componentID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<IGPClientMessage> getUnreadMessages() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
