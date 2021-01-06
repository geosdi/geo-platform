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
package org.geosdi.geoplatform.support.async.spring.message.key.builder;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.support.async.spring.message.key.IGPMessageKeyValue;
import org.geosdi.geoplatform.support.async.spring.message.key.IGPMessageKeyValue.GPMessageKeyValue;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPMessageKeyValueBuilder {

    /**
     * @param key
     * @return {@link IGPMessageKeyValueBuilder}
     */
    IGPMessageKeyValueBuilder withKey(String... key);

    /**
     * @param value
     * @return {@link Object}
     */
    IGPMessageKeyValueBuilder withValue(Object... value);

    /**
     * @return {@link IGPMessageKeyValue}
     * @throws Exception
     */
    IGPMessageKeyValue[] build() throws Exception;

    /**
     *
     */
    class GPMessageKeyValueBuilder implements IGPMessageKeyValueBuilder {

        private String[] key;
        private Object[] value;

        private GPMessageKeyValueBuilder() {
        }

        public static IGPMessageKeyValueBuilder messageKeyValueBuilder() {
            return new GPMessageKeyValueBuilder();
        }

        /**
         * @param key
         * @return {@link IGPMessageKeyValueBuilder}
         */
        @Override
        public IGPMessageKeyValueBuilder withKey(String... key) {
            this.key = key;
            return self();
        }

        /**
         * @param value
         * @return {@link Object}
         */
        @Override
        public IGPMessageKeyValueBuilder withValue(Object... value) {
            this.value = value;
            return self();
        }

        /**
         * @return {@link IGPMessageKeyValue}
         * @throws Exception
         */
        @Override
        public IGPMessageKeyValue[] build() throws Exception {
            this.checkArguments();
            return IntStream.iterate(0, n -> n + 1)
                    .limit(this.key.length)
                    .boxed().map(n -> new GPMessageKeyValue(key[n], value[n]))
                    .toArray(GPMessageKeyValue[]::new);
        }

        /**
         * @throws Exception
         */
        protected void checkArguments() throws Exception {
            Preconditions.checkArgument((this.key != null) && !(this.key.length == 0),
                    "The Parameter Key must not be null or an empty Array");
            Preconditions.checkArgument((this.value != null) && !(this.value.length == 0),
                    "The Parameter Value must not be null.");
            Preconditions.checkArgument(this.value.length == this.key.length, "The Keys and " +
                    "Values lenght must be the same.");
            Arrays.stream(this.key).forEach(key -> Preconditions.checkArgument((key != null)
                    && !(key.isEmpty()), "Every Key parameter must not be null or an Empty String."));
            Arrays.stream(this.value).forEach(value -> Preconditions.checkArgument((value != null),
                    "Every Value parameter must not be null."));
        }

        protected IGPMessageKeyValueBuilder self() {
            return this;
        }
    }
}