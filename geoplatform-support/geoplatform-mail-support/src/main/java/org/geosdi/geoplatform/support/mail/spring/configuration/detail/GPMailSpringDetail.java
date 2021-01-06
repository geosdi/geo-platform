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
package org.geosdi.geoplatform.support.mail.spring.configuration.detail;

import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.support.mail.configuration.detail.GPMailDetail;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.geosdi.geoplatform.support.mail.spring.configuration.detail.GPMailDetailSupport.GP_ENC_MAIL_KEY;
import static org.geosdi.geoplatform.support.mail.spring.configuration.detail.GPMailDetailSupport.GP_REPLAY_KEY;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Component(value = "gpMailSpringDetail")
class GPMailSpringDetail implements GPMailDetail {

    @Value("gpMailConfigurator{gp.mail.username:@null}")
    private String from;
    @Value(value = "gpMailConfigurator{gp.mail.replayTo:@null}")
    private String replayTo;
    @Value(value = "gpMailConfigurator{gp.mail.fromName:@null}")
    private String fromName;
    @Value(value = "gpMailConfigurator{gp.mail.replayToName:@null}")
    private String replayToName;
    @Autowired
    private PooledPBEStringEncryptor gpMailPooledPBEStringEncryptor;

    /**
     * @return the from
     */
    @Override
    public String getFrom() {
        return this.from = (this.from != null) ? this.from : gpMailPooledPBEStringEncryptor.decrypt(GP_ENC_MAIL_KEY.getValue());
    }

    /**
     * @return the replayTo
     */
    @Override
    public String getReplayTo() {
        return this.replayTo = (this.replayTo != null) ? this.replayTo : gpMailPooledPBEStringEncryptor.decrypt(GP_ENC_MAIL_KEY.getValue());
    }

    /**
     * @return the fromName
     */
    @Override
    public String getFromName() {
        return this.fromName = (this.fromName != null) ? this.fromName : GP_REPLAY_KEY.getValue();
    }

    /**
     * @return the replayToName
     */
    @Override
    public String getReplayToName() {
        return this.replayToName = (this.replayToName != null) ? this.replayToName : GP_REPLAY_KEY.getValue();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{ " + "from = " + getFrom()
                + ", replayTo = " + getReplayTo()
                + ", fromName = " + getFromName()
                + ", replayToName = " + getReplayToName() + '}';
    }
}