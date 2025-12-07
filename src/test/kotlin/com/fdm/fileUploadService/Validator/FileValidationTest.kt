package com.fdm.fileUploadService.Validator

import com.fdm.fileUploadService.validator.FileValidation
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile
import kotlin.test.Test

class FileValidationTest {

    var fileValidation = FileValidation()

    @Test
    fun `When file size is higher than 2MB then through exception`(){
        val data = ByteArray(3 * 1024 * 1024)
        val invalidMultipartFile: MultipartFile = MockMultipartFile(
            "file",
            "testFileFour.txt",
            "text/plain",
            data
        )

        assertThrows<Exception>{
            fileValidation.storageAmount(invalidMultipartFile)
        }
    }

    @Test
    fun `When file size is lower than 2MB then dont through an exception`(){
        val data = ByteArray(1 * 1024 * 1024)
        val validMultipartFile: MultipartFile = MockMultipartFile(
            "file",
            "testFileFour.txt",
            "text/plain",
            data
        )

        assertDoesNotThrow { fileValidation.storageAmount(validMultipartFile) }
    }

    @Test
    fun `When file is null then through an exception`(){
        val invalidMultipartFile: MultipartFile = MockMultipartFile(
            "file",
            "testFileFour.txt",
            "text/plain",
            null
        )

        assertThrows<Exception>{
            fileValidation.storageAmount(invalidMultipartFile)
        }
    }

    @Test
    fun `When file size is zero then through exception`(){
        val data = ByteArray(0 * 0 * 0)
        val invalidMultipartFile: MultipartFile = MockMultipartFile(
            "file",
            "testFileFour.txt",
            "text/plain",
            data
        )

        assertThrows<Exception>{
            fileValidation.storageAmount(invalidMultipartFile)
        }
    }

    @Test
    fun `check one`(){
        val data = ByteArray(1 * 16 * 16)
        val invalidMultipartFile: MultipartFile = MockMultipartFile(
            "file",
            "testFileFour.png",
            "text/plain",
            data
        )

        assertThrows<Exception>{
            fileValidation.fileType(invalidMultipartFile)
        }
    }
}