package org.geosdi.geoplatform.support.wfs.feature.reader;

import org.geosdi.geoplatform.gml.api.parser.base.geometry.sextante.GMLBaseSextanteParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.stax.reader.GeoPlatformStaxReader;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWFSGetFeatureStaxReader extends GeoPlatformStaxReader {

    GPJAXBContextBuilder jaxbContextBuilder = GPJAXBContextBuilder.newInstance();
    GMLBaseSextanteParser sextanteParser = GMLBaseParametersRepo.getDefaultSextanteParser();
}
