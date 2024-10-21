package liber.app.kmptutorial

import kotlinx.coroutines.CoroutineScope

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect open class BaseViewModel() {
    val scope: CoroutineScope
}