package task.job;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.htmlparser.NodeFilter;

import task.TaskManager;

public class MutipleJob implements Callable<String> {
	
	private static final int STOP_TIME = 500;

	private Logger log = Logger.getLogger(MutipleJob.class);

	private String urlmode;
	private int task_count;
	private NodeFilter filter;
	private TaskManager manager;

	private List<Future<String>> resualt;

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

		for (int i = 1; i <= task_count; i++) {
			resualt.add(manager.runTask(new HtmlPaserJob(urlmode.replace(mark,
					String.valueOf(i)), filter)));
		}
		
		StringBuilder  sb = new StringBuilder();
		
		
		for(int i = 0; i<resualt.size();i++){
			Future<String> r = resualt.get(i);
			boolean flag = true;
			while(flag){
				if(r.isDone()){
					sb.append(r.get());
					sb.append("\n");
					System.out.println(i+" is Done");
					flag = false;
				}else{
					Thread.sleep(STOP_TIME);
				}
			}
		}

		return sb.toString();
	}
	
	@Override
	public String toString(){
		return "MutipleJob :"+urlmode + "_" +task_count +"_"+filter.toString();
	}

}
