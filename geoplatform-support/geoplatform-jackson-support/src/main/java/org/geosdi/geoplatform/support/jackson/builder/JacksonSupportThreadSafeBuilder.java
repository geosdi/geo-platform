/**
 *
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.support.jackson.builder;

import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.geosdi.geoplatform.support.jackson.annotation.GPJacksonXmlAnnotationIntrospectorBuilder;
import org.geosdi.geoplatform.support.jackson.property.JacksonSupportConfigFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.databind.JacksonModule;
import tools.jackson.databind.introspect.AnnotationIntrospectorPair;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Flowable.fromIterable;
import static java.lang.ScopedValue.where;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.JacksonSupport.of;
import static org.geosdi.geoplatform.support.jackson.annotation.JacksonAnnotationIntrospectorBuilder.JACKSON;

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
     * @return {@link JacksonSupport<M>}
     */
    @Override
    JacksonSupport<M> build();

    class GPJacksonSupportThreadSafeBuilder extends GPJacksonSupportBuilder implements JacksonSupportThreadSafeBuilder<JsonMapper> {

        private static final Logger logger = LoggerFactory.getLogger(GPJacksonSupportThreadSafeBuilder.class);
        //
        private static final ScopedValue<GPJacksonSupportBuilder> JACKSON_SUPPORT_BUILDER_SCOPED_VALUE = ScopedValue.newInstance();

        GPJacksonSupportThreadSafeBuilder() {
        }

        /**
         * @param other
         */
        GPJacksonSupportThreadSafeBuilder(@Nonnull(when = NEVER) GPJacksonSupportBuilder other) {
            checkArgument(other != null, "The Parameter other must not be null.");
            this.locale = other.locale;
            this.dateFormat = other.dateFormat;
            this.timeZone = other.timeZone;
            this.jacksonModules = new ArrayList<>(other.jacksonModules);
            this.jacksonSupportConfigFeatures = new ArrayList<>(other.jacksonSupportConfigFeatures);
            this.introspectorBuilder = other.introspectorBuilder;
        }

        /**
         * @param theLocale
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> withLocale(@Nullable Locale theLocale) {
            return where(JACKSON_SUPPORT_BUILDER_SCOPED_VALUE, copyWith(builder -> builder.locale = theLocale))
                    .call(JACKSON_SUPPORT_BUILDER_SCOPED_VALUE::get);
        }

        /**
         * @return {@link JacksonSupport<JsonMapper>}
         */
        @Override
        public JacksonSupport<JsonMapper> build() {
            return where(JACKSON_SUPPORT_BUILDER_SCOPED_VALUE, this).call(this::internalBuild);
        }

        /**
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        protected JacksonSupportBuilder<JsonMapper> self() {
            return copy();
        }

        /**
         * @return {@link GPJacksonSupportThreadSafeBuilder}
         */
        GPJacksonSupportThreadSafeBuilder copy() {
            return new GPJacksonSupportThreadSafeBuilder(this);
        }

        /**
         * @param theConsumer
         * @return {@link GPJacksonSupportBuilder}
         */
        GPJacksonSupportBuilder copyWith(@Nonnull(when = NEVER) Consumer<GPJacksonSupportBuilder> theConsumer) {
            checkArgument(theConsumer != null, "The Parameter consumer must not be null.");
            GPJacksonSupportThreadSafeBuilder copy = copy();
            theConsumer.accept(copy);
            return copy;
        }

        /**
         * @return {@link JacksonSupport<JsonMapper>}
         */
        JacksonSupport<JsonMapper> internalBuild() {
            checkArgument((JACKSON_SUPPORT_BUILDER_SCOPED_VALUE.isBound()) && (JACKSON_SUPPORT_BUILDER_SCOPED_VALUE.get() != null), "The Parameter GPJacksonSupportThreadSafeBuilder must not be null.");
            var jsonMapperbuilder = JsonMapper.builder();
            var builder = JACKSON_SUPPORT_BUILDER_SCOPED_VALUE.get();
            if (builder.locale != null) {
                jsonMapperbuilder.defaultLocale(builder.locale);
            }
            if (builder.dateFormat != null) {
                jsonMapperbuilder.defaultDateFormat(builder.dateFormat);
            }
            if (builder.timeZone != null) {
                jsonMapperbuilder.defaultTimeZone(builder.timeZone);
            }

            logger.trace("##############LOCALE : {} - JACKSON_SUPPORT_CONFIG_FEATURES : {} - INTROSPECTOR : {} - DATE_FORMAT : {}\n", builder.locale,
                    builder.jacksonSupportConfigFeatures, builder.introspectorBuilder, builder.dateFormat);

            fromIterable(builder.jacksonModules)
                    .filter(Objects::nonNull)
                    .subscribe(jsonMapperbuilder::addModule, Throwable::printStackTrace);

            fromIterable(builder.jacksonSupportConfigFeatures)
                    .filter(Objects::nonNull)
                    .subscribe(f -> f.configureMapper(jsonMapperbuilder), Throwable::printStackTrace);
            var primary = JACKSON.build();
            var secondary = ((builder.introspectorBuilder != null) ? builder.introspectorBuilder.build() : null);
            jsonMapperbuilder.annotationIntrospector((secondary != null) ? new AnnotationIntrospectorPair(primary, secondary) : primary);
            if (builder.coercionConfigs != null) {
                jsonMapperbuilder.withAllCoercionConfigs(builder.coercionConfigs);
            }
            return of(jsonMapperbuilder.build());
        }
    }
}