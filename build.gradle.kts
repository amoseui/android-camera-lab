// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.spotless)
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    kotlin {
        target("**/*.kt")
        targetExclude("$buildDir/**/*.kt")
        ktlint()
        licenseHeaderFile(rootProject.file("spotless/spotless.license.kt"))
        trimTrailingWhitespace()
        endWithNewline()
    }
    kotlinGradle {
        target("**/*.kts")
        targetExclude("$buildDir/**/*.kts")
        ktlint()
        licenseHeaderFile(rootProject.file("spotless/spotless.license.kt"))
        trimTrailingWhitespace()
        endWithNewline()
    }
    format("xml") {
        target("**/*.xml")
        targetExclude("**/build/**/*.xml")
        licenseHeaderFile(rootProject.file("spotless/spotless.license.xml"), "(<[^!?])")
        trimTrailingWhitespace()
        endWithNewline()
    }
}

true // Needed to make the Suppress annotation work for the plugins block