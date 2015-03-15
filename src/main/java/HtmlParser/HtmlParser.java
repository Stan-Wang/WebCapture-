package HtmlParser;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.filters.LinkRegexFilter;
import org.htmlparser.util.ParserException;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;

/**
 * Description:
 * <p/>
 * Created by STAN_WANG on 14-10-30.
 */
public class HtmlParser {

	public static final String DEFAULT_ENCODE = "gb2312";
	
//	private Logger log = Logger.getLogger(HtmlParser.class);

	public static NodeList getNodeList(String url, NodeFilter filter)
			throws ParserException {
		Parser parser;
		parser = new Parser(url);
		parser.setEncoding(DEFAULT_ENCODE);
		return parser.extractAllNodesThatMatch(filter);
	}

	public static void getLinks(String url, String regex)
			throws ParserException {
		Parser parser;
		parser = new Parser(url);
		parser.setEncoding(DEFAULT_ENCODE);
		NodeList list = parser.parse(new LinkRegexFilter(regex));
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			Node node = list.elementAt(i);
			System.out.println("link is :" + node.getText());
		}
	}

	public static String getDefaultEncode() {
		return DEFAULT_ENCODE;
	}

}
