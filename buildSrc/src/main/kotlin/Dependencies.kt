object Version {
    const val MIN_SDK = 23
    const val TARGET_SDK = 30
    const val COMPILE_SDK = 30

    const val BUILD_TOOLS = "30.0.3"

    const val KOTLIN = "1.5.21"

    const val KOTLIN_STDLIB = "1.5.0"

    const val COROUTINES = "1.5.0"
    const val ANDROIDX = "1.3.0"
    const val BIOMETRIC = "1.1.0"
    const val ANDROIDX_ANNOTATION = "1.2.0"
    const val HILT = "2.38.1"
    const val LIFECYCLE = "2.3.1"
    const val OKHTTP = "4.9.0"
    const val RETROFIT2 = "2.9.0"
    const val GSON = "2.8.7"
    const val LIFECYCLE_EXTENSIONS = "2.2.0"
    const val KTX = "1.6.0"
    const val ACTIVITY_KTX = "1.3.1"
    const val FRAGMENT_KTX = "1.3.6"
    const val CONSTRAINT = "2.0.4"
    const val MATERIAL = "1.4.0"
    const val TIMBER = "4.7.1"
    const val NAVIGATION = "2.3.5"
    const val GLIDE = "4.12.0"
    const val ROOM = "2.3.0"
    const val CAMERA = "1.0.0"
    const val CAMERA_SCAN = "1.1.0"
    const val CAMERA_VIEW = "1.0.0-alpha24"

    const val COMPOSE = "1.0.2"
    const val ACCOMPANIST = "0.16.1"
    const val COIL = "1.3.0"



    const val FIREBASE = "28.4.1"

    const val LOTTIE = "4.1.0"

    const val SECURITY_CRYPTO = "1.0.0"
    const val KOTLINX_SERIALIZATION_JSON = "1.3.0-RC"
}

