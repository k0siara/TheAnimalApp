package com.patrykkosieradzki.theanimalapp.ui.maintenance

import android.view.View
import com.patrykkosieradzki.theanimalapp.R
import com.patrykkosieradzki.theanimalapp.databinding.MaintenanceFragmentBinding
import com.patrykkosieradzki.theanimalapp.utils.BaseFragment

class MaintenanceFragment :
    BaseFragment<MaintenanceViewState, MaintenanceViewModel, MaintenanceFragmentBinding>(
        R.layout.maintenance_fragment, MaintenanceViewModel::class
    ) {
    override fun setupViews(view: View) {
        super.setupViews(view)
        onBackEvent = { requireActivity().moveTaskToBack(true) }
    }
}
