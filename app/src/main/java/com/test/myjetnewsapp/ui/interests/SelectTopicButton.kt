package com.test.myjetnewsapp.ui.interests

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Modifier
import androidx.ui.core.dp
import androidx.ui.foundation.selection.Toggleable
import androidx.ui.foundation.shape.DrawShape
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.border.DrawBorder
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.Container
import androidx.ui.layout.Size
import androidx.ui.layout.Spacing
import androidx.ui.material.ColorPalette
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import com.test.myjetnewsapp.R
import com.test.myjetnewsapp.ui.darkThemeColors
import com.test.myjetnewsapp.ui.lightThemeColors

@Composable
fun SelectTopicButton(
    modifier: Modifier = Modifier.None,
    onSelected: ((Boolean) -> Unit)? = null,
    selected: Boolean = false
) {
    Ripple(bounded = false) {
        Toggleable(selected, onSelected) {
            Container(modifier = modifier wraps Size(36.dp, 36.dp)) {
                if (selected) {
                    DrawSelectTopicButtonOn()
                } else {
                    DrawSelectTopicButtonOff()
                }
            }
        }
    }
}

@Composable
private fun DrawSelectTopicButtonOn() {
    DrawShape(
        shape = CircleShape,
        color = (+MaterialTheme.colors()).primary
    )
    DrawVector(+vectorResource(R.drawable.ic_check))
}

@Composable
private fun DrawSelectTopicButtonOff() {
    val borderColor = ((+MaterialTheme.colors()).onSurface).copy(alpha = 0.12f)
    DrawBorder(
        shape = CircleShape,
        border = Border(borderColor, 2.dp)
    )
    DrawVector(
        vectorImage = +vectorResource(R.drawable.ic_add),
        tintColor = (+MaterialTheme.colors()).primary
    )
}

@Preview("Off")
@Composable
fun SelectTopicButtonPreviewOff() {
    SelectTopicButtonPreviewTemplate(
        lightThemeColors,
        false
    )
}

@Preview("On")
@Composable
fun SelectTopicButtonPreviewOn() {
    SelectTopicButtonPreviewTemplate(
        lightThemeColors,
        true
    )
}

@Preview("Off - Dark")
@Composable
fun SelectTopicButtonPreviewOffDark() {
    SelectTopicButtonPreviewTemplate(
        darkThemeColors,
        false
    )
}

@Preview("On - Dark")
@Composable
fun SelectTopicButtonPreviewOnDark() {
    SelectTopicButtonPreviewTemplate(
        darkThemeColors,
        true
    )
}

@Composable
private fun SelectTopicButtonPreviewTemplate(themeColors: ColorPalette, selected: Boolean) {
    MaterialTheme(themeColors) {
        Surface {
            SelectTopicButton(
                modifier = Spacing(32.dp),
                selected = selected
            )
        }
    }
}
