package HtmlParser;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 * <p/>
 * Created by STAN_WANG on 14-10-30.
 */
public class HtmlClassFilter implements NodeFilter {
	
	private static final long serialVersionUID = 1498619952179148590L;
	
	private String class_name = "";

    public HtmlClassFilter(String class_name) {
        this.class_name = class_name;
    }

    private  boolean getStringsByRegex(String txt) {
        String regex="div class=\""+class_name+"\"";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(txt);
        return m.find();
    }


    @Override
    public boolean accept(Node node) {
        return getStringsByRegex(node.getText());
    }
    
    @Override
	public String toString(){
		return "HtmlClassFilter 【" +class_name+"】";
	}
}
