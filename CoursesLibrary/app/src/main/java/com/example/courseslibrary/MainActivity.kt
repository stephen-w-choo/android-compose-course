package com.example.courseslibrary


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.courseslibrary.model.Topic
import com.example.courseslibrary.ui.theme.CoursesLibraryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesLibraryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CoursesApp()
                }
            }
        }
    }
}

val topics = Datasource().topics

@Composable
fun CoursesApp() {
    TopicList(topics = topics)
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TopicList(
    topics: List<Topic>,
    modifier: Modifier = Modifier
){
    LazyVerticalGrid(
        cells = GridCells.Fixed(2)
    ) {
        items(topics) { topic ->
            TopicGridItem(topic = topic)
        }
    }
}

@Composable
fun TopicGridItem(topic: Topic, modifier: Modifier = Modifier) {
 Row {
     Image(
         painter = painterResource(id = topic.topicIcon),
         contentDescription = stringResource(id = topic.topicName),
         modifier = modifier
             .size(68.dp)
     )
     Column(
         modifier = modifier
             .padding(8.dp)
     ) {
         Text(
             text = stringResource(id = topic.topicName),
             fontSize = 14.sp,
             modifier = modifier
                 .padding(8.dp)
         )
         Row (
            verticalAlignment = Alignment.CenterVertically
         ){
             Image(
                 painter = painterResource(id = R.drawable.ic_grain),
                 contentDescription = "Icon",
                 modifier = modifier
                     .padding(horizontal = 8.dp)
                     .size(12.dp)

             )
             Text(
                 text = topic.numberOfCourses.toString(),
                 fontSize = 12.sp,
             )
         }
     }
 }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoursesLibraryTheme {
        CoursesApp()
    }
}