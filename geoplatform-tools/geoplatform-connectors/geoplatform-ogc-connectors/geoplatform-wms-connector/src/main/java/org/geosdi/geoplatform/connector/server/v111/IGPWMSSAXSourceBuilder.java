package org.geosdi.geoplatform.connector.server.v111;

import org.xml.sax.InputSource;

import javax.annotation.Nonnull;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import java.io.InputStream;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPWMSSAXSourceBuilder {

    /**
     * @param inputStream
     * @return {@link SAXSource}
     * @throws Exception
     */
    SAXSource buildSource(@Nonnull(when = NEVER) InputStream inputStream) throws Exception;

    class GPWMSSAXSourceBuilder implements IGPWMSSAXSourceBuilder {

        private static final String LOAD_EXTERNAL_DTD_FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
        private static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
        private static final SAXParserFactory spf = SAXParserFactory.newInstance();

        static {
            try {
                spf.setFeature(LOAD_EXTERNAL_DTD_FEATURE, FALSE);
                spf.setFeature(VALIDATION_FEATURE, FALSE);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new IllegalStateException(ex);
            }
        }

        GPWMSSAXSourceBuilder() {
        }

        /**
         * @param inputStream
         * @return {@link SAXSource}
         * @throws Exception
         */
        @Override
        public SAXSource buildSource(@Nonnull(when = NEVER) InputStream inputStream) throws Exception {
            checkArgument(inputStream != null, "The Parameter inputSource must not be null.");
            return new SAXSource(spf.newSAXParser().getXMLReader(), new InputSource(inputStream));
        }
    }
}