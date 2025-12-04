package com.fdm.fileUploadService.mappper

import com.fdm.fileUploadService.mapper.FileMapping
import com.fdm.fileUploadService.modle.FileUpload
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile
import kotlin.test.Test
import kotlin.test.assertNull

class FileMappingTest {

    var fileMapping = FileMapping()

    @Test
    fun `map user file to file storage`(){
        val data = ByteArray(1 * 8 * 8)
        val multipartFileOne: MultipartFile = MockMultipartFile(
            "file",
            "testFileOne.txt",
            "text/plain",
            data
        )

        val fileToBytes = multipartFileOne.bytes
        val userFile = FileUpload(fileToBytes)
        val result = fileMapping.convertUserFileToFile(userFile)

        // assert type
        assertNull(result.id)
    }

}