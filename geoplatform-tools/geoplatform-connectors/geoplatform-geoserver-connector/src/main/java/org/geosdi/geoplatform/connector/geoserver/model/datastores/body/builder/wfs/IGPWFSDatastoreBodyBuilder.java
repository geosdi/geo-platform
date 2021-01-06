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
package org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.wfs;

import org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey;
import org.geosdi.geoplatform.connector.geoserver.model.connection.key.wfs.WFSAxisOrder;
import org.geosdi.geoplatform.connector.geoserver.model.connection.key.wfs.WFSFilterCompliance;
import org.geosdi.geoplatform.connector.geoserver.model.connection.key.wfs.WFSStrategy;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.GPGeoserverCreateDatastoreBodyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.EntityResolver;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.Flowable.fromArray;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.wfs.GPGeoserverConnectionWFSValues.*;
import static org.geosdi.geoplatform.connector.geoserver.model.datastores.GeoserverDatastoreType.WFS;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPWFSDatastoreBodyBuilder extends GPGeoserverCreateDatastoreBodyBuilder<IGPWFSDatastoreBodyBuilder> {

    /**
     * <p>Sets a preference for the HTTP protocol to use when requesting WFS functionality. Set this value to Boolean.TRUE for POST, Boolean.FALSE for GET or NULL for AUTO.</p>
     *
     * @param theProtocol
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withProtocol(@Nullable Boolean theProtocol);

    /**
     * <p>Represents a URL to the getCapabilities document or a server instance.</p>
     *
     * @param theWFSGetCapabilitiesUrl
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withWFSGetCapabilitiesUrl(@Nonnull(when = NEVER) URL theWFSGetCapabilitiesUrl);

    /**
     * <p>This allows the user to specify a buffer size in features. This param has a default value of 10 features.</p>
     *
     * @param theBufferSize
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withBufferSize(@Nullable Integer theBufferSize);

    /**
     * <p>Level of compliance to WFS specification (0-low,1-medium,2-high).</p>
     *
     * @param theFilterCompliance
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withFilterCompliance(@Nullable WFSFilterCompliance theFilterCompliance);

    /**
     * <p>Sets the entity resolver used to expand XML entities. Default Value is an instance of : org.geotools.xml.PreventLocalEntityResolver</p>
     *
     * @param theEntityResolver
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withEntityResolver(@Nullable EntityResolver theEntityResolver);

    /**
     * <p>This allows the user to specify a timeout in milliseconds. This param has a default value of 3000ms.</p>
     *
     * @param theTimeOut
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withTimeOut(@Nullable Integer theTimeOut);

    /**
     * <p>Optional OGC GML compliance level required.</p>
     *
     * @param theGmlComplianceLevel
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withGmlComplianceLevel(@Nullable Integer theGmlComplianceLevel);

    /**
     * <p>
     * Indicates that datastore should do its best to create features from the provided data even if it does not accurately
     * match the schema. Errors will be logged but the parsing will continue if this is true. Default is false
     * </p>
     *
     * @param theLenient
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withLenient(@Nullable Boolean theLenient);

    /**
     * <p>This allows the user to specify a username. This param should not be used without the USERNAME param.</p>
     *
     * @param thePassword
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withPassword(@Nullable String thePassword);

    /**
     * <p>Use always the declared DefaultSRS for requests and reproject locally if necessary.</p>
     *
     * @param theUseDefaultSRS
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withUseDefaultSRS(@Nullable Boolean theUseDefaultSRS);

    /**
     * <p>Override the original WFS type name namespaces.</p>
     *
     * @param theNamespace
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withNamespace(@Nullable String theNamespace);

    /**
     * <p>This allows the user to specify a username. This param should not be used without the PASSWORD param.</p>
     *
     * @param theUsername
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withUsername(@Nullable String theUsername);

    /**
     * <p>Indicates axis order used by the remote WFS server for filters. It applies only to WFS 1.x.0 servers. Default is the same as AXIS_ORDER.</p>
     *
     * @param theAxisOrderFilter
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withAxisOrderFilter(@Nullable WFSAxisOrder theAxisOrderFilter);

    /**
     * <p>Use Gml Compatible TypeNames (replace : by _).</p>
     *
     * @param theGmlCompatibleTypeNames
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withGmlCompatibleTypeNames(@Nullable Boolean theGmlCompatibleTypeNames);

    /**
     * <p>
     * Positive integer used as a hard limit for the amount of Features to retrieve for each FeatureType.
     * A value of zero or not providing this parameter means no limit.
     * </p>
     *
     * @param theMaximumFeatures
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withMaximumFeatures(@Nullable Integer theMaximumFeatures);

    IGPWFSDatastoreBodyBuilder withAxisOrder(@Nullable WFSAxisOrder theAxisOrder);

    /**
     * @param theWFSStrategy
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withWFSStrategy(@Nullable WFSStrategy theWFSStrategy);

    /**
     * <p>Indicates that datastore should use gzip to transfer data if the server supports it. Default is true</p>
     *
     * @param theTryGZIP
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withTryGZIP(@Nullable Boolean theTryGZIP);

    /**
     * <p>This allows the user to specify the character encoding of the XML-Requests sent to the Server. Defaults to UTF-8.</p>
     *
     * @param theEncoding
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withEncoding(@Nullable String theEncoding);

    /**
     * <p>This allows the user to specify an outputFormat, different from the default one.</p>
     *
     * @param theOutputFormat
     * @return {@link IGPWFSDatastoreBodyBuilder}
     */
    IGPWFSDatastoreBodyBuilder withOutputFormat(@Nullable String theOutputFormat);

    class GPWFSDatastoreBodyBuilder extends GPCreateDatastoreBodyBuilder<IGPWFSDatastoreBodyBuilder> implements IGPWFSDatastoreBodyBuilder {

        private static final Logger logger = LoggerFactory.getLogger(GPWFSDatastoreBodyBuilder.class);

        static {
            requiredValues = requiredValues();
            defaultValues = defaultValues();
        }

        private static final List<IGPGeoserverConnectionKey> requiredValues;
        private static final List<IGPGeoserverConnectionKey> defaultValues;

        GPWFSDatastoreBodyBuilder() {
            super(WFS);
        }

        /**
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        public static IGPWFSDatastoreBodyBuilder wfsDatastoreBodyBuilder() {
            return new GPWFSDatastoreBodyBuilder().injectDefaultValues();
        }

        /**
         * <p>
         * Sets a preference for the HTTP protocol to use when requesting WFS functionality. Set this value to Boolean.TRUE
         * for POST, Boolean.FALSE for GET or NULL for AUTO.
         * </p>
         *
         * @param theProtocol
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withProtocol(@Nullable Boolean theProtocol) {
            this.connectionParameters.compute(PROTOCOL.getConnectionKey(), (k, v) -> (theProtocol != null) ? theProtocol.toString() : v);
            return self();
        }

        /**
         * <p>Represents a URL to the getCapabilities document or a server instance.</p>
         *
         * @param theWFSGetCapabilitiesUrl
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withWFSGetCapabilitiesUrl(@Nonnull(when = NEVER) URL theWFSGetCapabilitiesUrl) {
            this.connectionParameters.compute(WFS_GET_CAPABILITIES_URL.getConnectionKey(), (k, v) -> (theWFSGetCapabilitiesUrl != null) ? theWFSGetCapabilitiesUrl.toString() : v);
            return self();
        }

        /**
         * <p>This allows the user to specify a buffer size in features. This param has a default value of 10 features.</p>
         *
         * @param theBufferSize
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withBufferSize(@Nullable Integer theBufferSize) {
            this.connectionParameters.compute(BUFFER_SIZE.getConnectionKey(), (k, v) -> ((theBufferSize != null) && (theBufferSize > 0)) ? theBufferSize.toString() : v);
            return self();
        }

        /**
         * <p>Level of compliance to WFS specification (0-low,1-medium,2-high).</p>
         *
         * @param theFilterCompliance
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withFilterCompliance(@Nullable WFSFilterCompliance theFilterCompliance) {
            this.connectionParameters.compute(FILTER_COMPLIANCE.getConnectionKey(), (k, v) -> (theFilterCompliance != null) ? theFilterCompliance.toString() : v);
            return self();
        }

        /**
         * <p>Sets the entity resolver used to expand XML entities. Default Value is an instance of : org.geotools.xml.PreventLocalEntityResolver</p>
         *
         * @param theEntityResolver
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withEntityResolver(@Nullable EntityResolver theEntityResolver) {
            this.connectionParameters.compute(ENTITY_RESOLVER.getConnectionKey(), (k, v) -> (theEntityResolver != null) ? theEntityResolver.getClass().getName() : v);
            return self();
        }

        /**
         * <p>This allows the user to specify a timeout in milliseconds. This param has a default value of 3000ms.</p>
         *
         * @param theTimeOut
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withTimeOut(@Nullable Integer theTimeOut) {
            this.connectionParameters.compute(TIME_OUT.getConnectionKey(), (k, v) -> ((theTimeOut != null) && (theTimeOut > 0)) ? theTimeOut.toString() : v);
            return self();
        }

        /**
         * <p>Optional OGC GML compliance level required.</p>
         *
         * @param theGmlComplianceLevel
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withGmlComplianceLevel(@Nullable Integer theGmlComplianceLevel) {
            this.connectionParameters.compute(GML_COMPLIANCE_LEVEL.getConnectionKey(), (k, v) -> ((theGmlComplianceLevel != null) && (theGmlComplianceLevel > 0)) ? theGmlComplianceLevel.toString() : v);
            return self();
        }

        /**
         * <p>
         * Indicates that datastore should do its best to create features from the provided data even if it does not accurately
         * match the schema. Errors will be logged but the parsing will continue if this is true. Default is false
         * </p>
         *
         * @param theLenient
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withLenient(@Nullable Boolean theLenient) {
            this.connectionParameters.compute(LENIENT.getConnectionKey(), (k, v) -> (theLenient != null) ? theLenient.toString() : v);
            return self();
        }

        /**
         * <p>This allows the user to specify a username. This param should not be used without the USERNAME param.</p>
         *
         * @param thePassword
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withPassword(@Nullable String thePassword) {
            this.connectionParameters.compute(PASSWORD.getConnectionKey(), (k, v) -> ((thePassword != null) && !(thePassword.trim().isEmpty())) ? thePassword : v);
            return self();
        }

        /**
         * <p>Use always the declared DefaultSRS for requests and reproject locally if necessary.</p>
         *
         * @param theUseDefaultSRS
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withUseDefaultSRS(@Nullable Boolean theUseDefaultSRS) {
            this.connectionParameters.compute(USE_DEFAULT_SRS.getConnectionKey(), (k, v) -> (theUseDefaultSRS != null) ? theUseDefaultSRS.toString() : v);
            return self();
        }

        /**
         * <p>Override the original WFS type name namespaces.</p>
         *
         * @param theNamespace
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withNamespace(@Nullable String theNamespace) {
            this.connectionParameters.compute(NAMESPACE.getConnectionKey(), (k, v) -> ((theNamespace != null) && !(theNamespace.trim().isEmpty())) ? theNamespace : v);
            return self();
        }

        /**
         * <p>This allows the user to specify a username. This param should not be used without the PASSWORD param.</p>
         *
         * @param theUsername
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withUsername(@Nullable String theUsername) {
            this.connectionParameters.compute(USERNAME.getConnectionKey(), (k, v) -> ((theUsername != null) && !(theUsername.trim().isEmpty())) ? theUsername : v);
            return self();
        }

        /**
         * <p>Indicates axis order used by the remote WFS server for filters. It applies only to WFS 1.x.0 servers. Default is the same as AXIS_ORDER.</p>
         *
         * @param theAxisOrderFilter
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withAxisOrderFilter(@Nullable WFSAxisOrder theAxisOrderFilter) {
            this.connectionParameters.compute(AXIS_ORDER_FILTER.getConnectionKey(), (k, v) -> (theAxisOrderFilter != null) ? theAxisOrderFilter.toString() : v);
            return self();
        }

        /**
         * <p>Use Gml Compatible TypeNames (replace : by _).</p>
         *
         * @param theGmlCompatibleTypeNames
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withGmlCompatibleTypeNames(@Nullable Boolean theGmlCompatibleTypeNames) {
            this.connectionParameters.compute(GML_COMPATIBLE_TYPE_NAMES.getConnectionKey(), (k, v) -> (theGmlCompatibleTypeNames != null) ? theGmlCompatibleTypeNames.toString() : v);
            return self();
        }

        /**
         * <p>
         * Positive integer used as a hard limit for the amount of Features to retrieve for each FeatureType.
         * A value of zero or not providing this parameter means no limit.
         * </p>
         *
         * @param theMaximumFeatures
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withMaximumFeatures(@Nullable Integer theMaximumFeatures) {
            this.connectionParameters.compute(MAXIMUM_FEATURES.getConnectionKey(), (k, v) -> ((theMaximumFeatures != null) && (theMaximumFeatures > -1)) ? theMaximumFeatures.toString() : v);
            return self();
        }

        @Override
        public IGPWFSDatastoreBodyBuilder withAxisOrder(@Nullable WFSAxisOrder theAxisOrder) {
            this.connectionParameters.compute(AXIS_ORDER.getConnectionKey(), (k, v) -> (theAxisOrder != null) ? theAxisOrder.toString() : v);
            return self();
        }

        /**
         * @param theWFSStrategy
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withWFSStrategy(@Nullable WFSStrategy theWFSStrategy) {
            this.connectionParameters.compute(WFS_STRATEGY.getConnectionKey(), (k, v) -> (theWFSStrategy != null) ? theWFSStrategy.toString() : v);
            return self();
        }

        /**
         * <p>Indicates that datastore should use gzip to transfer data if the server supports it. Default is true</p>
         *
         * @param theTryGZIP
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withTryGZIP(@Nullable Boolean theTryGZIP) {
            this.connectionParameters.compute(TRY_GZIP.getConnectionKey(), (k, v) -> (theTryGZIP != null) ? theTryGZIP.toString() : v);
            return self();
        }

        /**
         * <p>This allows the user to specify the character encoding of the XML-Requests sent to the Server. Defaults to UTF-8.</p>
         *
         * @param theEncoding
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withEncoding(@Nullable String theEncoding) {
            this.connectionParameters.compute(ENCODING.getConnectionKey(), (k, v) -> ((theEncoding != null) && !(theEncoding.trim().isEmpty())) ? theEncoding : v);
            return self();
        }

        /**
         * <p>This allows the user to specify an outputFormat, different from the default one.</p>
         *
         * @param theOutputFormat
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        public IGPWFSDatastoreBodyBuilder withOutputFormat(@Nullable String theOutputFormat) {
            this.connectionParameters.compute(OUTPUT_FORMAT.getConnectionKey(), (k, v) -> ((theOutputFormat != null) && !(theOutputFormat.trim().isEmpty())) ? theOutputFormat : v);
            return self();
        }

        /**
         * @throws Exception
         */
        @Override
        protected void checkConnectionParameters() throws Exception {
            for (IGPGeoserverConnectionKey connectionKey : requiredValues) {
                logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@{} tries to check if : {} is present in connectionParameters.", this, connectionKey);
                String value = this.connectionParameters.get(connectionKey.getConnectionKey());
                checkArgument((value != null) && !(value.trim().isEmpty()), "For the Key : " + connectionKey.getConnectionKey()
                        + ", the value must not be null or an empty string.");
            }
        }

        /**
         * @return {@link IGPWFSDatastoreBodyBuilder}
         */
        @Override
        protected IGPWFSDatastoreBodyBuilder injectDefaultValues() {
            fromArray(defaultValues.stream().toArray(IGPGeoserverConnectionKey[]::new))
                    .filter(Objects::nonNull)
                    .blockingSubscribe(v -> this.connectionParameters.put(v.getConnectionKey(), v.getDefaultValue().toString()), Throwable::printStackTrace);
            return self();
        }
    }
}