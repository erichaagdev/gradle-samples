plugins {
    id("dev.erichaag.kotlin")
    `kotlin-dsl`
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
