package com.fdm.fileUploadService.configuration

import com.fdm.fileUploadService.Repository.FileRepository
import com.fdm.fileUploadService.modle.File
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PopulateFileDataBaseCongif{

    @Bean
    fun saveFiles(repository: FileRepository) : String{

        val data = ByteArray(1 * 8 * 8)

        var fileOne = File(null, data)
        var fileTwo = File( null, data)
        var fileThree = File(null, data)

        repository.save(fileOne)
        repository.save(fileTwo)
        repository.save(fileThree)

        return "Saved Files"
    }

}