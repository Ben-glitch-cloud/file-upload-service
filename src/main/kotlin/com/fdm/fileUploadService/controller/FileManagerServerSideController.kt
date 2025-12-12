package com.fdm.fileUploadService.controller

import com.fdm.fileUploadService.annotation.Generated
import com.fdm.fileUploadService.model.File
import com.fdm.fileUploadService.model.ResponseException
import com.fdm.fileUploadService.service.FileManagerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class FileManagerServerSideController(
    @set:Generated
    var fileManagerService: FileManagerService
) {

    @GetMapping("/files")
    fun getAllFiles() : Array<File>{
        return fileManagerService.getAllFiles()
    }

    @GetMapping("/file/{identifier}")
    fun getFileByIdentifier(@PathVariable(value="identifier") identifier : Long) : ResponseEntity<*> {
        return try{
             ResponseEntity(fileManagerService.getFileById(identifier), HttpStatus.OK)
        }catch(ex: Exception){
             ResponseEntity(ResponseException("${ex.message}", HttpStatus.OK.name), HttpStatus.OK)
        }

    }

    @PostMapping("/file/save")
    fun postNewFile(
        @RequestParam file: MultipartFile,
        @RequestParam fileDescription: String
    ) : ResponseEntity<ResponseException> {
        try {
            fileManagerService.saveFile(file, fileDescription)
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