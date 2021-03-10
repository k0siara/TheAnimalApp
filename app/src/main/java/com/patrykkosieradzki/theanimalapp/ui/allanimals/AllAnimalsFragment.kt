package com.patrykkosieradzki.theanimalapp.ui.allanimals

import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.patrykkosieradzki.theanimalapp.R
import com.patrykkosieradzki.theanimalapp.databinding.AllAnimalsFragmentBinding
import com.patrykkosieradzki.theanimalapp.ui.utils.BaseFragment
import com.patrykkosieradzki.theanimalapp.ui.utils.addGridSeparator
import com.patrykkosieradzki.theanimalapp.ui.utils.removeItemDecorations
import com.patrykkosieradzki.theanimalapp.ui.utils.valueNN
import kotlinx.coroutines.launch

class AllAnimalsFragment :
    BaseFragment<AllAnimalsViewState, AllAnimalsViewModel, AllAnimalsFragmentBinding>(
        R.layout.all_animals_fragment, AllAnimalsViewModel::class
    ) {
    lateinit var adapter: AnimalsAdapter

    override fun setupViews(view: View) {
        super.setupViews(view)
        onBackEvent = { requireActivity().moveTaskToBack(true) }
        adapter = AnimalsAdapter(AnimalDiffCallback())
        with(binding) {
            animalsRecyclerView.apply {
                adapter = this@AllAnimalsFragment.adapter
            }
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.list_grid_switch -> {
                        switchRecyclerViewMode()
                        true
                    }
                    else -> false
                }
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

    private fun switchRecyclerViewMode() {
        with(binding) {
            animalsRecyclerView.apply {
                removeItemDecorations()
                if (viewModel.viewState.valueNN.isGridModeEnabled) {
                    (layoutManager as GridLayoutManager).spanCount = 1
                    addGridSeparator(1, 10)
                    toolbar.menu.findItem(R.id.list_grid_switch).setIcon(R.drawable.ic_grid)
                } else {
                    (layoutManager as GridLayoutManager).spanCount = 2
                    addGridSeparator(2, 10)
                    toolbar.menu.findItem(R.id.list_grid_switch).setIcon(R.drawable.ic_list)
                }
                adapter?.notifyItemRangeChanged(0, adapter?.itemCount ?: 0)
            }
        }
        viewModel.updateGridMode()
    }
}
