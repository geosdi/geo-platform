package org.geosdi.geoplatform.wmc.support.v110.jaxb.repository;

import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;
import org.geosdi.geoplatform.jaxb.provider.GeoPlatformJAXBContextProvider;
import org.geosdi.geoplatform.jaxb.repository.GeoPlatformJAXBContextRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class WMCJAXBContextRepository extends GeoPlatformJAXBContextRepository {

    private static final Logger logger = LoggerFactory.getLogger(WMCJAXBContextRepository.class);

    @Override
    public <P extends GPBaseJAXBContext> P lookUpJAXBContext(GeoPlatformJAXBContextKey key) {
        Object jaxbContext = null;

        try {
            Class<?> classe = key.getJAXBContextClass();

            jaxbContext = classe.newInstance();

            if (!(jaxbContext instanceof GeoPlatformJAXBContextProvider)) {
                throw new IllegalArgumentException(
                        "The class " + jaxbContext.getClass().getName()
                                + " is not an instance of GeoPlatformJAXBContextProvider");
            }

            registerProvider(key, ((GeoPlatformJAXBContextProvider) jaxbContext).getJAXBProvider());
        } catch (InstantiationException ex) {
            logger.error("Failed to Initialize JAXBContext for Class " + getRepositoryName()
                    + ": @@@@@@@@@@@@@@@@@ " + ex);
        } catch (IllegalAccessException ex) {
            logger.error("Failed to Initialize JAXBContext for Class " + getRepositoryName()
                    + ": @@@@@@@@@@@@@@@@@ " + ex);
        }

        return (P) (((GeoPlatformJAXBContextProvider) jaxbContext).getJAXBProvider());
    }

    @Override
    public String getRepositoryName() {
        return getClass().getName();
    }

    @Override
    public String toString() {
        return getRepositoryName();
    }
}
