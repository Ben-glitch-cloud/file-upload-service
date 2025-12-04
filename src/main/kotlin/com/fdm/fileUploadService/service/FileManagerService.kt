package com.fdm.fileUploadService.service

import com.fdm.fileUploadService.Repository.FileRepository
import com.fdm.fileUploadService.mapper.FileMapping
import com.fdm.fileUploadService.modle.File
import com.fdm.fileUploadService.modle.FileUpload
import com.fdm.fileUploadService.validator.FileValidation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FileManagerService(
    var fileMapper: FileMapping,
    var fileValidation: FileValidation
){

    @Autowired
    lateinit var repository: FileRepository

    fun getAllFiles(): Array<File> {
        return repository.findAll()
    }

    @Throws(Exception::class)
    fun saveFile(userFile: FileUpload) {
        try{
            fileValidation.storageAmount(userFile)
        } catch (ex: Exception){
            throw ex
        }
        val convertedFile = fileMapper.convertUserFileToFile(userFile)
        repository.save(convertedFile)
    }

    fun deleteFile(fileIdentifier: Long){
        repository.deleteById(fileIdentifier)
    }
}