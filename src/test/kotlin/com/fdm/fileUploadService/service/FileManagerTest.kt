package com.fdm.fileUploadService.service



import com.fdm.fileUploadService.Repository.FileRepository
import com.fdm.fileUploadService.mapper.FileMapping
import com.fdm.fileUploadService.modle.File
import com.fdm.fileUploadService.modle.FileUpload
import com.fdm.fileUploadService.validator.FileValidation
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
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

    var fileMapper = FileMapping()

    var fileValidation = FileValidation()

    @Autowired
    lateinit var repository: FileRepository

    @Autowired
     var fileManagerService = FileManagerService(fileMapper, fileValidation)

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
    fun `When requesting getAllFiles then return all files found in the database`(){
        val result = fileManagerService.getAllFiles()

        assertEquals(3, result.count())
    }

    @Test
    fun `When requesting to save a valid file then a file should be added to the database`(){
        val data = ByteArray(1 * 8 * 8)
        val multipartFileOne: MultipartFile = MockMultipartFile(
            "file",
            "testFileFour.txt",
            "text/plain",
            data
        )

        val userFile = FileUpload(multipartFileOne.bytes)

        fileManagerService.saveFile(userFile)

        val result = fileManagerService.getAllFiles()
        assertEquals(result.size, 4)
    }

    @Test
    fun `When requesting to save a invalid file then through an exception`(){
        val data = ByteArray(3 * 1024 * 1024)
        val multipartFileOne: MultipartFile = MockMultipartFile(
            "file",
            "testFileFour.txt",
            "text/plain",
            data
        )

        val userFile = FileUpload(multipartFileOne.bytes)

        assertThrows<Exception> {
            fileManagerService.saveFile(userFile)
        }

        val result = fileManagerService.getAllFiles()

        assertEquals(result.size, 3)
    }

    @Test
    fun `delete file with ID`(){
        val firstInstanceIdentifier = fileManagerService.getAllFiles()[0].id as Long

        fileManagerService.deleteFile(firstInstanceIdentifier)

        val result = fileManagerService.getAllFiles()
        assertEquals(result.size, 2)
    }

    @AfterEach
    fun teardown(){
        repository.deleteAll()
    }

}

