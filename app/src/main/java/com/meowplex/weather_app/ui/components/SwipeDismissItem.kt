package com.meowplex.weather_app.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meowplex.weather_app.R
import com.meowplex.weather_app.ui.theme.Dimensions

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeDismissItem(
    content: @Composable () -> Unit,
    onDismissed: () -> Unit,
) {

    val dismissState = remember {
        DismissState(
            initialValue = DismissValue.Default,
        )
    }

    val isDismissed by remember {
        derivedStateOf {
            dismissState.isDismissed(DismissDirection.EndToStart)
        }
    }

    if (isDismissed) {
        onDismissed()
    }


    AnimatedVisibility(
        visible = !isDismissed,
    ) {
        SwipeToDismiss(
            state = dismissState,
            directions = setOf(DismissDirection.EndToStart),
            modifier = Modifier.padding(vertical = Dimensions.spaceSmall).wrapContentHeight(),
            dismissThresholds = {
                FractionalThreshold(0.5f)
            },
            background = {
                if (dismissState.dismissDirection == DismissDirection.EndToStart)
                    SwipeBackground()
            },
        ) {
            content()
        }
    }
}

@Composable
fun SwipeBackground() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Red, shape = RoundedCornerShape(Dimensions.spaceSmall)),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_delete),
            contentDescription = "",
            modifier = Modifier
                .size(30.dp)
                .padding(end = Dimensions.spaceSmall)
        )

    }
}