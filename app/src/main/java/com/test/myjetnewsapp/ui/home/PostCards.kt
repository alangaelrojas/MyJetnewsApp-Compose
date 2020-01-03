package com.test.myjetnewsapp.ui.home

import android.content.res.Resources
import android.widget.Toast
import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.selection.Toggleable
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.withOpacity
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import com.test.myjetnewsapp.R
import com.test.myjetnewsapp.data.post3
import com.test.myjetnewsapp.model.Post
import com.test.myjetnewsapp.ui.MyJetnewsStatus
import com.test.myjetnewsapp.ui.Screen
import com.test.myjetnewsapp.ui.VectorImage
import com.test.myjetnewsapp.ui.navigateTo

val context = +ambient(ContextAmbient)

@Composable
fun AuthorAndReadTime(post: Post){
    Row{
        val textStyle = ((+MaterialTheme.typography()).body2).withOpacity(0.6f)
        Text(
            text = " - ${post.metadata.readTimeMinutes} min read",
            style = textStyle
        )
    }
}

@Composable
fun PostTitle(post: Post){
    Text(
        post.title, style = ((+MaterialTheme.typography()).subtitle1).withOpacity(0.87f))
}

@Composable
fun PostImage(modifier: Modifier = Modifier.None, post: Post) {
    val image = post.imageThumb ?: +imageResource(R.drawable.placeholder_1_1)

    Container(modifier = modifier wraps Size(40.dp, 40.dp)) {
        DrawImage(image)
    }
}

@Composable
fun PostCardSimple(post: Post){
    Ripple(bounded = true) {
        Clickable(onClick = {
            navigateTo(Screen.Article(post.id))
            Toast.makeText(context, "Clicked on: ${post.title}", Toast.LENGTH_SHORT).show()
        }) {
            Row(
                modifier = Spacing(16.dp)
            ) {
                PostImage(modifier = Spacing(right = 16.dp), post = post)
                Column(modifier = Flexible(1f)) {
                    PostTitle(post)
                    AuthorAndReadTime(post)
                }
                BookmarkButton(
                    isBookmarked = isFavorite(postId = post.id),
                    onBookmark = { toggleBookmark(postId = post.id) }
                )
            }
        }
    }
}

@Composable
fun PostCardHistory(post: Post){
    Ripple(bounded = true) {
        Clickable(onClick = {
            navigateTo(Screen.Article(post.id))
            Toast.makeText(context, "Clicked on: ${post.title}", Toast.LENGTH_SHORT).show()
        }) {
            Row(modifier = Spacing(all = 16.dp)) {
                PostImage(modifier = Spacing(right = 16.dp),post = post)
                Column(modifier = Flexible(1f)) {
                    Text(
                        text = "BASED ON YOUR HISTORY",
                        style = ((+MaterialTheme.typography()).overline).withOpacity(0.38f)
                    )
                    PostTitle(post = post)
                    AuthorAndReadTime(post)
                }
                VectorImage(
                    modifier = Spacing(top = 8.dp, bottom = 8.dp),
                    id = R.drawable.ic_more
                )
            }
        }
    }
}

@Composable
fun BookmarkButton(isBookmarked: Boolean, onBookmark: (Boolean) -> Unit) {
    Ripple(
        bounded = false,
        radius = 24.dp
    ) {
        Toggleable(isBookmarked, onBookmark) {
            Container(modifier = Size(48.dp, 48.dp)) {
                if(isBookmarked){
                    VectorImage(id = R.drawable.ic_bookmarked)
                    Toast.makeText(context, "like :)" , Toast.LENGTH_SHORT).show()
                } else{
                    VectorImage(id = R.drawable.ic_bookmark)
                    Toast.makeText(context, "dislike :(" , Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

fun toggleBookmark(postId: String){
    with(MyJetnewsStatus){
        if(favorites.contains(postId)){
            favorites.remove(postId)
        }else {
            favorites.add(postId)
        }
    }
}

fun isFavorite(postId: String) = MyJetnewsStatus.favorites.contains(postId)

@Preview
@Composable
fun runPreview(){
    PostCardSimple(post = post3)
}


