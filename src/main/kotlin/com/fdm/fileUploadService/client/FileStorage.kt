package com.fdm.fileUploadService.client

import com.fdm.fileUploadService.model.File
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


@FeignClient(name = "file-manager-service", url="http://localhost:8080")
interface FileStorage {
    @GetMapping("/files")
    fun getAllFiles() : Array<File>

    @GetMapping("/file/{identifier}")
    fun getFileById(@RequestParam("identifier") id : Long) : Any
}