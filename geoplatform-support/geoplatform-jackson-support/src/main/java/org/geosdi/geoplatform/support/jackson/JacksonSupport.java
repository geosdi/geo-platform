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
package org.geosdi.geoplatform.support.jackson;

import org.geosdi.geoplatform.support.jackson.function.GPJacksonCheck;
import org.geosdi.geoplatform.support.jackson.property.JacksonSupportConfigFeature;
import tools.jackson.databind.JacksonModule;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.cfg.CoercionConfigs;
import tools.jackson.databind.cfg.MapperBuilder;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.json.JsonMapper.Builder;

import javax.annotation.Nonnull;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromArray;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FunctionalInterface
public interface JacksonSupport<M extends ObjectMapper> {

    /**
     * @param feature
     * @param <J>
     * @return {@link J}
     */
    default <J extends JacksonSupport<M>> J configure(@Nonnull(when = NEVER) JacksonSupportConfigFeature feature) {
        checkArgument(feature != null, "The Parameter feature must not be null.");
        checkArgument(this.getDefaultMapper() != null, "The Parameter ObjectMapper must not be null.");
        MapperBuilder<JsonMapper, Builder> builder = this.getDefaultMapper().rebuild();
        feature.configureMapper(builder);
        return (J) new GPJacksonSupport(builder.build());
    }

    /**
     * @param features
     * @param <J>
     * @return {@link J}
     */
    default <J extends JacksonSupport<M>> J configure(@Nonnull(when = NEVER) JacksonSupportConfigFeature... features) {
        checkArgument(features != null, "The Parameter features must not be null.");
        checkArgument(this.getDefaultMapper() != null, "The Parameter ObjectMapper must not be null.");
        MapperBuilder<JsonMapper, Builder> builder = this.getDefaultMapper().rebuild();
        fromArray(features)
                .filter(Objects::nonNull)
                .subscribe(f -> f.configureMapper(builder), Throwable::printStackTrace);
        return (J) new GPJacksonSupport(builder.build());
    }

    /**
     * @return {@link M}
     */
    M getDefaultMapper();

    /**
     * @param module
     * @param <J>
     * @return {@link J}
     */
    default <J extends JacksonSupport<M>> J registerModule(@Nonnull(when = NEVER) JacksonModule module) {
        checkArgument(module != null, "The Parameter module must not be null.");
        checkArgument(this.getDefaultMapper() != null, "The Parameter ObjectMapper must not be null.");
        MapperBuilder<JsonMapper, Builder> builder = this.getDefaultMapper().rebuild();
        builder.addModule(module);
        return (J) new GPJacksonSupport(builder.build());
    }

    /**
     * @param format
     * @param <J>
     * @return {@link J}
     */
    default <J extends JacksonSupport<M>> J setDateFormat(@Nonnull(when = NEVER) DateFormat format) {
        checkArgument(format != null, "The Parameter format must not be null.");
        checkArgument(this.getDefaultMapper() != null, "The Parameter ObjectMapper must not be null.");
        MapperBuilder<JsonMapper, Builder> builder = this.getDefaultMapper().rebuild();
        builder.defaultDateFormat(format);
        return (J) new GPJacksonSupport(builder.build());
    }

    /**
     * @param locale
     * @param <J>
     * @return {@link J}
     */
    default <J extends JacksonSupport<M>> J setLocale(@Nonnull(when = NEVER) Locale locale) {
        checkArgument(locale != null, "The Parameter locale must not be null.");
        checkArgument(this.getDefaultMapper() != null, "The Parameter ObjectMapper must not be null.");
        MapperBuilder<JsonMapper, Builder> builder = this.getDefaultMapper().rebuild();
        builder.defaultLocale(locale);
        return (J) new GPJacksonSupport(builder.build());
    }

    /**
     * @param timeZone
     * @param <J>
     * @return {@link J}
     */
    default <J extends JacksonSupport<M>> J setTimeZone(@Nonnull(when = NEVER) TimeZone timeZone) {
        checkArgument(timeZone != null, "The Parameter timeZone must not be null.");
        checkArgument(this.getDefaultMapper() != null, "The Parameter ObjectMapper must not be null.");
        MapperBuilder<JsonMapper, Builder> builder = this.getDefaultMapper().rebuild();
        builder.defaultTimeZone(timeZone);
        return (J) new GPJacksonSupport(builder.build());
    }

    /**
     * @return {@link String}
     */
    default String getProviderName() {
        return this.getClass().getSimpleName();
    }

    /**
     * @param theCheck
     * @return {@link String}
     * @throws Exception
     */
    default String writeAsString(@Nonnull(when = NEVER) GPJacksonCheck<Object> theCheck) throws Exception {
        checkArgument(theCheck != null, "The Parameter checkFunction must not be null.");
        return getDefaultMapper().writeValueAsString(theCheck.apply());
    }

    /**
     * @param theCoercionConfig
     * @return {@link J}
     * @param <J>
     */
    default <J extends JacksonSupport<M>> J withAllCoercionConfigFeature(@Nonnull(when = NEVER) Consumer<CoercionConfigs> theCoercionConfig) {
        checkArgument(theCoercionConfig != null, "The Parameter Consumer<CoercionConfigs> must not be null.");
        checkArgument(this.getDefaultMapper() != null, "The Parameter ObjectMapper must not be null.");
        MapperBuilder<JsonMapper, Builder> builder = this.getDefaultMapper().rebuild();
        builder.withAllCoercionConfigs(theCoercionConfig);
        return (J) new GPJacksonSupport(builder.build());
    }

    /**
     * @param theJsonMapper
     * @return {@link JacksonSupport<JsonMapper>}
     */
    static JacksonSupport<JsonMapper> of(@Nonnull(when = NEVER) JsonMapper theJsonMapper) {
        return new GPJacksonSupport(theJsonMapper);
    }
}