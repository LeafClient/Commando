plugins {
    java
    `maven-publish`
}

group = "com.leafclient"
version = "1.0.0"
description = "A command library designed for Minecraft clients"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    compile("com.github.LeafClient", "Struct", "1.03")
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

artifacts {
    archives(sourcesJar)
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
        }
    }
}