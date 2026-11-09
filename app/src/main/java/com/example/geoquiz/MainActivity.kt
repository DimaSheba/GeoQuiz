
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.geoquiz.ui.theme.GeoQuizTheme

data class QuizQuestion(
    val text: String,
    val answer: Boolean
)

val questions = listOf(
    QuizQuestion(
        "Столица Австралии — Канберра.",
        true
    ),
    QuizQuestion(
        "Тихий океан больше Атлантического океана.",
        true
    ),
    QuizQuestion(
        "Суэцкий канал соединяет Красное море и Индийский океан.",
        false
    ),
    QuizQuestion(
        "Исток реки Нил находится в Египте.",
        false
    ),
    QuizQuestion(
        "Река Амазонка — самая длинная река Америки.",
        true
    ),
    QuizQuestion(
        "Озеро Байкал — самое древнее и самое глубокое пресноводное озеро в мире.",
        true
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            GeoQuizTheme {
                GeoQuizScreen()
            }
        }
    }
}

@Composable
fun GeoQuizScreen() {
    var currentQuestion by remember { mutableIntStateOf(0) }
    var correctAnswers by remember { mutableIntStateOf(0) }
    var answered by remember { mutableStateOf(false) }
    var showResult by remember { mutableStateOf(false) }

    val question = questions[currentQuestion]
    val isLastQuestion = currentQuestion == questions.lastIndex

    fun checkAnswer(userAnswer: Boolean) {
        if (!answered) {
            answered = true

            if (userAnswer == question.answer) {
                correctAnswers++
            }

            if (isLastQuestion) {
                showResult = true
            }
        }
    }

    fun restartQuiz() {
        currentQuestion = 0
        correctAnswers = 0
        answered = false
        showResult = false
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "GeoQuiz",
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.primary
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
                textAlign = TextAlign.Center,
                lineHeight = 30.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Кнопки ответа скрываются после выбора ответа.
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

            Spacer(modifier = Modifier.height(24.dp))

            // После последнего вопроса кнопка Next не отображается.
            if (answered && !isLastQuestion) {
                Button(
                    onClick = {
                        currentQuestion++
                        answered = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Следующий вопрос")
                }
            }
        }
    }

    // Всплывающее окно с результатами теста.
    if (showResult) {
        AlertDialog(
            onDismissRequest = {
                showResult = false
            },
            title = {
                Text("Тест завершён!")
            },
            text = {
                Text(
                    "Правильных ответов: " +
                            "$correctAnswers из ${questions.size}"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        restartQuiz()
                    }
                ) {
                    Text("Пройти заново")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showResult = false
                    }
                ) {
                    Text("Закрыть")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GeoQuizPreview() {
    GeoQuizTheme {
        GeoQuizScreen()
    }
}
