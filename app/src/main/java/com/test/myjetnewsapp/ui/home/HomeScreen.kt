package com.test.myjetnewsapp.ui.home

import android.widget.Toast
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Opacity
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.Spacing
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.withOpacity
import androidx.ui.tooling.preview.Preview
import com.test.myjetnewsapp.R
import com.test.myjetnewsapp.data.posts
import com.test.myjetnewsapp.model.Post
import com.test.myjetnewsapp.ui.Screen
import com.test.myjetnewsapp.ui.VectorImageButton
import com.test.myjetnewsapp.ui.WidthSpacer
import com.test.myjetnewsapp.ui.navigateTo


@Composable
fun HomeScreen(openDrawer: () -> Unit){
    val postTop = posts[3]
    val postsSimple = posts.subList(0,2)
    val postsPopular = posts.subList(2,7)
    val postsHistory = posts.subList(7,10)

    Column {
        TopAppBar(
            title = { Text(text = "My Jetnews") },
            navigationIcon = {
                VectorImageButton(R.drawable.ic_jetnews_logo){
                    openDrawer()
                }
            }
        )
        VerticalScroller(modifier = Flexible(1f)) {
            Column{
                HomeScreenTopSelection(post = postTop)
                HomeScreenHistorySection(posts = postsSimple)
                HomeScreenPopularSection(posts = postsPopular)
                HomeScreenHistorySection(posts = postsHistory)
            }
        }
    }
}

@Composable
fun HomeScreenHistorySection(posts: List<Post>) {
    posts.forEach { post ->
        PostCardHistory(post)
        HomeScreenDivider()
    }
}

@Composable
fun HomeScreenPopularSection(posts: List<Post>) {
    Text(
        modifier = Spacing(16.dp),
        text = "Popular on My Jetnews",
        style = ((+MaterialTheme.typography()).subtitle1).withOpacity(0.87f)
    )
    HorizontalScroller {
        Row(modifier = Spacing(bottom = 16.dp, right = 16.dp)){
            posts.forEach { post ->
                WidthSpacer(16.dp)
                PostCardPopular(post)
            }
        }
    }
    HomeScreenDivider()
}

@Composable
fun HomeScreenSimpleSection(posts: List<Post>) {
    posts.forEach { post ->
        PostCardSimple(post)
        HomeScreenDivider()
    }
}

@Composable
fun HomeScreenTopSelection(post: Post) {
    Text(
        modifier = Spacing(top = 16.dp, left = 16.dp, right = 16.dp),
        text = "Top stories for you",
        style = ((+MaterialTheme.typography()).subtitle1).withOpacity(0.87f)
    )
    Ripple(bounded = true) {
        Clickable(onClick = {
            navigateTo(Screen.Article(post.id))
            Toast.makeText(context, "Clicked on: ${post.title}", Toast.LENGTH_SHORT).show()
        }) {
            PostCardTop(post = post)
        }
    }
    HomeScreenDivider()
}

@Composable
private fun HomeScreenSimpleSelection(posts: List<Post>){
    posts.forEach { post ->
        PostCardSimple(post)
        HomeScreenDivider()
    }
}

@Composable
fun HomeScreenDivider() {
    Opacity(0.08f) {
        Divider(modifier = Spacing(left = 14.dp, right = 14.dp))
    }
}

@Preview
@Composable
fun preview() {
    HomeScreen {}
}
