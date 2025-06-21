package com.pdm.saec.coderplus.data

data class QuizQuestionRaw(
    val id: Int,
    val topic: String,
    val type: String,
    val difficulty: String,
    val question: QuestionText,
    val answers: List<AnswerOption>
)

data class QuestionText(
    val text: String
)

data class AnswerOption(
    val answer: String,
    val correct: Boolean? = false
)
