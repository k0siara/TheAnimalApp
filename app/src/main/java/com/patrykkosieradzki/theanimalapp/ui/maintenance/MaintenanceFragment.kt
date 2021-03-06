package com.patrykkosieradzki.theanimalapp.ui.maintenance

import android.view.View
import com.patrykkosieradzki.theanimalapp.R
import com.patrykkosieradzki.theanimalapp.databinding.MaintenanceFragmentBinding
import com.patrykkosieradzki.theanimalapp.ui.utils.BaseFragment
import com.patrykkosieradzki.theanimalapp.ui.utils.navigateTo

class MaintenanceFragment :
    BaseFragment<MaintenanceViewState, MaintenanceViewModel, MaintenanceFragmentBinding>(
        R.layout.maintenance_fragment, MaintenanceViewModel::class
    ) {



}
