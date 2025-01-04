package dev.soul.moviedbkmp.android.presentation.screen.detail.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import dev.soul.moviedbkmp.android.R
import dev.soul.moviedbkmp.android.presentation.screen.detail.MovieDetailIntent
import dev.soul.moviedbkmp.android.presentation.screen.detail.MovieDetailViewModel
import dev.soul.moviedbkmp.android.presentation.screen.home.ui.MovieItem
import dev.soul.moviedbkmp.android.presentation.screen.home.ui.MovieItemView
import dev.soul.moviedbkmp.android.utils.formatMinutesToHoursAndMinutes
import dev.soul.moviedbkmp.android.utils.formatNumber
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailScreen(navController: NavHostController, movieId: Long) {
    val viewModel: MovieDetailViewModel = koinViewModel<MovieDetailViewModel>()
    val detailUiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(MovieDetailIntent.Detail(movieId))
    }

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val boxHeight = screenHeight * 0.6f

    val brush = Brush.verticalGradient(
        colors = listOf(
            Color.Black.copy(alpha = 0.4f),
            Color.Transparent,
            Color.Transparent,
            Color.Black
        ),
        startY = 0f,
    )
//    LaunchedEffect(detailUiState.selectedTab) {
//        if (detailUiState.selectedTab == 0) {
//            viewModel.handleIntent(MovieDetailIntent.Actors(movieId))
//        } else {
//            viewModel.handleIntent(MovieDetailIntent.SimilarMovies(movieId))
//        }
//    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Log.e("dasdas", "MovieDetailScreen: ${detailUiState.movieDetail}")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(boxHeight), contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = detailUiState.movieDetail?.posterUrl ?: ""
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .height(boxHeight)
                    )

                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape),
                        contentAlignment = Alignment.Center
                    ) {

                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(0.2f))
                                .blur(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = rememberImagePainter(R.drawable.ic_play_video),
                                contentDescription = null,
                                modifier = Modifier.size(72.dp)
                            )
                        }
                        Image(
                            painter = rememberImagePainter(
                                R.drawable.ic_triangel
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                                .padding(top = 8.dp, start = 8.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(brush)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp, bottom = 16.dp),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "${detailUiState.movieDetail?.title}",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontFamily = FontFamily(
                                Font(R.font.pop_sembold)
                            )
                        )

                        Row(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .width(82.dp)
                                .height(36.dp)
                                .background(Color(0xFFE1C527), shape = RoundedCornerShape(6.dp))
                                .clip(RoundedCornerShape(12.dp)),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                "IMDB",
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.pop_bold)
                                )
                            )
                            Text(
                                formatNumber(detailUiState.movieDetail?.vote_average ?: 0.0),
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.pop_bold)
                                ), modifier = Modifier.padding(start = 2.dp)
                            )
                        }

                    }
                }


            }

            item {

                Row(modifier = Modifier.padding(start = 16.dp, top = 16.dp)) {

                    detailUiState.movieDetail?.genres?.let {
                        it.take(2).forEachIndexed() { index, genre ->
                            genre.name?.let {
                                CategoryItem(name = it)
                                Spacer(modifier = Modifier.width(6.dp))
                            }
                        }
                    }
                    CategoryItem(
                        formatMinutesToHoursAndMinutes(
                            detailUiState.movieDetail?.runtime ?: 0
                        )
                    )
                }
            }
            item {
                Overview(detailUiState.movieDetail?.overview ?: "")
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))

//                CustomTabBar {
//                    viewModel.handleIntent(MovieDetailIntent.onTabChange(it))
//                }

                Text(
                    "Similar Movies",
                    modifier = Modifier.padding(start = 16.dp),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(
                        Font(R.font.pop_sembold)
                    ),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))

                LazyRow {
                    item {
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                    itemsIndexed(detailUiState.similarMovies) { index, item ->
                        MovieItemView(
                            MovieItem(
                                title = item.title ?: "",
                                imageUrl = item.posterUrl,
                                age = "+18"
                            )
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .size(48.dp)
                .background(Color.Transparent)
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    navController.popBackStack()
                },
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(0.2f))
                .graphicsLayer {
                    alpha = 0.8f
                }
                .blur(16.dp)
            )

            Image(
                painter = rememberImagePainter(R.drawable.ic_back),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )

        }

    }

}
