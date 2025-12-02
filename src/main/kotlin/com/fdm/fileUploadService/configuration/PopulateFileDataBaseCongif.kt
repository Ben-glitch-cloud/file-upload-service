package com.fdm.fileUploadService.configuration

import com.fdm.fileUploadService.Repository.FileRepository
import com.fdm.fileUploadService.modle.File
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PopulateFileDataBaseCongif{

    @Bean
    fun saveFiles(repository: FileRepository) : String{
        var fileOne = File(null, "helloworld.txt")
        var fileTwo = File( null, "learningOutcome.txt")
        var fileThree = File(null, "shoppingList.txt")

        repository.save(fileOne)
        repository.save(fileTwo)
        repository.save(fileThree)

        return "Saved Files"
    }

}