import org.junit.Test;

public class RunnerTest {

    @Test
    public void testMain() throws Exception {
        Runner.main(new String[]{"http://184.154.128.244/read.php?tid=1382062&page=$","88"});
    }

}