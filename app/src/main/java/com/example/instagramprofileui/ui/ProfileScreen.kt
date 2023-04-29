package com.example.instagramprofileui.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instagramprofileui.R

@Composable
fun ProfileScreen() {

    val scrollState = rememberScrollState()

    var selectedTab by remember {
        mutableStateOf(0)
    }

    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF000000))
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            TopBar(name = "spacex")
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF000000))
                .verticalScroll(state = scrollState, enabled = true)

        ) {

            Spacer(modifier = Modifier.height(15.dp))
            ProfileDetails(modifier = Modifier)
            Spacer(modifier = Modifier.height(15.dp))
            ButtonsSection(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(15.dp))
            HighlightSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), listOf(
                    ImageWithText(
                        "Nasa", painterResource(
                            id = R.drawable.h_2
                        )
                    ),
                    ImageWithText(
                        "Nasa", painterResource(
                            id = R.drawable.h_2
                        )
                    ),
                    ImageWithText(
                        "SpaceX", painterResource(
                            id = R.drawable.h_2
                        )
                    ),
                    ImageWithText(
                        "The new future", painterResource(
                            id = R.drawable.h_2
                        )
                    ),
                    ImageWithText(
                        "The new future", painterResource(
                            id = R.drawable.h_2
                        )
                    )
                )
            )


            val tabList = listOf(
                ImageWithText(image = painterResource(id = R.drawable.ic_grid)),
                ImageWithText(image = painterResource(id = R.drawable.ic_reels)),
                ImageWithText (image = painterResource(id = R.drawable.profile)),
            )

            Spacer(modifier = Modifier.height(10.dp))

            TapView(tabList = tabList, inactiveColor = Color(0xF1838383)) {
                selectedTab = it
            }

            val postList = listOf(
                painterResource(id = R.drawable.post_1),
                painterResource(id = R.drawable.post_1),
                painterResource(id = R.drawable.post_1),
                painterResource(id = R.drawable.post_1),
                painterResource(id = R.drawable.post_1),
                painterResource(id = R.drawable.post_1),
                painterResource(id = R.drawable.post_1),
                painterResource(id = R.drawable.post_1),
                painterResource(id = R.drawable.post_1),
                painterResource(id = R.drawable.post_1),
                painterResource(id = R.drawable.post_1),
                painterResource(id = R.drawable.post_1),
                painterResource(id = R.drawable.post_1),
                painterResource(id = R.drawable.post_1),


//                painterResource(id = R.drawable.rocket),
//        painterResource(id = R.drawable.post_4),
//        painterResource(id = R.drawable.post_5),
//        painterResource(id = R.drawable.post_6),
//                painterResource(id = R.drawable.post_6),
//        painterResource(id = R.drawable.post_8)
            )

            when (selectedTab) {
                0 -> PostsSection(postList = postList, modifier = Modifier.fillMaxWidth())
            }
        }
    }


}

@Composable
fun TapView(
    modifier: Modifier = Modifier,
    tabList: List<ImageWithText>,
    inactiveColor: Color,
    onTabSelected: (Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = Color.White,
        modifier = modifier
    ) {
        tabList.forEachIndexed { index, item ->
            Tab(
                selected = index == selectedTabIndex,
                selectedContentColor = Color.White,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(selectedTabIndex)
                }
            ) {
                Icon(
                    painter = item.image,
                    contentDescription = item.name,
                    tint = if (selectedTabIndex == index) Color.White else inactiveColor,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp)
                )
            }
        }
    }
}

@Composable
fun PostsSection(
    modifier: Modifier = Modifier,
    postList: List<Painter>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .scale(1.01f)
            .height(250.dp)
    ) {
        items(postList.size) {
            Image(
                painter = postList[it],
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(width = 1.dp, color = Color.Black)
            )
        }
    }
}

@Composable
fun RoundImage(
    modifier: Modifier = Modifier,
    image: Painter,
    drawBorder: Boolean = true
) {

    if (drawBorder) {
        Image(
            painter = image, contentDescription = "", modifier = modifier
                .aspectRatio(1f)
                .border(
                    width = 1.dp,
                    color = Color(0xFF414141),
                    shape = CircleShape
                )
                .padding(3.dp)
                .clip(CircleShape)
        )
    } else {
        Image(
            painter = image, contentDescription = "", modifier = modifier
                .aspectRatio(1f)
                .clip(CircleShape)
        )
    }
}

