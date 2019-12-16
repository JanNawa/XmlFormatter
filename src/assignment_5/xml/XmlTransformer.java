package assignment_5.xml;

import assignment_5.datamodel.*;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

/**
 * This class transform data into xml format and send the output to the file
 *
 * @author Jan
 */
public class XmlTransformer {

    // defines variable to be used in xml format
    private Document dom;

    // write data in xml format to the file
    public void writeToXml(String xmlLocation, Summary summary) {
        // create instance and cast into the correct object type
        Summary s = null;
        if (summary instanceof YearEndSummary) {
            s = (YearEndSummary) summary;
        }

        try {
            // format the xml
            XmlFormatter xmlFormatter = new YearEndSummaryXmlFormatter();
            dom = xmlFormatter.formatXml(s);

            // transform data into xml format and sent to designate location
            try {
                Transformer transform = TransformerFactory.newInstance().newTransformer();
                transform.setOutputProperty(OutputKeys.INDENT, "yes");
                transform.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                transform.setOutputProperty(OutputKeys.METHOD, "xml");
                transform.setOutputProperty(OutputKeys.VERSION, "1.0");
                transform.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

                // send DOM to file
                transform.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(xmlLocation)));

            } catch (TransformerException | IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("Error trying to instantiate DocumentBuilder " + pce);
        }
    }
}
