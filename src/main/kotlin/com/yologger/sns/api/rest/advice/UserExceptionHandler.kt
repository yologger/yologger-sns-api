package com.yologger.sns.api.rest.advice

//@RestControllerAdvice(basePackageClasses = [UserResource::class])
//class UserExceptionHandler {
//
//    @ExceptionHandler(value = [DuplicateUserException::class])
//    fun handleUserAlreadyExistException(e: DuplicateUserException): ResponseEntity<Response<JoinFailureResponse>> {
//        return JoinFailureResponse(message = "User already exists").wrapConflict()
//    }
//}