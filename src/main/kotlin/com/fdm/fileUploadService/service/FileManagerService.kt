package com.fdm.fileUploadService.service

import com.fdm.fileUploadService.Repository.FileRepository
import com.fdm.fileUploadService.mapper.FileMapping
import com.fdm.fileUploadService.modle.File
import com.fdm.fileUploadService.validator.FileValidation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

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
    fun saveFile(file: MultipartFile) {
        try{
            fileValidation.fileType(file)
            fileValidation.storageAmount(file)
        } catch (ex: Exception){
            throw ex
        }
        val convertedFile = fileMapper.convertUserFileToFile(file)
        repository.save(convertedFile)
    }

    fun deleteFile(fileIdentifier: Long){
        repository.deleteById(fileIdentifier)
    }

    @Throws(Exception::class)
    fun getFileById(fileIdentifier: Long) : File {
        try{
            return repository.findById(fileIdentifier)
        } catch (ex: Exception){
            throw Exception("No file found with Identifier $fileIdentifier")
        }
    }
}