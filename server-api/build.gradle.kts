plugins {
    `java-library`
    `maven-publish`
    id("org.checkerframework") version "0.6.7"
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
