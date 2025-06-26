package com.example.workoutapp



import android.app.Application
import android.content.Context
import android.media.MediaPlayer
import android.os.CountDownTimer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.workoutapp.model.Exercise
import com.example.workoutapp.model.exerciseList

class WorkoutViewModel(application: Application) : AndroidViewModel(application) {

    val exerciseIndex = mutableStateOf(0)
    val isResting = mutableStateOf(false)
    val timeLeft = mutableStateOf(0)
    val workoutCompleted = mutableStateOf(false)


    private var timer: CountDownTimer? = null

    private val context = application.applicationContext

    fun startWorkout() {
        workoutCompleted.value = false // ← hinzufügen
        exerciseIndex.value = 0
        startExercise()
    }


    private fun startExercise() {
        isResting.value = false
        playSound(R.raw.start_exercise)
        val currentExercise = exerciseList.getOrNull(exerciseIndex.value) ?: return
        startTimer(currentExercise.duration) {
            startRest()
        }
    }

    private fun startRest() {
        isResting.value = true
        playSound(R.raw.start_rest)
        startTimer(10) {
            nextExerciseOrFinish()
        }
    }

    private fun nextExerciseOrFinish() {
        val nextIndex = exerciseIndex.value + 1
        if (nextIndex < exerciseList.size) {
            exerciseIndex.value = nextIndex
            startExercise()
        } else {
            playSound(R.raw.end)
            saveProgress()
            workoutCompleted.value = true // ← HIER einfügen
        }
    }


    private fun startTimer(seconds: Int, onFinish: () -> Unit) {
        timer?.cancel()
        timeLeft.value = seconds
        timer = object : CountDownTimer(seconds * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft.value = (millisUntilFinished / 1000).toInt()
            }

            override fun onFinish() {
                timeLeft.value = 0
                onFinish()
            }
        }.start()
    }




    private fun playSound(resId: Int) {
        val mediaPlayer = MediaPlayer.create(context, resId)
        mediaPlayer.setOnCompletionListener { it.release() }
        mediaPlayer.start()
    }

    private fun saveProgress() {
        val prefs = context.getSharedPreferences("progress", Context.MODE_PRIVATE)
        val completed = prefs.getInt("completed", 0)
        prefs.edit().putInt("completed", completed + 1).apply()
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}
