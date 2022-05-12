package com.example.marvelapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.marvelapp.util.FragmentInflater

abstract class BaseFragment<Binding : ViewBinding>(
    private val inflater: FragmentInflater<Binding>
) : Fragment() {

    private var _binding: Binding? = null
    protected val binding: Binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        this.inflater.invoke(
            inflater,
            container,
            false
        ).let { viewBind ->
            _binding = viewBind
            viewBind.root
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}