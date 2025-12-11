package com.fdm.fileUploadService

import com.fdm.fileUploadService.repository.FileRepository
import com.fdm.fileUploadService.configuration.PopulateFileDataBaseCongif
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class FileUploadServiceApplication

    @Autowired
    lateinit var populateFileDataBaseCongif: PopulateFileDataBaseCongif

    @Autowired
    lateinit var repository: FileRepository

    fun main(args: Array<String>) {
        runApplication<FileUploadServiceApplication>(*args)

        populateFileDataBaseCongif.saveFiles(repository)
    }


