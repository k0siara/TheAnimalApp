package com.patrykkosieradzki.theanimalapp.ui.list.details

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.patrykkosieradzki.theanimalapp.R
import com.patrykkosieradzki.theanimalapp.databinding.AnimalDetailsFragmentBinding
import com.patrykkosieradzki.theanimalapp.utils.BaseFragment
import com.patrykkosieradzki.theanimalapp.utils.extensions.addPagerSnapHelper
import kotlinx.coroutines.launch

class AnimalDetailsFragment :
    BaseFragment<AnimalDetailsViewState, AnimalDetailsViewModel, AnimalDetailsFragmentBinding>(
        R.layout.animal_details_fragment,
        AnimalDetailsViewModel::class
    ) {

    private val args by navArgs<AnimalDetailsFragmentArgs>()

    lateinit var adapter: AnimalDetailsAdapter
    private val layoutManager by lazy {
        binding.animalsRecyclerView.layoutManager as LinearLayoutManager
    }

    override fun setupViews(view: View) {
        super.setupViews(view)
        adapter = AnimalDetailsAdapter()
        with(binding) {
            onBackEvent = {
                navigateBackWithResult(
                    bundleOf("position" to layoutManager.findFirstVisibleItemPosition())
                )
            }
            animalsRecyclerView.apply {
                adapter = this@AnimalDetailsFragment.adapter
                addPagerSnapHelper()
                post {
                    layoutManager?.scrollToPosition(args.position)
                }
            }
            toolbar.setNavigationOnClickListener {
                onBackEvent.invoke()
            }
        }
        with(viewModel) {
            updateAnimalsEvent.observe(viewLifecycleOwner) {
                lifecycleScope.launch {
                    adapter.submitData(it)
                }
            }
        }
    }
}
