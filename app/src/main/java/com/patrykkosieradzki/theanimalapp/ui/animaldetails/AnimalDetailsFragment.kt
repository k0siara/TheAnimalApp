package com.patrykkosieradzki.theanimalapp.ui.animaldetails

import android.view.View
import androidx.navigation.fragment.navArgs
import com.patrykkosieradzki.theanimalapp.R
import com.patrykkosieradzki.theanimalapp.databinding.AnimalDetailsFragmentBinding
import com.patrykkosieradzki.theanimalapp.ui.utils.BaseFragment

class AnimalDetailsFragment :
    BaseFragment<AnimalDetailsViewState, AnimalDetailsViewModel, AnimalDetailsFragmentBinding>(
        R.layout.animal_details_fragment,
        AnimalDetailsViewModel::class
    ) {

    private val args by navArgs<AnimalDetailsFragmentArgs>()

    override fun setupViews(view: View) {
        super.setupViews(view)
        with(binding) {
            toolbar.setNavigationOnClickListener {
                onBackEvent.invoke()
            }
        }
    }
}
