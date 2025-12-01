package com.fdm.fileUploadService.service

import com.fdm.fileUploadService.Repository.FileRepository
import com.fdm.fileUploadService.modle.File
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FileManagerService{

    @Autowired
    lateinit var repository: FileRepository

    fun getAllFiles(): Array<File> {
        return repository.findAll()
    }
}