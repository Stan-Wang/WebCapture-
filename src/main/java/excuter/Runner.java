package excuter;

import HtmlParser.HtmlClassFilter;
import task.TaskManager;
import task.job.MutipleJob;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

/**
 * Description:
 *
 * Created by STAN_WANG on 14-10-28.
 */
public class Runner {

	private static final String NUMFLAG = "\\d*";
	
	static Logger log = Logger.getLogger(Runner.class);

	public static void main(String args[]) {

		String url = null;
		int count = 0;

		log.info("WebCapture Staring ...\n");

		if (args.length > 0) {
			List<String> list = Arrays.asList(args);

			for (String s : list) {

				if (s.equals("-p")) {
					setProxy();
				}

				if (s.startsWith("http")) {
					url = s;
					log.debug("Task URL :==> " + s);
				}

				if (isNumeric(s)) {
					count = Integer.valueOf(s);
					log.debug("Task count :==> " + s);
				}

			}

		}

		if (url == null || count == 0) {
			log.error("url or count is null");
			return;
		}

		TaskManager taskManager = new TaskManager();

		MutipleJob job = new MutipleJob(url, count, new HtmlClassFilter("tpc_content"), taskManager);

		Future<String> res = taskManager.runTask(job);

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(url.hashCode() + ".txt")));
			writer.write(res.get());
			writer.close();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}

		log.info("\nWebCapture Ending ...");
	}

	public static void setProxy() {
		log.info("开启代理模式 ==> 代理地址[127.0.0.1:8087]");
		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "8087");
	}

	public static boolean isNumeric(String str) {
		return str.matches(NUMFLAG);
	}
}
