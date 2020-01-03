package com.test.myjetnewsapp.ui.home

import android.view.Surface
import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.Clip
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.ColorPalette
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Typography
import androidx.ui.material.surface.Surface
import androidx.ui.material.withOpacity
import androidx.ui.tooling.preview.Preview
import com.test.myjetnewsapp.R
import com.test.myjetnewsapp.data.getPostsWithImagesLoaded
import com.test.myjetnewsapp.data.post2
import com.test.myjetnewsapp.data.posts
import com.test.myjetnewsapp.model.Post
import com.test.myjetnewsapp.ui.darkThemeColors
import com.test.myjetnewsapp.ui.lightThemeColors
import com.test.myjetnewsapp.ui.themeTypography

@Composable
fun PostCardTop(post: Post){
    val typography = +MaterialTheme.typography()
    Column(modifier = ExpandedWidth wraps Spacing(16.dp)) {
        post.image?.let { image ->
            Container(modifier = MinHeight(180.dp) wraps ExpandedWidth) {
                Clip(shape = RoundedCornerShape(4.dp)) {
                    DrawImage(image)
                }
            }
        }
        HeightSpacer(16.dp)
        Text(
            text = post.title,
            style = typography.h6.withOpacity(0.87f)
        )
        Text(
            text = post.metadata.author.name,
            style = typography.h6.withOpacity(0.87f)
        )
        Text(
            text = "${post.metadata.date} - ${post.metadata.readTimeMinutes} min read",
            style = typography.body2.withOpacity(0.6f)
        )
    }
}

//Preview section
@Preview("Default colors")
@Composable
fun TutorialPreview(){
    TutorialPreviewTemplate()
}

@Preview("Dark colors")
@Composable
fun TutorialPreviewDark(){
    TutorialPreviewTemplate(colors = darkThemeColors)
}
@Preview("Font scaling 1.5", fontScale = 1.5f)
@Composable
fun TutorialPreviewFontScale() {
    TutorialPreviewTemplate()
}

@Composable
fun TutorialPreviewTemplate(
    colors: ColorPalette = lightThemeColors,
    typography: Typography = themeTypography
) {
    val context = +ambient(ContextAmbient)
    val previewPost = getPostsWithImagesLoaded(posts.subList(1, 2), context.resources)
    val post = previewPost[0]
    MaterialTheme(
        colors = colors,
        typography = typography
    ) {
        Surface {
            PostCardTop(post)
        }
    }
}

@Preview
@Composable
fun previewPostCardTop(){
    PostCardTop(post = post2)
}