package com.plass.travling.ui.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plass.travling.R
import com.plass.travling.ui.component.Coupon
import com.plass.travling.ui.component.TVTopAppBar
import com.plass.travling.ui.component.bounceClick
import com.plass.travling.ui.feature.root.NavRoot
import com.plass.travling.ui.theme.TravelingTheme

@Composable
fun HomeScreen(
    navController: NavController
) {
    var location by remember {
        mutableStateOf("전체")
    }
    val testItems by remember {
        mutableStateOf(listOf(
            CouponModel(0, "40% 할인 쿠폰", "다른 쿠폰와 교환 가능", "대구"),
            CouponModel(1, "100% 할인 쿠폰", "형이 쏜다", "대구"),
            CouponModel(2, "10% 할인 쿠폰", "\"예성이\"와 교환 가능", "대구"),
        ))
    }

    Column(
        modifier = Modifier
            .background(Color(0xFFF4F5F9))
            .fillMaxSize()
    ) {
        TVTopAppBar(
            text = "홈",
            backgroundColor = Color(0xFF0078F9)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .padding(start = 12.dp)
                    .padding(vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Column {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "지금까지 총",
                            color = TravelingTheme.colorScheme.White,
                            style = TravelingTheme.typography.bodyRegular
                        )
                        Text(
                            text = "51 트랩",
                            color = TravelingTheme.colorScheme.White,
                            style = TravelingTheme.typography.title1B
                        )
                        Spacer(modifier = Modifier.height(11.dp))
                    }
                    Image(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(170.dp)
                            .padding(bottom = 11.dp, end = 0.dp),
                        painter = painterResource(id = R.drawable.ic_emoji),
                        contentDescription = ""
                    )

                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier.padding(
                start = 12.dp,
                top = 4.dp,
                bottom = 4.dp
            ),
            text = "사용가능한 트랩이\n3개 남아있어요",
            color = TravelingTheme.colorScheme.Black,
            style = TravelingTheme.typography.headline2B
        )
        LazyRow(
            modifier = Modifier
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(arrayListOf("전체", "대구", "부산", "서울", "광주", "인천", "공주")) {
                LocationCell(text = it, selected = it == location) {
                    location = it
                }
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(testItems) {
                Coupon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .bounceClick {
                            navController.navigate(
                                NavRoot.COUPON.replace("{id}", "${it.id}")
                            )
                        },
                    title = it.title,
                    description = it.description,
                    category = it.category,
                    isHome = true
                )
            }
        }
    }
}

@Composable
fun LocationCell(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val w = if (selected) 1.5.dp else 1.dp
    val c = if (selected) TravelingTheme.colorScheme.Blue else Color(0xFFE3E3E3)
    val tc = if (selected) TravelingTheme.colorScheme.Blue else Color(0xFF646464)
    Box(
        modifier = Modifier
            .bounceClick(onClick = onClick)
            .border(width = w, color = c, shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            style = TravelingTheme.typography.labelMedium,
            color = tc
        )
    }
}

data class CouponModel(
    val id: Int = 0,
    val title: String,
    val description: String,
    val category: String
)