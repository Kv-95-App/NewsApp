package eu.tutorials.newsapp.view

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import eu.tutorials.newsapp.viewmodel.NewsViewModel

@Composable
fun CategoriesBar(newsViewModel: NewsViewModel) {

    var searchQuery = remember {
        mutableStateOf("")
    }
    var isSearchOpened = remember {
        mutableStateOf(false)
    }
    val categoriesList = listOf(
        "General",
        "Entertainment",
        "Business",
        "Health",
        "Science",
        "Sports",
        "Technology"
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if(isSearchOpened.value){
            OutlinedTextField(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                modifier = Modifier
                    .padding(4.dp)
                    .border(1.dp,Color.Black, CircleShape)
                    .clip(CircleShape),
                trailingIcon = {
                    IconButton(onClick = {
                        isSearchOpened.value = false
                        if(searchQuery.value.isNotEmpty()){
                            newsViewModel.fetchEverythingWithQuery(searchQuery.value)
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        }else{
            IconButton(onClick = { isSearchOpened.value = true
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        }
        categoriesList.forEach { category ->
            Button(
                onClick = {newsViewModel.fetchNewsTopHeadlines(category)},
                modifier = Modifier.padding(4.dp)
            ) {
                Text(text = category)
            }
        }
    }
}