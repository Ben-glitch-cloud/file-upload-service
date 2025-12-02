package com.fdm.fileUploadService.modle

import kotlinx.serialization.Serializable

@Serializable
data class FileDTO(
    var data: ByteArray? = null
)
