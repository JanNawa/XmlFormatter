package assignment_5.xml;

import assignment_5.datamodel.*;
import org.w3c.dom.*;

/**
 *
 * @author Jan
 */
public abstract class XmlFormatter {
    // defines variable to be used in xml format
    Document dom;
    
    private Element rootElement;
    private Element element1;
    private Element element2;
    private Element element3;
    private Element element4;
    
    // to determine the level of tag
    enum Level {
        ROOT, LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4;
    }
    
    public abstract Document formatXml(Summary summary);
    
    // create tag in xml
    void createTag(Level level, String tagName) {
        switch (level) {
            case ROOT:
                rootElement = dom.createElement(tagName);
                dom.appendChild(rootElement);
                break;
            case LEVEL_1:
                element1 = dom.createElement(tagName);
                rootElement.appendChild(element1);
                break;
            case LEVEL_2:
                element2 = dom.createElement(tagName);
                element1.appendChild(element2);
                break;
            case LEVEL_3:
                element3 = dom.createElement(tagName);
                element2.appendChild(element3);
                break;
            case LEVEL_4:
                element4 = dom.createElement(tagName);
                element3.appendChild(element4);
                break;
        }
    }

    // create tag in xml with data extract from database
    void createTag(Level level, String tagName, String data) {
        switch (level) {
            case ROOT:
                createTag(level, tagName);
                rootElement.appendChild(dom.createTextNode(data));
                break;
            case LEVEL_1:
                createTag(level, tagName);
                element1.appendChild(dom.createTextNode(data));
                break;
            case LEVEL_2:
                createTag(level, tagName);
                element2.appendChild(dom.createTextNode(data));
                break;
            case LEVEL_3:
                createTag(level, tagName);
                element3.appendChild(dom.createTextNode(data));
                break;
            case LEVEL_4:
                createTag(level, tagName);
                element4.appendChild(dom.createTextNode(data));
                break;
        }
    }
}