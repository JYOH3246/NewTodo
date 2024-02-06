package com.example.newtodo.common.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Aspect
@Component
class StopWatchAspect {
    private val logger = LoggerFactory.getLogger("Execution Time Logger")

    @Around("@annotation(com.example.newtodo.common.aop.StopWatch)")
    fun run(joinPoint: ProceedingJoinPoint) {
        val stopWatch = StopWatch()

        stopWatch.start()
        joinPoint.proceed()
        stopWatch.stop()

        val timeElapsedMs = stopWatch.totalTimeMillis

        val methodName = joinPoint.signature.name
        val methodArguments = joinPoint.args

        logger.info("Method Name: $methodName | Arguments: ${methodArguments.joinToString(", ")} | Execution Time: ${timeElapsedMs}ms")
    }
    /*
    fun <T> loggingStopWatch(function: () -> T): T {
        val start = LocalDateTime.now()
        logger.info("시작 시간 : $start")
        val result = function.invoke()
        val end = LocalDateTime.now()
        logger.info("종료 시간 : $end")
        logger.info("실행 시간 : ${Duration.between(start, end).toMillis()}ms")
        return result
    }

     */

}