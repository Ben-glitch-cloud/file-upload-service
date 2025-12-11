package com.fdm.fileUploadService.mapper

import com.fdm.fileUploadService.model.File
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.text.SimpleDateFormat
import java.util.Date

@Component
class FileMapping {

    fun convertUserFileToFile(file: MultipartFile, fileDescription: String) : File {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = sdf.format(Date())

        return File(
            id = null,
            fileName = file.originalFilename,
            description = fileDescription,
            data = file.bytes,
            date = currentDate
        )
    }

}