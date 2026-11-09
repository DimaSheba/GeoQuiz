
package com.example.geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.geoquiz.ui.theme.GeoQuizTheme

data class QuizQuestion(
    val text: String,
    val answer: Boolean
)

val questions = listOf(
    QuizQuestion("Столица Австралии — Канберра.", true),
    QuizQuestion("Тихий океан больше Атлантического океана.", true),
    QuizQuestion("Суэцкий канал соединяет Красное море и Индийский океан.", false),
    QuizQuestion("Исток реки Нил находится в Египте.", false),
    QuizQuestion("Река Амазонка — самая длинная река Америки.", true),
    QuizQuestion("Озеро Байкал — самое древнее и глубокое пресноводное озеро в мире.", true)
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
        }
    }
}

@Composable
fun MainScreen() {
    val currentQuestion = 0

    var answered by remember { mutableStateOf(false) }
    var correctAnswers by remember { mutableIntStateOf(0) }

    val question = questions[currentQuestion]

    fun checkAnswer(userAnswer: Boolean) {
        if (!answered) {
            answered = true

            if (userAnswer == question.answer) {
                correctAnswers++
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "GeoQuiz",
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Вопрос ${currentQuestion + 1} из ${questions.size}",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = question.text,
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (!answered) {
            Button(
                onClick = { checkAnswer(true) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Верно")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { checkAnswer(false) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Неверно")
            }
        }
    }
}
