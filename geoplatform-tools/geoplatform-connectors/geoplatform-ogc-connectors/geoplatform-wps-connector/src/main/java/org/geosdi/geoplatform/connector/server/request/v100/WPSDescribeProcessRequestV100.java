package org.geosdi.geoplatform.connector.server.request.v100;

import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.WPSBaseDescribeProcessRequest;
import org.geosdi.geoplatform.connector.server.request.v100.function.ProcessIdentifierFunction;
import org.geosdi.geoplatform.xml.ows.v110.CodeType;
import org.geosdi.geoplatform.xml.wps.v100.DescribeProcess;
import org.geosdi.geoplatform.xml.wps.v100.ProcessDescriptions;

import java.util.List;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toList;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WPSDescribeProcessRequestV100 extends WPSBaseDescribeProcessRequest<ProcessDescriptions, DescribeProcess> {

    private static Function<String, CodeType> PROCESS_IDENTIFIER = new ProcessIdentifierFunction();

    /**
     * @param server
     */
    public WPSDescribeProcessRequestV100(GPServerConnector server) {
        super(server);
    }

    /**
     * @return {@link DescribeProcess}
     * @throws Exception
     */
    @Override
    protected DescribeProcess createRequest() throws Exception {
        checkArgument(this.isSetProcessIdentifiers(),
                "The Parameter ProcessIdentifiers must not be null or Empty.");
        List<CodeType> processCodeTypes = this.processIdentifiers.stream()
                .filter(value -> ((value != null) && !(value.trim().isEmpty())))
                .map(PROCESS_IDENTIFIER)
                .collect(toList());
        checkArgument((processCodeTypes != null) && !(processCodeTypes.isEmpty()),
                "The Parameter ProcessCodeTypes must not be null or an Empty List.");
        DescribeProcess describeProcess = new DescribeProcess();
        describeProcess.setIdentifier(processCodeTypes);
        if (this.isLanguageSet())
            describeProcess.setLanguage(this.language);
        return describeProcess;
    }
}
