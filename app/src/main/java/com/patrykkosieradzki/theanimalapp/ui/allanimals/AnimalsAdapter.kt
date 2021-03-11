package com.patrykkosieradzki.theanimalapp.ui.allanimals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.patrykkosieradzki.theanimalapp.databinding.AnimalItemBinding
import com.patrykkosieradzki.theanimalapp.domain.model.AnimalData
import com.patrykkosieradzki.theanimalapp.ui.utils.setOnClick

class AnimalsAdapter(
    diffCallback: DiffUtil.ItemCallback<AnimalData>,
    private val onClick: (AnimalData) -> Unit = {}
) : PagingDataAdapter<AnimalData, AnimalsAdapter.AnimalsViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalsViewHolder {
        val binding = AnimalItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalsViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it).setOnClick { onClick(item) }
        }
    }

    inner class AnimalsViewHolder(
        private val binding: AnimalItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(animal: AnimalData): View {
            binding.item = animal
            return binding.root
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
