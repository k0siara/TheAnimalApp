package com.patrykkosieradzki.theanimalapp.ui.maintenance

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MaintenanceData(
    val title: String,
    val description: String
) : Parcelable