import me.nghialt.helloWorld
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MainKtTest {

    @Test
    fun helloWorld_test() {
        assertEquals("Hello World!", helloWorld())
    }
}