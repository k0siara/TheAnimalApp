package com.patrykkosieradzki.thecatapp.ui.randomcat

import android.view.View
import com.patrykkosieradzki.thecatapp.R
import com.patrykkosieradzki.thecatapp.databinding.RandomCatFragmentBinding
import com.patrykkosieradzki.thecatapp.ui.utils.BaseFragment

class RandomCatFragment : BaseFragment<RandomCatViewState, RandomCatViewModel, RandomCatFragmentBinding>(
    R.id.random_cat_fragment
) {
    override fun setupViews(view: View) {
        super.setupViews(view)

    }
}