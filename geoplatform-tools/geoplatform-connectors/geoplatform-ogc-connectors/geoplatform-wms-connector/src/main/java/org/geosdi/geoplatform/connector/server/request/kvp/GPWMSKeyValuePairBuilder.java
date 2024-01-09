/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.server.request.kvp;

import org.geosdi.geoplatform.connector.server.request.GPWMSKeyValuePair;
import org.geosdi.geoplatform.connector.server.request.kvp.GPWMSRequestKvpReader.WMSRequestKvpReader;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSKeyValuePairBuilder<R, KVPBuilder extends GPWMSKeyValuePairBuilder> extends GPWMSKeyValuePair {

    /**
     * @param theKeyValuePair
     * @return {@link KVPBuilder}
     */
     KVPBuilder withKeyValuePair(@Nonnull(when = NEVER) String theKeyValuePair);

    /**
     * @return {@link R}
     * @throws Exception
     */
    R build() throws Exception;

    abstract class GPWMSBaseKeyValuePairBuilder<R, KVPBuilder extends GPWMSKeyValuePairBuilder> implements GPWMSKeyValuePairBuilder<R, KVPBuilder> {

        protected static final GPWMSRequestKvpReader wmsRequestKvpReader = new WMSRequestKvpReader();
        //
        protected ThreadLocal<String> keyValuePair = withInitial(() -> null);

        protected GPWMSBaseKeyValuePairBuilder() {
        }

        /**
         * @param theKeyValuePair
         * @return {@link KVPBuilder}
         */
        @Override
        public KVPBuilder withKeyValuePair(@Nonnull(when = NEVER) String theKeyValuePair) {
            this.keyValuePair.set(theKeyValuePair);
            return self();
        }

        /**
         * @return {@link R}
         * @throws Exception
         */
        @Override
        public final R build() throws Exception {
            checkArgument((this.keyValuePair.get() != null) && !(this.keyValuePair.get().trim().isEmpty()), "The Parameter keyValuePair must not be null or an empty string.");
            return this.internalBuild();
        }

        /**
         * @return {@link KVPBuilder}
         */
        protected KVPBuilder self() {
            return (KVPBuilder) this;
        }

        /**
         * @return {@link String}
         */
        @Override
        public final String toWMSKeyValuePair() {
            return this.keyValuePair.get();
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }

        /**
         * @return
         * @throws Exception
         */
        protected abstract R internalBuild() throws Exception;
    }
}