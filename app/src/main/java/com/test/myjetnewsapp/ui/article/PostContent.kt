package com.test.myjetnewsapp.ui.article

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Clip
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.withOpacity
import com.test.myjetnewsapp.model.Metadata
import com.test.myjetnewsapp.model.Paragraph
import com.test.myjetnewsapp.model.Post
import com.test.myjetnewsapp.ui.VectorImage

private val defaultSpacerSize = 16.dp
private val codeBlockBackground = Color(0xFFF1F1F1.toInt())

@Composable
fun PostContent(modifier: Modifier = Modifier.None, post: Post){
    val typography = +MaterialTheme.typography()
    VerticalScroller(modifier = modifier) {
        Column(modifier = Spacing(left = defaultSpacerSize, right = defaultSpacerSize)) {
            HeightSpacer(height = defaultSpacerSize)
            PostHeaderImage(post)
        }
    }
}

@Composable
private fun PostHeaderImage(post: Post){
    post.image?.let { image ->
        Container(modifier = MinHeight(100.dp) wraps ExpandedWidth) {
            Clip(shape = RoundedCornerShape(4.dp)) {
                DrawImage(image)
            }
        }
        HeightSpacer(height = defaultSpacerSize)
    }
}

@Composable
private fun PostMetadata(metadata: Metadata){
    val typography = +MaterialTheme.typography()
    Row {
       VectorImage(id = R.drawable.ic_account_circle_black)
        WidthSpacer(width = 8.dp)
        Column {
            HeightSpacer(4.dp)
            Text(
                text = metadata.author.name,
                style = typography.caption.withOpacity(0.6f)
            )
            Text(
                text = "${metadata.date} • ${metadata.readTimeMinutes} min read",
                style = typography.caption.withOpacity(0.6f)
            )
        }
    }
}

@Composable
private fun PostContents(paragraph: List<Paragraph>){
    paragraph.forEach {
        Paragraph(paragraph = it)
    }
}

@Composable
private fun Paragraph(paragraph: Paragraph){
    val (textStyle, paragraphStyle, trailingPadding) = paragraph.type.getTextAndParagraphStyle()

    val annotatedString = paragraphToAnnotatedString(paragraph)
    Container(modifier = Spacing(bottom = trailingPadding)) {
        when
    }
}