package com.fdm.fileUploadService.validator

import com.fdm.fileUploadService.modle.FileUpload
import org.springframework.stereotype.Component

@Component
class FileValidation {

    fun storageAmount(userFile : FileUpload){
        if(userFile.data == null){
            throw Exception("Invalid File - no file found!")
        }
        val fileKiloBytesLength = (userFile.data!!.size.div(1024))
        val fileMegaBytesLength = (fileKiloBytesLength.div(1024))
        if(fileMegaBytesLength > 2){
            throw Exception("Invalid File - size limited reached must be small or equal than 2MB")
        } else if(userFile.data!!.isEmpty()) {
            throw Exception("Invalid File - no data in file found")
        } else {
            return
        }
    }

}