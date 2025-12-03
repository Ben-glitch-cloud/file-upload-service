package com.fdm.fileUploadService.service

import com.fdm.fileUploadService.Repository.FileRepository
import com.fdm.fileUploadService.mapper.FileMapping
import com.fdm.fileUploadService.modle.File
import com.fdm.fileUploadService.modle.FileDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FileManagerService(
    var fileMapper: FileMapping
){

    @Autowired
    lateinit var repository: FileRepository

    fun getAllFiles(): Array<File> {
        return repository.findAll()
    }

    fun saveFile(userFile: FileDTO) {
        val convertedFile = fileMapper.convertUserFileToFile(userFile)
        repository.save(convertedFile)
    }

    fun deleteFile(fileIdentifier: Long){
        repository.deleteById(fileIdentifier)
    }
}