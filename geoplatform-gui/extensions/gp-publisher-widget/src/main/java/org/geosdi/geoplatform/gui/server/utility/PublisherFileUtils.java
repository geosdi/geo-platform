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
package org.geosdi.geoplatform.gui.server.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.geosdi.geoplatform.responce.InfoPreview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "publisherFileUtils")
public class PublisherFileUtils {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private String TMP_DIR_PATH = System.getProperty("java.io.tmpdir");
    private File pbDir;

    public File createFileWithUniqueName(String fileName) {
        return new File(pbDir.getAbsolutePath() + File.separator + fileName
                + Long.toString(System.nanoTime()));
    }

    public static String generateJSONObjects(List<InfoPreview> infoPreview) {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<InfoPreview>>() {}.getType();
        //Note: the name previewLayers must correspond to the field name in PreviewLayerList class
        return "{\"previewLayers\":" + gson.toJson(infoPreview, listType) + "}";
    }

    @PostConstruct
    public void init() {
        if (!TMP_DIR_PATH.endsWith(File.separator)) {
            TMP_DIR_PATH += File.separator;
        }
        pbDir = new File(TMP_DIR_PATH + "GPPublishDir");
        if (!pbDir.exists()) {
            pbDir.mkdir();
        }
        logger.info("GP_PUBLISH_DIR_PATH : ***************************** " + pbDir.getAbsolutePath());
    }

    @PreDestroy
    public void destroy() {
        try {
            if (pbDir.exists()) {
                FileUtils.deleteDirectory(pbDir);
                logger.info("- ----------------------> Destroy PublisherFileUtils "
                        + "and  Delete GPPublishDir");
            }
        } catch (IOException ex) {
            logger.error("GeoPlatform Publish Dir Delete Error : " + ex);
        }
    }
}