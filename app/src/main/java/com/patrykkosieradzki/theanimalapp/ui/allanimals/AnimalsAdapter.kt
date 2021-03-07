package com.patrykkosieradzki.theanimalapp.ui.allanimals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.patrykkosieradzki.theanimalapp.databinding.AnimalItemBinding
import com.patrykkosieradzki.theanimalapp.domain.model.AnimalData

class AnimalsAdapter(
    diffCallback: DiffUtil.ItemCallback<AnimalData>
) : PagingDataAdapter<AnimalData, AnimalsAdapter.AnimalsViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalsViewHolder {
        val binding = AnimalItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalsViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    inner class AnimalsViewHolder(
        private val binding: AnimalItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(animal: AnimalData) {
            binding.animal = animal
        }
    }
}

class AnimalDiffCallback : DiffUtil.ItemCallback<AnimalData>() {
    override fun areItemsTheSame(oldItem: AnimalData, newItem: AnimalData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AnimalData, newItem: AnimalData): Boolean {
        return oldItem == newItem
    }
}