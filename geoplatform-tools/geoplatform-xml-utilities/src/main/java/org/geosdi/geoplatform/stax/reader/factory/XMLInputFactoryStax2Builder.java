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
package org.geosdi.geoplatform.stax.reader.factory;

import com.ctc.wstx.stax.WstxInputFactory;
import com.fasterxml.aalto.stax.InputFactoryImpl;
import org.codehaus.stax2.XMLInputFactory2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLInputFactory;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromIterable;
import static java.util.stream.Collectors.toMap;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum XMLInputFactoryStax2Builder implements GPXMLInputFactoryStax2Builder {

    WOODSTOX_BUILDER {
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
        @Override
        public <F extends XMLInputFactory2> F configureForXmlConformance() {
            System.setProperty("javax.xml.stream.XMLInputFactory", "com.ctc.wstx.stax.WstxInputFactory");
            XMLInputFactory2 factory = (XMLInputFactory2) WstxInputFactory.newInstance();
            factory.configureForXmlConformance();
            logger.debug("###########################{}#Creates : {}\n", this.getClass().getSimpleName(), factory);
            return (F) factory;
        }

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
        @Override
        public <F extends XMLInputFactory2> F configureForConvenience() {
            System.setProperty("javax.xml.stream.XMLInputFactory", "com.ctc.wstx.stax.WstxInputFactory");
            XMLInputFactory2 factory = (XMLInputFactory2) WstxInputFactory.newInstance();
            factory.configureForConvenience();
            logger.debug("###########################{}#Creates : {}\n", this.getClass().getSimpleName(), factory);
            return (F) factory;
        }

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
        @Override
        public <F extends XMLInputFactory2> F configureForSpeed() {
            System.setProperty("javax.xml.stream.XMLInputFactory", "com.ctc.wstx.stax.WstxInputFactory");
            XMLInputFactory2 factory = (XMLInputFactory2) WstxInputFactory.newInstance();
            factory.configureForSpeed();
            logger.debug("###########################{}#Creates : {}\n", this.getClass().getSimpleName(), factory);
            return (F) factory;
        }

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
        @Override
        public <F extends XMLInputFactory2> F configureForLowMemUsage() {
            System.setProperty("javax.xml.stream.XMLInputFactory", "com.ctc.wstx.stax.WstxInputFactory");
            XMLInputFactory2 factory = (XMLInputFactory2) WstxInputFactory.newInstance();
            factory.configureForLowMemUsage();
            logger.debug("###########################{}#Creates : {}\n", this.getClass().getSimpleName(), factory);
            return (F) factory;
        }

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
        @Override
        public <F extends XMLInputFactory2> F configureForRoundTripping() {
            System.setProperty("javax.xml.stream.XMLInputFactory", "com.ctc.wstx.stax.WstxInputFactory");
            XMLInputFactory2 factory = (XMLInputFactory2) WstxInputFactory.newInstance();
            factory.configureForRoundTripping();
            logger.debug("###########################{}#Creates : {}\n", this.getClass().getSimpleName(), factory);
            return (F) factory;
        }

        /**
         * @param theProp
         * @return {@link F}
         */
        @Override
        public <F extends XMLInputFactory> F withProp(@Nonnull(when = NEVER) Map<String, Object> theProp) {
            checkArgument((theProp != null) && !(theProp.isEmpty()), "The Parameter prop must not be null or an empty Map.");
            System.setProperty("javax.xml.stream.XMLInputFactory", "com.ctc.wstx.stax.WstxInputFactory");
            XMLInputFactory2 factory = (XMLInputFactory2) WstxInputFactory.newInstance();
            Map<String, Object> properties = theProp.entrySet().stream()
                    .filter(Objects::nonNull)
                    .filter(entry -> (entry.getKey() != null) && !(entry.getKey().trim().isEmpty()))
                    .filter(entry -> entry.getValue() != null)
                    .collect(toMap(entry -> entry.getKey(), entry -> entry.getValue()));
            checkArgument((properties != null) && !(properties.isEmpty()), "The Parameter prop must not contains null keys or null values.");
            properties.entrySet().forEach(entry -> factory.setProperty(entry.getKey(), entry.getValue()));
            logger.debug("###########################{}#Creates : {}, with Prop : \n{}\n", this.getClass().getSimpleName(), factory, theProp);
            return (F) factory;
        }
    },
    AALTO_BUILDER {
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
        @Override
        public <F extends XMLInputFactory2> F configureForXmlConformance() {
            System.setProperty("javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
            InputFactoryImpl factory = new InputFactoryImpl();
            factory.configureForXmlConformance();
            logger.debug("###########################{}#Creates : {}\n", this.getClass().getSimpleName(), factory);
            return (F) factory;
        }

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
        @Override
        public <F extends XMLInputFactory2> F configureForConvenience() {
            System.setProperty("javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
            InputFactoryImpl factory = new InputFactoryImpl();
            factory.configureForConvenience();
            logger.debug("###########################{}#Creates : {}\n", this.getClass().getSimpleName(), factory);
            return (F) factory;
        }

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
        @Override
        public <F extends XMLInputFactory2> F configureForSpeed() {
            System.setProperty("javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
            InputFactoryImpl factory = new InputFactoryImpl();
            factory.configureForSpeed();
            logger.debug("###########################{}#Creates : {}\n", this.getClass().getSimpleName(), factory);
            return (F) factory;
        }

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
        @Override
        public <F extends XMLInputFactory2> F configureForLowMemUsage() {
            System.setProperty("javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
            InputFactoryImpl factory = new InputFactoryImpl();
            factory.configureForLowMemUsage();
            logger.debug("###########################{}#Creates : {}\n", this.getClass().getSimpleName(), factory);
            return (F) factory;
        }

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
        @Override
        public <F extends XMLInputFactory2> F configureForRoundTripping() {
            System.setProperty("javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
            InputFactoryImpl factory = new InputFactoryImpl();
            factory.configureForRoundTripping();
            logger.debug("###########################{}#Creates : {}\n", this.getClass().getSimpleName(), factory);
            return (F) factory;
        }

        /**
         * @param theProp
         * @return {@link F}
         */
        @Override
        public <F extends XMLInputFactory> F withProp(@Nonnull(when = NEVER) Map<String, Object> theProp) {
            checkArgument((theProp != null) && !(theProp.isEmpty()), "The Parameter prop must not be null or an empty Map.");
            System.setProperty("javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
            Map<String, Object> properties = theProp.entrySet().stream()
                    .filter(Objects::nonNull)
                    .filter(entry -> (entry.getKey() != null) && !(entry.getKey().trim().isEmpty()))
                    .filter(entry -> entry.getValue() != null)
                    .collect(toMap(entry -> entry.getKey(), entry -> entry.getValue()));
            checkArgument((properties != null) && !(properties.isEmpty()), "The Parameter prop must not contains null keys or null values.");
            InputFactoryImpl factory = new InputFactoryImpl();
            fromIterable(properties.entrySet())
                    .doOnComplete(() -> logger.debug("###########RX terminated its work"))
                    .subscribe(e -> factory.setProperty(e.getKey(), e.getValue()), e -> e.printStackTrace());
            logger.debug("###########################{}#Creates : {}, with Prop : \n{}\n", this.getClass().getSimpleName(), factory, theProp);
            return (F) factory;
        }
    };

    private static final Logger logger = LoggerFactory.getLogger(XMLInputFactoryStax2Builder.class);
}
