package com.fdm.fileUploadService.configuration

import com.fdm.fileUploadService.Repository.FileRepository
import com.fdm.fileUploadService.annotation.Generated
import com.fdm.fileUploadService.model.File
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
class PopulateFileDataBaseCongif{

    @set:Generated
    @Autowired
    lateinit var env: Environment

    @Bean
    fun saveFiles(repository: FileRepository) : String {

        val activeProfile = env.activeProfiles.toMutableList()

        if(activeProfile.isEmpty()) {
            val data = ByteArray(1 * 8 * 8)

            var fileOne = File(null, data)
            var fileTwo = File(null, data)
            var fileThree = File(null, data)

            repository.save(fileOne)
            repository.save(fileTwo)
            repository.save(fileThree)

            var numberOfFilesSaved = repository.findAll().size

            println("Environment : not found \n" +
                    "- Default environment : dev \n" +
                    "- Database has been populated with $numberOfFilesSaved files")
            return ""
        } else {
            println("Environment : $activeProfile \n" +
                    "- Database has not been populated")
            return ""
        }
    }

}