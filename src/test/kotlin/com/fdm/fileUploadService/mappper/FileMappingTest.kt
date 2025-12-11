package com.fdm.fileUploadService.mappper

import com.fdm.fileUploadService.mapper.FileMapping
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
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
        val fileDescription = "Test file description"

        val result = fileMapping.convertUserFileToFile(multipartFileOne, fileDescription)

        assertNull(result.id)
        assertEquals(result.fileName, "testFileOne.txt")
        assertNotNull(result.description)
        assertNotNull(result.data)
        assertNotNull(result.date)
    }

}