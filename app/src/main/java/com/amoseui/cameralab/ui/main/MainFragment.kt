package com.amoseui.cameralab.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.amoseui.cameralab.MainActivity
import com.amoseui.cameralab.R

fun sampleMethod(a: Int, b: Int): Int {
    return a + b
}

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Use the ViewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val navigationController = findNavController()

        val buttonCamera1 = view.findViewById<Button>(R.id.button_camera_1)
        buttonCamera1?.setOnClickListener {
            navigationController.navigate(R.id.action_mainFragment_to_cameraFragment,
                bundleOf("cameraId"   to CameraFactory.CameraType.CAMERA1))
        }

        val buttonCamera2 = view.findViewById<Button>(R.id.button_camera_2)
        buttonCamera2?.setOnClickListener {
            navigationController.navigate(R.id.action_mainFragment_to_cameraFragment,
                bundleOf("cameraId"   to CameraFactory.CameraType.CAMERA2))
        }

        val buttonCameraX = view.findViewById<Button>(R.id.button_camera_x)
        buttonCameraX?.setOnClickListener {
            navigationController.navigate(R.id.action_mainFragment_to_cameraFragment,
                bundleOf("cameraId"   to CameraFactory.CameraType.CAMERAX))
        }

        val button = view.findViewById<Button>(R.id.button)
        button?.setOnClickListener {
            navigationController.navigate(R.id.action_mainFragment_to_settingsFragment)
        }
    }
}
