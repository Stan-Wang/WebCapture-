/**
 *   Description:
 *
 *   Created by STAN_WANG on 14-10-28.
 */
public class Runner {

    public static void main(String args[]){

        System.out.println("WebCapture Staring ...");

        for(String order : args){
            System.out.println("operation ==> " + order);
        }

        System.out.println("WebCapture Ending ...");
    }
}
