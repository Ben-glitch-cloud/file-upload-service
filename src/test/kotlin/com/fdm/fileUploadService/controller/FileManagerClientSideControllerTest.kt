package com.fdm.fileUploadService.controller

import com.fdm.fileUploadService.client.FileStorage
import com.fdm.fileUploadService.model.File
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import kotlin.test.Test
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view
import java.text.SimpleDateFormat
import java.util.Date

@WebMvcTest(FileManagerClientSideController::class)
@ExtendWith(MockitoExtension::class)
class FileManagerClientSideControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockitoBean
    private lateinit var FileStorage: FileStorage

    @Test
    fun `View - When directing to the file page then display all files stored`(){
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val currentTestDate = sdf.format(Date())

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

}