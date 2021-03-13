package com.patrykkosieradzki.theanimalapp.ui.list.all

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.patrykkosieradzki.theanimalapp.databinding.AnimalItemBinding
import com.patrykkosieradzki.theanimalapp.domain.model.AnimalData
import com.patrykkosieradzki.theanimalapp.ui.list.AnimalDiffCallback
import com.patrykkosieradzki.theanimalapp.utils.setOnClick

class AnimalsAdapter(
    private val onClick: (Int) -> Unit = {}
) : PagingDataAdapter<AnimalData, AnimalsAdapter.AnimalsViewHolder>(AnimalDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalsViewHolder {
        val binding = AnimalItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalsViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it).setOnClick { onClick(position) }
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
