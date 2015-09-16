package org.geosdi.geoplatform.connector.server.request.v110.query.responsibility;

import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.gui.shared.wfs.LogicOperatorType;
import org.geosdi.geoplatform.xml.filter.v110.BinaryLogicOpType;
import org.geosdi.geoplatform.xml.filter.v110.ComparisonOpsType;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;

import javax.xml.bind.JAXBElement;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class OrOperatorHandler extends LogicOperatorHandler {

    public OrOperatorHandler() {
        super.setSuccessor(new AndOperatorHandler());
    }

    /**
     * @param queryDTO
     * @param filter
     * @throws IllegalStateException
     */
    @Override
    public void buildLogicFilterOperator(QueryDTO queryDTO, FilterType filter) throws IllegalStateException {
        if (super.canBuildLogicOperator(queryDTO.getMatchOperator())) {
            this.processQueryRestrictions(filter, queryDTO.getQueryRestrictionList());
        } else {
            super.forwardBuildLogicFilterOperator(queryDTO, filter);
        }
    }

    /**
     * @param filter
     * @param queryRestrictionDTOs
     */
    @Override
    protected void processQueryRestrictions(FilterType filter, List<QueryRestrictionDTO> queryRestrictionDTOs) {
        logger.debug("################### {} Processing............\n", getFilterName());
        List<JAXBElement<?>> elements = super.buildJAXBElementList(queryRestrictionDTOs);
        logger.debug("##################{} builds : {} " + (elements.size() > 1 ? "elements" : "element") + "\n",
                getFilterName(), elements.size());
        if (elements.size() == 1) {
            JAXBElement<ComparisonOpsType> element = (JAXBElement<ComparisonOpsType>) elements.get(0);
            filter.setComparisonOps(element);
        } else if (elements.size() > 1) {
            BinaryLogicOpType or = new BinaryLogicOpType();
            or.setComparisonOpsOrSpatialOpsOrLogicOps(elements);
            filter.setLogicOps(filterFactory.createOr(or));
        }
    }

    /**
     * <p>We have three Operator type see {@link LogicOperatorType}</p>
     *
     * @return the Operator
     */
    @Override
    protected String getOperatorValue() {
        return LogicOperatorType.ANY.name();
    }
}
