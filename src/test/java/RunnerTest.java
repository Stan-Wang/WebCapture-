import org.junit.Test;

public class RunnerTest {

    @Test
    public void testMain() throws Exception {
        Runner.main(new String[]{"-p","http://184.154.128.244/read.php?tid=138296&page=$","14"});
    }

}