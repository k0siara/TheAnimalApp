package com.patrykkosieradzki.theanimalapp.ui.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.RelativeLayout
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.hadilq.liveevent.LiveEvent
import com.patrykkosieradzki.theanimalapp.R
import com.patrykkosieradzki.theanimalapp.BR
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber
import kotlin.reflect.KClass


@Suppress("TooManyFunctions")
abstract class BaseFragment<STATE : ViewState, VM : BaseViewModel<STATE>, VDB : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
    vmKClass: KClass<VM>
) : Fragment() {

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
        view.findViewById<BottomAppBar>(R.id.bottom_app_bar).apply {
            setNavigationOnClickListener {
                val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
                bottomNavDrawerFragment.show(parentFragmentManager, bottomNavDrawerFragment.tag)
            }
            setOnMenuItemClickListener {
                when(it.itemId) {
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
        }
    }
}

interface ViewState {
    val inProgress: Boolean
    fun toSuccess(): ViewState
}

inline val <T> MutableLiveData<T>.readOnly: LiveData<T>
    get() = this

inline val <T> LiveData<T>.valueNN
    get() = this.value!!

fun <T> LiveEvent<T>.fireEvent(event: T) {
    this.value = event
}

fun LiveEvent<Unit>.fireEvent() {
    this.value = Unit
}

fun Fragment.navigateTo(directions: NavDirections) {
    findNavController().navigate(directions)
}

val Fragment.appCompatActivity: AppCompatActivity
    get() = requireActivity() as AppCompatActivity