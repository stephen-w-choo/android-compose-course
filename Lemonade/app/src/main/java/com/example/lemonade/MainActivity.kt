package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                LemonadeApp()

            }
        }
    }
}

@Composable
fun LemonButtonAndText(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)
){
    var status by remember { mutableStateOf(0) }

    val lemonImage = when(status) {
        0 -> R.drawable.lemon_tree
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val lemonText: Int = when(status) {
        0 -> R.string.lemon_0
        1 -> R.string.lemon_1
        2 -> R.string.lemon_2
        else -> R.string.lemon_3
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(lemonText))
        Button(onClick = { status = (status + 1) % 4 }) {
            Image(
                painter = painterResource(id = lemonImage),
                contentDescription = stringResource(lemonText)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonButtonAndText()
}