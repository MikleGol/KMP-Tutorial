plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    val iosX64 = iosX64()
    val iosArm64 = iosArm64()
    val iosSimulatorArm64 = iosSimulatorArm64()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val androidMain by getting{
            dependencies{
                implementation(libs.androidx.lifecycle.viewmodel.ktx)
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
            iosX64.compilations["main"].defaultSourceSet.dependsOn(this)
            iosArm64.compilations["main"].defaultSourceSet.dependsOn(this)
            iosSimulatorArm64.compilations["main"].defaultSourceSet.dependsOn(this)

            dependencies {
                // ваші iOS залежності
            }
        }
    }
}

android {
    namespace = "liber.app.kmptutorial"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
