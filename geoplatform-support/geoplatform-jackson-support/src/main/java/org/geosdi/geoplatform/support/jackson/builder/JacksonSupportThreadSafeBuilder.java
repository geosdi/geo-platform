/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.support.jackson.builder;

import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.geosdi.geoplatform.support.jackson.annotation.GPJacksonXmlAnnotationIntrospectorBuilder;
import org.geosdi.geoplatform.support.jackson.property.JacksonSupportConfigFeature;
import tools.jackson.databind.JacksonModule;
import tools.jackson.databind.cfg.CoercionConfigs;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.text.DateFormat;
import java.util.*;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public interface JacksonSupportThreadSafeBuilder<M extends JsonMapper> extends JacksonSupportBuilder<M> {

    /**
     * @param theLocale
     * @return {@link JacksonSupportBuilder}
     */
    @Override
    JacksonSupportBuilder<M> withLocale(@Nullable Locale theLocale);

    /**
     * @param theFeature
     * @return {@link JacksonSupportBuilder}
     */
    @Override
    JacksonSupportBuilder<M> configure(@Nullable JacksonSupportConfigFeature theFeature);

    /**
     * @param theFeatures
     * @return {@link JacksonSupportBuilder}
     */
    @Override
    JacksonSupportBuilder<M> configure(@Nullable JacksonSupportConfigFeature... theFeatures);

    /**
     * @param theJacksonModule
     * @return {@link JacksonSupportBuilder}
     */
    @Override
    JacksonSupportBuilder<M> registerModule(@Nullable JacksonModule theJacksonModule);

    /**
     * @param theJacksonModules
     * @return {@link JacksonSupportBuilder}
     */
    @Override
    JacksonSupportBuilder<M> registerModule(@Nullable JacksonModule... theJacksonModules);

    /**
     * @param theDateFormat
     * @return {@link JacksonSupportBuilder}
     */
    @Override
    JacksonSupportBuilder<M> withDateFormat(@Nullable DateFormat theDateFormat);

    /**
     * @param theTimeZone
     * @return {@link JacksonSupportBuilder}
     */
    @Override
    JacksonSupportBuilder<M> withTimeZone(@Nullable TimeZone theTimeZone);

    /**
     * @param theIntrospectorBuilder
     * @return {@link JacksonSupportBuilder}
     */
    @Override
    JacksonSupportBuilder<M> withIntespectorBuilder(@Nullable GPJacksonXmlAnnotationIntrospectorBuilder theIntrospectorBuilder);

    /**
     * @param theCoercionConfigs
     * @return {@link JacksonSupportBuilder}
     */
    @Override
    JacksonSupportBuilder<M> withAllCoercionConfigFeature(@Nullable Consumer<CoercionConfigs> theCoercionConfigs);

    /**
     * @return {@link JacksonSupport<M>}
     */
    @Override
    JacksonSupport<M> build();

    /**
     * Thread-safe builder based on <em>copy-on-first-write</em>.
     * <p>
     * The instance returned by {@code builder(true)} is a shared, {@code frozen} root: its state is
     * never mutated. The first mutating call on a frozen instance forks a single, thread-confined
     * (unfrozen) copy; every subsequent call in that chain mutates the copy in place. So each chain
     * pays exactly ONE copy regardless of its length, and concurrent chains starting from the shared
     * root only ever read it (safe), each deriving its own private copy.
     */
    @ThreadSafe
    class GPJacksonSupportThreadSafeBuilder extends GPJacksonSupportBuilder implements JacksonSupportThreadSafeBuilder<JsonMapper> {

        private final boolean frozen;

        GPJacksonSupportThreadSafeBuilder() {
            this.frozen = true;
        }

        /**
         * @param other
         */
        GPJacksonSupportThreadSafeBuilder(@Nonnull(when = NEVER) GPJacksonSupportBuilder other) {
            checkArgument(other != null, "The Parameter other must not be null.");
            this.locale = other.locale;
            this.dateFormat = other.dateFormat;
            this.timeZone = other.timeZone;
            this.jacksonModules = new HashMap<>(other.jacksonModules);
            this.jacksonSupportConfigFeatures = new HashSet<>(other.jacksonSupportConfigFeatures);
            this.introspectorBuilder = other.introspectorBuilder;
            this.coercionConfigs = other.coercionConfigs;
            this.frozen = false;
        }

        /**
         * Applies {@code theConsumer} either to a fresh copy (if this instance is the shared frozen
         * root) or directly to this instance (if it is already a thread-confined copy).
         *
         * @param theConsumer
         * @return {@link JacksonSupportBuilder}
         */
        JacksonSupportBuilder<JsonMapper> mutate(@Nonnull(when = NEVER) Consumer<GPJacksonSupportBuilder> theConsumer) {
            checkArgument(theConsumer != null, "The Parameter consumer must not be null.");
            GPJacksonSupportThreadSafeBuilder target = (this.frozen) ? new GPJacksonSupportThreadSafeBuilder(this) : this;
            theConsumer.accept(target);
            return target;
        }

        /**
         * @param theLocale
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> withLocale(@Nullable Locale theLocale) {
            return mutate(builder -> builder.locale = theLocale);
        }

        /**
         * @param theFeature
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> configure(@Nullable JacksonSupportConfigFeature theFeature) {
            return mutate(builder -> builder.applyConfigure(theFeature));
        }

        /**
         * @param theFeatures
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> configure(@Nullable JacksonSupportConfigFeature... theFeatures) {
            return mutate(builder -> builder.applyConfigure(theFeatures));
        }

        /**
         * @param theJacksonModule
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> registerModule(@Nullable JacksonModule theJacksonModule) {
            return mutate(builder -> builder.applyRegisterModule(theJacksonModule));
        }

        /**
         * @param theJacksonModules
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> registerModule(@Nullable JacksonModule... theJacksonModules) {
            return mutate(builder -> builder.applyRegisterModule(theJacksonModules));
        }

        /**
         * @param theDateFormat
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> withDateFormat(@Nullable DateFormat theDateFormat) {
            return mutate(builder -> builder.applyDateFormat(theDateFormat));
        }

        /**
         * @param theTimeZone
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> withTimeZone(@Nullable TimeZone theTimeZone) {
            return mutate(builder -> builder.applyTimeZone(theTimeZone));
        }

        /**
         * @param theIntrospectorBuilder
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> withIntespectorBuilder(@Nullable GPJacksonXmlAnnotationIntrospectorBuilder theIntrospectorBuilder) {
            return mutate(builder -> builder.applyIntrospectorBuilder(theIntrospectorBuilder));
        }

        /**
         * @param theCoercionConfigs
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> withAllCoercionConfigFeature(@Nullable Consumer<CoercionConfigs> theCoercionConfigs) {
            return mutate(builder -> builder.applyCoercionConfigs(theCoercionConfigs));
        }
    }
}