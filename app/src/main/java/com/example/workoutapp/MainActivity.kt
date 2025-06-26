package com.example.workoutapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.workoutapp.ui.WorkoutScreen
import com.example.workoutapp.ui.theme.WorkoutAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: WorkoutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutAppTheme {
                WorkoutScreen(viewModel = viewModel)
            }
        }
    }
}
