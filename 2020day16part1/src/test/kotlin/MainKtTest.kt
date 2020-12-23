import main.kotlin.helloWorld
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MainKtTest {

    @Test
    fun helloWorld_test() {
        assertEquals("Sub Hello World!", helloWorld())
    }
}