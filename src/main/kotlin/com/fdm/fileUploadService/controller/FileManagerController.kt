package com.fdm.fileUploadService.controller

import com.fdm.fileUploadService.service.FileManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FileManagerController(
    @Autowired var fileManager: FileManager
) {

    @GetMapping("/files")
    fun getAllFiles() : Array<*>{
        return fileManager.getAllFiles()
    }

}