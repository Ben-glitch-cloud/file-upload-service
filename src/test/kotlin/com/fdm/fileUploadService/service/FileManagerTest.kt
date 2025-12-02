package com.fdm.fileUploadService.service



import com.fdm.fileUploadService.Repository.FileRepository
import com.fdm.fileUploadService.mapper.FileMapping
import com.fdm.fileUploadService.modle.FileDTO
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.PropertySource
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

   @BeforeEach
   fun setup(){
//       val data = ByteArray(1 * 1024 * 1024)
//       val multipartFileOne: MultipartFile = MockMultipartFile(
//            "file",
//            "testFileOne.txt",
//            "text/plain",
//            data
//        )
//       val multipartFileTwo: MultipartFile = MockMultipartFile(
//           "file",
//           "testFileTwo.txt",
//           "text/plain",
//           data
//       )
//       val multipartFileThree: MultipartFile = MockMultipartFile(
//           "file",
//           "testFileThree.txt",
//           "text/plain",
//           data
//       )
//
//       fileManagerService.saveFile(FileDTO(multipartFileOne.bytes))
//       fileManagerService.saveFile(FileDTO(multipartFileTwo.bytes))
//       fileManagerService.saveFile(FileDTO(multipartFileThree.bytes))
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

    @AfterEach
    fun tearDown(){
//        fileManagerService.deleteAllFiles()
//        println("All Files Deleted")
//        val result = fileManagerService.getAllFiles()
//
//        println(result)
    }
}

