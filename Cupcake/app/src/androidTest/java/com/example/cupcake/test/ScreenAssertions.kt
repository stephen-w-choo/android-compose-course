package com.example.cupcake.test

import android.util.Log
import androidx.navigation.NavController
import junit.framework.Assert.assertEquals
import org.junit.Assert

fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    Assert.assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}