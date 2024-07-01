plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.dagger.hilt.android") version "2.51.1"
    id("androidx.navigation.safeargs") version "2.5.3"
    id("org.jetbrains.kotlin.kapt")

}

android {
    namespace = "com.example.seasalon"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.seasalon"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures{
        viewBinding = true
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // Room and Lifecycle dependencies
    //noinspection UseTomlInstead
    implementation ("androidx.room:room-runtime:2.6.1")
    //noinspection UseTomlInstead
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
    //noinspection UseTomlInstead
    implementation("androidx.test:core-ktx:1.5.0")
    //noinspection KaptUsageInsteadOfKsp,UseTomlInstead
    kapt ("androidx.room:room-compiler:2.6.1")
    //noinspection UseTomlInstead
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    //noinspection UseTomlInstead
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    // Kotlin Extensions and Coroutines support for Room
    //noinspection UseTomlInstead
    implementation ("androidx.room:room-ktx:2.6.1")

    // Glide
    //noinspection UseTomlInstead
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    implementation ("com.google.dagger:hilt-android:2.51.1")
    //noinspection UseTomlInstead
    kapt ("com.google.dagger:hilt-compiler:2.51.1")
    //noinspection UseTomlInstead
    kapt ("androidx.hilt:hilt-compiler:1.0.0")

    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    //noinspection UseTomlInstead
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    // Navigation
    //noinspection UseTomlInstead
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    //noinspection UseTomlInstead
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // Coroutines
    //noinspection UseTomlInstead
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    //noinspection UseTomlInstead
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
}