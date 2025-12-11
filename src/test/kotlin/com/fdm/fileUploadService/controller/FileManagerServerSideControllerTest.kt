package com.fdm.fileUploadService.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fdm.fileUploadService.model.File
import com.fdm.fileUploadService.model.ResponseException
import com.fdm.fileUploadService.service.FileManagerService
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.multipart.MultipartFile
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.test.Test

@WebMvcTest(FileManagerServerSideController::class)
@ExtendWith(MockitoExtension::class)
class FileManagerServerSideControllerTest(
    @Value("\${storage.filePath}") var filePath: String
) {
    @Autowired
    private lateinit var mvc: MockMvc

    private var mockException = mockk<Exception>()

    @MockitoBean
    private lateinit var fileManagerService: FileManagerService

    val sdf = SimpleDateFormat("dd/MM/yyyy")
    val currentTestDate = sdf.format(Date())

    @Test
    fun `GET Request - Successfully return all files when called`(){
        val data = ByteArray(1 * 1024 * 1024)
        val multipartFileOne: MultipartFile = MockMultipartFile(
            "file",
            "testFileOne.txt",
            "text/plain",
            data
        )
        val fileOneDtoBytes = multipartFileOne.bytes

        val expectedResult = arrayOf<File>(
            File(id = null, fileName = "", description = "", data = fileOneDtoBytes, date = currentTestDate)
        )

        `when`(fileManagerService.getAllFiles()).thenReturn(expectedResult)

        mvc.perform(get("/files"))
            .andExpect(status().isOk)
            .andExpect(content()
                .string(jacksonObjectMapper().writeValueAsString(expectedResult)))
    }

    @Test
    fun `GET Request - Successfully return a single file with an identity`(){
        val data = ByteArray(1 * 8 * 8)
        val multipartFileOne: MultipartFile = MockMultipartFile(
            "file",
            "testFileOne.txt",
            "text/plain",
            data
        )
        val fileOneDtoBytes = multipartFileOne.bytes

        val expectedResult = File(id = null, fileName = "", description = "", data = fileOneDtoBytes, date = currentTestDate)

        `when`(fileManagerService.getFileById(1L)).thenReturn(expectedResult)

        mvc.perform(get("/file/${1L}"))
            .andExpect(status().isOk)
            .andExpect(content()
                .string(jacksonObjectMapper().writeValueAsString(expectedResult)))
    }

    @Test
    fun `GET Request - Failed to return a non existing`(){
        val testIdentifier200 = 200L
        var response = ResponseException(
            errorMessage = "No file found with Identifier $testIdentifier200",
            HttpStatus.OK.name
        )

        `when`(fileManagerService.getFileById(testIdentifier200)).thenThrow(Exception(
            "No file found with Identifier $testIdentifier200")
        )

        mvc.perform(get("/file/$testIdentifier200")
        ).andExpect(status().isOk)
            .andExpect(
                content().string(jacksonObjectMapper().writeValueAsString(response)
                )
            )

    }

    @Test
    fun `POST Request - Successfully save valid file`(){
        val data = ByteArray(1 * 8 * 8)
        val vaildFile = MockMultipartFile(
            "file",
            "testFileOne.txt",
            "text/plain",
            data
        )
        val fileDescription = "Test file description"

        mvc.perform(multipart("/file/save")
            .file(vaildFile)
            .param("fileDescription", fileDescription)
        ).andExpect(status().isOk)
    }

    @Test
    fun `POST Request - Failed as file size was larger than 2MB`(){
        val data = ByteArray(3 * 1024 * 1024)
        val invalidFileSize = MockMultipartFile(
            "file",
            "testFileOne.txt",
            "text/plain",
            data
        )
        val fileDescription = "Test file description"

        var response = ResponseException(
            errorMessage = "Invalid File - size limited reached must be small or equal than 2MB",
            HttpStatus.BAD_REQUEST.name
        )

        `when`(fileManagerService.saveFile(invalidFileSize, fileDescription))
            .thenThrow(Exception(
                "Invalid File - size limited reached must be small or equal than 2MB"
            ))

        mvc.perform(multipart("/file/save")
            .file(invalidFileSize)
            .param("fileDescription", fileDescription)
        ).andExpect(status().isBadRequest)
            .andExpect(
                content().string(jacksonObjectMapper().writeValueAsString(response)
                )
            )
    }

    @Test
    fun `POST Request - Failed as file description was to large`(){
        val data = ByteArray(1 * 16 * 16)
        val validFileSize = MockMultipartFile(
            "file",
            "testFileOne.txt",
            "text/plain",
            data
        )
        val invalidFileDescription = "Test file description"

        val response = ResponseException(
            errorMessage = "Invalid File Description - must be under 200 characters",
            HttpStatus.BAD_REQUEST.name
        )

        `when`(fileManagerService.saveFile(validFileSize, invalidFileDescription))
            .thenThrow(Exception(
                "Invalid File Description - must be under 200 characters"
            ))

        mvc.perform(multipart("/file/save")
            .file(validFileSize)
            .param("fileDescription", invalidFileDescription)
        ).andExpect(status().isBadRequest)
            .andExpect(
                content().string(jacksonObjectMapper().writeValueAsString(response)
                )
            )
    }

    @Test
    fun `Delete Request - Successfully delete file by identifier`(){
        val fileIdentifier = 1L

        mvc.perform(
            delete("/file/delete?id=${fileIdentifier}")
        ).andExpect(status().isOk)
    }

}
