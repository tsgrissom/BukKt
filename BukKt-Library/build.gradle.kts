plugins {
    kotlin("jvm")
}

group = "io.github.tsgrissom.bukkt.library"
version = "1.0.0"

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.20.5-R0.1-SNAPSHOT")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(22)
}