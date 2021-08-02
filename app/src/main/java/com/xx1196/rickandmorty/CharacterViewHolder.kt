package com.xx1196.rickandmorty

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.xx1196.rickandmorty.databinding.ItemCharacterBinding
import com.xx1196.rickandmorty.models.RickAndMorty

class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemCharacterBinding.bind(view)

    fun bind(character: RickAndMorty) {
        Picasso.get().load(character.image).into(binding.ivCharacter)
    }
}