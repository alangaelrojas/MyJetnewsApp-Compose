package com.test.myjetnewsapp.ui.interests

import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.selection.Toggleable
import androidx.ui.foundation.shape.DrawShape
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.border.DrawBorder
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.layout.Size
import androidx.ui.material.*
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.res.imageResource
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import com.test.myjetnewsapp.R
import com.test.myjetnewsapp.data.people
import com.test.myjetnewsapp.data.publications
import com.test.myjetnewsapp.data.topics
import com.test.myjetnewsapp.ui.*
import com.test.myjetnewsapp.ui.HeightSpacer

private enum class Sections(val title: String){
    Topics("Topics"),
    People("People"),
    Publications("Publications")
}

@Composable
fun InterestsScreen(openDrawer: () -> Unit){
    var section by +state{ Sections.Topics }
    val sectionTitles = Sections.values().map { it.title }

    Column {
        TopAppBar(
            title = { Text("Interests") },
            navigationIcon = {
                VectorImageButton(R.drawable.ic_jetnews_logo){
                    openDrawer()
                }
            }
        )
        TabRow(items = sectionTitles, selectedIndex = section.ordinal) {index, text ->
            Tab(selected = section.ordinal == index, text = text){
                section = Sections.values()[index]
            }
        }
        Container(modifier = Flexible(1f)) {
            when(section){
                Sections.Topics -> TopicsTab()
                Sections.People -> PeopleTab()
                Sections.Publications -> PublicationsTab()
            }
        }
    }
}

@Composable
private fun PublicationsTab() {
    TabWithTopics(
        Sections.Publications.title,
        publications
    )
}

@Composable
private fun PeopleTab() {
    TabWithTopics(
        Sections.People.title,
        people
    )
}

@Composable
private fun TopicsTab() {
    TabWithSections(
        "Topics",
        topics
    )
}

fun TabWithSections(s: String, topics: Any) {

}

@Composable
fun TabWithTopics(tabname: String, topics: List<String>) {
    VerticalScroller {
        Column {
            HeightSpacer(16.dp)
            topics.forEach { topic ->
                TopicItem(
                    getTopicKey(
                        tabname,
                        "- ",
                        topic
                    ), topic
                )
                TopicDivider()
            }
        }
    }
}

@Composable
fun TopicDivider() {
    Opacity(0.08f) {
        Divider(Spacing(top = 8.dp, bottom = 8.dp, left = 72.dp))
    }
}

@Composable
private fun TopicItem(topicKey: String, itemTitle: String) {
    val image = +imageResource(R.drawable.placeholder_1_1)
    Row(
        modifier = Spacing(left = 18.dp, right = 18.dp)
    ) {
        Container(modifier = Gravity.Center wraps Size(56.dp, 56.dp)) {
            Clip(RoundedCornerShape(4.dp)) {
                DrawImage(image)
            }
        }
        Text(
            text = itemTitle,
            modifier = Flexible(1f) wraps Gravity.Center wraps Spacing(16.dp),
            style = (+MaterialTheme.typography()).subtitle1
        )
        val selected = isTopicSelected(topicKey)
        SelectTopicButton(
            modifier = Gravity.Center,
            onSelected = {
                selectTopic(topicKey, !selected)
            },
            selected = selected
        )
    }
}

private fun selectTopic(topicKey: String, select: Boolean) {
    if(select){
        MyJetnewsStatus.selectedTopics.add(topicKey)
    } else {
        MyJetnewsStatus.selectedTopics.remove(topicKey)
    }
}

private fun isTopicSelected(topicKey: String) = MyJetnewsStatus.selectedTopics.contains(topicKey)

private fun getTopicKey(tab: String, group: String, topic: String) = "$tab-$group-$topic"
