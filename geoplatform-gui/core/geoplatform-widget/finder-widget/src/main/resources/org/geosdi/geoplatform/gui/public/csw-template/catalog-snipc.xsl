<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    
    <xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes"/>

    <xsl:template match="*" priority="3">
        
        <html>
            <body>
                <h2>SNIPC catalog - GetRecordById request</h2>
                <h3>Metadata</h3>

                <table border="1">
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
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>