@Composable
fun TopBar(
    name: String,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow back",
                tint = Color.White,
                modifier = modifier.size(20.dp)
            )
            Spacer(modifier = modifier.width(20.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = modifier.width(4.dp))

                Image(
                    painter = painterResource(id = R.drawable.verified),
                    contentDescription = "Verified",
                    modifier = modifier
                        .size(18.dp)
                        .padding(2.dp)
                )
            }

        }
        Spacer(modifier = modifier.width(150.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_bell),
            contentDescription = "Menu",
            tint = Color.White,
            modifier = modifier.size(19.dp)
        )
        Spacer(modifier = modifier.width(20.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_dotmenu),
            contentDescription = "Menu",
            tint = Color.White,
            modifier = modifier.size(19.dp)
        )
    }
}

@Composable
fun StatSection(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        ProfileStat(statName = "Posts", statNumber = "101")
        ProfileStat(statName = "Followers", statNumber = "16.5M")
        ProfileStat(statName = "Following", statNumber = "3")
    }
}

@Composable
fun ProfileStat(
    modifier: Modifier = Modifier,
    statName: String,
    statNumber: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = statNumber, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
        Spacer(modifier = modifier.height(4.dp))
        Text(text = statName, color = Color.White)
    }
}

@Composable
fun ButtonsSection(
    modifier: Modifier = Modifier
) {
    val minWidth = 95.dp
    val height = 30.dp

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.padding(10.dp)
    ) {
        ActionButton(
            modifier = Modifier
                .size(150.dp, height),
            "Following",
            Icons.Default.KeyboardArrowDown
        )

        Spacer(modifier = Modifier.width(5.dp))
        ActionButton(
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .size(150.dp, height),
            "Message"
        )
        Spacer(modifier = Modifier.width(5.dp))
        ActionButton(
            modifier = Modifier
                .height(height),
            icon = Icons.Default.KeyboardArrowDown
        )
    }

}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: ImageVector? = null
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(Color(0xFD424242))
            .padding(6.dp)
    ) {
        if (text != null) {
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }

        if (icon != null) {
            Icon(imageVector = icon, contentDescription = "", tint = Color.White)
        }
    }
}


@Composable
fun ProfileDescription(
    modifier: Modifier,
    name: String,
    bio: String,
    followedBy: List<String>,
    url: String = "",
    otherCount: Int
) {

    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        Text(
            text = name,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            lineHeight = lineHeight,
            letterSpacing = letterSpacing
        )
        Text(
            text = bio,
            color = Color.White,
            lineHeight = lineHeight,
            letterSpacing = letterSpacing
        )

        Text(
            text = url,
            color = Color(0xFFD7DCE0),
            lineHeight = lineHeight,
            letterSpacing = letterSpacing
        )

        Text(
            text = buildAnnotatedString {
                val boldStyle = SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)
                append("Followed by ")
                followedBy.forEachIndexed { index, name ->
                    pushStyle(boldStyle)
                    append(name)
                    pop()

                    if (index < followedBy.size - 1) {
                        append(", ")
                    }
                }
                if (otherCount > 2) {
                    append(" and ")
                    pushStyle(boldStyle)
                    append("$otherCount others")
                }
            },
            color = Color.White,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )

    }
}

@Composable
fun ProfileDetails(modifier: Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            RoundImage(
                image = painterResource(id = R.drawable.space_x),
                modifier = modifier
                    .size(100.dp)
                    .weight(3f),
                drawBorder = false
            )
            StatSection(
                modifier = Modifier
                    .weight(7f)
                    .padding(10.dp)
            )
            Spacer(modifier = modifier.height(10.dp))
        }

        ProfileDescription(
            modifier = modifier.padding(10.dp),
            name = "SpaceX",
            "SpaceX designs, manufactures and launches the\n" +
                    "world's most advanced rockets and spacecraft",
            listOf("joseph.zaki", "nasa"),
            otherCount = 4,
            url = "/spacex.com"
        )
    }
}

@Composable
fun HighlightSection(
    modifier: Modifier = Modifier,
    highlightList: List<ImageWithText>
) {
    LazyRow(modifier = modifier) {
        items(highlightList.size) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(15.dp)
            ) {
                RoundImage(
                    modifier = Modifier.size(70.dp),
                    image = highlightList[it].image,
                    drawBorder = true
                )
                Text(
                    text = highlightList[it].name,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
