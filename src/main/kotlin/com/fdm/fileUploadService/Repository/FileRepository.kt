package com.fdm.fileUploadService.Repository

import com.fdm.fileUploadService.modle.File
import org.springframework.data.repository.Repository

interface FileRepository : Repository<File, Long> {
    fun save(file: File)

    fun findAll() : Array<File>

    // Work on this later
    fun findById(id: Long) : File

    fun deleteById(id: Long)

    fun deleteAll()
}