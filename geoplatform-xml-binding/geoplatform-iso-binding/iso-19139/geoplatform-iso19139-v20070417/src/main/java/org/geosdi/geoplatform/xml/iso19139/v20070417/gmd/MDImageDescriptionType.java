/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
//
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v4.0.9 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine.
// Generato il: 2026.11.02 alle 03:48:01 PM CET
//

package org.geosdi.geoplatform.xml.iso19139.v20070417.gmd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.BooleanPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.IntegerPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.RealPropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Information about an image's suitability for use
 * 
 * <p>Classe Java per MD_ImageDescription_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MD_ImageDescription_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gmd}MD_CoverageDescription_Type">
 *       <sequence>
 *         <element name="illuminationElevationAngle" type="{http://www.isotc211.org/2005/gco}Real_PropertyType" minOccurs="0"/>
 *         <element name="illuminationAzimuthAngle" type="{http://www.isotc211.org/2005/gco}Real_PropertyType" minOccurs="0"/>
 *         <element name="imagingCondition" type="{http://www.isotc211.org/2005/gmd}MD_ImagingConditionCode_PropertyType" minOccurs="0"/>
 *         <element name="imageQualityCode" type="{http://www.isotc211.org/2005/gmd}MD_Identifier_PropertyType" minOccurs="0"/>
 *         <element name="cloudCoverPercentage" type="{http://www.isotc211.org/2005/gco}Real_PropertyType" minOccurs="0"/>
 *         <element name="processingLevelCode" type="{http://www.isotc211.org/2005/gmd}MD_Identifier_PropertyType" minOccurs="0"/>
 *         <element name="compressionGenerationQuantity" type="{http://www.isotc211.org/2005/gco}Integer_PropertyType" minOccurs="0"/>
 *         <element name="triangulationIndicator" type="{http://www.isotc211.org/2005/gco}Boolean_PropertyType" minOccurs="0"/>
 *         <element name="radiometricCalibrationDataAvailability" type="{http://www.isotc211.org/2005/gco}Boolean_PropertyType" minOccurs="0"/>
 *         <element name="cameraCalibrationInformationAvailability" type="{http://www.isotc211.org/2005/gco}Boolean_PropertyType" minOccurs="0"/>
 *         <element name="filmDistortionInformationAvailability" type="{http://www.isotc211.org/2005/gco}Boolean_PropertyType" minOccurs="0"/>
 *         <element name="lensDistortionInformationAvailability" type="{http://www.isotc211.org/2005/gco}Boolean_PropertyType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_ImageDescription_Type", propOrder = {
    "illuminationElevationAngle",
    "illuminationAzimuthAngle",
    "imagingCondition",
    "imageQualityCode",
    "cloudCoverPercentage",
    "processingLevelCode",
    "compressionGenerationQuantity",
    "triangulationIndicator",
    "radiometricCalibrationDataAvailability",
    "cameraCalibrationInformationAvailability",
    "filmDistortionInformationAvailability",
    "lensDistortionInformationAvailability"
})
public class MDImageDescriptionType
    extends MDCoverageDescriptionType
    implements ToString
{

    protected RealPropertyType illuminationElevationAngle;
    protected RealPropertyType illuminationAzimuthAngle;
    protected MDImagingConditionCodePropertyType imagingCondition;
    protected MDIdentifierPropertyType imageQualityCode;
    protected RealPropertyType cloudCoverPercentage;
    protected MDIdentifierPropertyType processingLevelCode;
    protected IntegerPropertyType compressionGenerationQuantity;
    protected BooleanPropertyType triangulationIndicator;
    protected BooleanPropertyType radiometricCalibrationDataAvailability;
    protected BooleanPropertyType cameraCalibrationInformationAvailability;
    protected BooleanPropertyType filmDistortionInformationAvailability;
    protected BooleanPropertyType lensDistortionInformationAvailability;

    /**
     * Recupera il valore della proprietà illuminationElevationAngle.
     * 
     * @return
     *     possible object is
     *     {@link RealPropertyType }
     *     
     */
    public RealPropertyType getIlluminationElevationAngle() {
        return illuminationElevationAngle;
    }

    /**
     * Imposta il valore della proprietà illuminationElevationAngle.
     * 
     * @param value
     *     allowed object is
     *     {@link RealPropertyType }
     *     
     */
    public void setIlluminationElevationAngle(RealPropertyType value) {
        this.illuminationElevationAngle = value;
    }

    public boolean isSetIlluminationElevationAngle() {
        return (this.illuminationElevationAngle!= null);
    }

    /**
     * Recupera il valore della proprietà illuminationAzimuthAngle.
     * 
     * @return
     *     possible object is
     *     {@link RealPropertyType }
     *     
     */
    public RealPropertyType getIlluminationAzimuthAngle() {
        return illuminationAzimuthAngle;
    }

    /**
     * Imposta il valore della proprietà illuminationAzimuthAngle.
     * 
     * @param value
     *     allowed object is
     *     {@link RealPropertyType }
     *     
     */
    public void setIlluminationAzimuthAngle(RealPropertyType value) {
        this.illuminationAzimuthAngle = value;
    }

    public boolean isSetIlluminationAzimuthAngle() {
        return (this.illuminationAzimuthAngle!= null);
    }

    /**
     * Recupera il valore della proprietà imagingCondition.
     * 
     * @return
     *     possible object is
     *     {@link MDImagingConditionCodePropertyType }
     *     
     */
    public MDImagingConditionCodePropertyType getImagingCondition() {
        return imagingCondition;
    }

    /**
     * Imposta il valore della proprietà imagingCondition.
     * 
     * @param value
     *     allowed object is
     *     {@link MDImagingConditionCodePropertyType }
     *     
     */
    public void setImagingCondition(MDImagingConditionCodePropertyType value) {
        this.imagingCondition = value;
    }

    public boolean isSetImagingCondition() {
        return (this.imagingCondition!= null);
    }

    /**
     * Recupera il valore della proprietà imageQualityCode.
     * 
     * @return
     *     possible object is
     *     {@link MDIdentifierPropertyType }
     *     
     */
    public MDIdentifierPropertyType getImageQualityCode() {
        return imageQualityCode;
    }

    /**
     * Imposta il valore della proprietà imageQualityCode.
     * 
     * @param value
     *     allowed object is
     *     {@link MDIdentifierPropertyType }
     *     
     */
    public void setImageQualityCode(MDIdentifierPropertyType value) {
        this.imageQualityCode = value;
    }

    public boolean isSetImageQualityCode() {
        return (this.imageQualityCode!= null);
    }

    /**
     * Recupera il valore della proprietà cloudCoverPercentage.
     * 
     * @return
     *     possible object is
     *     {@link RealPropertyType }
     *     
     */
    public RealPropertyType getCloudCoverPercentage() {
        return cloudCoverPercentage;
    }

    /**
     * Imposta il valore della proprietà cloudCoverPercentage.
     * 
     * @param value
     *     allowed object is
     *     {@link RealPropertyType }
     *     
     */
    public void setCloudCoverPercentage(RealPropertyType value) {
        this.cloudCoverPercentage = value;
    }

    public boolean isSetCloudCoverPercentage() {
        return (this.cloudCoverPercentage!= null);
    }

    /**
     * Recupera il valore della proprietà processingLevelCode.
     * 
     * @return
     *     possible object is
     *     {@link MDIdentifierPropertyType }
     *     
     */
    public MDIdentifierPropertyType getProcessingLevelCode() {
        return processingLevelCode;
    }

    /**
     * Imposta il valore della proprietà processingLevelCode.
     * 
     * @param value
     *     allowed object is
     *     {@link MDIdentifierPropertyType }
     *     
     */
    public void setProcessingLevelCode(MDIdentifierPropertyType value) {
        this.processingLevelCode = value;
    }

    public boolean isSetProcessingLevelCode() {
        return (this.processingLevelCode!= null);
    }

    /**
     * Recupera il valore della proprietà compressionGenerationQuantity.
     * 
     * @return
     *     possible object is
     *     {@link IntegerPropertyType }
     *     
     */
    public IntegerPropertyType getCompressionGenerationQuantity() {
        return compressionGenerationQuantity;
    }

    /**
     * Imposta il valore della proprietà compressionGenerationQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link IntegerPropertyType }
     *     
     */
    public void setCompressionGenerationQuantity(IntegerPropertyType value) {
        this.compressionGenerationQuantity = value;
    }

    public boolean isSetCompressionGenerationQuantity() {
        return (this.compressionGenerationQuantity!= null);
    }

    /**
     * Recupera il valore della proprietà triangulationIndicator.
     * 
     * @return
     *     possible object is
     *     {@link BooleanPropertyType }
     *     
     */
    public BooleanPropertyType getTriangulationIndicator() {
        return triangulationIndicator;
    }

    /**
     * Imposta il valore della proprietà triangulationIndicator.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanPropertyType }
     *     
     */
    public void setTriangulationIndicator(BooleanPropertyType value) {
        this.triangulationIndicator = value;
    }

    public boolean isSetTriangulationIndicator() {
        return (this.triangulationIndicator!= null);
    }

    /**
     * Recupera il valore della proprietà radiometricCalibrationDataAvailability.
     * 
     * @return
     *     possible object is
     *     {@link BooleanPropertyType }
     *     
     */
    public BooleanPropertyType getRadiometricCalibrationDataAvailability() {
        return radiometricCalibrationDataAvailability;
    }

    /**
     * Imposta il valore della proprietà radiometricCalibrationDataAvailability.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanPropertyType }
     *     
     */
    public void setRadiometricCalibrationDataAvailability(BooleanPropertyType value) {
        this.radiometricCalibrationDataAvailability = value;
    }

    public boolean isSetRadiometricCalibrationDataAvailability() {
        return (this.radiometricCalibrationDataAvailability!= null);
    }

    /**
     * Recupera il valore della proprietà cameraCalibrationInformationAvailability.
     * 
     * @return
     *     possible object is
     *     {@link BooleanPropertyType }
     *     
     */
    public BooleanPropertyType getCameraCalibrationInformationAvailability() {
        return cameraCalibrationInformationAvailability;
    }

    /**
     * Imposta il valore della proprietà cameraCalibrationInformationAvailability.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanPropertyType }
     *     
     */
    public void setCameraCalibrationInformationAvailability(BooleanPropertyType value) {
        this.cameraCalibrationInformationAvailability = value;
    }

    public boolean isSetCameraCalibrationInformationAvailability() {
        return (this.cameraCalibrationInformationAvailability!= null);
    }

    /**
     * Recupera il valore della proprietà filmDistortionInformationAvailability.
     * 
     * @return
     *     possible object is
     *     {@link BooleanPropertyType }
     *     
     */
    public BooleanPropertyType getFilmDistortionInformationAvailability() {
        return filmDistortionInformationAvailability;
    }

    /**
     * Imposta il valore della proprietà filmDistortionInformationAvailability.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanPropertyType }
     *     
     */
    public void setFilmDistortionInformationAvailability(BooleanPropertyType value) {
        this.filmDistortionInformationAvailability = value;
    }

    public boolean isSetFilmDistortionInformationAvailability() {
        return (this.filmDistortionInformationAvailability!= null);
    }

    /**
     * Recupera il valore della proprietà lensDistortionInformationAvailability.
     * 
     * @return
     *     possible object is
     *     {@link BooleanPropertyType }
     *     
     */
    public BooleanPropertyType getLensDistortionInformationAvailability() {
        return lensDistortionInformationAvailability;
    }

    /**
     * Imposta il valore della proprietà lensDistortionInformationAvailability.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanPropertyType }
     *     
     */
    public void setLensDistortionInformationAvailability(BooleanPropertyType value) {
        this.lensDistortionInformationAvailability = value;
    }

    public boolean isSetLensDistortionInformationAvailability() {
        return (this.lensDistortionInformationAvailability!= null);
    }

    @Override
    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.getInstance();
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    @Override
    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    @Override
    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        super.appendFields(locator, buffer, strategy);
        {
            RealPropertyType theIlluminationElevationAngle;
            theIlluminationElevationAngle = this.getIlluminationElevationAngle();
            strategy.appendField(locator, this, "illuminationElevationAngle", buffer, theIlluminationElevationAngle, this.isSetIlluminationElevationAngle());
        }
        {
            RealPropertyType theIlluminationAzimuthAngle;
            theIlluminationAzimuthAngle = this.getIlluminationAzimuthAngle();
            strategy.appendField(locator, this, "illuminationAzimuthAngle", buffer, theIlluminationAzimuthAngle, this.isSetIlluminationAzimuthAngle());
        }
        {
            MDImagingConditionCodePropertyType theImagingCondition;
            theImagingCondition = this.getImagingCondition();
            strategy.appendField(locator, this, "imagingCondition", buffer, theImagingCondition, this.isSetImagingCondition());
        }
        {
            MDIdentifierPropertyType theImageQualityCode;
            theImageQualityCode = this.getImageQualityCode();
            strategy.appendField(locator, this, "imageQualityCode", buffer, theImageQualityCode, this.isSetImageQualityCode());
        }
        {
            RealPropertyType theCloudCoverPercentage;
            theCloudCoverPercentage = this.getCloudCoverPercentage();
            strategy.appendField(locator, this, "cloudCoverPercentage", buffer, theCloudCoverPercentage, this.isSetCloudCoverPercentage());
        }
        {
            MDIdentifierPropertyType theProcessingLevelCode;
            theProcessingLevelCode = this.getProcessingLevelCode();
            strategy.appendField(locator, this, "processingLevelCode", buffer, theProcessingLevelCode, this.isSetProcessingLevelCode());
        }
        {
            IntegerPropertyType theCompressionGenerationQuantity;
            theCompressionGenerationQuantity = this.getCompressionGenerationQuantity();
            strategy.appendField(locator, this, "compressionGenerationQuantity", buffer, theCompressionGenerationQuantity, this.isSetCompressionGenerationQuantity());
        }
        {
            BooleanPropertyType theTriangulationIndicator;
            theTriangulationIndicator = this.getTriangulationIndicator();
            strategy.appendField(locator, this, "triangulationIndicator", buffer, theTriangulationIndicator, this.isSetTriangulationIndicator());
        }
        {
            BooleanPropertyType theRadiometricCalibrationDataAvailability;
            theRadiometricCalibrationDataAvailability = this.getRadiometricCalibrationDataAvailability();
            strategy.appendField(locator, this, "radiometricCalibrationDataAvailability", buffer, theRadiometricCalibrationDataAvailability, this.isSetRadiometricCalibrationDataAvailability());
        }
        {
            BooleanPropertyType theCameraCalibrationInformationAvailability;
            theCameraCalibrationInformationAvailability = this.getCameraCalibrationInformationAvailability();
            strategy.appendField(locator, this, "cameraCalibrationInformationAvailability", buffer, theCameraCalibrationInformationAvailability, this.isSetCameraCalibrationInformationAvailability());
        }
        {
            BooleanPropertyType theFilmDistortionInformationAvailability;
            theFilmDistortionInformationAvailability = this.getFilmDistortionInformationAvailability();
            strategy.appendField(locator, this, "filmDistortionInformationAvailability", buffer, theFilmDistortionInformationAvailability, this.isSetFilmDistortionInformationAvailability());
        }
        {
            BooleanPropertyType theLensDistortionInformationAvailability;
            theLensDistortionInformationAvailability = this.getLensDistortionInformationAvailability();
            strategy.appendField(locator, this, "lensDistortionInformationAvailability", buffer, theLensDistortionInformationAvailability, this.isSetLensDistortionInformationAvailability());
        }
        return buffer;
    }

}
