package com.fdm.fileUploadService.controller

import com.fdm.fileUploadService.client.FileStorage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

@Controller
class FileManagerClientSideController {

    @Autowired
    lateinit var fileStorage: FileStorage

    @GetMapping("/home")
    @ResponseBody
    fun retrieveStoredFiles(model : Model) : ModelAndView {
        val mav = ModelAndView("dashboard")
        mav.addObject("storedFilesResponsePayload", fileStorage.getAllFiles())
        return mav
    }

}