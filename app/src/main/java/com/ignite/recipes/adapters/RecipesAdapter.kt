package com.ignite.recipes.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ignite.recipes.R
import com.ignite.recipes.data.entities.Recipe
import com.ignite.recipes.databinding.ItemRecipeBinding
import com.squareup.picasso.Picasso

class RecipesAdapter (var items:List<Recipe> = listOf(),
                      private val onClickListener: (position:Int) -> Unit
) : RecyclerView.Adapter<RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.render(items[position])
        holder.itemView.setOnClickListener { onClickListener(position) }
    }

    fun updateItems(results: List<Recipe>) {
        items = results
        notifyDataSetChanged()
    }
}

class RecipeViewHolder(val binding:ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(recipe: Recipe) {
        binding.nameTextView.text = recipe.name
        binding.descriptionTextView.text = recipe.cuisine
        binding.servingsTextView.text = recipe.servings.toString()
        binding.ratingTextView.text = recipe.rating.toString()
        binding.timeTextView.text = recipe.time.toString()
        binding.difficultyTextView.text = recipe.difficulty
        val difficultyColor = when(recipe.difficulty) {
            "Easy" -> R.color.easy_color
            "Medium" -> R.color.medium_color
            "Hard" -> R.color.hard_color
            else -> R.color.grey
        }
        binding.difficultyTextView.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(binding.difficultyTextView.context, difficultyColor))
        Picasso.get().load(recipe.image).into(binding.photoImageView)
    }

}