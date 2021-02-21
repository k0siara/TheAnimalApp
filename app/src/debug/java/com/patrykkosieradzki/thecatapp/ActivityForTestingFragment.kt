package com.patrykkosieradzki.thecatapp

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class ActivityForTestingFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity)
    }

    fun initWithNavigation(
        @IdRes destinationId: Int,
        @NavigationRes navigationId: Int
    ) {
        with(findNavController(R.id.test_fragment)) {
            val graphToSet = navInflater.inflate(navigationId)
            graphToSet.startDestination = destinationId
            setGraph(graphToSet, intent.extras)
        }
    }
}