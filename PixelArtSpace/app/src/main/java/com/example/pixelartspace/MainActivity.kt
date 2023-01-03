package com.example.pixelartspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pixelartspace.ui.theme.PixelArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixelArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PixelArtApp()
                }
            }
        }
    }
}

data class ArtObject(
    val art: Int,
    val description: Int
)

@Composable
fun NavButton(
    buttonFunction: () -> Unit,
    modifier: Modifier = Modifier,
    text: String
){
    Button(
        onClick = buttonFunction
    ) {
        Text(text)
    }
}


@Composable
fun PixelArtApp() {
    val artResources = arrayOf(
        ArtObject(
            art = R.drawable.ramen_1,
            description = R.string.ramen_1_description
        ),
        ArtObject(
            art = R.drawable.ramen_2,
            description = R.string.ramen_2_description
        ),
        ArtObject(
            art = R.drawable.ramen_3,
            description = R.string.ramen_3_description
        ),
        ArtObject(
            art = R.drawable.torii_1,
            description = R.string.torii_1_description
        ),
        ArtObject(
            art = R.drawable.torii_2,
            description = R.string.torii_2_description
        ),
    )

    var currentArtResourceIndex by remember { mutableStateOf(0) }

    val changeCurrentArtResourceIndex: (Int) -> Unit = {
        currentArtResourceIndex = ((currentArtResourceIndex + it) + artResources.size) % artResources.size
    }

    Column(
        modifier = Modifier.padding(32.dp)
    ) {
        Image(
            modifier = Modifier
                .padding(32.dp)
                .weight(1f),
            painter = painterResource(id = artResources[currentArtResourceIndex].art),
            contentDescription = stringResource(id = artResources[currentArtResourceIndex].description)
        )
        Text(
            text = stringResource(artResources[currentArtResourceIndex].description),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            NavButton(
                buttonFunction = { changeCurrentArtResourceIndex(-1) },
                text = "previous"
            )
            NavButton(
                buttonFunction = { changeCurrentArtResourceIndex(1) },
                text = "next"
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PixelArtSpaceTheme {
        PixelArtApp()
    }
}