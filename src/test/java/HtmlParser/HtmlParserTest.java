package HtmlParser;

import org.htmlparser.Node;
import org.htmlparser.util.NodeList;
import org.junit.Test;

import static HtmlParser.HtmlParser.getNodeList;

public class HtmlParserTest {

    @Test
    public void testGetNodeList() throws Exception {
        NodeList rt = getNodeList("http://ifeve.com",new HtmlClassFilter("post_content"));

        for (Node node : rt.toNodeArray()) {
            System.out.println(node.toPlainTextString());
        }
    }
}