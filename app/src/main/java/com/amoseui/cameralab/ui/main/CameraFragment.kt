package com.amoseui.cameralab.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amoseui.cameralab.R

class CameraFragment : Fragment() {

    private lateinit var camera : ICamera

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.camera_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        camera = CameraFactory.createCamera(arguments?.getSerializable("cameraId") as CameraFactory.CameraType)
        camera.setUpPreview()
    }

    override fun onDestroy() {
        super.onDestroy()
        camera.closeCamera()
    }
}