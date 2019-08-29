package org.geosdi.geoplatform.connector.geoserver.request.settings;

import com.google.common.io.CharStreams;
import net.jcip.annotations.ThreadSafe;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.model.settings.GPGeoserverGlobalSettings;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPutConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.BufferedReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverUpdateGlobalSettingsRequest extends GPJsonPutConnectorRequest<Boolean> implements GeoserverUpdateGlobalSettingsRequest {

    private final ThreadLocal<GPGeoserverGlobalSettings> settingsBody;

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    public GPGeoserverUpdateGlobalSettingsRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.settingsBody = withInitial(() -> null);
    }

    /**
     * @param theSettingsBody
     * @return {@link GeoserverUpdateGlobalSettingsRequest}
     */
    @Override
    public <SettingsBody extends GPGeoserverGlobalSettings> GeoserverUpdateGlobalSettingsRequest withSettingsBody(@Nonnull(when = NEVER) SettingsBody theSettingsBody) {
        this.settingsBody.set(theSettingsBody);
        return this;
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        GPGeoserverGlobalSettings settingsBody = this.settingsBody.get();
        checkArgument(settingsBody != null, "The Parameter settingsBody must not be null.");
        String settingsBodyAsString = this.jacksonSupport.getDefaultMapper().writeValueAsString(settingsBody);
        logger.debug("#############################SETTINGS_BODY : \n{}\n", settingsBodyAsString);
        return new StringEntity(settingsBodyAsString, APPLICATION_JSON);
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("settings.json") : baseURI.concat("/settings.json")));
    }

    /**
     * @param reader
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    protected Boolean readInternal(BufferedReader reader) throws Exception {
        String value = CharStreams.toString(reader);
        return ((value != null) && (value.trim().isEmpty()) ? TRUE : FALSE);
    }

    /**
     * @return {@link Class<Boolean>}
     */
    @Override
    protected Class<Boolean> forClass() {
        return Boolean.class;
    }
}