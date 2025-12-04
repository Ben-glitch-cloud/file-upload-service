package com.fdm.fileUploadService.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fdm.fileUploadService.modle.File
import com.fdm.fileUploadService.modle.FileDTO
import com.fdm.fileUploadService.service.FileManagerService
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.multipart.MultipartFile
import kotlin.test.Test

@WebMvcTest(FileManagerController::class)
@ExtendWith(MockitoExtension::class)
class FileManagerControllerTest(
    @Value("\${storage.filePath}") var filePath: String
) {
    @Autowired
    private lateinit var mvc: MockMvc

    private var mockException = mockk<Exception>()

    @MockitoBean
    private lateinit var fileManagerService: FileManagerService

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
            File(null, fileOneDtoBytes)
        )

        `when`(fileManagerService.getAllFiles()).thenReturn(expectedResult)

        mvc.perform(get("/files"))
            .andExpect(status().isOk)
            .andExpect(content()
                .string(jacksonObjectMapper().writeValueAsString(expectedResult)))
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

        mvc.perform(multipart("/file/save")
            .file(vaildFile)
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

        `when`(fileManagerService.saveFile(FileDTO(invalidFileSize.bytes)))
            .thenThrow(Exception(
                "Invalid File - size limited reached must be small or equal than 2MB"
            ))

        mvc.perform(multipart("/file/save")
            .file(invalidFileSize)
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `Delete Request - Successfully delete file by identifier`(){
        val fileIdentifier = 1L

        mvc.perform(
            delete("/file/delete?id=${fileIdentifier}")
        ).andExpect(status().isOk)
    }

}
