package com.fdm.fileUploadService.model


import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import java.util.Date

@Entity
open class File(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null,

    var fileName: String = "",

    var description: String = "",

    @Lob
    var data: ByteArray? = null,

    var date: String = ""
)