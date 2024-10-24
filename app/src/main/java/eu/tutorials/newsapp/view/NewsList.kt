package eu.tutorials.newsapp.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import eu.tutorials.newsapp.viewmodel.NewsViewModel

@Composable
fun NewsList(
    newsViewModel: NewsViewModel,
    navController: NavHostController
) {
    val articles by newsViewModel.articles.observeAsState(emptyList())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CategoriesBar(newsViewModel = newsViewModel)

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(articles) { article ->
                ArticleItem(article = article, navController = navController, newsViewModel = newsViewModel)
            }
        }
    }
}
