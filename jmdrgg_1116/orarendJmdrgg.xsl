<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/" > 
<html>
    <h1>NR Órarend - 2022/23. I. félév.</h1>
    <body>
        <table border="1">
            <tr>
                <td>id</td>
                <td>tipus</td>
                <td>targy</td>
                <td>nap</td>
                <td>tol</td>
                <td>ig</td>
                <td>helyszin</td>
                <td>oktato</td>
                <td>szak</td>
            </tr>
            <xsl:for-each select="NR_orarend/ora">
                <tr>
                    <td> <xsl:value-of select="@id"/></td>
                    <td> <xsl:value-of select="@tipus"/></td>
                    <td> <xsl:value-of select="targy"/></td>
                    <td> <xsl:value-of select="nap"/></td>
                    <td> <xsl:value-of select="tol"/></td>
                    <td> <xsl:value-of select="ig"/></td>
                    <td> <xsl:value-of select="helyszin"/></td>
                    <td> <xsl:value-of select="oktato"/></td>
                    <td> <xsl:value-of select="szak"/></td>
                </tr>
            </xsl:for-each>
        </table>
    </body>
</html>
</xsl:template>
</xsl:stylesheet>