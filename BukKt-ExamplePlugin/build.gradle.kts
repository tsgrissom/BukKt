val serverPath = "G:/Servers/Minecraft/1.21.5"

plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "io.github.tsgrissom.bukkt.plugin"
version = "1.0.0"

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.20.5-R0.1-SNAPSHOT")
    implementation(project(":BukKt-Library"))

    testImplementation(kotlin("test"))
}

tasks.register("deploy") {
    dependsOn("shadowJar")

    doLast {
        copy {
            from("build/libs")
            into("${serverPath}/plugins")
            include("*-all.jar")
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(22)
}