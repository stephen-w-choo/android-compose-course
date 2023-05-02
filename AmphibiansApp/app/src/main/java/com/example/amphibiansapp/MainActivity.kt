package com.example.amphibiansapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibiansapp.ui.AmphibiansView
import com.example.amphibiansapp.ui.AmphibiansViewModel
import com.example.amphibiansapp.ui.theme.AmphibiansAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmphibiansAppTheme {
                // A surface container using the 'background' color from the theme
                val amphibiansViewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)
                AmphibiansView(amphibiansViewModel)
            }
        }
    }
}
