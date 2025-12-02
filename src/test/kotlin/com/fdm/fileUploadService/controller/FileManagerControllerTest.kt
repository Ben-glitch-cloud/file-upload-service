package com.fdm.fileUploadService.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fdm.fileUploadService.modle.File
import com.fdm.fileUploadService.modle.FileDTO
import com.fdm.fileUploadService.service.FileManagerService
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.Test
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import org.springframework.web.multipart.MultipartFile

@WebMvcTest(FileManagerController::class)
@ExtendWith(MockitoExtension::class)
class FileManagerControllerTest(
    @Value("\${storage.filePath}") var filePath: String
) {
    @Autowired
    private lateinit var mvc: MockMvc

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
        val multipartFileOne = MockMultipartFile(
            "file",
            "testFileOne.txt",
            "text/plain",
            data
        )

        mvc.perform(multipart("/file/save")
            .file(multipartFileOne)
        ).andExpect(status().isOk)
    }

}
