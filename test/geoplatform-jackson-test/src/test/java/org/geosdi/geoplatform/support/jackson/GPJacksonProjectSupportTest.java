/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.support.jackson;

import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.response.ProjectDTO;
import org.geosdi.geoplatform.response.WSGetAccountProjectsResponse;
import static org.geosdi.geoplatform.support.jackson.GPBaseJacksonSupportTest.jacksonSupport;
import org.junit.Test;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJacksonProjectSupportTest extends GPBaseJacksonSupportTest {

    @Test
    public void projectsDataMapperTest() throws Exception {
        ProjectDTO projectDTO = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        PROJECTS_DATA_JSON), ProjectDTO.class);

        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@PROJECTS_DATA_MAPPING"
                + " : {}\n\n", projectDTO);
        
        super.marshall(projectDTO);
    }

    @Test
    public void getAllProjectsDataMapperTest() throws Exception {
        WSGetAccountProjectsResponse response = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        GET_ALL_PROJECTS_DATA_JSON),
                WSGetAccountProjectsResponse.class);

        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
                + "GET_ALL_PROJECTS_DATA_MAPPING : {}\n\n", response);
        
        super.marshall(response);
    }

    @Test
    public void projectDataMapperTest() throws Exception {
        GPProject project = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        PROJECT_DATA_JSON), GPProject.class);

        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@PROJECT_DATA_MAPPING"
                + " : {}\n\n", project);
        
        super.marshall(project);
    }

}
