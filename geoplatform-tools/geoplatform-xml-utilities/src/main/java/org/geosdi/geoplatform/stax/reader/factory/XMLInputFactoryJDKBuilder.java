package org.geosdi.geoplatform.stax.reader.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLInputFactory;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static java.util.stream.Collectors.toMap;
import static javax.annotation.meta.When.NEVER;
import static javax.xml.stream.XMLInputFactory.IS_COALESCING;
import static javax.xml.stream.XMLInputFactory.IS_NAMESPACE_AWARE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum XMLInputFactoryJDKBuilder implements GPXMLInputFactoryJDKBuilder {

    JDK_BUILDER {
        /**
         * @param theProp
         * @return {@link F}
         */
        @Override
        public <F extends XMLInputFactory> F withProp(@Nonnull(when = NEVER) Map<String, Object> theProp) {
            checkArgument((theProp != null) && !(theProp.isEmpty()), "The Parameter prop must not be null or an empty Map.");
            XMLInputFactory factory = XMLInputFactory.newDefaultFactory();
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

        /**
         * @return {@link F}
         */
        @Override
        public <F extends XMLInputFactory> F defaultFactory() {
            XMLInputFactory factory = XMLInputFactory.newDefaultFactory();
            factory.setProperty(IS_COALESCING, TRUE);
            factory.setProperty(IS_NAMESPACE_AWARE, TRUE);
            factory.setProperty("http://java.sun.com/xml/stream/properties/report-cdata-event", TRUE);
            logger.debug("###########################{}#Creates : {}\n", this.getClass().getSimpleName(), factory);
            return (F) factory;
        }
    };

    private static final Logger logger = LoggerFactory.getLogger(XMLInputFactoryJDKBuilder.class);
}