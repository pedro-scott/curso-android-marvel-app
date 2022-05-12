package com.example.marvelapp.presentation.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.example.marvelapp.util.ActivityInflater

abstract class BaseActivity<Binding : ViewBinding>(
    private val inflater: ActivityInflater<Binding>
) : AppCompatActivity() {

    protected lateinit var binding: Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflater.invoke(layoutInflater).also { viewBind ->
            setContentView(viewBind.root)
        }
    }

    protected fun findNavController(@IdRes navHostFragment: Int) =
        (supportFragmentManager.findFragmentById(navHostFragment) as NavHostFragment).navController
}