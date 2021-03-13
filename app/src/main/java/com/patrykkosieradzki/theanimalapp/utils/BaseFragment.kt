package com.patrykkosieradzki.theanimalapp.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.RelativeLayout
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomappbar.BottomAppBar
import com.patrykkosieradzki.theanimalapp.BR
import com.patrykkosieradzki.theanimalapp.R
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber
import kotlin.properties.Delegates
import kotlin.reflect.KClass

@Suppress("TooManyFunctions")
abstract class BaseFragment<STATE : ViewState, VM : BaseViewModel<STATE>, VDB : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
    vmKClass: KClass<VM>
) : Fragment() {

    @get:StringRes
    open val titleId: Int? = null
    open val title: String? = null

    @get:MenuRes
    open val menuId: Int? = null

    protected lateinit var binding: VDB

    val viewModel: VM by lazy {
        getViewModel(clazz = vmKClass)
    }

    var onBackEvent: () -> Unit = {
        try {
            findNavController().navigateUp()
        } catch (e: IllegalStateException) {
            Timber.e("Fragment $this is not a NavHostFragment or within a NavHostFragment")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<VDB>(inflater, layoutId, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                setVariable(BR.vm, viewModel)
            }
        return RelativeLayout(requireContext()).apply {
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
            addView(binding.root, ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT))
        }
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                onBackEvent.invoke()
            }
            setupViews(view)
            initialize()
        }
    }

    open fun setupViews(view: View) {
        setupToolbar(view)
        setupBottomAppBar(view)
        viewModel.navigationCommandEvent.observe(viewLifecycleOwner) {
            when (it) {
                is NavigationCommand.To -> {
                    findNavController().navigate(it.directions)
                }
                is NavigationCommand.Back -> {
                    onBackEvent.invoke()
                }
                is NavigationCommand.BackWithArgs -> {
                    navigateBackWithArgs(it.bundle)
                }
                else -> throw IllegalStateException("Unknown navigation command")
            }
        }
    }

    private fun setupToolbar(view: View) {
        view.findViewById<MaterialToolbar>(R.id.toolbar)?.apply {
            this@BaseFragment.titleId?.let { setTitle(it) }
            this@BaseFragment.title?.let { title = it }
            setOnMenuItemClickListener {
                onToolbarMenuItemClicked(it)
            }
        }
    }

    open fun onToolbarMenuItemClicked(it: MenuItem): Boolean = true

    private fun setupBottomAppBar(view: View) {
        view.findViewById<BottomAppBar>(R.id.bottom_app_bar)?.apply {
            setNavigationOnClickListener {
                val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
                bottomNavDrawerFragment.show(parentFragmentManager, bottomNavDrawerFragment.tag)
            }
            setOnMenuItemClickListener {
                onBottomAppBarMenuItemClicked(it)
            }
        }
    }

    open fun onBottomAppBarMenuItemClicked(it: MenuItem): Boolean {
        return when (it.itemId) {
            R.id.search -> {
                viewModel.onBottomAppBarSearchClicked()
                true
            }
            R.id.more -> {
                viewModel.onBottomAppBarMoreClicked()
                true
            }
            else -> false
        }
    }

    fun navigateBackWithArgs(bundle: Bundle): Boolean {
        val childFragmentManager =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host)
                ?.childFragmentManager
        var backStackListener: FragmentManager.OnBackStackChangedListener by Delegates.notNull()
        backStackListener = FragmentManager.OnBackStackChangedListener {
            (childFragmentManager?.fragments?.get(0) as? NavigationResult)?.onNavigationResult(
                bundle
            )
            childFragmentManager?.removeOnBackStackChangedListener(backStackListener)
        }
        childFragmentManager?.addOnBackStackChangedListener(backStackListener)
        val backStackPopped = findNavController().popBackStack()
        if (!backStackPopped) {
            childFragmentManager?.removeOnBackStackChangedListener(backStackListener)
        }
        return backStackPopped
    }
}

interface ViewState {
    val inProgress: Boolean
    fun toSuccess(): ViewState
}
