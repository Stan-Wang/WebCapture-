package HtmlParser;

import org.htmlparser.Node;
import org.htmlparser.util.NodeList;
import org.junit.Test;

import static HtmlParser.HtmlParser.getLinks;
import static HtmlParser.HtmlParser.getNodeList;

public class HtmlParserTest {

	@Test
	public void testGetNodeList() throws Exception {

//		System.setProperty("http.proxyHost", "127.0.0.1");
//		System.setProperty("http.proxyPort", "8087");

		NodeList rt = getNodeList(
				"http://m.58.com/zz/hezu/18710994264714x.shtml",
				new HtmlClassFilter("*"));

		for (Node node : rt.toNodeArray()) {
			System.out.println(node.toPlainTextString());
		}

	}

	@Test
	public void testGetLinks() throws Exception {
		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "8087");
		getLinks("http://m.58.com/zz/hezu/18710994264714x.shtml",
				".*page.*");

	}

	@Test
	public void test() {
		for (int i = 1; i <= 5; i++) {
			System.out.println(i);
		}
	}

}