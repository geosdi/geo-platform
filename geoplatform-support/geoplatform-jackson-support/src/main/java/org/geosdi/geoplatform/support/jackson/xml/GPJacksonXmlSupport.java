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
package org.geosdi.geoplatform.support.jackson.xml;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.geosdi.geoplatform.support.jackson.property.JacksonSupportConfigFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import static com.fasterxml.jackson.databind.type.TypeFactory.defaultInstance;
import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromArray;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.GPJacksonSupport.defaultProp;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.UNWRAP_ROOT_VALUE_ENABLE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJacksonXmlSupport implements JacksonXmlSupport {

    private static final Logger logger = LoggerFactory.getLogger(GPJacksonXmlSupport.class);
    //
    private final XmlMapper xmlMapper;

    public GPJacksonXmlSupport() {
        this(defaultProp());
    }

    /**
     * @param format
     */
    public GPJacksonXmlSupport(@Nonnull(when = NEVER) DateFormat format) {
        this();
        this.xmlMapper.setDateFormat(format);
    }

    /**
     * @param features
     */
    public GPJacksonXmlSupport(@Nonnull(when = NEVER) JacksonSupportConfigFeature... features) {
        checkArgument(features != null, "The Parameter features must not be null.");
        this.xmlMapper = new XmlMapper();
        AnnotationIntrospector primary = new JaxbAnnotationIntrospector(defaultInstance());
        AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
        this.xmlMapper.setAnnotationIntrospector(new AnnotationIntrospectorPair(primary, secondary));
        fromArray(features)
                .filter(Objects::nonNull)
                .filter(f -> f != UNWRAP_ROOT_VALUE_ENABLE)
                .doOnComplete(() -> logger.info("##############{} configure all Features.", this.getProviderName()))
                .subscribe(f -> f.configureMapper(this.getDefaultMapper()), e -> e.printStackTrace());
    }

    /**
     * @param format
     * @return {@link JacksonSupport}
     */
    @Override
    public JacksonXmlSupport setDateFormat(@Nonnull(when = NEVER) DateFormat format) {
        checkArgument(format != null, "The Parameter format must not be null.");
        this.xmlMapper.setDateFormat(format);
        return this;
    }

    /**
     * @return {@link XmlMapper}
     */
    @Override
    public final XmlMapper getDefaultMapper() {
        return this.xmlMapper;
    }

    /**
     * @param module
     * @return {@link JacksonXmlSupport}
     */
    @Override
    public final JacksonXmlSupport registerModule(Module module) {
        this.xmlMapper.registerModule(module);
        return this;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getProviderName() {
        return getClass().getSimpleName();
    }

    /**
     * @param feature
     * @return {@link JacksonXmlSupport}
     */
    @Override
    public JacksonXmlSupport configure(@Nonnull(when = NEVER) JacksonSupportConfigFeature feature) {
        checkArgument(feature != null, "The Parameter feature must not be null.");
        if (feature != UNWRAP_ROOT_VALUE_ENABLE) {
            feature.configureMapper(this.xmlMapper);
        }
        return this;
    }

    /**
     * @param features
     * @return {@link JacksonXmlSupport}
     */
    @Override
    public JacksonXmlSupport configure(@Nonnull(when = NEVER) JacksonSupportConfigFeature... features) {
        checkArgument(features != null, "The Parameter features must not be null.");
        fromArray(features)
                .filter(Objects::nonNull)
                .filter(f -> f != UNWRAP_ROOT_VALUE_ENABLE)
                .doOnComplete(() -> logger.info("##############{} configure all Features.", this.getProviderName()))
                .subscribe(f -> f.configureMapper(this.getDefaultMapper()), e -> e.printStackTrace());
        return this;
    }

    /**
     * @param locale
     * @return {@link JacksonXmlSupport}
     */
    @Override
    public JacksonXmlSupport setLocale(@Nonnull(when = NEVER) Locale locale) {
        checkArgument(locale != null, "The Parameter locale must not be null.");
        this.xmlMapper.setLocale(locale);
        return this;
    }

    /**
     * @param timeZone
     * @return {@link JacksonXmlSupport}
     */
    @Override
    public JacksonXmlSupport setTimeZone(@Nonnull(when = NEVER) TimeZone timeZone) {
        checkArgument(timeZone != null, "The Parameter timeZone must not be null.");
        this.xmlMapper.setTimeZone(timeZone);
        return this;
    }
}