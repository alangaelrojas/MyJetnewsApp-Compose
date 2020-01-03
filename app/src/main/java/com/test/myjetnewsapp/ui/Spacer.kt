package com.test.myjetnewsapp.ui

import androidx.compose.Composable
import androidx.ui.core.Dp
import androidx.ui.core.dp
import androidx.ui.layout.ConstrainedBox
import androidx.ui.layout.DpConstraints

/**
 * Component that represents an empty space with given width and heigth.
 *
 * @param width width of the empty space
 * @param height heigth of the empty space
 */

@Composable
fun FixedSpacer(width: Dp, height: Dp){
    ConstrainedBox(constraints = DpConstraints.tightConstraints(width, height)) {
        // no children as we only need space
    }
}

/**
 * Component that represents an empty space with fixed width and zero heiht
 *
 * @param width width of the empty space
 */
@Composable
fun WidthSpacer(width: Dp){
    FixedSpacer(width = width, height = 0.dp)
}

/**
 * Component that represents an empty space with fixed heigth and zero width.
 *
 * @param height height of the empty space
 */
@Composable
fun HeightSpacer(height: Dp){
    FixedSpacer(width = 0.dp, height = height)
}
