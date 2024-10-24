package eu.tutorials.newsapp.view

import TopBarWithBackArrow
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.CookieManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import eu.tutorials.newsapp.viewmodel.NewsViewModel

@Composable
fun ArticleDetailScreen(url: String, newsViewModel: NewsViewModel, navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBarWithBackArrow(navController = navController, title = "")
        }
    ) {
        AndroidView(
            factory = {
                WebView(context).apply {

                    val cookieManager = CookieManager.getInstance()
                    cookieManager.setAcceptCookie(true)
                    cookieManager.setAcceptThirdPartyCookies(this, true)

                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true

                    loadUrl(url)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
