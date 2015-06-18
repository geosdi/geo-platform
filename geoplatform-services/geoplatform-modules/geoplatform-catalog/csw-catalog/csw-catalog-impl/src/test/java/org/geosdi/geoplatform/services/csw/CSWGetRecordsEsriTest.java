/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2015 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.services.csw;

import java.math.BigInteger;
import java.net.URL;
import java.util.List;
import javax.annotation.Resource;
import javax.xml.bind.JAXBElement;
import org.geosdi.geoplatform.connector.GPCSWConnectorBuilder;
import org.geosdi.geoplatform.connector.GPCatalogConnectorStore;
import static org.geosdi.geoplatform.connector.schema.CSWOperationsWithOutputSchema.GET_RECORDS;
import org.geosdi.geoplatform.connector.schema.CSWOutputSchemaFinder;
import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordsRequest;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.gui.responce.TextInfo;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.geosdi.geoplatform.responce.FullRecordDTO;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguage;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguageVersion;
import org.geosdi.geoplatform.xml.csw.OutputSchema;
import org.geosdi.geoplatform.xml.csw.TypeName;
import org.geosdi.geoplatform.xml.csw.v202.ElementSetType;
import org.geosdi.geoplatform.xml.csw.v202.GetRecordsResponseType;
import org.geosdi.geoplatform.xml.csw.v202.ResultType;
import org.geosdi.geoplatform.xml.csw.v202.SearchResultsType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.AbstractMDIdentificationType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.CICitationPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.CICitationType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDIdentificationPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDKeywordsPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDKeywordsType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDMetadataType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Test.xml",
    "classpath:applicationContext-Logger.xml"})
public class CSWGetRecordsEsriTest {

    @GeoPlatformLog
    private static Logger logger;
    //
    @Resource(name = "gpCSWOutputSchemaFinder")
    private CSWOutputSchemaFinder gpCSWOutputSchemaFinder;

    @Test
    public void testFullRecordEsri() throws Exception {
        URL url = new URL("http://www.geoportale.isprambiente.it/geoportale"
                + "/csw/discovery");
        GPCatalogConnectorStore serverConnector = GPCSWConnectorBuilder
                .newConnector().withServerUrl(url).build();

        CatalogFinderBean finderBean = new CatalogFinderBean();
        finderBean.setTextInfo(new TextInfo() {

            private static final long serialVersionUID = 1L;

            {
                super.setText("scuole");
                super.setSearchTitle(Boolean.TRUE);
                super.setSearchSubjects(Boolean.FALSE);
                super.setSearchAbstract(Boolean.FALSE);
            }

        });

        CatalogGetRecordsRequest<GetRecordsResponseType> request = serverConnector.createGetRecordsRequest();
        request.setConstraintLanguage(ConstraintLanguage.FILTER);
        request.setConstraintLanguageVersion(ConstraintLanguageVersion.V110);
        request.setCatalogFinder(finderBean);

        OutputSchema outputSchema = this.gpCSWOutputSchemaFinder
                .retrieveBestOutputSchemaForRequest(serverConnector,
                        GET_RECORDS.toString());

        if (outputSchema == OutputSchema.GMD) {
            request.setOutputSchema(outputSchema);
            request.setTypeName(TypeName.METADATA);
        } else {
            request.setOutputSchema(OutputSchema.CSW_V202);
            request.setTypeName(TypeName.RECORD_V202);
        }
        request.setElementSetName(ElementSetType.FULL.toString());
        request.setResultType(ResultType.RESULTS.toString());

        request.setStartPosition(BigInteger.ONE);
        request.setMaxRecords(BigInteger.valueOf(25));

        logger.debug("\n\n#####################RESPONSE AS STRING : {}\n\n",
                request.getResponseAsString());

        GetRecordsResponseType response = request.getResponse();

        SearchResultsType result = response.getSearchResults();
        List<Object> metadati = result.getAny();

        for (Object metadato : metadati) {
            logger.info("\n\n@@@@@@@@@@@@@@@@@@METADATO : {}\n\n",
                    ((JAXBElement) metadato).getValue());
        }
//        this.fillFullRecordDTO(((JAXBElement<MDMetadataType>) metadati.get(0))
//                .getValue());
//        
//        for (Object m : metadati) {
//            logger.trace("@@@@@@@@@@@@@@@@@@FOUND METADATA : {}\n\n",
//                    ((JAXBElement<MDMetadataType>) m).getValue());
//        }
    }

    private void fillFullRecordDTO(MDMetadataType metadata) {
        FullRecordDTO fullRecordDTO = new FullRecordDTO();

        CharacterStringPropertyType cspt = metadata.getFileIdentifier();
        JAXBElement<?> jaxbElement = cspt.getCharacterString();
        if (jaxbElement.getValue() instanceof String) {
            fullRecordDTO.setIdentifier((String) jaxbElement.getValue());
        }

        logger.info("MDIdentificationPropertyType : {}\n\n",
                metadata.getIdentificationInfo());

        List<MDIdentificationPropertyType> identificationInfos = metadata
                .getIdentificationInfo();

        for (MDIdentificationPropertyType i : identificationInfos) {
            if (i.isSetAbstractMDIdentification()) {
                AbstractMDIdentificationType elem = i.getAbstractMDIdentification().getValue();
                fillTitle(elem.getCitation(), fullRecordDTO);
                if (elem.isSetAbstract()) {
                    fillAbstract(elem.getAbstract(), fullRecordDTO);
                }
                if (elem.isSetDescriptiveKeywords()) {
                    fillKeywords(elem.getDescriptiveKeywords(), fullRecordDTO);
                }

            }
        }

        logger.debug("####################FULL_RECORD_DTO : {}\n\n",
                fullRecordDTO);
    }

    private void fillTitle(CICitationPropertyType citation,
            FullRecordDTO fullRecordDTO) {
        logger.debug("{}", citation);
        if (citation.isSetCICitation()) {
            CICitationType ciCitation = citation.getCICitation();
            CharacterStringPropertyType title = ciCitation.getTitle();
            logger.info("\n\n################TITLE : {}\n\n",
                    title.getCharacterString().getValue());
            if (title.getCharacterString().getValue() instanceof String) {
                fullRecordDTO.setTitle((String) title.getCharacterString()
                        .getValue());
            }
        }
    }

    private void fillAbstract(CharacterStringPropertyType _abstract,
            FullRecordDTO fullRecordDTO) {
        logger.info("\n\n################ABSTRACT : {}\n\n",
                _abstract.getCharacterString().getValue());
        if (_abstract.getCharacterString().getValue() instanceof String) {
            fullRecordDTO.setAbstractText((String) _abstract.getCharacterString()
                    .getValue());
        }
    }

    private void fillKeywords(List<MDKeywordsPropertyType> descriptiveKeywords,
            FullRecordDTO fullRecordDTO) {
        for (MDKeywordsPropertyType keyword : descriptiveKeywords) {
            if (keyword.isSetMDKeywords()) {
                MDKeywordsType mdKeywords = keyword.getMDKeywords();
                if (mdKeywords.isSetKeyword()) {
                    for (CharacterStringPropertyType k : mdKeywords.getKeyword()) {
                        if (k.getCharacterString().getValue() instanceof String) {
                            fullRecordDTO.addSubject((String) k.getCharacterString().getValue());
                        }
                    }
                }
            }
        }
    }

}
