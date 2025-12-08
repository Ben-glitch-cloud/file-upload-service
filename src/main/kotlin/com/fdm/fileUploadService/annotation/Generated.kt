package com.fdm.fileUploadService.annotation
import java.lang.annotation.Documented
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*

@Retention(RUNTIME)
@Target(PROPERTY_GETTER, PROPERTY_SETTER)
annotation class Generated {
}