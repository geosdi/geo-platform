package org.geosdi.geoplatform.wms.v130;

import org.geosdi.geoplatform.xml.context.provider.GPContextServiceProvider;

import static java.lang.String.join;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.wms.v130.VMSContextServiceProviderTypeV130.VMS_130;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMSContextServiceProviderV130 extends GPContextServiceProvider {

    public static final GPContextServiceProvider WMS_CONTEXT_SERVICE_PROVIDER_V130 = new WMSContextServiceProviderV130();

    protected WMSContextServiceProviderV130() {
        super(join(":", "org.geosdi.geoplatform.xml.xlink.v100",
                "org.geosdi.geoplatform.wms.v130"), of(org.geosdi.geoplatform.xml.xlink.v100.ObjectFactory.class,
                ObjectFactory.class).toArray(size -> new Class[size]));
    }

    /**
     * @param <Type>
     * @return {@link Type}
     */
    @Override
    public <Type extends GPContextProviderType> Type getType() {
        return (Type) VMS_130;
    }
}
