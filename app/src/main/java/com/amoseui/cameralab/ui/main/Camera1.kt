package com.amoseui.cameralab.ui.main

import android.content.Context
import android.hardware.Camera
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.ViewGroup
import androidx.camera.view.PreviewView
import java.io.IOException
import java.lang.RuntimeException

class Camera1 : ICamera {

    private companion object {
        private const val TAG = "Camera1"

        // TODO(eui-sang.lim): temporarily fixed preview size
        private const val previewWidth = 1920
        private const val previewHeight = 1280
    }

    private var camera: Camera? = null

    override fun setPreview(previewView: PreviewView) {
    }

    override fun setUpPreview(context: Context) {
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
        // preview?.setCamera(null)
        camera?.also {
            it.release()
            camera = null
        }
    }

    inner class Preview(
        context: Context,
        private val surfaceView: SurfaceView = SurfaceView(context)
    ) : ViewGroup(context), SurfaceHolder.Callback {

        private var mCamera : Camera? = null

        private var holder: SurfaceHolder = surfaceView.holder.apply {
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

        override fun surfaceCreated(p0: SurfaceHolder) {
            TODO("Not yet implemented")
        }

        override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            mCamera?.apply {
                // Now that the size is known, set up the camera parameters and begin
                // the preview.
                parameters?.also { params ->
                    params.setPreviewSize(previewWidth, previewHeight)
                    requestLayout()
                    parameters = params
                }

                // Important: Call startPreview() to start updating the preview surface.
                // Preview must be started before you can take a picture.
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

        override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
            TODO("Not yet implemented")
        }
    }
}
