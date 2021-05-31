import androidx.test.runner.AndroidJUnit4
import com.shahin.meistersearch.di.networkModule
import com.shahin.meistersearch.di.repositoryModule
import com.shahin.meistersearch.di.roomModule
import com.shahin.meistersearch.di.uiModule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.check.checkModules

@RunWith(AndroidJUnit4::class)
class TestKoinModules {
    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun checkModules() {
        checkModules {
            modules(
                uiModule,
                repositoryModule,
                networkModule,
                roomModule
            )
        }
    }
}