package com.fdm.fileUploadService.validator

import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class FileValidation {

    fun storageAmount(fileUpload : MultipartFile){
        var file = fileUpload.bytes
        val fileKiloBytesLength = (file.size.div(1024))
        val fileMegaBytesLength = (fileKiloBytesLength.div(1024))
        if(fileMegaBytesLength > 2){
            throw Exception("Invalid File - size limited reached must be small or equal than 2MB")
        } else if(file.isEmpty()) {
            throw Exception("Invalid File - no data in file found")
        } else {
            return
        }
    }

    fun fileType(fileUpload: MultipartFile){
        val fileName = fileUpload.originalFilename
        val fileType = fileName?.split(".")?.last()
        if(fileType != "txt"){ throw Exception("Invalid File - must be plan text") }
        return
    }

}