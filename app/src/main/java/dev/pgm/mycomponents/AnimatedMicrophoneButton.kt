import android.widget.Toast
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicNone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedMicrophoneButton(
    onMicrophoneStateChange: (Boolean) -> Unit = {}
) {
    var isListening by remember { mutableStateOf(false) }

    // Colores definidos
    val activeButtonColor = Color(0xFFFF1744) // Rojo intenso
    val inactiveButtonColor = Color(0xffb84e6c) // Gris carbón
    val activeWaveColor = Color(0xFF4E5CDE).copy(alpha = 0.3f)
    val inactiveWaveColor = Color(0xFF455A64).copy(alpha = 0.3f)

    // Animación de ondas
    val infiniteTransition = rememberInfiniteTransition(label = "mic-waves")
    val waveAnimations = listOf(
        infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.5f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "wave1"
        ),


        infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.5f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, delayMillis = 400, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "wave3"
        )
    )

    Box(
        modifier = Modifier.size(100.dp),
        contentAlignment = Alignment.Center
    ) {
        // Animación de ondas
        if (isListening) {
            waveAnimations.forEachIndexed { index, animation ->
                Box(
                    modifier = Modifier
                        .size(80.dp + (index * 20).dp)
                        .scale(animation.value)
                        .clip(CircleShape)
                        .background(
                            if (isListening) activeWaveColor
                            else inactiveWaveColor
                        )
                )
            }
        }

        // Botón de micrófono
        IconButton(
            onClick = {
                isListening = !isListening
                onMicrophoneStateChange(isListening)
            },
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(
                    if (isListening) activeButtonColor else inactiveButtonColor
                )
        ) {
            Icon(
                imageVector = if (isListening) Icons.Default.Mic else Icons.Default.MicNone,
                contentDescription = if (isListening) "Detener Escucha" else "Iniciar Escucha",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

// Ejemplo de uso
@Preview
@Composable
fun MicrophoneButtonPreview() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        AnimatedMicrophoneButton(
            onMicrophoneStateChange = { isListening ->
                if (isListening) {
                    Toast.makeText(context, "Escuchando...", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Detenido", Toast.LENGTH_LONG).show()
                }
            })
    }
}
