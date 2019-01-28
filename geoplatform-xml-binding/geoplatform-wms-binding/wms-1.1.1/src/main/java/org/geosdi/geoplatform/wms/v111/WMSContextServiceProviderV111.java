package org.geosdi.geoplatform.wms.v111;

import org.geosdi.geoplatform.xml.context.provider.GPContextServiceProvider;

import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.wms.v111.WMSContextServiceProviderTypeV111.WMS_111;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMSContextServiceProviderV111 extends GPContextServiceProvider {

    public static final GPContextServiceProvider WMS_CONTEXT_SERVICE_PROVIDER_V110 = new WMSContextServiceProviderV111();

    protected WMSContextServiceProviderV111() {
        super("org.geosdi.geoplatform.wms.v111", of(ObjectFactory.class).toArray(size -> new Class[size]));
    }

    /**
     * @param <Type>
     * @return {@link Type}
     */
    @Override
    public <Type extends GPContextProviderType> Type getType() {
        return (Type) WMS_111;
    }
}