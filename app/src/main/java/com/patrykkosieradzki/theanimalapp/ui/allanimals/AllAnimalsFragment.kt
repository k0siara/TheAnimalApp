package com.patrykkosieradzki.theanimalapp.ui.allanimals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.patrykkosieradzki.theanimalapp.R
import com.patrykkosieradzki.theanimalapp.databinding.AllAnimalsFragmentBinding
import com.patrykkosieradzki.theanimalapp.databinding.AnimalViewPagerFragmentBinding
import com.patrykkosieradzki.theanimalapp.ui.allanimals.RecyclerViewMode.GRID
import com.patrykkosieradzki.theanimalapp.ui.allanimals.RecyclerViewMode.LIST
import com.patrykkosieradzki.theanimalapp.ui.utils.BaseFragment
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
        adapter = AnimalsAdapter(AnimalDiffCallback()) { viewModel.onAnimalItemClicked(it) }
        with(binding) {
            animalsRecyclerView.adapter = this@AllAnimalsFragment.adapter
            animalsViewPager.adapter = ViewPagerPagingAdapter(requireActivity())
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.list_grid_switch -> {
                        viewModel.onListGridSwitchClick()
                        true
                    }
                    else -> false
                }
            }
        }
        with(viewModel) {
            viewState.observe(viewLifecycleOwner) {
                switchRecyclerViewMode()
            }
            updateAnimalsEvent.observe(viewLifecycleOwner) {
                lifecycleScope.launch {
                    adapter.submitData(it)
                }
            }
            showAnimalDetailsEvent.observe(viewLifecycleOwner) {
            }
        }
    }

    private fun switchRecyclerViewMode() {
        with(binding) {
            animalsRecyclerView.apply {
                removeItemDecorations()
                when (viewModel.viewState.valueNN.recyclerViewMode) {
                    LIST -> {
                        (layoutManager as GridLayoutManager).spanCount = 1
                        toolbar.menu.findItem(R.id.list_grid_switch).apply {
                            title = getString(R.string.list_to_grid_switch)
                            setIcon(R.drawable.ic_grid)
                        }
                    }
                    GRID -> {
                        (layoutManager as GridLayoutManager).spanCount = 2
                        toolbar.menu.findItem(R.id.list_grid_switch).apply {
                            title = getString(R.string.grid_to_list_switch)
                            setIcon(R.drawable.ic_list)
                        }
                    }
                }
                adapter?.notifyItemRangeChanged(0, adapter?.itemCount ?: 0)
            }
        }
    }
}

class ViewPagerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return AnimalViewPagerFragmentBinding.inflate(inflater, container, false).root
    }
}

class ViewPagerPagingAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return ViewPagerFragment()
    }
}
