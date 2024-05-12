package com.yologger.sns.api.rest.advice

//@RestControllerAdvice(basePackageClasses = [PmsResource::class])
//class PostExceptionHandler {
//
//    @ExceptionHandler(value = [UserNotFoundException::class])
//    fun handle(e: UserNotFoundException): ResponseEntity<Response<CreatePostFailureResponse>> {
//        return CreatePostFailureResponse(message = e.message!!).wrapBadRequest()
//    }
//
//    @ExceptionHandler(value = [PostNotFoundException::class])
//    fun handle(e: PostNotFoundException): ResponseEntity<Response<EditPostFailureResponse>> {
//        return EditPostFailureResponse(message = e.message!!).wrapBadRequest()
//    }
//
//    @ExceptionHandler(value = [WrongPostWriterException::class])
//    fun handle(e: WrongPostWriterException): ResponseEntity<Response<EditPostFailureResponse>> {
//        return EditPostFailureResponse(message = e.message!!).wrapBadRequest()
//    }
//}