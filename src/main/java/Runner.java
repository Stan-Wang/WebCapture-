import HtmlParser.HtmlClassFilter;
import task.TaskManager;
import task.job.HtmlPaserJob;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *   Description:
 *
 *   Created by STAN_WANG on 14-10-28.
 */
public class Runner {

    public static void main(String args[]){

        System.out.println("WebCapture Staring ...\n");


        if(args.length > 0){
            List<String> list= Arrays.asList(args);

            if(list.contains("-p")){
                setProxy();
            }
        }

        TaskManager taskManager = new TaskManager();

        Future<String> res = taskManager.runTask(new HtmlPaserJob("http://184.154.128.244/htm_data/20/1411/1261982.html",new HtmlClassFilter("tpc_content")));

        try {
            System.out.println(res.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        System.out.println("\nWebCapture Ending ...");
    }

    public static void setProxy(){
        System.out.println("开启代理模式 ==> 代理地址[127.0.0.1:8087]");
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8087");
    }
}
