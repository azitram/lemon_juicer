package com.example.lemonsqueeze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonsqueeze.ui.theme.LemonSqueezeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonSqueezeTheme {
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonSqueeze(modifier: Modifier = Modifier) {
    var clickCount by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    var randomTreshold by remember { mutableStateOf((2..4).random()) }

    val imageResource = when (clickCount) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textResource = when (clickCount) {
        1 -> R.string.lemon_tree_description
        2 -> R.string.lemon_squeeze_description
        3 -> R.string.lemon_drink_description
        else -> R.string.lemon_restart_description
    }
    CenterAlignedTopAppBar(
        title = {
        Text(
            text="Lemonade",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color(0xFFFFF49B))
    )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                if (clickCount == 2) {
                    squeezeCount ++
                    if (squeezeCount >= randomTreshold){
                        clickCount ++
                        squeezeCount = 0
                        randomTreshold = (2..4).random()
                    }
                }
                else {
                    clickCount++
                }
                if (clickCount > 4){
                    clickCount = 1
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC3DAD9)),
            shape = RoundedCornerShape(30.dp)
            ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = clickCount.toString(),
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(textResource),
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonSqueezeApp(modifier: Modifier = Modifier) {
    LemonSqueeze(modifier= Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}