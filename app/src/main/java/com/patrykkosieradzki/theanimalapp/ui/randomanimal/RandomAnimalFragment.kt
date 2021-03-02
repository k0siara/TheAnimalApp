package com.patrykkosieradzki.theanimalapp.ui.randomanimal

import android.view.View
import com.patrykkosieradzki.theanimalapp.ui.utils.BaseFragment
import com.patrykkosieradzki.theanimalapp.ui.utils.loadWithPicasso
import com.patrykkosieradzki.theanimalapp.R
import com.patrykkosieradzki.theanimalapp.databinding.RandomAnimalFragmentBinding

class RandomAnimalFragment : BaseFragment<RandomAnimalViewState, RandomAnimalViewModel, RandomAnimalFragmentBinding>(
    R.layout.random_animal_fragment, RandomAnimalViewModel::class
) {
    override fun setupViews(view: View) {
        super.setupViews(view)
        viewModel.loadAnimalImageEvent.observe(viewLifecycleOwner) {
            loadWithPicasso(
                url = it,
                into = binding.catImage,
                onSuccess = { viewModel.onAnimalImageLoadedSuccessfully() }
            )
        }
    }
}