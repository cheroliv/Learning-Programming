package testplatformer

class App {
    companion object {
        const val HELLO_WORLD = "Hello World!"
    }

    val greeting get() = HELLO_WORLD
}

fun main() = println(App().greeting)
