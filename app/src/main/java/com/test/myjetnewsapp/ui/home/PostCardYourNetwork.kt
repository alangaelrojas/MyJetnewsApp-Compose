package com.test.myjetnewsapp.ui.home

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Card
import androidx.ui.material.withOpacity
import androidx.ui.res.imageResource
import androidx.ui.text.style.TextOverflow
import com.test.myjetnewsapp.R
import com.test.myjetnewsapp.model.Post
import com.test.myjetnewsapp.ui.Screen
import com.test.myjetnewsapp.ui.navigateTo

@Composable
fun PostCardPopular(post: Post){
    Card(shape = RoundedCornerShape(4.dp)) {
        Ripple(bounded = true) {
            Clickable(onClick = {
                navigateTo(Screen.Article(post.id))
            }) {
                Container(modifier = Size(280.dp, 240.dp)) {
                    Column(modifier = Expanded) {
                        val image = post.image ?: +imageResource(R.drawable.placeholder_4_3)
                        Container(modifier = Height(100.dp) wraps Expanded) {
                            DrawImage(image)
                        }
                        Column(modifier = Spacing(16.dp)) {
                            Text(
                                text = post.title,
                                style = ((+MaterialTheme.typography()).h6).withOpacity(0.87f),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = post.metadata.author.name,
                                style = ((+MaterialTheme.typography()).body2).withOpacity(0.87f)
                            )
                            Text(
                                text = "${post.metadata.date} - "+
                                        "${post.metadata.readTimeMinutes} min read",
                                style = ((+MaterialTheme.typography()).body2).withOpacity(0.6f)
                            )
                        }
                    }
                }
            }
        }
    }
}