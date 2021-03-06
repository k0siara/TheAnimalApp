package com.patrykkosieradzki.theanimalapp.ui.launcher

import android.view.View
import com.patrykkosieradzki.theanimalapp.R
import com.patrykkosieradzki.theanimalapp.databinding.LauncherFragmentBinding
import com.patrykkosieradzki.theanimalapp.ui.utils.BaseFragment
import com.patrykkosieradzki.theanimalapp.ui.utils.navigateTo

class LauncherFragment :
    BaseFragment<LauncherViewState, LauncherViewModel, LauncherFragmentBinding>(
        R.layout.launcher_fragment, LauncherViewModel::class
    ) {
    override fun setupViews(view: View) {
        super.setupViews(view)
        with(viewModel) {
            showBlockingMaintenanceModeScreenEvent.observe(viewLifecycleOwner) {
                val directions = LauncherFragmentDirections.toMaintenanceFragment(it)
                navigateTo(directions)
            }
            goToDesktopEvent.observe(viewLifecycleOwner) {
                val directions = LauncherFragmentDirections.toAllAnimalsFragment()
                navigateTo(directions)
            }
        }
    }
}
