package com.fdm.fileUploadService.mapper

import com.fdm.fileUploadService.model.File
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class FileMapping {

    fun convertUserFileToFile(file: MultipartFile) : File {
        return File(
            id = null,
            data = file.bytes
        )
    }

}