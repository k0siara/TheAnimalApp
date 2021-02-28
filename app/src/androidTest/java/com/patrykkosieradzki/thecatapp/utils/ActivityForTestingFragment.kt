package com.patrykkosieradzki.thecatapp.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.patrykkosieradzki.thecatapp.R

class ActivityForTestingFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity)
    }
}