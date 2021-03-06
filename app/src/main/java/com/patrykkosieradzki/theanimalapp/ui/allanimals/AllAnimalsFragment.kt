package com.patrykkosieradzki.theanimalapp.ui.allanimals

import android.view.View
import com.patrykkosieradzki.theanimalapp.R
import com.patrykkosieradzki.theanimalapp.databinding.AllAnimalsFragmentBinding
import com.patrykkosieradzki.theanimalapp.ui.utils.BaseFragment

class AllAnimalsFragment :
    BaseFragment<AllAnimalsViewState, AllAnimalsViewModel, AllAnimalsFragmentBinding>(
        R.layout.all_animals_fragment, AllAnimalsViewModel::class
    ) {

    override fun setupViews(view: View) {
        super.setupViews(view)
        onBackEvent = { requireActivity().moveTaskToBack(true) }
    }
}
