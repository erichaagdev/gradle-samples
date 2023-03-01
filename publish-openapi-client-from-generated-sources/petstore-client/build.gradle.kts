plugins {
  id("java-library")
  id("maven-publish")
  id("org.openapi.generator") version "6.4.0"
}

repositories {
  mavenCentral()
}

// These are the dependencies required by the generated sources.
dependencies {
  api("com.google.code.gson:gson:2.10.1")
  api("com.squareup.okhttp3:logging-interceptor:4.10.0")
  api("com.squareup.okhttp3:okhttp:4.10.0")
  api("io.gsonfire:gson-fire:1.8.5")
  api("jakarta.annotation:jakarta.annotation-api:2.1.1")
  api("jakarta.ws.rs:jakarta.ws.rs-api:3.1.0")
  api("org.apache.oltu.oauth2:org.apache.oltu.oauth2.client:1.0.2")
}

openApiGenerate {
  generatorName.set("java")

  // Set the input specification to the petstore.yaml file located in the project directory.
  inputSpec.set(layout.projectDirectory.file("petstore.yaml").asFile.absolutePath)

  // Set the generated output to build/generated/openapi.
  outputDir.set(layout.buildDirectory.file("generated/openapi").map { it.asFile.absolutePath })

  // Set the ignore file to the .openapi-generator-ignore file located in the project directory.
  ignoreFileOverride.set(layout.projectDirectory.file(".openapi-generator-ignore").asFile.absolutePath)

  // Additional generator specific configuration options.
  // See: https://github.com/OpenAPITools/openapi-generator/blob/v6.4.0/docs/generators/java.md
  //
  // NOTE: It is very important to set the sourceFolder to an empty string. This will generate the sources with the
  //       package hierarchy starting at the root of build/generated/openapi. The hideGenerationTimestamp property
  //       should always be set to true as generating timestamps in source files will create volatile inputs making the
  //       build not reproducible and ultimately breaking cacheability.
  configOptions.set(
    mapOf(
      "hideGenerationTimestamp" to "true",
      "sourceFolder" to "",
      "useJakartaEe" to "true",
    )
  )
}

// This adds the generated sources to the main Java source set. This means the classes will be included in artifacts
// built by this project, just as if they had existed in this project's src/main/java directory.
sourceSets {
  main {
    java {
      srcDir(tasks.openApiGenerate)
    }
  }
}

// Configure artifact publishing as you normally would.
publishing {
  publications {
    register<MavenPublication>("maven") {
      groupId = "dev.erichaag"
      artifactId = "petstore-client"
      version = "1.2.3"

      from(components["java"])
    }
  }
}
