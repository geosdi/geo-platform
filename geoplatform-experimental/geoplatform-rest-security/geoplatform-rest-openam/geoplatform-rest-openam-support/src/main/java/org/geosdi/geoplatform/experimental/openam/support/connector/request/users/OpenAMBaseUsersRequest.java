package org.geosdi.geoplatform.experimental.openam.support.connector.request.users;

import com.google.common.base.Preconditions;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public abstract class OpenAMBaseUsersRequest<Request extends OpenAMBaseUsersRequest> implements BaseOpenAMRequest<Request> {

    private static final long serialVersionUID = 2686234365791867017L;
    //
    @Value("openAMConnectorConfigurator{openam.users.request.path:@null}")
    protected String path;

    /**
     * @return {@link String}
     */
    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " path = '" + path + '\'' +
                ", requestType = '" + getRequestType() + '\'' +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkArgument(((this.path != null)
                && !(this.path.isEmpty())), "The Path Parameter must not be "
                + "null or an empty value.");
    }
}
