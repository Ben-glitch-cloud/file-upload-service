package com.fdm.fileUploadService.controller

import com.fdm.fileUploadService.modle.File
import com.fdm.fileUploadService.modle.FileUpload
import com.fdm.fileUploadService.modle.ResponseException
import com.fdm.fileUploadService.service.FileManagerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    fun postNewFile(@RequestParam file: MultipartFile) : ResponseEntity<ResponseException> {
        try {
            fileManagerService.saveFile(FileUpload(file.bytes))
        }catch (ex: Exception){
            return ResponseEntity(ResponseException("${ex.message}", HttpStatus.BAD_REQUEST.name),
                HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity(ResponseException("", HttpStatus.OK.name),
            HttpStatus.OK)
    }

    @DeleteMapping("/file/delete")
    fun deleteFileByIdentifier(@RequestParam id: Long){
        fileManagerService.deleteFile(id)
    }
}