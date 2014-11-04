package task;

import java.util.concurrent.*;

import org.apache.log4j.Logger;

/**
 *   Description:
 *
 *   Created by STAN_WANG on 14-10-28.
 */
public class TaskManager {

    public static final int MAX_THREAD_COUNT = 5;
    
    private ExecutorService executorService;
    
    private Logger log = Logger.getLogger(TaskManager.class);

    public TaskManager(){
        executorService = Executors.newFixedThreadPool(MAX_THREAD_COUNT);
        log.debug("TaskManager init ok ... size:" + MAX_THREAD_COUNT);
    }

    public Future<String> runTask(Callable<String> runnable){
        return executorService.submit(runnable);
    }

}
