plugins {
    `java-library`
    `maven-publish`
    id("net.kyori.indra") version "2.0.6"
    id("net.kyori.indra.checkstyle") version "2.0.6"
}

group = rootProject.group
version = rootProject.version

repositories {
    maven("https://papermc.io/repo/repository/maven-public/") {
        name = "papermc"
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18.1-R0.1-SNAPSHOT")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
