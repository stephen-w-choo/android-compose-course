package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.HeroRepository
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HeroApp()
                }
            }
        }
    }
}

@Composable
fun SuperheroListItem(
    hero: Hero,
    modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(4.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement=Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f, fill = false)
            ) {
                Text(
                    text = stringResource(id = hero.nameRes),
                    style = MaterialTheme.typography.h3
                )
                Text(
                    text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.body1
                )
            }
            Spacer(modifier.width(16.dp))
            Image(
                modifier = modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(16.dp)),
                painter = painterResource(id = hero.imageRes),
                contentDescription = stringResource(id = hero.nameRes),
            )
        }
    }
}

@Composable
fun HeroList(heroList: List<Hero>) {
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(12.dp)
    ) {
        items(heroList) {
            SuperheroListItem(hero = it)
        }
    }
}

@Composable
fun HeroTopAppBar(modifier: Modifier = Modifier) {
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
fun HeroApp() {
    Scaffold(
        topBar = {
            HeroTopAppBar()
        }
    ) {
        HeroList(HeroRepository.heroes)
    }
}


@Preview(showBackground = true)
@Composable
fun Test() {
    SuperheroesTheme {
        HeroApp()
    }
}

@Preview
@Composable
fun DarkThemePreview() {
    SuperheroesTheme(darkTheme = true) {
        HeroApp()
    }
}