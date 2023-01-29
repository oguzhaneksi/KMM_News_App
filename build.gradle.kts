plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.4.0").apply(false)
    id("com.android.library").version("7.4.0").apply(false)
    kotlin("android").version("1.7.20").apply(false)
    kotlin("multiplatform").version("1.7.20").apply(false)
    kotlin("plugin.serialization").version("1.7.20")
    id("com.squareup.sqldelight").version("1.5.4").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
