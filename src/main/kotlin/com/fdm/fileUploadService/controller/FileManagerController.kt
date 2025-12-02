package com.fdm.fileUploadService.controller

import com.fdm.fileUploadService.modle.File
import com.fdm.fileUploadService.modle.FileDTO
import com.fdm.fileUploadService.service.FileManagerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FileManagerController(
    var fileManagerService: FileManagerService
) {

    @GetMapping("/files")
    fun getAllFiles() : Array<File>{
        return fileManagerService.getAllFiles()
    }

    @PostMapping("/file/save")
    fun postNewFile(@RequestBody file: FileDTO){
        fileManagerService.saveFile(file)
    }
}