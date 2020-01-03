package com.test.myjetnewsapp.ui.article

import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import com.test.myjetnewsapp.data.posts
import androidx.ui.core.Text
import androidx.ui.layout.Column
import androidx.ui.material.*
import com.test.myjetnewsapp.ui.Screen
import com.test.myjetnewsapp.ui.VectorImageButton
import com.test.myjetnewsapp.ui.navigateTo

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
        PostContent()
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