package dev.pgm.mycomponents

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextToSpeechSpeedSelector(
    speedList: List<Float> = listOf(0.5f, 0.75f, 1f, 1.25f, 1.5f),
    initSpeed: Int = 2,
    onSpeedChange: (Float) -> Unit = {}
) {
    var indexSpeed by remember { mutableIntStateOf(initSpeed) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        Text(
            text = "Velocidad de Voz",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = {
                    if (indexSpeed > 0) {
                        indexSpeed--
                        onSpeedChange(speedList[indexSpeed])
                    }
                },
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color.Red)

            ) {
                Icon(
                    imageVector = Icons.Filled.Remove,
                    contentDescription = "Disminuir velocidad"
                )
            }

            Text(
                text = "${speedList[indexSpeed]}x",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            IconButton(
                onClick = {
                    if (indexSpeed < speedList.size - 1) {
                        indexSpeed++
                        onSpeedChange(speedList[indexSpeed])
                    }
                },
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color.Green)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Aumentar velocidad"
                )
            }
        }
    }
}

@Preview
@Composable
fun EjemploSelectorVelocidadTTS() {
    val context = LocalContext.current

    TextToSpeechSpeedSelector(
        onSpeedChange = { speed ->
            // Aquí puedes configurar la velocidad de tu TextToSpeech
            Toast.makeText(context, "Velocidad cambiada a $speed", Toast.LENGTH_SHORT).show()
        }
    )
}