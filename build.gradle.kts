plugins {
    kotlin("jvm") version "1.5.31"
}

group = "org.example"
version = "1.0-SNAPSHOT"

object Versions {
    const val akka = "2.6.17"
    const val scala = "2.13"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("com.typesafe.akka:akka-bom_${Versions.scala}:${Versions.akka}"))
    implementation("com.typesafe.akka:akka-actor-typed_${Versions.scala}")
    implementation("ch.qos.logback:logback-classic:1.2.6")
}