package eu.tutorials.newsapp.view

import android.net.Uri
import kotlinx.serialization.Serializable

@Serializable
object ArticlePage {
    const val route = "article/{url}"
    fun route(url: String) = "article/${Uri.encode(url)}"
}





@Serializable
object NewsListPage {
    const val route = "news_list"
}
