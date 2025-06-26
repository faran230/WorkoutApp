package com.example.workoutapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutapp.WorkoutViewModel
import com.example.workoutapp.model.exerciseList
import androidx.compose.foundation.shape.CircleShape



@Composable
fun WorkoutScreen(viewModel: WorkoutViewModel) {
    val exerciseIndex by remember { viewModel.exerciseIndex }
    val isResting by remember { viewModel.isResting }
    val timeLeft by remember { viewModel.timeLeft }
    val workoutCompleted by remember { viewModel.workoutCompleted }


    val currentExercise = exerciseList.getOrNull(exerciseIndex)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (currentExercise != null) {
            Image(
                painter = painterResource(id = currentExercise.imageRes),
                contentDescription = currentExercise.name,
                modifier = Modifier.size(200.dp)
            )
            Text(text = currentExercise.name, fontSize = 24.sp)
            Text(text = currentExercise.description, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (isResting) "Pause" else "Übung läuft",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "$timeLeft Sekunden",
            fontSize = 32.sp,
            color = if (isResting) Color.Gray else MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        LinearProgressIndicator(
            progress = (exerciseIndex + if (isResting) 0.5f else 0f) / exerciseList.size.toFloat(),
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )




        Spacer(modifier = Modifier.height(24.dp))

        if (workoutCompleted) {
            Text(
                text = "🎉 Super gemacht!",
                fontSize = 20.sp,
                color = Color(0xFFFF9800), // Orange
                modifier = Modifier.padding(top = 16.dp)
            )
        }


        Button(
            onClick = { viewModel.startWorkout() },
            enabled = exerciseIndex == 0 && timeLeft == 0 && !workoutCompleted,
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
            modifier = Modifier.size(100.dp)
        ) {
            Text("Start", color = Color.White)
        }
    }}
