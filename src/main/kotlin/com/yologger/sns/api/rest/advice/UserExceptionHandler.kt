package com.yologger.sns.api.rest.advice

//@RestControllerAdvice(basePackageClasses = [UserResource::class])
//class UserExceptionHandler {
//
//    @ExceptionHandler(value = [UserAlreadyExistException::class])
//    fun handleUserAlreadyExistException(e: UserAlreadyExistException): ResponseEntity<Response<JoinFailureResponse>> {
//        return JoinFailureResponse(message = "User already exists").wrapConflict()
//    }
//}