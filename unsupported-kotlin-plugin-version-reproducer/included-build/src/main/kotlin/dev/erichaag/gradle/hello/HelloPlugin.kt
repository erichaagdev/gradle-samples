package dev.erichaag.gradle.hello

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.register

class HelloPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register<HelloTask>("hello")
    }
}

abstract class HelloTask : DefaultTask() {
    @TaskAction
    fun action() {
        println("Hello, world!")
    }
}
