package API.architecture.kotlinAPI.api

import API.architecture.kotlinAPI.errorHandling.CustomMessageError
import API.architecture.kotlinAPI.errorHandling.ErrorMessage
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@RestController
@ControllerAdvice
class ErrorHandler : ResponseEntityExceptionHandler() {
    //Logger
    var logger = LoggerFactory.getLogger(ErrorHandler::class.java)
    @ExceptionHandler(CustomMessageError::class)
    fun handleCustomMessageErrorException(
        exception: CustomMessageError,
        webRequest: WebRequest?
    ): ResponseEntity<ErrorMessage> {
        //Logger
        logger.info("Error: " + exception.message)
        //Custom Error Message
        val obj = ErrorMessage(Date(), exception.message)

        return ResponseEntity<ErrorMessage>(obj, HttpStatus.NOT_ACCEPTABLE)
    }
}