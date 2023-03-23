package testplatformer

import testplatformer.App.Companion.HELLO_WORLD
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.lang.System.out
import java.lang.System.setOut
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AppTest {
    @Test
    fun appHasAGreeting() {
        val classUnderTest = App()
        assertNotNull(classUnderTest.greeting, "app should have a greeting")
    }

    @Test
    fun `main() function should display helloworld`() {
        val standardOut: PrintStream? = out
        val outputStreamCaptor = ByteArrayOutputStream()

        //c'est normal si helloworld n'apparait pas,
        // on a capturé la sortie standard!
        setOut(PrintStream(outputStreamCaptor))
        main()
        assertEquals(
            HELLO_WORLD, outputStreamCaptor
                .toString()
                .trim()
        )
        setOut(standardOut)
    }
}
