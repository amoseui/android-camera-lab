package com.amoseui.cameralab.ui.main

object CameraFactory {

    enum class CameraType {
        CAMERA1,
        CAMERA2,
        CAMERAX,
    }

    fun createCamera(cameraType: CameraType): ICamera {
        return when(cameraType) {
            CameraType.CAMERA1 -> Camera1()
            CameraType.CAMERA2 -> Camera2()
            CameraType.CAMERAX -> CameraX()
        }
    }
}