plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.roomwordsample"
    compileSdk = 34  // Updated to 34 to match dependency requirements

    defaultConfig {
        applicationId = "com.example.roomwordsample"
        minSdk = 24
        targetSdk = 34  // Optionally updated to 34 for consistency
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Existing dependencies
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    // Dependencies for architecture components
    // Room Database Component
    implementation("androidx.room:room-runtime:2.5.2") // Use the latest version here
    annotationProcessor("androidx.room:room-compiler:2.5.2")
    testImplementation("androidx.room:room-testing:2.5.2")

    // Lifecycle Components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.6.1")
}
