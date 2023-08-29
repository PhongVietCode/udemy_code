import org.bouncycastle.asn1.iana.IANAObjectIdentifiers.experimental
import org.gradle.internal.impldep.org.bouncycastle.asn1.iana.IANAObjectIdentifiers
import org.gradle.internal.impldep.org.bouncycastle.asn1.iana.IANAObjectIdentifiers.experimental

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.kotlin_learn"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kotlin_learn"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures{
        //noinspection DataBindingWithoutKapt
        dataBinding = true
        viewBinding = true
        compose = true

    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
}

val kotlin_version = "1.7.20"
val lifecycle_version = "2.6.1"
val room_version = "2.4.0"
val nav_version = "2.7.1"
val coroutine_version = "1.3.9"
dependencies {
    implementation("androidx.compose.runtime:runtime:1.5.0")
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version") //
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")//
    // Annotation processor
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")//

    //ROOM-database
    implementation ("androidx.room:room-ktx:$room_version")//
    implementation("androidx.room:room-runtime:$room_version")//
    annotationProcessor("androidx.room:room-compiler:$room_version")///
    kapt("androidx.room:room-compiler:$room_version")//
    androidTestImplementation ("androidx.room:room-testing:$room_version")

    // coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")//
    implementation( "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")//

    // navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // okHttp
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.2.2"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-storage-ktx:20.2.1")
    implementation("com.google.firebase:firebase-firestore-ktx:24.7.1")
    implementation ("com.google.firebase:firebase-database-ktx:20.2.2")
    //kotlin
    implementation("androidx.core:core-ktx:$kotlin_version")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutine_version")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine_version")
    // Lifecycle components
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}