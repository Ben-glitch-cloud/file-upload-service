package com.fdm.fileUploadService.configuration

import com.fdm.fileUploadService.repository.FileRepository
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

            val fileOne = File(
                id = null,
                fileName = "Budget",
                description = "Finance statements for 2025",
                data = data, date = "08/09/2025"
            )
            val fileTwo = File(
                id = null,
                fileName = "Contacts",
                description = "Company contact list for the human resource department",
                data = data,
                date = "22/07/2025"
            )
            val fileThree = File(
                id = null,
                fileName = "Policies",
                description = "Updated company goals and objects for 2025",
                data = data,
                date = "03/09/2025"
            )

            repository.save(fileOne)
            repository.save(fileTwo)
            repository.save(fileThree)

            val numberOfFilesSaved = repository.findAll().size

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