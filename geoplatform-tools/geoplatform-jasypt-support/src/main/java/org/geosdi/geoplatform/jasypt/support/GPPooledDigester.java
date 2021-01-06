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
package org.geosdi.geoplatform.jasypt.support;

import net.jcip.annotations.Immutable;
import org.jasypt.digest.PooledStringDigester;
import org.jasypt.digest.config.StringDigesterConfig;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class GPPooledDigester implements GPDigesterConfigurator, InitializingBean {

    private final PooledStringDigester digester;
    private StringDigesterConfig config;

    public GPPooledDigester() {
        this.digester = new PooledStringDigester();
    }

    /**
     * @param plainText
     * @return {@link String}
     */
    @Override
    public String digest(@Nonnull(when = NEVER) String plainText) {
        checkArgument(((plainText != null) && !(plainText.trim().isEmpty())), "The Parameter plainText must not be null or an empty string");
        return this.digester.digest(plainText);
    }

    /**
     * @param encryptedText
     * @param plainText
     * @return {@link Boolean}
     */
    @Override
    public boolean matches(@Nonnull(when = NEVER) String encryptedText, @Nonnull(when = NEVER) String plainText) {
        checkArgument(((plainText != null) && !(plainText.trim().isEmpty())), "The Parameter plainText must not be null or an empty string");
        checkArgument(((encryptedText != null) && !(encryptedText.trim().isEmpty())), "The Parameter encryptedText must not be null or an empty string");
        return this.digester.matches(plainText, encryptedText);
    }

    /**
     * @param config the config to set
     */
    public void setConfig(@Nonnull(when = NEVER) StringDigesterConfig config) {
        checkArgument(config != null, "The Parameter config must not be null.");
        this.config = config;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.digester.setConfig(config);
    }
}