package org.geosdi.geoplatform.stax.reader.factory;

import org.codehaus.stax2.XMLInputFactory2;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPXMLInputFactoryStax2Builder extends GPXMLInputFactoryBuilder {

    /**
     * Method to call to make Reader created conform as closely to XML
     * standard as possible, doing all checks and transformations mandated
     * by the XML specification (linefeed conversions, attr value
     * normalizations).
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
     * In addition, implementations should set implementation-dependant
     * settings appropriately, to be as strict as possible with regards
     * to XML specification mandated checks and transformations.
     */
    <F extends XMLInputFactory2> F configureForXmlConformance();

    /**
     * Method to call to make Reader created be as "convenient" to use
     * as possible; ie try to avoid having to deal with some of things
     * like segmented text chunks. This may incur some slight performance
     * penalties, but should not affect XML conformance.
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
     */
    <F extends XMLInputFactory2> F configureForConvenience();

    /**
     * Method to call to make the Reader created be as fast as possible reading
     * documents, especially for long-running processes where caching is
     * likely to help. This means reducing amount of information collected
     * (ignorable white space in prolog/epilog, accurate Location information
     * for Event API), and possibly even including simplifying handling
     * of XML-specified transformations (skip attribute value and text
     * linefeed normalization).
     * Potential downsides are somewhat increased memory usage
     * (for full-sized input buffers), and reduced XML conformance (will not
     * do some of transformations).
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
     */
    <F extends XMLInputFactory2> F configureForSpeed();

    /**
     * Method to call to minimize the memory usage of the stream/event reader;
     * both regarding Objects created, and the temporary memory usage during
     * parsing.
     * This generally incurs some performance penalties, due to using
     * smaller input buffers.
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
     */
    <F extends XMLInputFactory2> F configureForLowMemUsage();

    /**
     * Method to call to make Reader try to preserve as much of input
     * formatting as possible, so that round-tripping would be as lossless
     * as possible. This means that the matching writer should be able to
     * reproduce output as closely matching input format as possible
     * (most implementations won't be able to provide 100% vis-a-vis;
     * white space between attributes is generally lost, as well as use
     * of character entities).
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
     */
    <F extends XMLInputFactory2> F configureForRoundTripping();
}