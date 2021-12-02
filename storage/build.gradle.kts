import com.google.protobuf.gradle.*

plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KAPT)
    id("com.google.protobuf").version("0.8.12")
}

android {
    compileSdk = Version.COMPILE_SDK

    defaultConfig {
        minSdk = Version.MIN_SDK
        targetSdk = Version.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName(BuildTypes.DEBUG) {

        }
        getByName(BuildTypes.RELEASE) {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // To avoid the compile error: "Cannot inline bytecode built with JVM target 1.8
    // into bytecode that is being built with JVM target 1.6"
    kotlinOptions {
        val options = this as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
        options.jvmTarget = "1.8"
    }
}

dependencies {

    implementation(Lib.KOTLIN_STDLIB)

    implementation(Lib.TIMBER)
    implementation(Lib.THREETENABP)

    implementation(Lib.HILT_ANDROID)

    implementation(Lib.ROOM_RUNTIME)
    implementation(Lib.ROOM_KTX)
    implementation(Lib.GSON)
    kapt(Lib.ROOM_COMPILER)

    implementation(Lib.DATASTORE)
    implementation(Lib.PROTOBUF_JAVALITE)

    implementation(project(Modules.DATASOURCES))
    implementation(project(Modules.DOMAIN_MODELS))
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.10.0"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}