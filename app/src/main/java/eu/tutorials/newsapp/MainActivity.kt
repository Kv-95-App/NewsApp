package eu.tutorials.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.tutorials.newsapp.ui.theme.NewsAppTheme
import eu.tutorials.newsapp.view.ArticleDetailScreen
import eu.tutorials.newsapp.view.ArticlePage
import eu.tutorials.newsapp.view.NewsList
import eu.tutorials.newsapp.view.NewsListPage
import eu.tutorials.newsapp.viewmodel.NewsViewModel

class MainActivity : ComponentActivity() {
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        setContent {
            val navController = rememberNavController()

            NewsAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()

                ) { innerPadding ->


                    NavHost(
                        navController = navController,
                        startDestination = NewsListPage.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(NewsListPage.route) {
                            NewsList(newsViewModel = newsViewModel, navController)
                        }
                        composable(ArticlePage.route) { backStackEntry ->
                            val url = backStackEntry.arguments?.getString("url")
                            if (url != null) {
                                ArticleDetailScreen(url = url, newsViewModel = newsViewModel, navController = navController)
                            } else {

                            }
                        }
                    }
                }
            }
        }
    }
}
