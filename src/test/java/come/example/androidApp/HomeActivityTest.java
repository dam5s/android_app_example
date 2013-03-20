package come.example.androidApp;

/**
 * Created with IntelliJ IDEA.
 * User: pivotal
 * Date: 3/20/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */

import com.example.androidApp.HomeActivity;
import com.example.androidApp.R;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.api.Assertions.assertThat;


@RunWith(RobolectricTestRunner.class)
public class HomeActivityTest {

    @Test
    public void shouldHaveProperAppName() throws Exception {
        String appName = new HomeActivity().getResources().getString(R.string.app_name);
        assertThat(appName).isEqualTo("AndroidApp");
    }
}