package com.fdm.fileUploadService.Validator

import com.fdm.fileUploadService.modle.FileDTO
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

        val invalidFileSize = invalidMultipartFile.bytes

        assertThrows<Exception>{
            fileValidation.maximumSize(FileDTO(invalidFileSize))
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

        val validFileSize = validMultipartFile.bytes

        assertDoesNotThrow { fileValidation.maximumSize(FileDTO(validFileSize)) }
    }

    @Test
    fun `When file is null then dont through an exception`(){
        assertDoesNotThrow { fileValidation.maximumSize(FileDTO(null)) }
    }

}