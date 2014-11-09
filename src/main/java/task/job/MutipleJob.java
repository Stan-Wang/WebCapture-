package task.job;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.htmlparser.NodeFilter;

import task.TaskManager;

public class MutipleJob implements Callable<String> {

	private static final long WAITTIME = 10L;

	private static final int STOP_TIME = 500;

	private Logger log = Logger.getLogger(MutipleJob.class);

	private String urlmode;
	private int task_count;
	private NodeFilter filter;
	private TaskManager manager;

	private List<Future<String>> resualt;

	/*
	 * 此列表用来存储结果
	 */
	private String[] tempResualt;

	/*
	 * 此列表存储未完成的任务
	 */
	private LinkedList<Integer> tempUnDoneList;

	private static String mark = "$";

	public MutipleJob(String urlmode, int task_count, NodeFilter filter,
			TaskManager manager) {
		this.urlmode = urlmode;
		this.task_count = task_count;
		this.filter = filter;
		this.manager = manager;

		log.debug("MutipleJob init ==> " + this.toString());
	}

	@Override
	public String call() throws Exception {

		if (task_count == 0) {
			log.error("task_count is 0");
			return "task_count is 0";
		}

		resualt = new LinkedList<Future<String>>();

		tempResualt = new String[task_count];

		tempUnDoneList = new LinkedList<>();

		for (int i = 1; i <= task_count; i++) {
			resualt.add(manager.runTask(new HtmlPaserJob(urlmode.replace(mark,
					String.valueOf(i)), filter)));
			tempUnDoneList.add(i - 1);
		}

		while (true) {

			Iterator<Integer> iterator = tempUnDoneList.iterator();

			while (iterator.hasNext()) {
				int index = iterator.next();
				Future<String> r = resualt.get(index);

				String chapter = null;
				try {
					chapter = r.get(WAITTIME, TimeUnit.SECONDS);
				} catch (TimeoutException e) { // 此处处理超过WAITTIME仍未获取到结果的情况
					// 停止任务
					r.cancel(true);
					// 重新添加任务
					resualt.set(index, manager.runTask(new HtmlPaserJob(urlmode
							.replace(mark, String.valueOf(index)), filter)));
					log.error("task [" + index + "] time out ,run again...");
					continue;
				}

				if (chapter != null) {
					// 放置入结果缓存中
					tempResualt[index] = chapter.replaceAll("&nbsp;", " ");
					// 从待处理列表中删除
					iterator.remove();

					log.info("task [" + index + "] is Done...");
				}

			}

			if (tempUnDoneList.size() == 0) {
				break;
			} else if(!iterator.hasNext()){
				log.error("iterator hasnext is null ==> "+tempUnDoneList.toString());
				continue;
			}else{
				log.error("task left 【" + tempUnDoneList.size()
						+ "】 run again...");
			}
		}

		StringBuilder sb = new StringBuilder();

		for (String s : tempResualt) {
			sb.append(s);
			sb.append("\n");
		}

		return sb.toString();
	}

	@Override
	public String toString() {
		return "MutipleJob :" + urlmode + "_" + task_count + "_"
				+ filter.toString();
	}

}
