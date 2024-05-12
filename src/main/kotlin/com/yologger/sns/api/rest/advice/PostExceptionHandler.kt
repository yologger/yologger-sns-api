package com.yologger.sns.api.rest.advice

//@RestControllerAdvice(basePackageClasses = [PmsResource::class])
//class PostExceptionHandler {
//
//    @ExceptionHandler(value = [UserNotExistException::class])
//    fun handle(e: UserNotExistException): ResponseEntity<Response<CreatePostFailureResponse>> {
//        return CreatePostFailureResponse(message = e.message!!).wrapBadRequest()
//    }
//
//    @ExceptionHandler(value = [PostNotExistException::class])
//    fun handle(e: PostNotExistException): ResponseEntity<Response<EditPostFailureResponse>> {
//        return EditPostFailureResponse(message = e.message!!).wrapBadRequest()
//    }
//
//    @ExceptionHandler(value = [WrongPostWriterException::class])
//    fun handle(e: WrongPostWriterException): ResponseEntity<Response<EditPostFailureResponse>> {
//        return EditPostFailureResponse(message = e.message!!).wrapBadRequest()
//    }
//}