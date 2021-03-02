package com.patrykkosieradzki.theanimalapp.utils

import android.os.Bundle
import androidx.test.internal.runner.filters.ParentFilter
import com.patrykkosieradzki.theanimalapp.BuildConfig
import org.junit.runner.Description

class FlavorTestFilter(bundle: Bundle) : ParentFilter() {

    override fun evaluateTest(description: Description): Boolean {
        val classTestFilter = description.testClass.getAnnotation(FlavorTest::class.java)
        val testFilter = description.getAnnotation(FlavorTest::class.java)
        if (testFilter != null) {
            return evaluateTestWithFilter(testFilter)
        } else if (classTestFilter != null) {
            return evaluateTestWithFilter(classTestFilter)
        }
        return true
    }

    private fun evaluateTestWithFilter(flavorTest: FlavorTest): Boolean {
        val flavors = flavorTest.flavors
        return flavors.contains(BuildConfig.FLAVOR)
    }

    override fun describe(): String {
        return "Flavor filter"
    }
}
