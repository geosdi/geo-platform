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

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.global.security.IGPUserManageDetail;
import org.geosdi.geoplatform.response.UserDTO;
import org.springframework.stereotype.Component;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Component(value = "dtoUserConverter")
public class DTOUserConverter {

    public GPUser convertToGPUser(IGPUserManageDetail userDetail) {
        GPUser user = new GPUser();

        if (userDetail.getId() != null) {
            user.setId(userDetail.getId());
            user.setEnabled(true);
        }

        user.setName(userDetail.getName());
        user.setEmailAddress(userDetail.getEmail());
        user.setUsername(userDetail.getUsername());
        user.setPassword(userDetail.getPassword());
        user.setEnabled(userDetail.isEnabled());
        user.setAccountTemporary(userDetail.isTemporary());

        GPAuthority authority = new GPAuthority();
        authority.setAuthority(userDetail.getAuthority());
        authority.setTrustedLevel(userDetail.getTrustedLevel());
        user.setGPAuthorities(Arrays.asList(authority));

        return user;
    }

    // All properties unless the password
    public GPUserManageDetail convertToGPUserManageDetail(UserDTO userDTO) {
        GPUserManageDetail user = new GPUserManageDetail();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmailAddress());
        user.setEnabled(userDTO.isEnabled());
        user.setCreationDate(userDTO.getCreationDate());
        user.setTemporary(userDTO.isTemporary());
        user.setExpired(userDTO.isExpired());
        user.setAuthority(this.convertToAuthority(userDTO.getRoles()));
        user.setOrganization(userDTO.getOrganization());
        user.setTrustedLevel(userDTO.getTrustedLevel());
        return user;
    }

    // NOTE: Now a user must have at most one role
    private String convertToAuthority(List<String> roles) {
        Iterator<String> iterator = roles.iterator();
        String authority = null;
        if (iterator.hasNext()) {
            authority = iterator.next();
        }
        return authority;
    }

    // All properties unless the password
    public GPUserManageDetail convertToGPUserManageDetail(GPUser gpUser) {
        GPUserManageDetail user = new GPUserManageDetail();
        user.setId(gpUser.getId());
        user.setName(gpUser.getName());
        user.setUsername(gpUser.getUsername());
        user.setEmail(gpUser.getEmailAddress());
        user.setCreationDate(gpUser.getCreationDate());
        user.setTemporary(gpUser.isAccountTemporary());
        this.extractGPAuthoritiesInToUser(user, gpUser.getGPAuthorities());
        user.setOrganization(gpUser.getOrganization().getName());
        return user;
    }

    /**
     * A User must have at most one role.
     *
     * @todo user can have more roles
     */
    private void extractGPAuthoritiesInToUser(GPUserManageDetail user, List<GPAuthority> authorities) {
        Iterator<GPAuthority> iterator = authorities.iterator();
        if (iterator.hasNext()) {
            GPAuthority authority = iterator.next();
            user.setAuthority(authority.getAuthority());
            user.setTrustedLevel(authority.getTrustedLevel());
        }
    }
}
