package com.example.amphibiansapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.amphibiansapp.R
import com.example.amphibiansapp.ui.AmphibianUiState
import com.example.amphibiansapp.ui.AmphibiansViewModel

@Composable
fun IndexScreen(
    amphibiansViewModel: AmphibiansViewModel,
    modifier: Modifier = Modifier
) {
    when (amphibiansViewModel.amphibianUiState) {
//        is AmphibianUiState.Loading -> LoadingScreen(modifier = modifier)
        else -> LoadingScreen(modifier = modifier)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(stringResource(id = R.string.loading))
    }
}