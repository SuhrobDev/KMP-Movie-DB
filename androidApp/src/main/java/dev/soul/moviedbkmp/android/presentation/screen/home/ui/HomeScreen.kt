package dev.soul.moviedbkmp.android.presentation.screen.home.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import dev.soul.moviedbkmp.android.R
import dev.soul.moviedbkmp.android.presentation.navigation.Screens
import dev.soul.moviedbkmp.android.presentation.screen.home.AndroidHomeViewModel
import dev.soul.moviedbkmp.android.utils.formatNumber
import dev.soul.moviedbkmp.home.presentation.HomeIntent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavHostController) {

    val viewModel: AndroidHomeViewModel = koinViewModel<AndroidHomeViewModel>()
    val homeUiState by viewModel.state.collectAsStateWithLifecycle()

    val brush = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            Color.Black
        )
    )

//    LaunchedEffect(key1 = state.error) {
//        val message = when(state.error) {
//            TranslateError.SERVICE_UNAVAILABLE -> context.getString(androidx.compose.foundation.layout.R.string.error_service_unavailable)
//            TranslateError.CLIENT_ERROR -> context.getString(androidx.compose.foundation.layout.R.string.client_error)
//            TranslateError.SERVER_ERROR -> context.getString(androidx.compose.foundation.layout.R.string.server_error)
//            TranslateError.UNKNOWN_ERROR -> context.getString(androidx.compose.foundation.layout.R.string.unknown_error)
//            else -> null
//        }
//        message?.let {
//            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
//            onEvent(TranslateEvent.OnErrorSeen)
//        }
//    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(HomeIntent.GetNowPlaying)
        viewModel.onEvent(HomeIntent.GetPeople)
    }

    val nowPlaying = homeUiState.nowPlaying.collectAsLazyPagingItems()

    val lazyListState = rememberLazyListState()
    val isCarouselVisible = remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    var currentItem by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex }
            .collect { index ->
                currentItem = index
                isCarouselVisible.value = index == 0
            }
    }

    LaunchedEffect(nowPlaying) {
        while (true) {
            delay(3000L)

            val nextIndex = lazyListState.firstVisibleItemIndex + 1
            if (nextIndex < nowPlaying.itemCount) {
                coroutineScope.launch {
                    lazyListState.animateScrollToItem(nextIndex)
                }
            } else {
                coroutineScope.launch {
                    lazyListState.animateScrollToItem(0)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {

            item {
                LazyRow(
                    state = lazyListState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillParentMaxHeight(0.65f),
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(nowPlaying.itemCount) { index ->
                        val item = nowPlaying[index]
                        item?.let {
                            Box(
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .fillParentMaxHeight()// Ensures each item fills the width of the screen
                            ) {
                                Image(
                                    painter = rememberImagePainter(data = item.imageUrl),
                                    contentDescription = item.title,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.FillBounds
                                )

                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(brush)
                                ) {

                                    Row(
                                        modifier = Modifier
                                            .align(Alignment.BottomEnd)
                                            .fillParentMaxWidth(),
                                        verticalAlignment = Alignment.Bottom,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(
                                                start = 16.dp,
                                                bottom = 18.dp
                                            )
                                        ) {
                                            Text(
                                                text = item.title,
                                                color = Color.White,
                                                fontSize = 20.sp,
                                                fontFamily = FontFamily(
                                                    Font(R.font.pop_sembold)
                                                )
                                            )
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Image(
                                                    painter = painterResource(R.drawable.ic_star),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .size(24.dp)
                                                        .padding(end = 6.dp)
                                                )
                                                Text(
                                                    text = formatNumber(item.vote_average),
                                                    color = Color(0xFFFFA500)
                                                )
                                            }

                                        }

                                        Box(
                                            modifier = Modifier
                                                .padding(end = 16.dp, bottom = 18.dp)
                                                .clip(RoundedCornerShape(24.dp))
                                                .width(120.dp), contentAlignment = Alignment.Center
                                        ) {

                                            Button(
                                                modifier = Modifier
                                                    .align(Alignment.BottomEnd)
//                                                    .padding(end = 16.dp, bottom = 18.dp)
                                                    .clip(RoundedCornerShape(24.dp))
                                                    .width(120.dp)
                                                    .graphicsLayer {
                                                        scaleX = 1.2f
                                                        scaleY = 1.2f
                                                    }
                                                    .blur(16.dp),
                                                shape = RoundedCornerShape(24.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = Color(
                                                        0xFF751111
                                                    ), contentColor = Color.White
                                                ),
                                                onClick = {
                                                    navController.navigate(
                                                        Screens.Details.createRoute(
                                                            item.id
                                                        )
                                                    )
                                                }) {
                                                Text(
                                                    text = "Watch now",
                                                    fontSize = 20.sp,
                                                    maxLines = 1,
                                                    modifier = Modifier.fillMaxWidth(),
                                                    textAlign = TextAlign.Center,
                                                    color = Color.Black
                                                )
                                            }
                                            Text(
                                                text = "Watch now",
                                                fontSize = 16.sp,
                                                maxLines = 1,
                                                textAlign = TextAlign.Center,
                                                color = Color.White,
                                            )
                                        }

                                    }

                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "People",
                    style = MaterialTheme.typography.headlineMedium.copy(color = Color.White),
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(
                        Font(R.font.pop_reg)
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow {
                    item {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    items(homeUiState.people.size) {
                        val item = homeUiState.people[it]
                        PeopleItemView(peopleItem = item, onItemClick = {
//                            navController.navigate("detail/${item.id}")
                        })
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    item {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Now Playing",
                    style = MaterialTheme.typography.headlineMedium.copy(color = Color.White),
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(
                        Font(R.font.pop_reg)
                    )
                )
                LazyRow {
                    items(nowPlaying.itemCount) { index ->
                        val item = nowPlaying[index]
                        item?.let {
                            MovieItemView(
                                movieItem = MovieItem(
                                    title = it.title,
                                    imageUrl = it.orgImage,
                                    age = "+18"
                                )
                            )
                        }
                    }
                }
            }

            nowPlaying.loadState.apply {
                when {
                    refresh is LoadStateNotLoading && nowPlaying.itemCount < 1 -> {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No Items",
                                    modifier = Modifier.align(Alignment.Center),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    refresh is LoadStateLoading -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    color = Color.Red
                                )
                            }
                        }
                    }

                    append is LoadStateLoading -> {
                        item {
                            CircularProgressIndicator(
                                color = Color.Red,
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .padding(20.dp)
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                            )
                        }
                    }

                    refresh is LoadStateError -> {
                        val error = refresh as LoadState.Error
                        item {
                            ErrorView(
                                message = error.error.message ?: error.error.localizedMessage,
                                onClickRetry = { nowPlaying.retry() },
                                modifier = Modifier.fillMaxWidth(1f)
                            )
                        }
                    }

                    append is LoadStateError -> {
                        val error = append as LoadState.Error
                        item {
                            ErrorItem(
                                message = error.error.message ?: error.error.localizedMessage,
                                onClickRetry = { nowPlaying.retry() },
                            )
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(72.dp)) }
        }
    }
}