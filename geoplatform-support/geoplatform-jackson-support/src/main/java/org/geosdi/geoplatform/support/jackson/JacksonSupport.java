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
package org.geosdi.geoplatform.support.jackson;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geosdi.geoplatform.support.jackson.property.JacksonSupportConfigFeature;

import javax.annotation.Nonnull;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromArray;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FunctionalInterface
public interface JacksonSupport {

    /**
     * @param feature
     * @param <J>
     * @return {@link J}
     */
    default <J extends JacksonSupport> J configure(@Nonnull(when = NEVER) JacksonSupportConfigFeature feature) {
        checkArgument(feature != null, "The Parameter feature must not be null.");
        checkArgument(this.getDefaultMapper() != null, "The Parameter ObjectMapper must not be null.");
        feature.configureMapper(this.getDefaultMapper());
        return (J) this;
    }

    /**
     * @param features
     * @param <J>
     * @return {@link J}
     */
    default <J extends JacksonSupport> J configure(@Nonnull(when = NEVER) JacksonSupportConfigFeature... features) {
        checkArgument(features != null, "The Parameter features must not be null.");
        checkArgument(this.getDefaultMapper() != null, "The Parameter ObjectMapper must not be null.");
        fromArray(features)
                .filter(Objects::nonNull)
                .subscribe(f -> f.configureMapper(this.getDefaultMapper()), e -> e.printStackTrace());
        return (J) this;
    }

    /**
     * @return {@link ObjectMapper}
     */
    ObjectMapper getDefaultMapper();

    /**
     * @param module
     * @param <J>
     * @return {@link J}
     */
    default <J extends JacksonSupport> J registerModule(@Nonnull(when = NEVER) Module module) {
        checkArgument(module != null, "The Parameter module must not be null.");
        checkArgument(this.getDefaultMapper() != null, "The Parameter ObjectMapper must not be null.");
        this.getDefaultMapper().registerModule(module);
        return (J) this;
    }

    /**
     * @param format
     * @param <J>
     * @return {@link J}
     */
    default <J extends JacksonSupport> J setDateFormat(@Nonnull(when = NEVER) DateFormat format) {
        checkArgument(format != null, "The Parameter format must not be null.");
        checkArgument(this.getDefaultMapper() != null, "The Parameter ObjectMapper must not be null.");
        this.getDefaultMapper().setDateFormat(format);
        return (J) this;
    }

    /**
     * @param locale
     * @param <J>
     * @return {@link J}
     */
    default <J extends JacksonSupport> J setLocale(@Nonnull(when = NEVER) Locale locale) {
        checkArgument(locale != null, "The Parameter locale must not be null.");
        checkArgument(this.getDefaultMapper() != null, "The Parameter ObjectMapper must not be null.");
        this.getDefaultMapper().setLocale(locale);
        return (J) this;
    }

    /**
     * @param timeZone
     * @param <J>
     * @return {@link J}
     */
    default <J extends JacksonSupport> J setTimeZone(@Nonnull(when = NEVER) TimeZone timeZone) {
        checkArgument(timeZone != null, "The Parameter timeZone must not be null.");
        checkArgument(this.getDefaultMapper() != null, "The Parameter ObjectMapper must not be null.");
        this.getDefaultMapper().setTimeZone(timeZone);
        return (J) this;
    }

    /**
     * @return {@link String}
     */
    default String getProviderName() {
        return this.getClass().getSimpleName();
    }
}
