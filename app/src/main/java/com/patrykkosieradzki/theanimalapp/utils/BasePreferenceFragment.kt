package com.patrykkosieradzki.theanimalapp.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.activity.addCallback
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceFragmentCompat
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber
import kotlin.reflect.KClass

@Suppress("TooManyFunctions")
abstract class BasePreferenceFragment<
    STATE : ViewState,
    VM : BaseViewModel<STATE>,
    VDB : ViewDataBinding>(
    vmKClass: KClass<VM>
) : PreferenceFragmentCompat() {

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

    open fun setupViews(view: View) {}
}
