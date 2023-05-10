package com.example.amphibiansapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibiansapp.R
import com.example.amphibiansapp.network.AmphibianData
import com.example.amphibiansapp.network.DefaultAppContainer
import com.example.amphibiansapp.ui.AmphibianUiState
import com.example.amphibiansapp.ui.AmphibiansViewModel

@Composable
fun IndexScreen(
    amphibiansViewModel: AmphibiansViewModel,
    modifier: Modifier = Modifier
) {
    when (amphibiansViewModel.amphibianUiState) {
        is AmphibianUiState.Loading -> LoadingScreen(modifier = modifier)
        is AmphibianUiState.Success -> SuccessScreen(
            amphibiansUiState = amphibiansViewModel.amphibianUiState as AmphibianUiState.Success,
            modifier = modifier
        )
        is AmphibianUiState.Error -> ErrorScreen(modifier = modifier)
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
@Composable
fun SuccessScreen(
    amphibiansUiState: AmphibianUiState.Success,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        DataList(amphibiansData = amphibiansUiState.amphibiansData)
    }
}
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(stringResource(id = R.string.error))
    }
}


@Composable
fun AmphibianDataCard(
    amphibianData: AmphibianData,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Text(
            text = "${amphibianData.name} (${amphibianData.type})",
            modifier = modifier
                .padding(12.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )
        Text(
            text = amphibianData.description,
            modifier = modifier
                .padding(12.dp)
                .fillMaxWidth(),
            fontSize = MaterialTheme.typography.bodySmall.fontSize
        )
        Box(
            modifier = modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            // log imgsrc
            Log.d("AmphibianDataCard", "imgSrc: ${amphibianData.imgSrc}")
            AsyncImage(model = ImageRequest.Builder(context = LocalContext.current)
                .data(amphibianData.imgSrc)
                .build(),
                contentDescription = "${amphibianData.name} image",
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.Center,
                modifier = modifier
                    .padding(12.dp)
                // center image
            )
        }
    }
}

// Composable to display Amphibian Data Card in a lazy vertical list
@Composable
fun DataList(
    amphibiansData: List<AmphibianData>,
    modifier: Modifier = Modifier
) {
    // lazy list
    LazyColumn(content = {
        items(amphibiansData) { amphibianData ->
            AmphibianDataCard(
                amphibianData = amphibianData,
                modifier = modifier
            )
        }
    })
}