object Lib {
    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Version.KOTLIN}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Version.ANDROIDX}"
    const val ANDROIDX_ANNOTATION = "androidx.annotation:annotation:${Version.ANDROIDX_ANNOTATION}"
    const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL}"
    const val CONSTRAINTLAYOUT = "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT}"
    const val CORE_KTX = "androidx.core:core-ktx:${Version.KTX}"
    const val BIOMETRIC = "androidx.biometric:biometric:${Version.BIOMETRIC}"
    const val LIFECYCLE_VIEWMODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFECYCLE}"
    const val LIFECYCLE_RUNTIME_KTX =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LIFECYCLE}"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Version.ACTIVITY_KTX}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Version.FRAGMENT_KTX}"
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Version.HILT}"
    const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Version.HILT}"
    const val TIMBER = "com.jakewharton.timber:timber:${Version.TIMBER}"
    const val KOTLINX_COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINES}"
    const val NAVIGATION_FRAGMENT_KTX =
        "androidx.navigation:navigation-fragment-ktx:${Version.NAVIGATION}"
    const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Version.NAVIGATION}"
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Version.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Version.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Version.ROOM}"
    const val DATASTORE = "androidx.datastore:datastore:1.0.0-rc02"
    const val PROTOBUF_JAVALITE = "com.google.protobuf:protobuf-javalite:3.10.0"
    const val VIEWBINDING_PROPERTY_DELEGATE =
        "com.github.kirich1409:viewbindingpropertydelegate:1.4.7"
    const val VIEWBINDING_PROPERTY_DELEGATE_NO_REFLECTION =
        "com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.4.7"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.RETROFIT2}"
    const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Version.RETROFIT2}"
    const val GSON = "com.google.code.gson:gson:${Version.GSON}"
    const val OKHTTP3_LOGGING_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${Version.OKHTTP}"

    const val THREETENABP = "com.jakewharton.threetenabp:threetenabp:1.3.1"

    const val CAMERA_CORE = "androidx.camera:camera-core:${Version.CAMERA}"
    const val CAMERA_CAMERA2 = "androidx.camera:camera-camera2:${Version.CAMERA}"
    const val CAMERA_LIFECYCLE = "androidx.camera:camera-lifecycle:${Version.CAMERA}"
    const val CAMERA_VIEW = "androidx.camera:camera-view:${Version.CAMERA_VIEW}"
    const val CAMERA_SCAN = "cards.pay:paycardsrecognizer:${Version.CAMERA_SCAN}"

    // compose
    const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:${Version.ACTIVITY_KTX}"

    // Compose Material Design
    const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Version.COMPOSE}"

    // Animations
    const val COMPOSE_ANIMATION = "androidx.compose.animation:animation:${Version.COMPOSE}"

    // Tooling support (Previews, etc.)
    const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:${Version.COMPOSE}"

    // Integration with ViewModels
    const val COMPOSE_LIFECYCLE_VIEWMODEL =
        "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"

    const val COIL =
        "io.coil-kt:coil-compose:${Version.COIL}"


    // UI Tests
    const val COMPOSE_UI_TEST_JUNIT4 = "androidx.compose.ui:ui-test-junit4:${Version.COMPOSE}"
    const val CONSTRAINTLAYOUT_COMPOSE =
        "androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02"

    const val ACCOMPANIST_INSETS =
        "com.google.accompanist:accompanist-insets:${Version.ACCOMPANIST}"
    const val ACCOMPANIST_SYSTEMUICONTROLLER =
        "com.google.accompanist:accompanist-systemuicontroller:${Version.ACCOMPANIST}"
    const val ACCOMPANIST_APPCOMPAT =
        "com.google.accompanist:accompanist-appcompat-theme:${Version.ACCOMPANIST}"
    const val ACCOMPANIST_PAGER = "com.google.accompanist:accompanist-pager:${Version.ACCOMPANIST}"
    const val ACCOMPANIST_PERMISSIONS =
        "com.google.accompanist:accompanist-permissions:${Version.ACCOMPANIST}"
    const val ACCOMPANIST_FLOWLAYOUT =
        "com.google.accompanist:accompanist-flowlayout:${Version.ACCOMPANIST}"
    const val ACCOMPANIST_NAVIGATION_ANIMATION =
        "com.google.accompanist:accompanist-navigation-animation:${Version.ACCOMPANIST}"
    const val ACCOMPANIST_NAVIGATION_MATERIAL =
        "com.google.accompanist:accompanist-navigation-material:${Version.ACCOMPANIST}"
    const val ACCOMPANIST_DRAWABLEPAINTER =
        "com.google.accompanist:accompanist-drawablepainter:${Version.ACCOMPANIST}"
    const val ACCOMPANIST_SWIPEREFRESH =
        "com.google.accompanist:accompanist-swiperefresh:${Version.ACCOMPANIST}"
    const val ACCOMPANIST_PAGER_INDICATOR =
        "com.google.accompanist:accompanist-pager-indicators:${Version.ACCOMPANIST}"

    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Version.FIREBASE}"
    const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics"
    const val FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics"

//    const val VOLLEY = "com.android.volley:volley:1.2.0"

    const val MAPS = "com.google.android.libraries.maps:maps:3.1.0-beta"
    const val MAPS_KTX = "com.google.maps.android:maps-v3-ktx:2.2.0"

    const val TRUTH = "com.google.truth:truth:1.0"
    const val JUNIT = "junit:junit:4.13.1"
    const val TEST_CORE = "androidx.test:core:1.3.0"
    const val MOCKITO_CORE = "org.mockito:mockito-core:2.21.0"
    const val MOCKITO_INLINE = "org.mockito:mockito-inline:2.13.0"

    const val LOTTIE = "com.airbnb.android:lottie:${Version.LOTTIE}"

    const val SECURITY = "androidx.security:security-crypto:${Version.SECURITY_CRYPTO}"
    const val JSON = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.KOTLINX_SERIALIZATION_JSON}"

}