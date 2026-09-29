package com.ignite.recipes.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ignite.recipes.data.daos.RecipeDAO
import com.ignite.recipes.data.serviceapis.RecipeServiceApi
import com.ignite.recipes.databinding.ActivityMainBinding
import com.ignite.recipes.utils.RetrofitProvider
import com.ignite.recipes.utils.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        session = SessionManager(this)
        if (session.didFetchData) {
            navigateToRecipesList()
        } else {
            getAllRecipes()
        }
    }

    private fun getAllRecipes() {
        val service: RecipeServiceApi = RetrofitProvider.getRecipeServiceApi()

        CoroutineScope(Dispatchers.IO).launch {
            // Llamada en segundo plano
            val response = service.findAll()

            runOnUiThread {
                // Modificar UI
                if (response.body() != null) {
                    Log.i("HTTP", "respuesta correcta :)")
                    var recipesList = response.body()?.results.orEmpty()

                    val recipeDAO = RecipeDAO(this@MainActivity)
                    recipeDAO.deleteAll()
                    for (recipe in recipesList) {
                        recipeDAO.insert(recipe)
                    }

                    session.didFetchData = true
                    navigateToRecipesList()
                } else {
                    Log.i("HTTP", "respuesta erronea :(")
                    Toast.makeText(this@MainActivity, "Hubo un error inesperado, vuelva a intentarlo más tarde", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun navigateToRecipesList() {
        val intent = Intent(this, RecipesActivity::class.java)
        startActivity(intent)
    }
}