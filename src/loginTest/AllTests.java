package loginTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({LoginPageTesting.class, LoginFailTesting.class, PlaceholderTesting.class, BackButton.class})
public class AllTests {

}
