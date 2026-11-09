
package com.example.geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.geoquiz.ui.theme.GeoQuizTheme

data class QuizQuestion(
    val text: String,
    val answer: Boolean
)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            GeoQuizTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MainScreen()
                }
            }
            val questions = listOf(
                QuizQuestion(
                    text = "Столица Австралии — Канберра.",
                    answer = true
                ),
                QuizQuestion(
                    text = "Тихий океан больше Атлантического океана.",
                    answer = true
                ),
                QuizQuestion(
                    text = "Суэцкий канал соединяет Красное море и Индийский океан.",
                    answer = false
                ),
                QuizQuestion(
                    text = "Исток реки Нил находится в Египте.",
                    answer = false
                ),
                QuizQuestion(
                    text = "Река Амазонка — самая длинная река Америки.",
                    answer = true
                ),
                QuizQuestion(
                    text = "Озеро Байкал — самое древнее и глубокое пресноводное озеро в мире.",
                    answer = true
                )
            )
        }
    }
}

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "GeoQuiz",
            fontSize = 32.sp
        )
    }
}
