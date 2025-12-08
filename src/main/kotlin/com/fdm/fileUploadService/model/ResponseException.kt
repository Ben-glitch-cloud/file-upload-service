package com.fdm.fileUploadService.model

data class ResponseException(
    var errorMessage: String = "",
    var httpStatus: String = ""
)