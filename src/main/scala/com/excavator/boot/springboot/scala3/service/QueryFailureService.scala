package com.excavator.boot.springboot.scala3.service

import com.excavator.boot.springboot.scala3.entity.FailureRecord
import com.excavator.boot.springboot.scala3.repository.QueryFailureRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

import java.time.LocalDate
import java.util.List as JList

@Service
class QueryFailureService(val queryFailureRepository: QueryFailureRepository) {
  val log = LoggerFactory.getLogger(classOf[QueryFailureService])

  def queryFailure(time:LocalDate):JList[FailureRecord] = {
    val startTime = time.atTime(0, 0, 0, 0)
    val endTime = time.atTime(23,59,59,59)
    queryFailureRepository.queryFailure(startTime, endTime)
  }

}
