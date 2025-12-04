package com.fdm.fileUploadService.validator

import com.fdm.fileUploadService.modle.FileDTO
import org.springframework.stereotype.Component

@Component
class FileValidation {

    fun maximumSize(userFile : FileDTO){

        if(userFile.data == null){ return }

        val fileKiloBytesLength = (userFile.data!!.size.div(1024))
        val fileMegaBytesLength = (fileKiloBytesLength.div(1024))

        if(fileMegaBytesLength > 2){
            throw Exception("Invalid File - size limited reached must be small or equal than 2MB")
        } else {
            return
        }

    }

}