package com.fdm.fileUploadService.modle


import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
class File(
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var name: String = ""
)