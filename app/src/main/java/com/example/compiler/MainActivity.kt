package com.example.compiler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import com.example.compiler.ui.screens.MainScreen
import com.example.compiler.ui.theme.YourAppTheme
import com.example.compiler.viewmodel.CompilerViewModel
import com.example.compiler.data.repository.CompilerRepository
import com.example.compiler.data.network.CompilerApiService
import com.google.firebase.analytics.FirebaseAnalytics
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase Analytics
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        // Log an event when the app opens
        logAppOpenEvent()

        // 🔥 Log a custom test event to verify Firebase
        logTestEvent()

        setContent {
            // ✅ Retrofit setup
            val apiService = Retrofit.Builder()
                .baseUrl("https://ast-generator-production.up.railway.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CompilerApiService::class.java)

            // ✅ Repository + ViewModel
            val repository = remember { CompilerRepository(apiService) }
            val viewModel = remember { CompilerViewModel(repository) }

            // ✅ UI Theme + MainScreen
            YourAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainScreen(viewModel = viewModel)
                }
            }
        }
    }

    private fun logAppOpenEvent() {
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Param.METHOD, "manual")
        }
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle)
    }

    // ✅ New method to log a custom test event
    private fun logTestEvent() {
        val testBundle = Bundle().apply {
            putString("language", "java")
            putString("screen", "MainActivity")
        }
        firebaseAnalytics.logEvent("test_compile_event", testBundle)
    }
}
