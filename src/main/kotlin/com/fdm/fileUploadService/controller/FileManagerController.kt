package com.fdm.fileUploadService.controller

import com.fdm.fileUploadService.modle.File
import com.fdm.fileUploadService.modle.FileDTO
import com.fdm.fileUploadService.service.FileManagerService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class FileManagerController(
    var fileManagerService: FileManagerService
) {

    @GetMapping("/files")
    fun getAllFiles() : Array<File>{
        return fileManagerService.getAllFiles()
    }

    @PostMapping("/file/save")
    fun postNewFile(@RequestParam file: MultipartFile){
        var fileToBytes = file.bytes
        fileManagerService.saveFile(FileDTO(fileToBytes))
    }

    @DeleteMapping("/file/delete")
    fun deleteFileByIdentifier(@RequestParam id: Long){
        fileManagerService.deleteFile(id)
    }
}