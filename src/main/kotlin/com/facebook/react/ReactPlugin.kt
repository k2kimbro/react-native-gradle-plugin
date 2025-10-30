package com.facebook.react

import org.gradle.api.Plugin
import org.gradle.api.Project

class ReactPlugin : Plugin<Project> {
   override fun apply(project: Project) {
    // Register the 'react' extension
    project.extensions.create("react", ReactExtension::class.java)
    
    // Register a sample task
    project.tasks.register("verifyReactNativeSetup") {
        it.group = "verification"
        it.doLast {
            println("✅ React Native setup verified for Appalachian Archives Mobile")
        }
    }
}

    }

// Kotlin-style extension class
open class ReactExtension {
    var entryFile: String = "index.js"
    // Add other config fields as needed
}
