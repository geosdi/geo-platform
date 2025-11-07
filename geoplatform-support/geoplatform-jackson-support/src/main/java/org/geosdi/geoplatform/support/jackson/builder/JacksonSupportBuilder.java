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
import org.geosdi.geoplatform.support.jackson.builder.JacksonSupportThreadSafeBuilder.GPJacksonSupportThreadSafeBuilder;
import org.geosdi.geoplatform.support.jackson.property.JacksonSupportConfigFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.databind.JacksonModule;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.cfg.CoercionConfigs;
import tools.jackson.databind.introspect.AnnotationIntrospectorPair;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nullable;
import java.text.DateFormat;
import java.util.*;
import java.util.function.Consumer;

import static io.reactivex.rxjava3.core.Flowable.fromArray;
import static io.reactivex.rxjava3.core.Flowable.fromIterable;
import static java.util.TimeZone.getDefault;
import static org.geosdi.geoplatform.support.jackson.JacksonSupport.of;
import static org.geosdi.geoplatform.support.jackson.annotation.JacksonAnnotationIntrospectorBuilder.JACKSON;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface JacksonSupportBuilder<M extends ObjectMapper> {

    /**
     * @param theLocale
     * @return {@link JacksonSupportBuilder}
     */
    JacksonSupportBuilder<M> withLocale(@Nullable Locale theLocale);

    /**
     * @param theFeature
     * @return {@link JacksonSupportBuilder}
     */
    JacksonSupportBuilder<M> configure(@Nullable JacksonSupportConfigFeature theFeature);

    /**
     * @param theFeatures
     * @return {@link JacksonSupportBuilder}
     */
    JacksonSupportBuilder<M> configure(@Nullable JacksonSupportConfigFeature... theFeatures);

    /**
     * @param theJacksonModule
     * @return {@link JacksonSupportBuilder}
     */
    JacksonSupportBuilder<M> registerModule(@Nullable JacksonModule theJacksonModule);

    /**
     * @param theJacksonModules
     * @return {@link JacksonSupportBuilder}
     */
    JacksonSupportBuilder<M> registerModule(@Nullable JacksonModule... theJacksonModules);

    /**
     * @param theDateFormat
     * @return {@link JacksonSupportBuilder}
     */
    JacksonSupportBuilder<M> withDateFormat(@Nullable DateFormat theDateFormat);

    /**
     * @param theTimeZone
     * @return {@link JacksonSupportBuilder}
     */
    JacksonSupportBuilder<M> withTimeZone(@Nullable TimeZone theTimeZone);

    /**
     * @param theIntrospectorBuilder
     * @return {@link JacksonSupportBuilder}
     */
    JacksonSupportBuilder<M> withIntespectorBuilder(@Nullable GPJacksonXmlAnnotationIntrospectorBuilder theIntrospectorBuilder);

    /**
     * @param theCoercionConfigs
     * @return {@link JacksonSupport}
     */
    JacksonSupportBuilder<M> withAllCoercionConfigFeature(@Nullable Consumer<CoercionConfigs> theCoercionConfigs);

    /**
     * @return {@link JacksonSupport<M>}
     */
    JacksonSupport<M> build();

    class GPJacksonSupportBuilder implements JacksonSupportBuilder<JsonMapper> {

        private static final Logger logger = LoggerFactory.getLogger(GPJacksonSupportBuilder.class);
        //
        Locale locale = Locale.getDefault();
        DateFormat dateFormat;
        TimeZone timeZone = getDefault();
        List<JacksonModule> jacksonModules = new ArrayList<>();
        List<JacksonSupportConfigFeature> jacksonSupportConfigFeatures = new ArrayList<>();
        GPJacksonXmlAnnotationIntrospectorBuilder introspectorBuilder = null;
        Consumer<CoercionConfigs> coercionConfigs;

        GPJacksonSupportBuilder() {
        }

        /**
         * @param threadSafe
         * @return {@link JacksonSupportBuilder}
         */
        public static JacksonSupportBuilder<JsonMapper> builder(boolean threadSafe) {
            return ((threadSafe) ? new GPJacksonSupportThreadSafeBuilder() : new GPJacksonSupportBuilder());
        }

        /**
         * @param theLocale
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> withLocale(@Nullable Locale theLocale) {
            this.locale = theLocale;
            return self();
        }

        /**
         * @param theFeature
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> configure(@Nullable JacksonSupportConfigFeature theFeature) {
            if (theFeature != null) {
                this.jacksonSupportConfigFeatures.add(theFeature);
            }
            return self();
        }

        /**
         * @param theFeatures
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> configure(@Nullable JacksonSupportConfigFeature... theFeatures) {
            if (theFeatures != null) {
                fromArray(theFeatures)
                        .filter(Objects::nonNull)
                        .subscribe(this.jacksonSupportConfigFeatures::add, Throwable::printStackTrace);
            }
            return self();
        }

        /**
         * @param theJacksonModule
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> registerModule(@Nullable JacksonModule theJacksonModule) {
            if (theJacksonModule != null) {
                this.jacksonModules.add(theJacksonModule);
            }
            return self();
        }

        /**
         * @param theJacksonModules
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> registerModule(@Nullable JacksonModule... theJacksonModules) {
            if (theJacksonModules != null) {
                fromArray(theJacksonModules)
                        .filter(Objects::nonNull)
                        .subscribe(this.jacksonModules::add, Throwable::printStackTrace);
            }
            return self();
        }

        /**
         * @param theDateFormat
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> withDateFormat(@Nullable DateFormat theDateFormat) {
           this.dateFormat = theDateFormat;
           return self();
        }

        /**
         * @param theTimeZone
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> withTimeZone(@Nullable TimeZone theTimeZone) {
           this.timeZone = theTimeZone;
           return self();
        }

        /**
         * @param theIntrospectorBuilder
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> withIntespectorBuilder(@Nullable GPJacksonXmlAnnotationIntrospectorBuilder theIntrospectorBuilder) {
          this.introspectorBuilder = theIntrospectorBuilder;
          return self();
        }

        /**
         * @param theCoercionConfigs
         * @return {@link JacksonSupportBuilder}
         */
        @Override
        public JacksonSupportBuilder<JsonMapper> withAllCoercionConfigFeature(@Nullable Consumer<CoercionConfigs> theCoercionConfigs) {
            this.coercionConfigs = theCoercionConfigs;
            return self();
        }

        /**
         * @return {@link JacksonSupport<JsonMapper>}
         */
        @Override
        public JacksonSupport<JsonMapper> build() {
            var jsonMapperbuilder = JsonMapper.builder();
            if (this.locale != null) {
                jsonMapperbuilder.defaultLocale(this.locale);
            }
            if (this.dateFormat != null) {
                jsonMapperbuilder.defaultDateFormat(this.dateFormat);
            }
            if (this.timeZone != null) {
                jsonMapperbuilder.defaultTimeZone(this.timeZone);
            }

            logger.trace("#####################LOCALE : {} - JACKSON_SUPPORT_CONFIG_FEATURES : {} - INTROSPECTOR : {} - DATE_FORMAT : {}\n", this.locale,
                    this.jacksonSupportConfigFeatures, this.introspectorBuilder, this.dateFormat);

            fromIterable(this.jacksonModules)
                    .filter(Objects::nonNull)
                    .subscribe(jsonMapperbuilder::addModule, Throwable::printStackTrace);

            fromIterable(this.jacksonSupportConfigFeatures)
                    .filter(Objects::nonNull)
                    .subscribe(f -> f.configureMapper(jsonMapperbuilder), Throwable::printStackTrace);
            var primary = JACKSON.build();
            var secondary = ((this.introspectorBuilder != null) ? this.introspectorBuilder.build() : null);
            jsonMapperbuilder.annotationIntrospector((secondary != null) ? new AnnotationIntrospectorPair(primary, secondary) : primary);
            if (this.coercionConfigs != null) {
                jsonMapperbuilder.withAllCoercionConfigs(this.coercionConfigs);
            }
            return of(jsonMapperbuilder.build());
        }

        /**
         * @return {@link JacksonSupportBuilder}
         */
        protected JacksonSupportBuilder<JsonMapper> self() {
            return this;
        }
    }
}