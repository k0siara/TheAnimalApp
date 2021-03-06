package com.patrykkosieradzki.theanimalapp.ui.launcher

import android.view.View
import androidx.navigation.fragment.findNavController
import com.patrykkosieradzki.theanimalapp.R
import com.patrykkosieradzki.theanimalapp.databinding.LauncherFragmentBinding
import com.patrykkosieradzki.theanimalapp.ui.utils.BaseFragment

class LauncherFragment :
    BaseFragment<LauncherViewState, LauncherViewModel, LauncherFragmentBinding>(
        R.layout.launcher_fragment, LauncherViewModel::class
    ) {
    override fun setupViews(view: View) {
        super.setupViews(view)

        findNavController().navigate(R.id.to_randomCatFragment)
    }
}
