package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {
            1 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.lemon_select,
                    drawableResourceId = R.drawable.lemon_tree,
                    contentDescriptionResourceId = R.string.lemon_tree_content_description,
                    onImageClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    }
                )
            }

            2 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.lemon_squeeze,
                    drawableResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionResourceId = R.string.lemon_content_description,
                    onImageClick = {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    }
                )
            }

            3 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.lemon_drink,
                    drawableResourceId = R.drawable.lemon_drink,
                    contentDescriptionResourceId = R.string.lemonade_content_description,
                    onImageClick = {
                        currentStep = 4
                    }
                )
            }

            4 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.lemon_empty_glass,
                    drawableResourceId = R.drawable.lemon_restart,
                    contentDescriptionResourceId = R.string.empty_glass_content_description,
                    onImageClick = {
                        currentStep = 1
                    }
                )
            }
        }

    }
}

@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                modifier = Modifier
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)

            ) {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = stringResource(contentDescriptionResourceId),
                    modifier = Modifier.padding(26.dp)
                )
            }
            Spacer(modifier = Modifier.height(64.dp))
            Text(
                text = stringResource(textLabelResourceId),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(86.dp))
            Text(text = stringResource(R.string.my_name), fontSize = 24.sp)
            Text(text = stringResource(R.string.my_code), fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}