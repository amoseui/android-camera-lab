package com.amoseui.cameralab.ui.main

import android.content.Context
import androidx.camera.view.PreviewView

interface ICamera {

    fun setPreview(previewView: PreviewView)
    fun setUpPreview(context: Context)
    fun openCamera()
    fun closeCamera()
}