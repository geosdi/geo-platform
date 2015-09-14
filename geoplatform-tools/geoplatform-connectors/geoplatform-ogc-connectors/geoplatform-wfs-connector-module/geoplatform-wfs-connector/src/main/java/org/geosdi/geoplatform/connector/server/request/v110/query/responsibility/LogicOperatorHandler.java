package org.geosdi.geoplatform.connector.server.request.v110.query.responsibility;

import org.geosdi.geoplatform.connector.server.request.v110.query.repository.QueryRestrictionRepository;
import org.geosdi.geoplatform.connector.server.request.v110.query.repository.QueryRestrictionStrategy;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.geosdi.geoplatform.xml.filter.v110.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class LogicOperatorHandler implements ILogicOperatorHandler {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected static final ObjectFactory filterFactory = QueryRestrictionStrategy.filterFactory;
    private LogicOperatorHandler successor;

    /**
     * @param queryDTO
     * @param filter
     * @throws IllegalStateException
     */
    protected void forwardBuildLogicFilterOperator(QueryDTO queryDTO, FilterType filter) throws IllegalStateException {
        if (this.successor != null) {
            this.successor.buildLogicFilterOperator(queryDTO, filter);
        }
    }

    /**
     * @param theLogicOperator
     * @return {@link Boolean}
     */
    protected Boolean canBuildLogicOperator(String theLogicOperator) {
        return this.getOperatorValue().equalsIgnoreCase(theLogicOperator);
    }

    /**
     * @param queryRestrictionDTOs
     * @return {@link List<JAXBElement<?>>} list
     */
    protected List<JAXBElement<?>> buildJAXBElementList(List<QueryRestrictionDTO> queryRestrictionDTOs) {
        List<JAXBElement<?>> elements = new ArrayList<>(queryRestrictionDTOs.size());
        for (QueryRestrictionDTO queryRestrictionDTO : queryRestrictionDTOs) {
            OperatorType operatorType = queryRestrictionDTO.getOperator();
            if (operatorType != null) {
                QueryRestrictionStrategy<?> queryRestrictionStrategy = QueryRestrictionRepository.getQueryRestrictionStrategy(
                        operatorType);
                if (queryRestrictionStrategy != null) {
                    elements.add(queryRestrictionStrategy.create(queryRestrictionDTO));
                } else {
                    logger.debug("###############{} doesn't found QueryRestrictionStrategy<?> " +
                            "for " + "OperatorType : {}\n", getFilterName(), operatorType);
                }
            }
        }
        return elements;
    }


    /**
     * @param filter
     * @param queryRestrictionDTOs
     */
    protected abstract void processQueryRestrictions(FilterType filter, List<QueryRestrictionDTO> queryRestrictionDTOs);

    /**
     * <p>We have three Operator type see {@link org.geosdi.geoplatform.gui.shared.wfs.LogicOperatorType}</p>
     *
     * @return the Operator
     */
    protected abstract String getOperatorValue();

    /**
     * @param successor
     */
    public void setSuccessor(LogicOperatorHandler successor) {
        this.successor = successor;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getFilterName() {
        return getClass().getSimpleName();
    }
}
