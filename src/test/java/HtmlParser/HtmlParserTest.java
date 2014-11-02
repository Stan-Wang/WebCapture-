package HtmlParser;

import org.htmlparser.Node;
import org.htmlparser.util.NodeList;
import org.junit.Test;

import static HtmlParser.HtmlParser.getLinks;
import static HtmlParser.HtmlParser.getNodeList;

public class HtmlParserTest {

	@Test
	public void testGetNodeList() throws Exception {

		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "8087");

		NodeList rt = getNodeList(
				"http://184.154.128.244/htm_data/20/1411/1261982.html",
				new HtmlClassFilter("tpc_content"));

		for (Node node : rt.toNodeArray()) {
			System.out.println(node.toPlainTextString());
		}

	}

	@Test
	public void testGetLinks() throws Exception {
		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "8087");
		getLinks("http://184.154.128.244/htm_data/20/1411/1261982.html",
				".*page.*");

	}

	@Test
	public void test() {
		for (int i = 1; i <= 5; i++) {
			System.out.println(i);
		}
	}

}