package org.geosdi.geoplatform.xml.wps.v100.context;

import org.geosdi.geoplatform.xml.context.provider.GPContextServiceProvider;

import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WPSContextServiceProviderV100 extends GPContextServiceProvider {

    public static final GPContextServiceProvider WPS_CONTEXT_SERVICE_PROVIDER = new WPSContextServiceProviderV100();

    protected WPSContextServiceProviderV100() {
        super(String.join(":", "org.geosdi.geoplatform.xml.ows.v110",
                "org.geosdi.geoplatform.xml.xlink.v100",
                "org.geosdi.geoplatform.xml.wps.v100"), of(org.geosdi.geoplatform.xml.ows.v110.ObjectFactory.class,
                org.geosdi.geoplatform.xml.xlink.v100.ObjectFactory.class,
                org.geosdi.geoplatform.xml.wps.v100.ObjectFactory.class).toArray(size -> new Class[size]));
    }

    /**
     * @return {@link Type}
     */
    @Override
    public <Type extends GPContextProviderType> Type getType() {
        return (Type) WPSContextServiceProviderTypeV100.WPS_100;
    }
}
