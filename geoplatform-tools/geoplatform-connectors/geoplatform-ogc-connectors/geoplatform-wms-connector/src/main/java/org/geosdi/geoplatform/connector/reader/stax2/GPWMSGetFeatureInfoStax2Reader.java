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
package org.geosdi.geoplatform.connector.reader.stax2;

import net.jcip.annotations.ThreadSafe;
import org.codehaus.stax2.XMLInputFactory2;
import org.geosdi.geoplatform.connector.reader.stax.WMSBaseGetFeatureInfoStaxReader;
import org.geosdi.geoplatform.stax.reader.builder.GPXmlStreamReaderBuilder;
import org.geosdi.geoplatform.stax.reader.builder.XmlStreamReaderStax2Builder;

import javax.annotation.Nonnull;
import java.util.Map;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPWMSGetFeatureInfoStax2Reader extends WMSBaseGetFeatureInfoStaxReader {

    /**
     * @param theXmlStreamBuilder
     */
    GPWMSGetFeatureInfoStax2Reader(@Nonnull(when = NEVER) GPXmlStreamReaderBuilder theXmlStreamBuilder) {
        super(theXmlStreamBuilder);
    }

    /**
     * Create {@link GPWMSGetFeatureInfoStax2Reader} with {@link XMLInputFactory2} using Woodstox Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     *      <li>Enable <code>SUPPORT_DTD</code> property.</li>
     *      <li>Enable <code>IS_NAMESPACE_AWARE</code></li>
     *      <li>Enable <code>IS_REPLACING_ENTITY_REFERENCES</code></li>
     *      <li>Enable <code>IS_SUPPORTING_EXTERNAL_ENTITIES</code></li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     *
     * @return {@link GPWMSGetFeatureInfoStax2Reader}
     */
    public static GPWMSGetFeatureInfoStax2Reader woodstoxConfigureForXmlConformance() {
        return new GPWMSGetFeatureInfoStax2Reader(XmlStreamReaderStax2Builder.woodstoxConfigureForXmlConformance());
    }

    /**
     * Create {@link GPWMSGetFeatureInfoStax2Reader} with {@link XMLInputFactory2} using Woodstox Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     *      <li>Enable <code>IS_COALESCING</code> (text coalescing)</li>
     *      <li>Enable <code>IS_REPLACING_ENTITY_REFERENCES</code></li>
     *      <li>Disable <code>P_REPORT_PROLOG_WHITESPACE</code> (StAX2); so
     *          that the application need not skip possible <code>SPACE</code>
     *          (and <code>COMMENT</code>, <code>PROCESSING_INSTRUCTION</code>) events.
     *      </li>
     *      <li>Enable <code>P_REPORT_ALL_TEXT_AS_CHARACTERS</code> (StAX2)</li>
     *      <li>Enable <code>P_PRESERVE_LOCATION</code> (StAX2)</li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     * In addition, implementations should set implementation-dependant
     * settings appropriately as well.
     *
     * @return {@link GPWMSGetFeatureInfoStax2Reader}
     */
    public static GPWMSGetFeatureInfoStax2Reader woodstoxConfigureForConvenience() {
        return new GPWMSGetFeatureInfoStax2Reader(XmlStreamReaderStax2Builder.woodstoxConfigureForConvenience());
    }

    /**
     * Create {@link GPWMSGetFeatureInfoStax2Reader} with {@link XMLInputFactory2} using Woodstox Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     *      <li>Disable <code>IS_COALESCING</code> (text coalescing)</li>
     *      <li>Disable <code>P_PRESERVE_LOCATION</code> (StAX2)</li>
     *      <li>Disable <code>P_REPORT_PROLOG_WHITESPACE</code> (StAX2)</li>
     *      <li>Enable <code>P_INTERN_NAMES</code> (StAX2)</li>
     *      <li>Enable <code>P_INTERN_NS_URIS</code> (StAX2)</li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     * In addition, implementations should set implementation-dependant
     * settings appropriately as well.
     *
     * @return {@link GPWMSGetFeatureInfoStax2Reader}
     */
    public static GPWMSGetFeatureInfoStax2Reader woodstoxConfigureForSpeed() {
        return new GPWMSGetFeatureInfoStax2Reader(XmlStreamReaderStax2Builder.woodstoxConfigureForSpeed());
    }

    /**
     * Create {@link GPWMSGetFeatureInfoStax2Reader} using Woodstox Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     *      <li>Disable <code>IS_COALECING</code> (text coalescing, can force
     *          longer internal result buffers to be used)
     *      </li>
     *      <li>Disable <code>P_PRESERVE_LOCATION</code> (StAX) to reduce
     *          temporary memory usage.
     *      </li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     * In addition, implementations should set implementation-dependant
     * settings appropriately so that the memory usage is minimized.
     *
     * @return {@link GPWMSGetFeatureInfoStax2Reader}
     */
    public static GPWMSGetFeatureInfoStax2Reader woodstoxConfigureForLowMemUsage() {
        return new GPWMSGetFeatureInfoStax2Reader(XmlStreamReaderStax2Builder.woodstoxConfigureForLowMemUsage());
    }

    /**
     * Create {@link GPWMSGetFeatureInfoStax2Reader} with {@link XMLInputFactory2} using Woodstox Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     *      <li>Disable <code>IS_COALESCING</code> (to prevent CDATA and Text
     *          segments from getting combined)
     *      <li>
     *      <li>Disable <code>IS_REPLACING_ENTITY_REFERENCES</code> to allow for
     *          preserving explicitly declared general entity references (that is,
     *          there is no way to preserve character entities, or pre-defined
     *          entities like 'gt', 'lt', 'amp', 'apos' and 'quot').
     *      <li>
     *      <li>Disable <code>P_REPORT_ALL_TEXT_AS_CHARACTERS</code> (StAX2)
     *          (so that CDATA sections are not reported as 'normal' text)
     *      <li>
     *      <li>Enable <code>P_REPORT_PROLOG_WHITESPACE</code> (StAX2)</li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     * In addition, implementations should set implementation-dependant
     * settings appropriately as well.
     *
     * @return {@link GPWMSGetFeatureInfoStax2Reader}
     */
    public static GPWMSGetFeatureInfoStax2Reader woodstoxConfigureForRoundTripping() {
        return new GPWMSGetFeatureInfoStax2Reader(XmlStreamReaderStax2Builder.woodstoxConfigureForRoundTripping());
    }

    /**
     * @param theProp
     * @return {@link GPWMSGetFeatureInfoStax2Reader}
     */
    public static GPWMSGetFeatureInfoStax2Reader woodstoxWithProp(@Nonnull(when = NEVER) Map<String, Object> theProp) {
        return new GPWMSGetFeatureInfoStax2Reader(XmlStreamReaderStax2Builder.woodstoxWithProp(theProp));
    }

    /**
     * Create {@link GPWMSGetFeatureInfoStax2Reader} with {@link XMLInputFactory2} using Aalto Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     *      <li>Enable <code>SUPPORT_DTD</code> property.</li>
     *      <li>Enable <code>IS_NAMESPACE_AWARE</code></li>
     *      <li>Enable <code>IS_REPLACING_ENTITY_REFERENCES</code></li>
     *      <li>Enable <code>IS_SUPPORTING_EXTERNAL_ENTITIES</code></li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     *
     * @return {@link GPWMSGetFeatureInfoStax2Reader}
     */
    public static GPWMSGetFeatureInfoStax2Reader aaltoConfigureForXmlConformance() {
        return new GPWMSGetFeatureInfoStax2Reader(XmlStreamReaderStax2Builder.aaltoConfigureForXmlConformance());
    }

    /**
     * Create {@link GPWMSGetFeatureInfoStax2Reader} with {@link XMLInputFactory2} using Aalto Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     *      <li>Enable <code>IS_COALESCING</code> (text coalescing)</li>
     *      <li>Enable <code>IS_REPLACING_ENTITY_REFERENCES</code></li>
     *      <li>Disable <code>P_REPORT_PROLOG_WHITESPACE</code> (StAX2); so
     *          that the application need not skip possible <code>SPACE</code>
     *          (and <code>COMMENT</code>, <code>PROCESSING_INSTRUCTION</code>) events.
     *      </li>
     *      <li>Enable <code>P_REPORT_ALL_TEXT_AS_CHARACTERS</code> (StAX2)</li>
     *      <li>Enable <code>P_PRESERVE_LOCATION</code> (StAX2)</li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     * In addition, implementations should set implementation-dependant
     * settings appropriately as well.
     *
     * @return {@link GPWMSGetFeatureInfoStax2Reader}
     */
    public static GPWMSGetFeatureInfoStax2Reader aaltoConfigureForConvenience() {
        return new GPWMSGetFeatureInfoStax2Reader(XmlStreamReaderStax2Builder.aaltoConfigureForConvenience());
    }

    /**
     * Create {@link GPWMSGetFeatureInfoStax2Reader} with {@link XMLInputFactory2} using Aalto Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     *      <li>Disable <code>IS_COALESCING</code> (text coalescing)</li>
     *      <li>Disable <code>P_PRESERVE_LOCATION</code> (StAX2)</li>
     *      <li>Disable <code>P_REPORT_PROLOG_WHITESPACE</code> (StAX2)</li>
     *      <li>Enable <code>P_INTERN_NAMES</code> (StAX2)</li>
     *      <li>Enable <code>P_INTERN_NS_URIS</code> (StAX2)</li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     * In addition, implementations should set implementation-dependant
     * settings appropriately as well.
     *
     * @return {@link GPWMSGetFeatureInfoStax2Reader}
     */
    public static GPWMSGetFeatureInfoStax2Reader aaltoConfigureForSpeed() {
        return new GPWMSGetFeatureInfoStax2Reader(XmlStreamReaderStax2Builder.aaltoConfigureForSpeed());
    }

    /**
     * Create {@link GPWMSGetFeatureInfoStax2Reader} with {@link XMLInputFactory2} using Aalto Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     *      <li>Disable <code>IS_COALECING</code> (text coalescing, can force
     *          longer internal result buffers to be used)
     *      </li>
     *      <li>Disable <code>P_PRESERVE_LOCATION</code> (StAX) to reduce
     *          temporary memory usage.
     *      </li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     * In addition, implementations should set implementation-dependant
     * settings appropriately so that the memory usage is minimized.
     *
     * @return {@link GPWMSGetFeatureInfoStax2Reader}
     */
    public static GPWMSGetFeatureInfoStax2Reader aaltoConfigureForLowMemUsage() {
        return new GPWMSGetFeatureInfoStax2Reader(XmlStreamReaderStax2Builder.aaltoConfigureForLowMemUsage());
    }

    /**
     * Create {@link GPWMSGetFeatureInfoStax2Reader} with {@link XMLInputFactory2} using Aalto Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     *      <li>Disable <code>IS_COALESCING</code> (to prevent CDATA and Text
     *          segments from getting combined)
     *      <li>
     *      <li>Disable <code>IS_REPLACING_ENTITY_REFERENCES</code> to allow for
     *          preserving explicitly declared general entity references (that is,
     *          there is no way to preserve character entities, or pre-defined
     *          entities like 'gt', 'lt', 'amp', 'apos' and 'quot').
     *      <li>
     *      <li>Disable <code>P_REPORT_ALL_TEXT_AS_CHARACTERS</code> (StAX2)
     *          (so that CDATA sections are not reported as 'normal' text)
     *      <li>
     *      <li>Enable <code>P_REPORT_PROLOG_WHITESPACE</code> (StAX2)</li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     * In addition, implementations should set implementation-dependant
     * settings appropriately as well.
     *
     * @return {@link GPWMSGetFeatureInfoStax2Reader}
     */
    public static GPWMSGetFeatureInfoStax2Reader aaltoConfigureForRoundTripping() {
        return new GPWMSGetFeatureInfoStax2Reader(XmlStreamReaderStax2Builder.aaltoConfigureForRoundTripping());
    }

    /**
     * @param theProp
     * @return {@link GPWMSGetFeatureInfoStax2Reader}
     */
    public static GPWMSGetFeatureInfoStax2Reader aaltoWithProp(@Nonnull(when = NEVER) Map<String, Object> theProp) {
        return new GPWMSGetFeatureInfoStax2Reader(XmlStreamReaderStax2Builder.aaltoWithProp(theProp));
    }
}