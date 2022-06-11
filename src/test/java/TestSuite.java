import banking.domain.AccountTest;
import banking.domain.CustomerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccountTest.class,
        CustomerTest.class
})
public class TestSuite {
}
