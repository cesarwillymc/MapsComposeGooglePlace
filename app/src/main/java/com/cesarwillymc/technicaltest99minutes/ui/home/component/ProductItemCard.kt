package com.cesarwillymc.technicaltest99minutes.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberImagePainter
import com.cesarwillymc.technicaltest99minutes.R
import com.cesarwillymc.technicaltest99minutes.extension.DEFAULT_IMG
import com.cesarwillymc.technicaltest99minutes.extension.SIZE_200
import com.cesarwillymc.technicaltest99minutes.ui.home.entities.PlacePresentation
import com.cesarwillymc.technicaltest99minutes.ui.theme.CardImageMin
import com.cesarwillymc.technicaltest99minutes.ui.theme.ForestGreen
import com.cesarwillymc.technicaltest99minutes.ui.theme.Normal100
import com.cesarwillymc.technicaltest99minutes.ui.theme.OnyxBlack
import com.cesarwillymc.technicaltest99minutes.ui.theme.Small100
import com.cesarwillymc.technicaltest99minutes.ui.theme.Small120
import com.cesarwillymc.technicaltest99minutes.ui.theme.White
import com.cesarwillymc.technicaltest99minutes.ui.utils.getLinkPhotoGoogle

/**
 * Created by Willy on 11/25/2021.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@Composable
fun ProductItemCard(
    modifier: Modifier = Modifier,
    product: PlacePresentation,
    onClickItem: (String) -> Unit
) {

    Card(modifier = modifier, shape = RoundedCornerShape(Normal100)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(Normal100)
        ) {
            Image(
                painter = rememberImagePainter(
                    data = product.mainPhoto?.getLinkPhotoGoogle(SIZE_200) ?: DEFAULT_IMG
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(CardImageMin)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(Normal100))
            )
            Text(
                "${product.name}\n",
                style = MaterialTheme.typography.subtitle1,
                color = OnyxBlack,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Small100),
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    CardSizeDefault(drawable = R.drawable.ic_start, background = ForestGreen)
                    Text(
                        "${product.rating}",
                        style = MaterialTheme.typography.h1,
                        color = OnyxBlack,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = Small100),
                        overflow = TextOverflow.Ellipsis
                    )
                }
                CardSizeDefault(
                    modifier = Modifier.clickable {
                        onClickItem.invoke(product.idPlace)
                    },
                    background = ForestGreen,
                    drawable = R.drawable.ic_go
                )
            }
        }
    }
}

@Composable
fun CardSizeDefault(modifier: Modifier = Modifier, drawable: Int, background: Color = White) {
    Card(
        backgroundColor = background,
        modifier = modifier
    ) {
        Image(
            modifier = Modifier.padding(Small120),
            painter = painterResource(id = drawable),
            contentDescription = null,
        )
    }
}
