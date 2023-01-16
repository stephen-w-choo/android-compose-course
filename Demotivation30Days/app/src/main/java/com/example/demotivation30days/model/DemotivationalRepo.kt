package com.example.demotivation30days.model

import com.example.demotivation30days.R


// object represents a singleton for encapsulation
// kind of like how you can declare a prototype in JS without requring a class that you then instantiate
// manually typing each one in seems tedious - I'm going to loop through the string xml instead
object DemotivationalRepo {
    val demotivationals = listOf(
        Demotivational(
            title = R.string.day1_name,
            tagline = R.string.day1_description,
            image = R.drawable.day1_tradition
        ),
        Demotivational(
            title = R.string.day2_name,
            tagline = R.string.day2_description,
            image = R.drawable.day2_meetings
        ),
        Demotivational(
            title = R.string.day3_name,
            tagline = R.string.day3_description,
            image = R.drawable.day3_mistakes
        ),
        Demotivational(
            title = R.string.day4_name,
            tagline = R.string.day4_description,
            image = R.drawable.day4_adversity
        ),
        Demotivational(
            title = R.string.day5_name,
            tagline = R.string.day5_description,
            image = R.drawable.day5_multitasking
        ),
        Demotivational(
            title = R.string.day6_name,
            tagline = R.string.day6_description,
            image = R.drawable.day6_agony
        ),
        Demotivational(
            title = R.string.day7_name,
            tagline = R.string.day7_description,
            image = R.drawable.day7_ambition
        ),
        Demotivational(
            title = R.string.day8_name,
            tagline = R.string.day8_description,
            image = R.drawable.day8_limitations
        ),
        Demotivational(
            title = R.string.day9_name,
            tagline = R.string.day9_description,
            image = R.drawable.day9_irresponsibility
        ),
        Demotivational(
            title = R.string.day10_name,
            tagline = R.string.day10_description,
            image = R.drawable.day10_leadership
        )
    )
}