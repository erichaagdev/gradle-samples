plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.7.20"
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("helloPlugin") {
            id = "dev.erichaag.gradle.hello"
            implementationClass = "dev.erichaag.gradle.hello.HelloPlugin"
        }
    }
}
