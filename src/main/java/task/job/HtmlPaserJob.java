package task.job;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.util.NodeList;

import java.util.concurrent.Callable;

import static HtmlParser.HtmlParser.getNodeList;

/**
 * 此Task处理单个Url指定filter的内容
 * <p/>
 * PACKAGE_NAME： task.job
 * 作者：  Satan
 * 创建时间： 14-10-30.
 */
public class HtmlPaserJob implements Callable<String> {

    private String url;
    private NodeFilter filter;

    public HtmlPaserJob(String url, NodeFilter filter) {
        this.url = url;
        this.filter = filter;
    }


    @Override
    public String call() throws Exception {

        StringBuilder sb = new StringBuilder();

        NodeList rt = getNodeList(url,filter);

        for (Node node : rt.toNodeArray()) {
            sb.append(node.toPlainTextString());
            sb.append("\n");
        }

        return sb.toString();
    }

}
