package com.patrykkosieradzki.theanimalapp.ui.launcher

import com.patrykkosieradzki.theanimalapp.R
import com.patrykkosieradzki.theanimalapp.databinding.LauncherFragmentBinding
import com.patrykkosieradzki.theanimalapp.utils.BaseFragment

class LauncherFragment :
    BaseFragment<LauncherViewState, LauncherViewModel, LauncherFragmentBinding>(
        R.layout.launcher_fragment, LauncherViewModel::class
    )
