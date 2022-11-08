import de.undercouch.gradle.tasks.download.Download

plugins {
  base
  id("de.undercouch.download") version "5.3.0"
  id("org.openapi.generator") version "6.2.0"
}

val openApiDownloadDestination = layout.buildDirectory.file("openapi/spec/petstore.yaml")
val openApiDownload by tasks.registering(Download::class) {
  src("https://raw.githubusercontent.com/OAI/OpenAPI-Specification/3.0.3/examples/v3.0/petstore.yaml")
  dest(openApiDownloadDestination)
  onlyIfModified(true)
}

openApiGenerate {
  generatorName.set("java")
  inputSpec.set(openApiDownloadDestination.map { it.toString() })
  outputDir.set(layout.buildDirectory.dir("generated/openapi").map { it.toString() })
}

tasks.named("openApiGenerate").configure {
  dependsOn(openApiDownload)
}
