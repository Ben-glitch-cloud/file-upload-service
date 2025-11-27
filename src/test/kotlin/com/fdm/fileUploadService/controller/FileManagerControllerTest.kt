package com.fdm.fileUploadService.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fdm.fileUploadService.service.FileManager
import io.mockk.every
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import io.mockk.spyk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import kotlin.test.Test

@WebMvcTest(FileManagerController::class)
@ExtendWith(MockitoExtension::class)
class FileManagerControllerTest(
    @Value("\${storage.filePath}") var filePath: String
) {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockitoBean
    var fileManager = FileManager(filePath)

    private var controller = FileManagerController(fileManager)

    @BeforeEach
    fun setup(){
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    @Test
    fun `GET Request - successfully return all files when called`(){
        val expectedResult = arrayOf("helloWorld.txt", "shoppingList.txt", "learningOutcome.txt") as Array<*>

        mvc.perform(get("/files"))
            .andExpect(status().isOk)
            .andExpect(content()
                .string(jacksonObjectMapper().writeValueAsString(expectedResult)))
    }
}
