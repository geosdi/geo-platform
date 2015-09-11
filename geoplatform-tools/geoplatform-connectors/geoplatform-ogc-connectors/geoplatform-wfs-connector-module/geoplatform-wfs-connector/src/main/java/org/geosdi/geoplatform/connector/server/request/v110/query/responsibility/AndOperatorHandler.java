package org.geosdi.geoplatform.connector.server.request.v110.query.responsibility;

import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.gui.shared.wfs.LogicOperatorType;
import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;
import org.geosdi.geoplatform.xml.filter.v110.BinaryLogicOpType;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;

import javax.xml.bind.JAXBElement;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class AndOperatorHandler extends LogicOperatorHandler {

    public AndOperatorHandler() {
        super.setSuccessor(new NotOperatorHandler());
    }

    /**
     * @param queryDTO
     * @param filter
     * @throws IllegalStateException
     */
    @Override
    public void buildLogicFilterOperator(QueryDTO queryDTO, FilterType filter) throws IllegalStateException {
        if (super.canBuildLogicOperator(queryDTO.getMatchOperator())) {
            processQueryRestrictions(filter, queryDTO.getQueryRestrictionList());
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
        BinaryLogicOpType and = new BinaryLogicOpType();
        List<JAXBElement<?>> elements = super.buildJAXBElementList(queryRestrictionDTOs);
        logger.debug("\n##################{} builds : {} " + (elements.size() > 1 ? "elements" : "element") + "\n",
                getFilterName(), elements.size());
        and.setComparisonOpsOrSpatialOpsOrLogicOps(elements);
        filter.setLogicOps(filterFactory.createAnd(and));
    }

    /**
     * <p>We have three Operator type see {@link OperatorType}</p>
     *
     * @return the Operator
     */
    @Override
    protected String getOperatorValue() {
        return LogicOperatorType.ALL.name();
    }
}
