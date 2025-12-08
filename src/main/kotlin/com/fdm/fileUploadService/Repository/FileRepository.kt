package com.fdm.fileUploadService.Repository

import com.fdm.fileUploadService.model.File
import org.springframework.data.repository.Repository

interface FileRepository : Repository<File, Long> {
    fun save(file: File)

    fun findAll() : Array<File>

    fun findById(id: Long) : File

    fun deleteById(id: Long)

    fun deleteAll()
}