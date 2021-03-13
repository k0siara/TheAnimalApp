package com.patrykkosieradzki.theanimalapp.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.patrykkosieradzki.theanimalapp.R
import kotlinx.android.synthetic.main.bottom_navigation_drawer_fragment.bottom_navigation_view

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_navigation_drawer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bottom_navigation_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_cat_breeds -> Toast.makeText(
                    requireContext(),
                    "Navigate to list of breeds",
                    Toast.LENGTH_SHORT
                ).show()
            }
            true
        }
    }
}
