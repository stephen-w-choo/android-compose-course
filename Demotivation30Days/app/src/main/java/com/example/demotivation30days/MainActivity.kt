package com.example.demotivation30days

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demotivation30days.model.Demotivational
import com.example.demotivation30days.model.DemotivationalRepo
import com.example.demotivation30days.ui.theme.Demotivation30DaysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Demotivation30DaysTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DemotivationalApp()
                }
            }
        }
    }
}
@Composable
fun DemotivationalColumn(
    demotivationals: List<Demotivational>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .padding(12.dp)
    ) {
        items(demotivationals) {
            DemotivationalPoster(demotivational = it, modifier = modifier)
        }
    }
}

@Composable
fun DemotivationalPoster(
    demotivational: Demotivational,
    modifier: Modifier = Modifier,
    ) {
        Card(
            elevation = 4.dp,
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .padding(8.dp)
            ) {
                Card(
                    modifier = modifier
                        .padding(2.dp)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colors.onSurface,
                        )
                        .padding(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = demotivational.image),
                        contentDescription = stringResource(id = demotivational.title),
                        contentScale = ContentScale.FillWidth,
                        modifier = modifier
                            .width(200.dp)
                    )
                }
                Text(
                    text = stringResource(id = demotivational.title),
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = stringResource(id = demotivational.tagline),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
}

@Composable
fun DemotivationalTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DemotivationalApp(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            DemotivationalTopBar()
        },
        modifier = modifier) {
        DemotivationalColumn(demotivationals = DemotivationalRepo.demotivationals)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Demotivation30DaysTheme {
        DemotivationalApp()
    }
}