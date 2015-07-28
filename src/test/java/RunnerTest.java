import org.junit.Test;

import excuter.Runner;

public class RunnerTest {

    @Test
    public void testMain() throws Exception {
        Runner.main(new String[]{"http://110.110.110.110&page=$","88"});
    }

}