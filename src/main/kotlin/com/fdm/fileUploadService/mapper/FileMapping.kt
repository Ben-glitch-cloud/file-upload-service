package com.fdm.fileUploadService.mapper

import com.fdm.fileUploadService.model.File
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class FileMapping {

    fun convertUserFileToFile(file: MultipartFile, fileDescription: String) : File {
        return File(
            id = null,
            description = fileDescription,
            data = file.bytes
        )
    }

}