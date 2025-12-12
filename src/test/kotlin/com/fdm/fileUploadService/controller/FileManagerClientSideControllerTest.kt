package com.fdm.fileUploadService.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fdm.fileUploadService.client.FileStorage
import com.fdm.fileUploadService.model.File
import com.fdm.fileUploadService.model.ResponseException
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.test.Test

@WebMvcTest(FileManagerClientSideController::class)
@ExtendWith(MockitoExtension::class)
class FileManagerClientSideControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockitoBean
    private lateinit var FileStorage: FileStorage

    val sdf = SimpleDateFormat("dd/MM/yyyy")
    val currentTestDate = sdf.format(Date())

    val mapper = jacksonObjectMapper()

    @Test
    fun `View - When directing to the dashboard page then display all files stored`(){
        val validFileArray = arrayOf(
            File(id = 1L, fileName = "", description = "", data = null, date = currentTestDate),
            File(id = 2L, fileName = "", description = "", data = null, date = currentTestDate),
            File(id = 3L, fileName = "", description = "", data = null, date = currentTestDate)
        )

        `when`(FileStorage.getAllFiles()).thenReturn(validFileArray)

        mvc.perform(get("/home"))
            .andExpect(status().isOk)
            .andExpect(view().name("dashboard"))
            .andExpect(model().attributeExists("storedFilesResponsePayload"))
            .andExpect(model().attribute("storedFilesResponsePayload", validFileArray))
    }

    @Test
    fun `View - When directing to file search page with a valid id then display the file`(){
        val validFile = File(id = 1L, fileName = "", description = "", data = null, date = currentTestDate)
        val fileMappedToObject: LinkedHashMap<String, String> = mapper.convertValue(validFile);

        `when`(FileStorage.getFileById(1L)).thenReturn(fileMappedToObject)

        mvc.perform(get("/retrieve/${1L}"))
            .andExpect(status().isOk)
            .andExpect(view().name("fileSearch"))
            .andExpect(model().attributeExists("storedFilesResponsePayload"))
            .andExpect(model().attributeDoesNotExist("errorHandler"))
    }

    @Test
    fun `View - When directing to file search page with an invalid id then display an error message`(){
        val response = ResponseException(errorMessage = "No file found with Identifier 6", httpStatus = "OK")
        val responseExceptionMappedToObject: LinkedHashMap<String, String> = mapper.convertValue(response);

        `when`(FileStorage.getFileById(6L)).thenReturn(responseExceptionMappedToObject)

        mvc.perform(get("/retrieve/${6L}"))
            .andExpect(status().isOk)
            .andExpect(view().name("fileSearch"))
            .andExpect(model().attributeExists("errorHandler"))
            .andExpect(model().attributeDoesNotExist("storedFilesResponsePayload"))
    }

}