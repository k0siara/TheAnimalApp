package com.patrykkosieradzki.thecatapp.ui.randomcat

import android.view.View
import com.patrykkosieradzki.thecatapp.R
import com.patrykkosieradzki.thecatapp.databinding.RandomCatFragmentBinding
import com.patrykkosieradzki.thecatapp.ui.utils.BaseFragment
import com.patrykkosieradzki.thecatapp.ui.utils.loadWithPicasso

class RandomCatFragment : BaseFragment<RandomCatViewState, RandomCatViewModel, RandomCatFragmentBinding>(
    R.layout.random_cat_fragment, RandomCatViewModel::class
) {
    override fun setupViews(view: View) {
        super.setupViews(view)
        viewModel.loadCatImageEvent.observe(viewLifecycleOwner) {
            loadWithPicasso(
                url = it,
                into = binding.catImage,
                onSuccess = { viewModel.onCatImageLoadedSuccessfully() }
            )
        }
    }
}