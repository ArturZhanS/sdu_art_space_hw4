package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var currentArtwork by remember { mutableStateOf(Artwork.MONA_LISA) }
    val navigatorArtwork = remember {ArtNavigator()}
    val currentArtData = navigatorArtwork.getArtData(currentArtwork)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(0.6f))
            ImageSection(Modifier.weight(1f), currentArtData = currentArtData)
            Spacer(modifier = Modifier.heightIn(80.dp))
            DescriptionSection(currentArtData = currentArtData)
            Spacer(modifier = Modifier.heightIn(16.dp))
            ButtonNavigationSection(
                onPreviousClick = {
                    currentArtwork = navigatorArtwork.switchArtwork(currentArtwork, false)
                },
                onNextClick = {
                    currentArtwork = navigatorArtwork.switchArtwork(currentArtwork, true)
                },
            )
        }
    }
}

@Composable
private fun ImageSection(modifier: Modifier = Modifier, currentArtData: ArtData) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .shadow(elevation = 16.dp)
            .background(Color.White)
            .border(width = 1.dp, color = Color.Gray)
            .padding(40.dp),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(currentArtData.image),
            contentDescription = null
        )
    }
}

@Composable
private fun DescriptionSection(currentArtData: ArtData) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        color = colorResource(R.color.description_background),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(currentArtData.title),
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(currentArtData.artist))
                    }
                    append(" ")
                    append(stringResource(currentArtData.year))
                }
            )
        }
    }
}

@Composable
private fun ButtonNavigationSection(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = colorResource(R.color.button_blue),
    )

    Row(
        modifier = Modifier.fillMaxWidth().padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onPreviousClick,
            colors = buttonColors,
            modifier = Modifier.width(150.dp)
        ) {
            Text(
                text = stringResource(R.string.previous),
            )
        }
        Button(
            onClick = onNextClick,
            colors = buttonColors,
            modifier = Modifier.width(150.dp)
        ) {
            Text(
                text = stringResource(R.string.next),
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceAppTheme() {
        ArtSpaceApp()
    }
}