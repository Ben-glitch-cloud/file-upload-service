package com.fdm.fileUploadService.service



import com.fdm.fileUploadService.Repository.FileRepository
import com.fdm.fileUploadService.mapper.FileMapping
import com.fdm.fileUploadService.modle.File
import com.fdm.fileUploadService.modle.FileDTO
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.multipart.MultipartFile
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
@ActiveProfiles("test")
@PropertySource("classpath:application-test.yml")
class FileManagerTest {

    @Autowired
    var fileMapper = FileMapping()

    @Autowired
    lateinit var repository: FileRepository

    @Autowired
     var fileManagerService = FileManagerService(fileMapper)

    @Autowired
    lateinit var env: Environment

    @BeforeEach
    fun setUp(){
        val data = ByteArray(1 * 8 * 8)

        var fileOne = File(null, data)
        var fileTwo = File(null, data)
        var fileThree = File(null, data)

        repository.save(fileOne)
        repository.save(fileTwo)
        repository.save(fileThree)
    }

    @Test
    fun `get all files from test storage`(){
        val result = fileManagerService.getAllFiles()

        assertEquals(3, result.count())
    }

    @Test
    fun `save valid file to test storage`(){
        val data = ByteArray(1 * 8 * 8)
        val multipartFileOne: MultipartFile = MockMultipartFile(
            "file",
            "testFileFour.txt",
            "text/plain",
            data
        )

        val userFile = FileDTO(multipartFileOne.bytes)

        fileManagerService.saveFile(userFile)

        val result = fileManagerService.getAllFiles()
        assertEquals(result.size, 4)
    }

    @Test
    fun `delete file with ID`(){
        fileManagerService.deleteFile(1L)

        val result = fileManagerService.getAllFiles()
        assertEquals(result.size, 2)
    }

    @AfterEach
    fun teardown(){
        repository.deleteAll()
    }

}

