package com.test.myjetnewsapp.ui.article

import android.content.Context
import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import com.test.myjetnewsapp.data.posts
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import com.test.myjetnewsapp.ui.Screen
import com.test.myjetnewsapp.ui.VectorImageButton
import com.test.myjetnewsapp.ui.navigateTo
import com.test.myjetnewsapp.R
import com.test.myjetnewsapp.data.post3
import com.test.myjetnewsapp.model.Post
import com.test.myjetnewsapp.ui.home.BookmarkButton
import com.test.myjetnewsapp.ui.home.context
import com.test.myjetnewsapp.ui.home.isFavorite
import com.test.myjetnewsapp.ui.home.toggleBookmark

@Composable
fun ArticleScreen(postId: String){
    var showDialog by +state { false }
    //greetting the post from our list of posts by Id
    val post = posts.find { it.id == postId } ?: return

    if(showDialog){
        FunctionalityNotAvailablePopUp{
            showDialog = false
        }
    }
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Published in: ${post.publication?.name}",
                    style = (+MaterialTheme.typography()).subtitle2
                )
            },
            navigationIcon = {
                VectorImageButton(R.drawable.ic_back){
                    navigateTo(Screen.Home)
                }
            }
        )
        PostContent(modifier = Flexible(1f), post = post)
        BottomBar(post) { showDialog = true }
    }
}

@Composable
private fun BottomBar(post: Post, onUnImplementedAction: () -> Unit){
    Surface(elevation = 2.dp) {
        Container(modifier = Height(56.dp) wraps Expanded) {
            Row{
                BottomBarAction(R.drawable.ic_favorite){
                    onUnImplementedAction()
                }
                BookmarkButton(
                    isBookmarked = isFavorite(postId = post.id),
                    onBookmark = { toggleBookmark(postId = post.id) }
                )
                BottomBarAction(R.drawable.ic_share){
                    sharePost(post, context)
                }
                Container(modifier = Flexible(1f)) { } //TODO: Any elements works
                BottomBarAction(R.drawable.ic_text_settings){
                    onUnImplementedAction()
                }
            }
        }
    }
}

private fun sharePost(post: Post, context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TITLE, post.title)
        putExtra(Intent.EXTRA_TEXT, post.url)
    }
    context.startActivity(Intent.createChooser(intent, "Share post..."))
}

@Composable
private fun BottomBarAction(
    @DrawableRes id: Int,
    onClick: () -> Unit
) {
    Ripple(
        bounded = false,
        radius = 24.dp
    ) {
        Clickable(onClick = onClick) {
            Container(modifier = Spacing(12.dp) wraps Size(24.dp, 24.dp)) {
                DrawVector(+vectorResource(id))
            }
        }
    }
}

@Composable
private fun FunctionalityNotAvailablePopUp(onDismiss: () -> Unit){
    AlertDialog(
        onCloseRequest = onDismiss,
        text ={
        Text(
            text = "Functionality not available \uD83D\uDE48",
            style = (+MaterialTheme.typography()).body2
        )
    },
        confirmButton = {
            Button(
                text = "CLOSE",
                style = TextButtonStyle(),
                onClick = onDismiss
            )
        }
    )
}


@Preview
@Composable
fun previewArticle(){
    ArticleScreen(post3.id)
}