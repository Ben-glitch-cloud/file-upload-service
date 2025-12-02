package com.fdm.fileUploadService.mappper

import com.fdm.fileUploadService.mapper.FileMapping
import com.fdm.fileUploadService.modle.FileDTO
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class FileMappingTest {

    var fileMapping = FileMapping()

    @Test
    fun `map user file to file storage`(){
        val userFile = FileDTO("testFile.txt")

        val result = fileMapping.convertUserFileToFile(userFile)

        assertEquals("testFile.txt", result.name)
        assertNull(result.id)
    }

}