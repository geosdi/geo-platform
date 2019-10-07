package org.geosdi.geoplatform.stax.reader.builder;

import org.codehaus.stax2.XMLInputFactory2;

import javax.annotation.Nonnull;
import java.util.Map;

import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.stax.reader.factory.XMLInputFactoryStax2Builder.AALTO_BUILDER;
import static org.geosdi.geoplatform.stax.reader.factory.XMLInputFactoryStax2Builder.WOODSTOX_BUILDER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class XmlStreamReaderStax2Builder extends BaseXmlStreamReaderBuilder<XMLInputFactory2> {

    /**
     * @param theFactory
     */
    XmlStreamReaderStax2Builder(@Nonnull(when = NEVER) XMLInputFactory2 theFactory) {
        super(theFactory);
    }

    /**
     * Create {@link XMLInputFactory2} using Woodstox Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     * <li>Enable <code>SUPPORT_DTD</code> property.
     *  </li>
     * <li>Enable <code>IS_NAMESPACE_AWARE</code>
     *  </li>
     * <li>Enable <code>IS_REPLACING_ENTITY_REFERENCES</code>
     *  </li>
     * <li>Enable <code>IS_SUPPORTING_EXTERNAL_ENTITIES</code>
     *  </li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     *
     * @return {@link XmlStreamReaderBuilder}
     */
    public static XmlStreamReaderStax2Builder woodstoxConfigureForXmlConformance() {
        return new XmlStreamReaderStax2Builder(WOODSTOX_BUILDER.configureForXmlConformance());
    }

    /**
     * Create {@link XMLInputFactory2} using Woodstox Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     * <li>Enable <code>IS_COALESCING</code> (text coalescing)
     *  </li>
     * <li>Enable <code>IS_REPLACING_ENTITY_REFERENCES</code>
     *  </li>
     * <li>Disable <code>P_REPORT_PROLOG_WHITESPACE</code> (StAX2); so
     *   that the application need not skip possible <code>SPACE</code>
     *   (and <code>COMMENT</code>, <code>PROCESSING_INSTRUCTION</code>)
     *   events.
     *  </li>
     * <li>Enable <code>P_REPORT_ALL_TEXT_AS_CHARACTERS</code> (StAX2)
     *  </li>
     * <li>Enable <code>P_PRESERVE_LOCATION</code> (StAX2)
     *  </li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     * In addition, implementations should set implementation-dependant
     * settings appropriately as well.
     *
     * @return {@link XmlStreamReaderBuilder}
     */
    public static XmlStreamReaderStax2Builder woodstoxConfigureForConvenience() {
        return new XmlStreamReaderStax2Builder(WOODSTOX_BUILDER.configureForConvenience());
    }

    /**
     * Create {@link XMLInputFactory2} using Woodstox Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     * <li>Disable <code>IS_COALESCING</code> (text coalescing)
     *  </li>
     * <li>Disable <code>P_PRESERVE_LOCATION</code> (StAX2)
     *  </li>
     * <li>Disable <code>P_REPORT_PROLOG_WHITESPACE</code> (StAX2)
     *  </li>
     * <li>Enable <code>P_INTERN_NAMES</code> (StAX2)
     *  </li>
     * <li>Enable <code>P_INTERN_NS_URIS</code> (StAX2)
     *  </li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     * In addition, implementations should set implementation-dependant
     * settings appropriately as well.
     *
     * @return {@link XmlStreamReaderBuilder}
     */
    public static XmlStreamReaderStax2Builder woodstoxConfigureForSpeed() {
        return new XmlStreamReaderStax2Builder(WOODSTOX_BUILDER.configureForSpeed());
    }

    /**
     * Create {@link XMLInputFactory2} using Woodstox Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     * <li>Disable <code>IS_COALECING</code> (text coalescing, can force
     *   longer internal result buffers to be used)
     *  </li>
     * <li>Disable <code>P_PRESERVE_LOCATION</code> (StAX) to reduce
     *   temporary memory usage.
     *  </li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     * In addition, implementations should set implementation-dependant
     * settings appropriately so that the memory usage is minimized.
     *
     * @return {@link XmlStreamReaderBuilder}
     */
    public static XmlStreamReaderStax2Builder woodstoxConfigureForLowMemUsage() {
        return new XmlStreamReaderStax2Builder(WOODSTOX_BUILDER.configureForLowMemUsage());
    }

    /**
     * Create {@link XMLInputFactory2} using Woodstox Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     * <li>Disable <code>IS_COALESCING</code> (to prevent CDATA and Text
     *   segments from getting combined)
     *  <li>
     * <li>Disable <code>IS_REPLACING_ENTITY_REFERENCES</code> to allow for
     *    preserving explicitly declared general entity references (that is,
     *    there is no way to preserve character entities, or pre-defined
     *    entities like 'gt', 'lt', 'amp', 'apos' and 'quot').
     *  <li>
     * <li>Disable <code>P_REPORT_ALL_TEXT_AS_CHARACTERS</code> (StAX2)
     *   (so that CDATA sections are not reported as 'normal' text)
     *  <li>
     * <li>Enable <code>P_REPORT_PROLOG_WHITESPACE</code> (StAX2)
     *  </li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     * In addition, implementations should set implementation-dependant
     * settings appropriately as well.
     *
     * @return {@link XmlStreamReaderBuilder}
     */
    public static XmlStreamReaderStax2Builder woodstoxConfigureForRoundTripping() {
        return new XmlStreamReaderStax2Builder(WOODSTOX_BUILDER.configureForRoundTripping());
    }

    /**
     * @param theProp
     * @return {@link XmlStreamReaderBuilder}
     */
    public static XmlStreamReaderStax2Builder woodstoxWithProp(@Nonnull(when = NEVER) Map<String, Object> theProp) {
        return new XmlStreamReaderStax2Builder(WOODSTOX_BUILDER.withProp(theProp));
    }

    /**
     * Create {@link XMLInputFactory2} using Aalto Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     * <li>Enable <code>SUPPORT_DTD</code> property.
     *  </li>
     * <li>Enable <code>IS_NAMESPACE_AWARE</code>
     *  </li>
     * <li>Enable <code>IS_REPLACING_ENTITY_REFERENCES</code>
     *  </li>
     * <li>Enable <code>IS_SUPPORTING_EXTERNAL_ENTITIES</code>
     *  </li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     *
     * @return {@link XmlStreamReaderBuilder}
     */
    public static XmlStreamReaderStax2Builder aaltoConfigureForXmlConformance() {
        return new XmlStreamReaderStax2Builder(AALTO_BUILDER.configureForXmlConformance());
    }

    /**
     * Create {@link XMLInputFactory2} using Aalto Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     * <li>Enable <code>IS_COALESCING</code> (text coalescing)
     *  </li>
     * <li>Enable <code>IS_REPLACING_ENTITY_REFERENCES</code>
     *  </li>
     * <li>Disable <code>P_REPORT_PROLOG_WHITESPACE</code> (StAX2); so
     *   that the application need not skip possible <code>SPACE</code>
     *   (and <code>COMMENT</code>, <code>PROCESSING_INSTRUCTION</code>)
     *   events.
     *  </li>
     * <li>Enable <code>P_REPORT_ALL_TEXT_AS_CHARACTERS</code> (StAX2)
     *  </li>
     * <li>Enable <code>P_PRESERVE_LOCATION</code> (StAX2)
     *  </li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     * In addition, implementations should set implementation-dependant
     * settings appropriately as well.
     *
     * @return {@link XmlStreamReaderBuilder}
     */
    public static XmlStreamReaderStax2Builder aaltoConfigureForConvenience() {
        return new XmlStreamReaderStax2Builder(AALTO_BUILDER.configureForConvenience());
    }

    /**
     * Create {@link XMLInputFactory2} using Aalto> Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     * <li>Disable <code>IS_COALESCING</code> (text coalescing)
     *  </li>
     * <li>Disable <code>P_PRESERVE_LOCATION</code> (StAX2)
     *  </li>
     * <li>Disable <code>P_REPORT_PROLOG_WHITESPACE</code> (StAX2)
     *  </li>
     * <li>Enable <code>P_INTERN_NAMES</code> (StAX2)
     *  </li>
     * <li>Enable <code>P_INTERN_NS_URIS</code> (StAX2)
     *  </li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     * In addition, implementations should set implementation-dependant
     * settings appropriately as well.
     *
     * @return {@link XmlStreamReaderBuilder}
     */
    public static XmlStreamReaderStax2Builder aaltoConfigureForSpeed() {
        return new XmlStreamReaderStax2Builder(AALTO_BUILDER.configureForSpeed());
    }

    /**
     * Create {@link XMLInputFactory2} using Aalto Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     * <li>Disable <code>IS_COALECING</code> (text coalescing, can force
     *   longer internal result buffers to be used)
     *  </li>
     * <li>Disable <code>P_PRESERVE_LOCATION</code> (StAX) to reduce
     *   temporary memory usage.
     *  </li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     * In addition, implementations should set implementation-dependant
     * settings appropriately so that the memory usage is minimized.
     *
     * @return {@link XmlStreamReaderBuilder}
     */
    public static XmlStreamReaderStax2Builder aaltoConfigureForLowMemUsage() {
        return new XmlStreamReaderStax2Builder(AALTO_BUILDER.configureForLowMemUsage());
    }

    /**
     * Create {@link XMLInputFactory2} using Aalto Implementation with these properties :
     * <p>
     * Regarding the default StAX property settings,
     * implementations are suggested to do following:
     * <ul>
     * <li>Disable <code>IS_COALESCING</code> (to prevent CDATA and Text
     *   segments from getting combined)
     *  <li>
     * <li>Disable <code>IS_REPLACING_ENTITY_REFERENCES</code> to allow for
     *    preserving explicitly declared general entity references (that is,
     *    there is no way to preserve character entities, or pre-defined
     *    entities like 'gt', 'lt', 'amp', 'apos' and 'quot').
     *  <li>
     * <li>Disable <code>P_REPORT_ALL_TEXT_AS_CHARACTERS</code> (StAX2)
     *   (so that CDATA sections are not reported as 'normal' text)
     *  <li>
     * <li>Enable <code>P_REPORT_PROLOG_WHITESPACE</code> (StAX2)
     *  </li>
     * </ul>
     * All the other standard settings should be left as is.
     * <p>
     * In addition, implementations should set implementation-dependant
     * settings appropriately as well.
     *
     * @return {@link XmlStreamReaderBuilder}
     */
    public static XmlStreamReaderStax2Builder aaltoConfigureForRoundTripping() {
        return new XmlStreamReaderStax2Builder(AALTO_BUILDER.configureForRoundTripping());
    }

    /**
     * @param theProp
     * @return {@link XmlStreamReaderStax2Builder}
     */
    public static XmlStreamReaderStax2Builder aaltoWithProp(@Nonnull(when = NEVER) Map<String, Object> theProp) {
        return new XmlStreamReaderStax2Builder(AALTO_BUILDER.withProp(theProp));
    }
}