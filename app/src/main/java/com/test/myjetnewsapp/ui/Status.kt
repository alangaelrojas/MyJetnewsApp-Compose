package com.test.myjetnewsapp.ui

import androidx.compose.Model
import androidx.compose.frames.ModelList

/**
 * Class defining the scrrens we have in the app: home, article details amd interests
 */

sealed class Screen{
    object Home: Screen()
    data class Article(val postId: String): Screen()
    object Interests : Screen()
}

@Model
object MyJetnewsStatus{
    var currentScreen: Screen = Screen.Home
    val favorites = ModelList<String>()
    val selectedTopics = ModelList<String>()
}

/**
 * Temporary solution pending navigation support.
 */

fun navigateTo(destination: Screen){
    MyJetnewsStatus.currentScreen = destination
}
