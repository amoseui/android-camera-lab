package com.amoseui.cameralab.ui.main

import android.content.Context
import android.hardware.Camera
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.ViewGroup
import java.io.IOException
import java.lang.RuntimeException

class Camera1 : ICamera {

    private val TAG = "Camera1"

    private var camera: Camera? = null

    override fun setUpPreview() {
    }

    override fun openCamera() {
        try {
            stopPreviewAndCamera()
            camera = Camera.open(0)
        } catch (e: RuntimeException) {
            Log.e(TAG, "failed to open Camera1: $e")
        }
    }

    override fun closeCamera() {
        TODO("Not yet implemented")
    }

    private fun stopPreviewAndCamera() {
        preview?.setCamera(null)
        camera?.also {
            it.release()
            camera = null
        }
    }
}
class Preview(
    context: Context,
    val surfaceView: SurfaceView = SurfaceView(context)
) : ViewGroup(context), SurfaceHolder.Callback {

    private var mCamera : Camera? = null

    var mHolder: SurfaceHolder = surfaceView.holder.apply {
        addCallback(this@Preview)
        setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
    }

    fun setCamera(camera: Camera?) {
        if (mCamera == camera) {
            return
        }

        stopPreviewAndFreeCamera()

        mCamera = camera

        mCamera?.apply {

            try {
                setPreviewDisplay(holder)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            // Important: Call startPreview() to start updating the preview
            // surface. Preview must be started before you can take a picture.
            startPreview()
        }
    }
    override fun surfaceDestroyed(holder: SurfaceHolder) {
        // Surface will be destroyed when we return, so stop the preview.
        // Call stopPreview() to stop updating the preview surface.
        mCamera?.stopPreview()
    }

    /**
     * When this function returns, mCamera will be null.
     */
    private fun stopPreviewAndFreeCamera() {
        mCamera?.apply {
            // Call stopPreview() to stop updating the preview surface.
            stopPreview()

            // Important: Call release() to release the camera for use by other
            // applications. Applications should release the camera immediately
            // during onPause() and re-open() it during onResume()).
            release()

            mCamera = null
        }
    }
}