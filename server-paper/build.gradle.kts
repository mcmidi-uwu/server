plugins {
    id("java")
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "xyz.tehbrian.mcmidi"
version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/") {
        name = "papermc"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
    maven("https://oss.sonatype.org/content/repositories/snapshots/") {
        name = "sonatype-snapshots"
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")

    implementation(project(":server-api"))
    implementation("com.google.inject:guice:5.0.1")
    implementation("com.sparkjava:spark-core:2.9.3")
    implementation("com.squareup.moshi:moshi:1.13.0")
}

tasks {
    processResources {
        expand("version" to project.version)
    }
}
