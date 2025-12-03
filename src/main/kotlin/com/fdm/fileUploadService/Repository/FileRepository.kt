package com.fdm.fileUploadService.Repository

import com.fdm.fileUploadService.modle.File
import org.springframework.data.repository.Repository

interface FileRepository : Repository<File, Long> {
    fun save(file: File)

    fun findAll() : Array<File>

    fun deleteById(id: Long)

    fun deleteAll()
}