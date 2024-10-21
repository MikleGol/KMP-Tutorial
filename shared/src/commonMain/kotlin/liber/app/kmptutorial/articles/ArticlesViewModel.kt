package liber.app.kmptutorial.articles

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import liber.app.kmptutorial.BaseViewModel

class ArticlesViewModel : BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticlesState> = MutableStateFlow(ArticlesState(loading = true))

    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            val fetchArticles = fetchArticles()

            delay(500)

            _articlesState.emit(ArticlesState(articles = fetchArticles))
        }
    }

    suspend fun fetchArticles(): List<Article> = mockArticles

    private val mockArticles = listOf(
        Article(
            "Stock Market",
            "Features asdf sdas da sd",
            "2024-11-09",
            "https://upload.wikimedia.org/wikipedia/commons/d/de/Ferran_Corominas_%28cropped%29.jpg"
        ),

        Article(
            "Stock Market",
            "Features asdf sdas da sd",
            "2024-11-09",
            "https://upload.wikimedia.org/wikipedia/commons/d/de/Ferran_Corominas_%28cropped%29.jpg"
        ),

        Article(
            "Stock Market",
            "Features asdf sdas da sd",
            "2024-11-09",
            "https://upload.wikimedia.org/wikipedia/commons/d/de/Ferran_Corominas_%28cropped%29.jpg"
        ),
    )
}