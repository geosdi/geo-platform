<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:gfc="http://www.isotc211.org/2005/gfc" 
                xmlns:gco="http://www.isotc211.org/2005/gco"
                xmlns:gts="http://www.isotc211.org/2005/gts"
                xmlns:gmx="http://www.isotc211.org/2005/gmx"
                xmlns:gmd="http://www.isotc211.org/2005/gmd"
                xmlns:gsr="http://www.isotc211.org/2005/gsr"
                xmlns:gss="http://www.isotc211.org/2005/gss"
                xmlns:csw="http://www.opengis.net/cat/csw/2.0.2"
                xmlns:gml="http://www.opengis.net/gml"
                xmlns:ogc="http://www.opengis.net/ogc"
                xmlns:ows="http://www.opengis.net/ows">
    
    <xsl:output method="html" version="4.0" encoding="iso-8859-1" indent="yes" />
        
    <xsl:template match="/">
        <html>
            <body>
                <h2 align="center">GetRecordById Request</h2>
                <h3 align="center">Full Metadata</h3>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="*">        
        <table border="1" align="center">
            <tr bgcolor="#9acd32">
                <th>
                    <xsl:value-of select="name()"/>
                </th>
            </tr>
            <tr>
                <td>
                    <xsl:element name="featureType" namespace="gfc">
                        <xsl:apply-templates select="@*|node()" />
                    </xsl:element>
                </td>
            </tr>
        </table>
    </xsl:template>

</xsl:stylesheet>