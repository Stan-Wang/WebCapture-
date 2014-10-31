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

    public static final String DEFAULT_ENCODE = "GB2312";

    public static NodeList getNodeList(String url , NodeFilter filter) throws ParserException {
        Parser parser;
        parser = Parser.createParser(url,DEFAULT_ENCODE);
        return parser.extractAllNodesThatMatch(filter);
    }
}
