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
package org.geosdi.geoplatform.connector.wfs.response;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
//@XmlRootElement(name = "FeatureCollectionDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class FeatureCollectionDTO implements Serializable {

    private static final long serialVersionUID = 4396973113154660358L;
    //
    private Date timeStamp;
    private int numberOfFeatures;
    //
    @XmlElementWrapper(name = "features")
    @XmlElement(name = "feature")
    private List<FeatureDTO> features;
    @XmlElement(name = "errorMessage")
    private String errorMessage;

    public Date getTimeStamp() {
        if (timeStamp != null) {
            return new Date(timeStamp.getTime());
        }
        return null;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = new Date(timeStamp.getTime());
    }

    public int getNumberOfFeatures() {
        return numberOfFeatures;
    }

    public void setNumberOfFeatures(int numberOfFeatures) {
        this.numberOfFeatures = numberOfFeatures;
    }

    public List<FeatureDTO> getFeatures() {
        if (features == null || features.isEmpty()) {
            return null;
        }
        return new ArrayList<FeatureDTO>(features);
    }

    public void setFeatures(List<FeatureDTO> features) {
        this.features = features;
    }

    public void addFeature(FeatureDTO feature) {
        if (features == null) {
            features = new ArrayList<FeatureDTO>();
        }
        features.add(feature);
    }

    public boolean isFeaturesLoaded() {
        return ((this.numberOfFeatures != 0)
                && (this.features != null));
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "FeatureCollectionDTO {" + "timeStamp = " + timeStamp
                + ", numberOfFeatures = " + numberOfFeatures
                + ", features = " + features
                + ", errorMessage = " + errorMessage + '}';
    }
}