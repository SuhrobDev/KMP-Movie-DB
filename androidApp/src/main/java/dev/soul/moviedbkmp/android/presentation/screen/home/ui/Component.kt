package dev.soul.moviedbkmp.android.presentation.screen.home.ui

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberImagePainter
import dev.soul.moviedbkmp.android.R
import dev.soul.moviedbkmp.data.network.dto.people.PeopleItemModel
import kotlinx.coroutines.delay

@Composable
fun GenreItem(string: String) {
    Box(
        modifier = Modifier
            .height(24.dp)
            .background(
                color = Color(0xFFFF0000).copy(alpha = 0.2f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {

        Text(
            string,
            color = Color(0xFFFFFFFF).copy(alpha = 0.6f),
            fontSize = 12.sp,
            fontFamily = FontFamily(
                Font(R.font.pop_reg)
            )
        )
    }
}

data class MovieItem(
    val title: String,
    val imageUrl: String?, // Image URL or null if no image
    val age: String // Age tag, e.g., "+0", "+18", "+21"
)

@Composable
fun MovieItemView(movieItem: MovieItem) {
    val painter = rememberImagePainter(
        data = movieItem.imageUrl,
        builder = {
            crossfade(true)
        }
    )

    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            // Movie Image
            Box(
                modifier = Modifier
                    .width(167.dp)
                    .height(240.dp),
                contentAlignment = Alignment.Center
            ) {

                // Movie Image
                Box(
                    modifier = Modifier
                        .width(167.dp)
                        .height(240.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painter,
                        contentDescription = movieItem.title,
                        modifier = Modifier
                            .fillMaxSize().clip(RoundedCornerShape(16.dp)) // Fill the Box for the main image
                            .then(
                                if (painter.state is AsyncImagePainter.State.Error) {
                                    Modifier.size(
                                        60.dp,
                                        70.dp
                                    ) // Constrain the size for error state
                                } else {
                                    Modifier // Do nothing for normal state
                                }
                            ),
                        contentScale = if (painter.state is AsyncImagePainter.State.Error) {
                            ContentScale.Fit // Fit the error image to 60x70 without cropping
                        } else {
                            ContentScale.Crop // Crop the main image to fill
                        }
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp)
                            .background(
                                Color.Black.copy(alpha = 0.6f),
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = movieItem.age,
                            style = TextStyle(color = Color.White, fontSize = 12.sp)
                        )
                    }
                }
            }

            // Movie title below image
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movieItem.title,
                style = TextStyle(color = Color.White, fontSize = 16.sp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontFamily = FontFamily(
                    Font(R.font.pop_sembold)
                )
            )
        }
    }
}

@Composable
fun ErrorView(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .onPlaced { _ ->
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            color = Color.Red
        )
        OutlinedButton(
            onClick = onClickRetry, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            Text(text = "Try again")
        }
    }
}

@Composable
fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Red
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = "Try again")
        }
    }
}

@Composable
fun CarouselTimedProgressIndicator(
    totalItems: Int,
    currentIndex: Int,
    progressDurationMillis: Int, // Time for one item's progress
    onProgressFinished: () -> Unit,
    modifier: Modifier = Modifier,
    activeColor: Color = Color.Blue,
    inactiveColor: Color = Color.Gray,
    progressHeight: Dp = 4.dp,
    spacing: Dp = 4.dp
) {
    var progress by remember { mutableFloatStateOf(0f) }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = progressDurationMillis), label = ""
    )

    LaunchedEffect(currentIndex) {
        progress = 0f // Reset progress on index change
        progress = 1f // Start progress animation
        delay(progressDurationMillis.toLong())
        onProgressFinished() // Notify when progress completes
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        repeat(totalItems) { index ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(progressHeight)
                    .background(
                        color = if (index == currentIndex) activeColor else inactiveColor,
                        shape = RoundedCornerShape(progressHeight / 2)
                    )
            ) {
                if (index == currentIndex) {
                    // Growing progress for the active item
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(animatedProgress)
                            .background(
                                color = activeColor,
                                shape = RoundedCornerShape(progressHeight / 2)
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun TimedProgressBar(
    currentItem: Int, // Current item index to trigger reset
    durationMillis: Int, // Total duration for the progress bar (e.g., 3 seconds)
    onFinished: () -> Unit = {}, // Callback when progress completes
    modifier: Modifier = Modifier,
    progressColor: Color = Color.Blue,
    backgroundColor: Color = Color.Gray,
    height: Dp = 6.dp
) {
    var progress by remember { mutableStateOf(0f) }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(3000)
    )

    val brush = remember {
        Brush.horizontalGradient(
            colors = listOf(
                Color.Black,
                Color(0xFF751111)
            )
        )
    }
    Log.e("cxzcx", "TimedProgressBar: $currentItem")
    LaunchedEffect(currentItem) { // Trigger whenever currentItem changes
        progress = 0f // Reset progress
        progress = 1f // Start animation
        delay(3000L)
        Log.e("cxzcx", "TimedProgressBar: ${durationMillis.toLong()}")

        onFinished() // Notify that the progress is complete
    }

    Box(
        modifier = modifier
            .height(height)
            .background(backgroundColor, shape = RoundedCornerShape(height / 2))
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(animatedProgress)
                .background(brush, shape = RoundedCornerShape(height / 2))
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleItemView(peopleItem: PeopleItemModel, onItemClick: (people: PeopleItemModel) -> Unit) {
    Card(
        onClick = { onItemClick.invoke(peopleItem) },
        shape = RoundedCornerShape(16.dp),
    ) {
        Image(
            painter = rememberImagePainter(data = peopleItem.imageUrl),
            contentDescription = peopleItem.name,
            modifier = Modifier
                .width(60.dp)
                .height(60.dp),
            contentScale = ContentScale.Crop
        )
    }

}

@Preview
@Composable
fun MovieItemViewPreview() {
    val movieItem = MovieItem(
        title = "Movie Title",
        imageUrl = "https://media.themoviedb.org/t/p/w220_and_h330_face/l0KU6dtv6EfJdrCdC6cHIdDuJOa.jpg",
        age = "+18"
    )
    MovieItemView(movieItem)
}

@Preview
@Composable
fun GenreItemPreview() {
    GenreItem("Action")
}