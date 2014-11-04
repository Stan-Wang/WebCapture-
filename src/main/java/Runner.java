import HtmlParser.HtmlClassFilter;
import task.TaskManager;
import task.job.HtmlPaserJob;
import task.job.MutipleJob;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Description:
 *
 * Created by STAN_WANG on 14-10-28.
 */
public class Runner {

	public static void main(String args[]) {
		
		String url = null;
		int count = 0;

		System.out.println("WebCapture Staring ...\n");

		if (args.length > 0) {
			List<String> list = Arrays.asList(args);

			
			for(String s : list){
				
				if(s.equals("-p")){
					setProxy();
				}
				
				if(s.startsWith("http")){
					url = s;
				}
				
				if(isNumeric(s)){
					count = Integer.valueOf(s);
				}
				
			}
			
			
		}
		
		if(url == null || count == 0){
			System.out.println("url or count is null");
			return;
		}

		TaskManager taskManager = new TaskManager();

		// Future<String> res = taskManager.runTask(new HtmlPaserJob(
		// "http://184.154.128.244/read.php?tid=1261982&page=1",
		// new HtmlClassFilter("tpc_content")));

		MutipleJob job = new MutipleJob(url, count,
				new HtmlClassFilter("tpc_content"), taskManager);

		Future<String> res = taskManager.runTask(job);

		try {
			 BufferedWriter writer = new BufferedWriter(new FileWriter(new File(url.hashCode()+".txt")));
			 writer.write(res.get());
		     writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nWebCapture Ending ...");
	}

	public static void setProxy() {
		System.out.println("开启代理模式 ==> 代理地址[127.0.0.1:8087]");
		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "8087");
	}

	public static boolean isNumeric(String str) {
		if (str.matches("\\d*")) {
			return true;
		} else {
			return false;
		}
	}
}
