package com.patrykkosieradzki.theanimalapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.patrykkosieradzki.theanimalapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

//    fun navigateBackWithResult(result: Bundle) {
//        val childFragmentManager =
//            supportFragmentManager.findFragmentById(R.id.nav_graph)?.childFragmentManager
//        var backStackListener: FragmentManager.OnBackStackChangedListener by Delegates.notNull()
//        backStackListener = FragmentManager.OnBackStackChangedListener {
//            (childFragmentManager?.fragments?.get(0) as NavigationResult).onNavigationResult(result)
//            childFragmentManager.removeOnBackStackChangedListener(backStackListener)
//        }
//        childFragmentManager?.addOnBackStackChangedListener(backStackListener)
//        findNavController(R.id.nav_graph).popBackStack()
//    }
}
