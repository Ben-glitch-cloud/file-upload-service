package com.fdm.fileUploadService.mapper

import com.fdm.fileUploadService.modle.File
import com.fdm.fileUploadService.modle.FileDTO
import org.springframework.stereotype.Component

@Component
class FileMapping {

    fun convertUserFileToFile(userFile: FileDTO) : File {
        val convertedFile = File(id = null, data = userFile.data)
        return convertedFile
    }

}