package com.fdm.fileUploadService.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fdm.fileUploadService.annotation.Generated
import com.fdm.fileUploadService.client.FileStorage
import com.fdm.fileUploadService.model.File
import com.fdm.fileUploadService.model.ResponseException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

@Controller
class FileManagerClientSideController(
    @set:Generated
    var fileStorage: FileStorage
) {

    @GetMapping("/home")
    @ResponseBody
    fun retrieveStoredFiles(model : Model) : ModelAndView {
        val mav = ModelAndView("dashboard")
        mav.addObject("storedFilesResponsePayload", fileStorage.getAllFiles())
        return mav
    }

    @GetMapping("/retrieve/{identifier}")
    @ResponseBody
    fun retrieveSingleFile(@PathVariable(value="identifier") identifier : Long, model : Model) : ModelAndView {
        val mapper = jacksonObjectMapper()
        val retrievedGetByIdResponse = fileStorage.getFileById(identifier)
        val mav = ModelAndView("fileSearch")
        try{
            val singleFileObject = mapper.convertValue(retrievedGetByIdResponse, File::class.java)
            mav.addObject("storedFilesResponsePayload", singleFileObject)
        }catch(ex: IllegalArgumentException){
            val errorHandler = mapper.convertValue(retrievedGetByIdResponse, ResponseException::class.java)
            mav.addObject("errorHandler", errorHandler)
        }
        return mav
    }

}