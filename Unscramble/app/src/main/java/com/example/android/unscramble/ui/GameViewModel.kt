package com.example.android.unscramble.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.android.unscramble.data.MAX_NO_OF_WORDS
import com.example.android.unscramble.data.SCORE_INCREASE
import com.example.android.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())

    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()
    // Makes _uiState private within GameViewModel but still allowing it to be read
    // Makes this mutable state flow read only state flow

    var userGuess by mutableStateOf("")
        private set

    private lateinit var currentWord: String
    // what does lateinit do?
    // non-null property where you want to initialise it but you don't know its properties yet

    private var usedWords: MutableSet<String> = mutableSetOf()
    // set to maintain list of words used

    private fun shuffleCurrentWord(word: String): String {
        val wordCharArray = word.toCharArray()
        wordCharArray.shuffle()
        while (String(wordCharArray) == (word)) {
            wordCharArray.shuffle()
        }
        return String(wordCharArray)
    }

    private fun pickRandomWordAndShuffle(): String {
        currentWord = allWords.random()
        if (usedWords.contains(currentWord)) {
            return pickRandomWordAndShuffle()
            // recursively calls function again until you get the desired result
        } else {
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }

    private fun progressGameState(updatedScore: Int) {
        if (_uiState.value.currentWordCount >= MAX_NO_OF_WORDS) {
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    isGameOver = true,
                    score = updatedScore
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    currentWordCount = currentState.currentWordCount.inc(),
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    score = updatedScore
                )
            }
        }
    }

    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }

    fun updateUserGuess(guessedWord: String) {
        userGuess = guessedWord
    }

    fun checkUserGuess() {
        if (userGuess.equals(currentWord, ignoreCase = true)) {
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            // renamed updateGameState function as it's specifically for when guess is correct.
            progressGameState(updatedScore)
        } else {
            _uiState.update { currentState ->
                currentState.copy(isGuessedWordWrong = true)
            }
        }
        updateUserGuess("")
    }

    fun skipWord() {
        progressGameState(_uiState.value.score)
        // maintains the score, unchanged
        updateUserGuess("")
    }

    init {
        resetGame()
    }
}