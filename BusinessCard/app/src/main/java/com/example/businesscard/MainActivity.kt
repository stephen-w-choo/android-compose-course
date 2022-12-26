package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCard()
            BottomLinks()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//@Composable
//fun AppContainer() {
//    Column(
//        Modifier.fillMaxHeight(),
//        verticalArrangement = Arrangement.SpaceBetween
//    ) {
//
//    }
//}

@Composable
fun BottomLinks() {
    val githubIcon = painterResource(id = R.drawable.github_icon)
    val linkedinIcon = painterResource(id = R.drawable.linkedin_icon)
    Row(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom)
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(bottom = 20.dp)
    ) {
        Image(
            painter = githubIcon,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
        )
        Image(
            painter = linkedinIcon,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
        )
    }
}

@Composable
fun CenterCard() {
    val image = painterResource(id = R.drawable.android_logo)
    Column(
        Modifier
            .background(Color(red = 7, green = 48, blue = 66))
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(top = 200.dp)
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 108.dp)
        )
        Name("Stephen Choo")
        Title("Baby Android Developer")
    }
}
@Composable
fun Name(name: String) {
    Text(
        text = name,
        color = Color.White,
        fontSize = 36.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    )
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        color = Color.Green,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
fun BusinessCard() {
    CenterCard()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusinessCardTheme {
        BusinessCard()
        BottomLinks()
    }
}