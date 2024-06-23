plugins {
    id("java")
}

group = "de.evicka"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
val mainClassName = "de.evicka.Main"

tasks.jar {
    manifest {
        attributes["Main-Class"] = mainClassName
    }
}
    }
}