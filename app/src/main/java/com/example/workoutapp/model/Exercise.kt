package com.example.workoutapp.model

import com.example.workoutapp.R


data class Exercise(
    val name: String,
    val description: String,
    val imageRes: Int,
    val duration: Int
)




val exerciseList = listOf(
    Exercise(
        name = "Jumping Jacks",
        description = "Springe aus dem Stand, Arme über den Kopf, Beine gespreizt. Kehre in Ausgangsposition zurück. Gleichmäßiger Rhythmus.",
        duration = 30,
        imageRes = R.drawable.jumping_jacks
    ),
    Exercise(
        name = "Squats",
        description = "Stelle dich schulterbreit hin. Beuge die Knie und senke das Gesäß. Rücken gerade, Gewicht auf den Fersen.",
        duration = 30,
        imageRes = R.drawable.squats
    ),
    Exercise(
        name = "Plank",
        description = "Stütze dich auf Unterarme und Zehen. Halte den Körper gerade, Po angespannt, nicht durchhängen lassen.",
        duration = 30,
        imageRes = R.drawable.plank
    )
)



