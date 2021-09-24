plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.21"
    id("org.jetbrains.kotlin.kapt") version "1.5.21"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("io.micronaut.application") version "2.0.4"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.5.21"
}

version = "0.1"
group = "com.marcoscouto"

val kotlinVersion=project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.marcoscouto.*")
    }
}

dependencies {
    kapt("io.micronaut:micronaut-http-validation")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.aws:micronaut-aws-sdk-v2")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("javax.annotation:javax.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

    // DynamoDB
    implementation("software.amazon.awssdk:dynamodb")
    implementation("software.amazon.awssdk:dynamodb-enhanced")

}

application {
    mainClass.set("com.marcoscouto.ApplicationKt")
}

java {
    sourceCompatibility = JavaVersion.toVersion("11")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}
