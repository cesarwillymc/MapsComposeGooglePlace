package com.cesarwillymc.technicaltest99minutes.ui.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.PlaceReview
import com.cesarwillymc.technicaltest99minutes.ui.theme.Gray
import com.cesarwillymc.technicaltest99minutes.ui.theme.ImageMini
import com.cesarwillymc.technicaltest99minutes.ui.theme.Normal100
import com.cesarwillymc.technicaltest99minutes.ui.theme.OnyxBlack
import com.cesarwillymc.technicaltest99minutes.ui.theme.Platinum
import com.cesarwillymc.technicaltest99minutes.ui.theme.Small100
import com.cesarwillymc.technicaltest99minutes.ui.theme.Small120
import com.cesarwillymc.technicaltest99minutes.ui.theme.White

/**
 * Created by Willy on 9/12/2021.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 *
 * Is the view that showed each product of the list
 */

@SuppressWarnings("LongMethod")
@Composable
fun CardReviewItem(
    review: PlaceReview
) {

    Column {
        ConstraintLayout(
            modifier = Modifier
                .background(White)
                .fillMaxWidth()
                .padding(horizontal = Normal100, vertical = Small120)
        ) {
            val (icon, column) = createRefs()
            Image(
                painter = rememberImagePainter(review.profilePhotoUrl),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(icon) {
                        top.linkTo(column.top)
                        bottom.linkTo(column.bottom)
                        start.linkTo(parent.start)
                    }
                    .size(ImageMini)
            )
            Column(
                modifier = Modifier.constrainAs(column) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(icon.end, margin = Small100)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
            ) {
                Text(
                    text = review.relativeTimeDescription,
                    style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Normal),
                    color = Gray,
                )
                Text(
                    text = review.authorName,
                    style = MaterialTheme.typography.subtitle1,
                    color = OnyxBlack,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Divider(color = Platinum, modifier = Modifier.padding(horizontal = Normal100))
    }
}
