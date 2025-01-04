package dev.soul.moviedbkmp.android.presentation.screen.detail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import dev.soul.moviedbkmp.android.R
import dev.soul.moviedbkmp.data.network.dto.movie.actors.MovieActorsItem

@Composable
fun CategoryItem(name: String) {
    Box(contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier
                .height(32.dp)
                .clip(RoundedCornerShape(12.dp))
                .graphicsLayer {
                    alpha = 0.8f
                }
                .blur(16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF861313).copy(alpha = 0.8f))
        ) {
            Text(
                "$name",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 12.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = 13.sp,
                fontFamily = FontFamily(
                    Font(
                        R.font.pop_reg
                    )
                ), color = Color.White
            )
        }
        Text(
            "$name",
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 12.dp),
            fontSize = 13.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.pop_reg
                )
            ), color = Color(0xFFDAD7D7)
        )
    }
}

@Composable
fun CustomTabBar(onTabSelected: (Int) -> Unit) {
    val tabs = listOf("Actors", "Similar Movies")
    var selectedTabIndex by remember { mutableStateOf(0) }

    LaunchedEffect(selectedTabIndex) {
        onTabSelected(selectedTabIndex)
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .background(color = Color.Black, shape = RoundedCornerShape(12.dp))
    ) {
        TabRow(
            modifier = Modifier
                .padding(4.dp)
                .background(color = Color.Black, shape = RoundedCornerShape(12.dp)),
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Black,
            contentColor = Color.Black,
            indicator = { /* No indicator */ },
            divider = { /* No divider */ }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    modifier = Modifier
                        .background(
                            color = if (selectedTabIndex == index) Color(0xFF861313) else Color.Black,
                            shape = RoundedCornerShape(12.dp)
                        ).padding(horizontal = 4.dp),
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTabIndex == index) Color.White else Color.Gray
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun Overview(text: String) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Overview : ", fontSize = 18.sp, color = Color.White, fontFamily = FontFamily(
                Font(R.font.pop_sembold)
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "$text",
            fontSize = 13.sp,
            color = Color.White.copy(alpha = 0.8f),
            fontFamily = FontFamily(
                Font(R.font.pop_reg)
            )
        )
    }
}
