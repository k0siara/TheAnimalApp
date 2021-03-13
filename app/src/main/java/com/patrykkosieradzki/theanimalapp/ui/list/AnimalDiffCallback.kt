package com.patrykkosieradzki.theanimalapp.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.patrykkosieradzki.theanimalapp.domain.model.AnimalData

class AnimalDiffCallback : DiffUtil.ItemCallback<AnimalData>() {
    override fun areItemsTheSame(oldItem: AnimalData, newItem: AnimalData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AnimalData, newItem: AnimalData): Boolean {
        return oldItem == newItem
    }
}