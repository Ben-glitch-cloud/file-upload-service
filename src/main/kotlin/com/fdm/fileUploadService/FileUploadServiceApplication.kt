package com.fdm.fileUploadService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FileUploadServiceApplication

fun main(args: Array<String>) {
	runApplication<FileUploadServiceApplication>(*args)
}
