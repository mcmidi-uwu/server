plugins {
    `java-library`
    id("net.kyori.indra") version "2.2.0"
    id("net.kyori.indra.checkstyle") version "2.1.1"
    id("net.kyori.indra.publishing") version "2.1.1"
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

indra {
    javaVersions {
        target(17)
    }

    github("mcmidi-uwu", "server")

    mitLicense()

    publishReleasesTo("thbn", "https://repo.thbn.me/releases")
    publishSnapshotsTo("thbn", "https://repo.thbn.me/snapshots")

    configurePublications {
        pom {
            developers {
                developer {
                    id.set("TehBrian")
                    url.set("https://tehbrian.xyz")
                    email.set("tehbrian@proton.me")
                }
            }
        }
    }
}
