package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.fragment.app.viewModels

class GameViewModel: ViewModel() {

    private var _score = 0
    private var _currentWordCount = 0
    private var _currentScrambleWord = "text"
    private var _count = 0
    private var wordsList: MutableList<String> = mutableListOf()

    private lateinit var currentWord: String
    init {
        getNextWord()
    }
    val currentWordCount: Int
        get() = _currentWordCount
    val currentScrambleWord: String
        get() = _currentScrambleWord
    val count: Int
        get() = _count
    val score: Int
        get() = _score

   fun getNextWord(){
       currentWord = allWordsList.random()
       val tempWord = currentWord.toCharArray()
       tempWord.shuffle()
       while(String(tempWord).equals(currentWord, false)){
           tempWord.shuffle()
       }
       if (wordsList.contains(currentWord)){
           getNextWord()
       } else {
           _currentScrambleWord = String(tempWord)
           ++_currentWordCount
           wordsList.add(currentWord)
       }
   }
    fun nextWord(): Boolean {
        return if (_currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }
    fun isUserWordCorrect(playerWord: String): Boolean{
        Log.e("some", currentWord)
        if (playerWord.equals(currentWord, true)){
            increaseScore()
            return true
        }
        return false
    }
    private fun increaseScore(){
        _score += SCORE_INCREASE
    }
    fun reinitializeData(){
        _currentWordCount = 0
        _score = 0
        wordsList.clear()
        getNextWord()
    }
}