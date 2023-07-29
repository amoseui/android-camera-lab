package com.amoseui.cameralab.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.amoseui.cameralab.MainActivity
import com.amoseui.cameralab.R

fun sampleMethod(a: Int, b: Int): Int {
    return a + b
}

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        val button = view?.findViewById<Button>(R.id.button)
        button?.setOnClickListener {
            (activity as MainActivity).replaceFragment(SettingsFragment.newInstance())
        }
    }
}
