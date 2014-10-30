package task;

import java.util.concurrent.*;

/**
 *   Description:
 *
 *   Created by STAN_WANG on 14-10-28.
 */
public class TaskManager {

    public static final int MAX_THREAD_COUNT = 5;
    private ExecutorService executorService;

    public TaskManager(){
        executorService = Executors.newFixedThreadPool(MAX_THREAD_COUNT);
    }

    public void runTask(Runnable runnable){
        executorService.submit(runnable);
    }

}
