plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.10"
    id("org.openjfx.javafxplugin") version "0.0.9"
    application
}


repositories { mavenCentral() }

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

application {
    mainClass.set("fr.chalodss.app.Main")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

sourceSets {
    getByName("main") {
        resources.srcDir("src/main/assets")
    }
}

javafx {
    version = "17"
    modules("javafx.controls", "javafx.fxml")
}