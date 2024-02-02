package com.example.newtodo.common.exception

import com.example.newtodo.common.exception.base.BaseResponseCode

class BaseException (val baseResponseCode: BaseResponseCode) : RuntimeException()