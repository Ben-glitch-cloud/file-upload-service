package com.fdm.fileUploadService.service



import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.PropertySource
import org.springframework.test.context.ActiveProfiles
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
@ActiveProfiles("test")
@PropertySource("classpath:application-test.yml")
class FileManagerTest {

    @Autowired
    lateinit var fileManagerService: FileManagerService

    @Test
    fun `get all files from test storage`(){
        var result = fileManagerService.getAllFiles()

        assertEquals(3, result.count())
    }
}
