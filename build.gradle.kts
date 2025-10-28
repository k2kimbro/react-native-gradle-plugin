import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

import org.gradle.api.internal.classpath.ModuleRegistry
import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
  kotlin("jvm") version "1.9.10"
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
  }

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
        allWarningsAsErrors.set(true)
        apiVersion.set(KotlinVersion.KOTLIN_1_8)
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

repositories {
  google()
  mavenCentral()
  gradlePluginPortal()
}

gradlePlugin {
  plugins {
    create("react") {
      id = "com.facebook.react"
      implementationClass = "com.facebook.react.ReactPlugin"
    }
    create("reactRootProject") {
      id = "com.facebook.react.rootproject"
      implementationClass = "com.facebook.react.ReactRootProjectPlugin"
    }
    create("reactPlugin") {
      id = "com.facebook.react.gradle.plugin"
      implementationClass = "com.facebook.react.ReactGradlePlugin"
    }
  }
}

group = "com.github.k2kimbro"
version = "1.0.0"

dependencies {
  implementation(gradleApi())
  implementation(localGroovy())

  // The KGP/AGP version is defined by React Native Gradle plugin.
  // Therefore we specify an implementation dep rather than a compileOnly.
}

// We intentionally don't build for Java 17 as users will see a cryptic bytecode version
// error first. Instead we produce a Java 11-compatible Gradle Plugin, so that AGP can print their
// nice message showing that JDK 11 (or 17) is required first
java { targetCompatibility = JavaVersion.VERSION_11 }

tasks.withType<Test>().configureEach {
  testLogging {
    exceptionFormat = TestExceptionFormat.FULL
    showExceptions = true
    showCauses = true
    showStackTraces = true
  }
}

sourceSets {
    main {
        java.srcDirs("src/main/kotlin")
    }
}

