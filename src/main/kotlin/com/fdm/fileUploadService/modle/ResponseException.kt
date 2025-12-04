package com.fdm.fileUploadService.modle

data class ResponseException(
    var errorMessage: String = "",
    var httpStatus: String = ""
)