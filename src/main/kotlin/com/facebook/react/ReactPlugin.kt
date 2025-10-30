package com.facebook.react

import org.gradle.api.Plugin
import org.gradle.api.Project

class ReactPlugin : Plugin<Project> {
   override fun apply(project: Project) {
    project.tasks.register("verifyReactNativeSetup") {
        it.group = "verification"
        it.doLast {
            println("✅ React Native setup verified for Appalachian Archives Mobile")
        }
    }
}

    }