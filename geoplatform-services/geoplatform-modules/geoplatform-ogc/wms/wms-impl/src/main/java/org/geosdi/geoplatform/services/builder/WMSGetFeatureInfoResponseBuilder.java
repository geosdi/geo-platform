package org.geosdi.geoplatform.services.builder;

import org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.request.WMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.v111.GPWMSGetFeatureInfoV111Request;
import org.geosdi.geoplatform.connector.server.v111.IGPWMSConnectorStoreV111;
import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoElement;
import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoReponseErrorStrategy;
import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoRequest;
import org.geosdi.geoplatform.services.request.WMSGetFeatureInfoResponseFormat;
import org.geosdi.geoplatform.services.response.WMSGetFeatureInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.pool.builder.v111.GPWMSConnectorBuilderPoolV111.wmsConnectorBuilderPoolV111;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat.GML;
import static org.geosdi.geoplatform.services.request.WMSGetFeatureInfoResponseFormat.GEOJSON;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMSGetFeatureInfoResponseBuilder implements GPWMSGetFeatureInfoResponseBuilder {

    private static final Logger logger = LoggerFactory.getLogger(WMSGetFeatureInfoResponseBuilder.class);
    //
    private GPWMSGetFeatureInfoRequest request;

    private WMSGetFeatureInfoResponseBuilder() {
    }

    /**
     * @return {@link GPWMSGetFeatureInfoResponseBuilder}
     */
    public static GPWMSGetFeatureInfoResponseBuilder wmsGetFeatureInfoResponseBuilder() {
        return new WMSGetFeatureInfoResponseBuilder();
    }

    /**
     * @param theRequest
     * @return {@link GPWMSGetFeatureInfoResponseBuilder}
     */
    @Override
    public GPWMSGetFeatureInfoResponseBuilder withRequest(@Nonnull(when = NEVER) GPWMSGetFeatureInfoRequest theRequest) {
        this.request = theRequest;
        return self();
    }

    /**
     * @return {@link WMSGetFeatureInfoResponse}
     * @throws Exception
     */
    @Override
    public WMSGetFeatureInfoResponse build() throws Exception {
        checkArgument(this.request != null, "The Parameter request must not be null.");
        WMSGetFeatureInfoResponse wmsGetFeatureInfoResponse = new WMSGetFeatureInfoResponse();
        WMSGetFeatureInfoResponseFormat format = request.getFormat();
        GPWMSGetFeatureInfoReponseErrorStrategy wmsErrorStrategy = ((format != null)
                ? format.toWMSGetFeatureInfoResponseErrorStrategy() : GEOJSON.toWMSGetFeatureInfoResponseErrorStrategy());
        for (GPWMSGetFeatureInfoElement wmsGetFeatureInfoElement : request.getWmsFeatureInfoElements()) {
            try {
                IGPWMSConnectorStoreV111 wmsServerConnector = wmsConnectorBuilderPoolV111()
                        .withServerUrl(new URL(wmsGetFeatureInfoElement.getWmsServerURL()))
                        .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                                .withMaxTotalConnections(60)
                                .withDefaultMaxPerRoute(30)
                                .withMaxRedirect(15)
                                .build()).build();
                GPWMSGetFeatureInfoV111Request<Object> wmsGetFeatureInfoV111Request = wmsServerConnector.createGetFeatureInfoRequest();
                GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(request.getBoundingBox().toWMSBoundingBox(),
                        wmsGetFeatureInfoElement.getLayers(), request.getCrs(), request.getWidth(), request.getHeight());
                Object response = wmsGetFeatureInfoV111Request
                        .withWMSGetMapRequest(wmsGetMapBaseRequest)
                        .withFeatureCount(20)
                        .withQueryLayers(wmsGetFeatureInfoElement.toLayers())
                        .withX(request.getPoint().getX())
                        .withY(request.getPoint().getY())
                        .withInfoFormat((format != null) ? format.toWMSFeatureInfoFormat() : GML)
                        .getResponse();
                logger.trace("########################FOUND : {}\n", response);
                wmsGetFeatureInfoResponse.addFeature(response);
            } catch (Exception ex) {
                wmsErrorStrategy.buildError(wmsGetFeatureInfoElement, ex);
                ex.printStackTrace();
            }
        }
        return wmsErrorStrategy.addError(wmsGetFeatureInfoResponse);
    }

    /**
     * @return {@link GPWMSGetFeatureInfoResponseBuilder}
     */
    protected GPWMSGetFeatureInfoResponseBuilder self() {
        return this;
    }
}