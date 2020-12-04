import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    id("com.github.johnrengelman.shadow") version "5.2.0"
    kotlin("jvm") version "1.4.20"
    application
}



group = "me.pedro"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("commons-io:commons-io:2.8.0")
    implementation("com.google.code.gson:gson:2.7")
    implementation("org.simplejavamail:simple-java-mail:6.4.4")
    testImplementation(kotlin("test-junit"))
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "me.pedro.mastermail.mainKt"
    }
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "me.pedro.mastermail.MainKt"
}