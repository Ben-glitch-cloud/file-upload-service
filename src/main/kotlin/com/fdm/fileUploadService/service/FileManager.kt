package com.fdm.fileUploadService.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Paths

@Component
class FileManager(
    @Value("\${storage.filePath}") var filePath: String
) {

    fun getAllFiles(): Array<Any> {
        val projectDirAbsolutePath = Paths.get("").toAbsolutePath().toString()
        val resourcesPath = Paths.get(projectDirAbsolutePath, filePath)
        val paths = Files.walk(resourcesPath)
            .filter { item -> Files.isRegularFile(item) }
            .map { it.toString().split("/").last() }.toArray()
        return paths
    }
}