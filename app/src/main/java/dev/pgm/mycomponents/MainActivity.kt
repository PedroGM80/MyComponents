package dev.pgm.mycomponents

import AnimatedMicrophoneButton
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import dev.pgm.mycomponents.ui.theme.MyComponentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyComponentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val context = LocalContext.current
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        val context = LocalContext.current

                        TextToSpeechSpeedSelector(
                            onSpeedChange = { speed ->
                                // Aquí puedes configurar la velocidad de tu TextToSpeech
                                Toast.makeText(context, "Velocidad cambiada a $speed", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyComponentsTheme {
        Greeting("Android")
    }
}