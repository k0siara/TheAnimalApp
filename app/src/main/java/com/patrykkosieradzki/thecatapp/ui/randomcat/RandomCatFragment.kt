package com.patrykkosieradzki.thecatapp.ui.randomcat

import android.view.View
import androidx.lifecycle.observe
import com.patrykkosieradzki.thecatapp.R
import com.patrykkosieradzki.thecatapp.databinding.RandomCatFragmentBinding
import com.patrykkosieradzki.thecatapp.ui.utils.BaseFragment
import com.squareup.picasso.Picasso

class RandomCatFragment : BaseFragment<RandomCatViewState, RandomCatViewModel, RandomCatFragmentBinding>(
    R.layout.random_cat_fragment, RandomCatViewModel::class
) {
    override fun setupViews(view: View) {
        super.setupViews(view)

        viewModel.catLoadedEvent.observe(viewLifecycleOwner) {
            Picasso.get().load(it).into(binding.catImage)
//            binding.url.text = it
        }
    }
}