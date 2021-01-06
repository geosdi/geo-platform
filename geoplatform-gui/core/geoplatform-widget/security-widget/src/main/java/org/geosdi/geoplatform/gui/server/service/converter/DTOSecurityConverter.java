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
package org.geosdi.geoplatform.gui.server.service.converter;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPMessage;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GPViewport;
import org.geosdi.geoplatform.gui.client.model.security.GPLoginUserDetail;
import org.geosdi.geoplatform.gui.configuration.map.client.GPClientViewport;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.impl.message.GPClientMessage;
import org.geosdi.geoplatform.gui.impl.users.options.UserTreeOptions;
import org.geosdi.geoplatform.gui.model.message.IGPClientMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Component(value = "dtoSecurityConverter")
public class DTOSecurityConverter {

    private @Value("configurator{host_xmpp_server}")
    String hostXmppServer;
    //
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public GPClientMessage convertMessage(GPMessage message) {
        GPClientMessage clientMessage = new GPClientMessage();
        clientMessage.setId(message.getId());
        clientMessage.setSender(message.getSender().getNaturalID());
        clientMessage.setRecipient(message.getRecipient().getNaturalID());
        clientMessage.setCreationDate(message.getCreationDate());
        clientMessage.setSubject(message.getSubject());
        clientMessage.setText(message.getText());
        clientMessage.setRead(message.isRead());
        clientMessage.setCommand(message.getCommands().get(0));
        clientMessage.setCommandProperties(message.getCommandsProperties());
        return clientMessage;
    }

    public IGPAccountDetail convertAccountToDTO(GPAccount account, GPAccountProject accountProject,
            GPViewport viewport, List<GPMessage> messages) {
        GPLoginUserDetail accountDetail = new GPLoginUserDetail();
        UserTreeOptions usertreeOptions = new UserTreeOptions();
        accountDetail.setId(account.getId());
        accountDetail.setUsername(account.getNaturalID()); // Forced representation
        accountDetail.setOrganization(account.getOrganization().getName());
        usertreeOptions.setLoadExpandedFolders(account.isLoadExpandedFolders());
        accountDetail.setTreeOptions(usertreeOptions);
        if (account instanceof GPUser) {
            GPUser user = (GPUser) account;
            accountDetail.setName(user.getName());
            accountDetail.setEmail(user.getEmailAddress());
            this.extractGPAuthoritiesInToUser(accountDetail, account.getGPAuthorities());
        }
        if (account.getGsAccount() != null) {
            accountDetail.setAuthkey(account.getGsAccount().getAuthkey());
        }
        accountDetail.setHostXmppServer(hostXmppServer);
        if (accountProject != null) {
            accountDetail.setBaseLayer(accountProject.getBaseLayer());
        }
        if (viewport != null) {
            GPBBox serverBBOX = viewport.getBbox();
            BBoxClientInfo clientBBOX = new BBoxClientInfo(serverBBOX.getMinX(), serverBBOX.getMinY(),
                    serverBBOX.getMaxX(), serverBBOX.getMaxY());
            GPClientViewport clientViewport = new GPClientViewport(viewport.getName(),
                    viewport.getDescription(), clientBBOX, viewport.getZoomLevel(), viewport.isIsDefault());
            accountDetail.setViewport(clientViewport);
        }
        if (messages != null) {
            List<IGPClientMessage> unreadMessages = Lists.newArrayListWithCapacity(messages.size());
            for (GPMessage message : messages) {
                GPClientMessage clientMessage = this.convertMessage(message);
                unreadMessages.add(clientMessage);
                logger.debug("\n*** {}", clientMessage);
            }
            Collections.sort(unreadMessages);
            accountDetail.setUnreadMessages(unreadMessages);
        }
        return (IGPAccountDetail) accountDetail;
    }

    /**
     * A User must have at most one role.
     *
     * @todo user can have more roles
     */
    private void extractGPAuthoritiesInToUser(GPLoginUserDetail userDetail, List<GPAuthority> authorities) {
        assert (authorities != null) : "Authorities must be not null";
        Iterator<GPAuthority> iterator = authorities.iterator();
        if (iterator.hasNext()) {
            GPAuthority authority = iterator.next();
            userDetail.setAuthority(authority.getAuthority());
            userDetail.setTrustedLevel(authority.getTrustedLevel());
        }
    }
}
