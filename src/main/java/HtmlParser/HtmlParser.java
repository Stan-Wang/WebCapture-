package HtmlParser;

import org.htmlparser.NodeFilter;
import org.htmlparser.util.ParserException;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
/**
 * Description:
 * <p/>
 * Created by STAN_WANG on 14-10-30.
 */
public class HtmlParser {

    public static NodeList getNodeList(String url , NodeFilter filter) throws ParserException {
        Parser parser;
        try {
            parser = new Parser(url);
            parser.setEncoding("UTF-8");
            return parser.extractAllNodesThatMatch(filter);
        } catch (ParserException e) {
            throw e;
        }
    }
}
