package com.fdm.fileUploadService.service



import com.fdm.fileUploadService.mapper.FileMapping
import com.fdm.fileUploadService.modle.FileDTO
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
    var fileMapper = FileMapping()

    @Autowired
     var fileManagerService = FileManagerService(fileMapper)

    @Test
    fun `get all files from test storage`(){
        var result = fileManagerService.getAllFiles()

        assertEquals(3, result.count())
    }

    @Test
    fun `save valid file to test storage`(){
        val userFile = FileDTO("testFile.txt")

        fileManagerService.saveFile(userFile)

        val result = fileManagerService.getAllFiles()
        assertEquals(result.size, 4)
    }
}
