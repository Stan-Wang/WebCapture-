import org.junit.Test;

public class RunnerTest {

    @Test
    public void testMain() throws Exception {
        Runner.main(new String[]{"proxy","url","get"});
    }

}