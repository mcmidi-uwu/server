plugins {
    id("java")
    id("org.checkerframework") version "0.6.15"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = rootProject.group
version = rootProject.version

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/") {
        name = "papermc"
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18.1-R0.1-SNAPSHOT")

    implementation(project(":server-api"))
    implementation("com.google.inject:guice:5.1.0")
    implementation("com.sparkjava:spark-core:2.9.4")
    implementation("com.squareup.moshi:moshi:1.14.0")
}

tasks {
    processResources {
        expand("version" to project.version)
    }
}
