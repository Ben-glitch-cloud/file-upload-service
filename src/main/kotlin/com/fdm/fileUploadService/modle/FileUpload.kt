package com.fdm.fileUploadService.modle

import kotlinx.serialization.Serializable

@Serializable
data class FileUpload(
    var data: ByteArray? = null
)
