package com.excavator.boot.springboot.scala3.repository

import com.excavator.boot.springboot.scala3.entity.FailureRecord
import jakarta.persistence.{EntityManager, PersistenceContext}
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

import java.time.LocalDateTime
import com.excavator.boot.springboot.scala3.helper.DateTimeHelper


@Repository
class QueryFailureRepository {
  val log = LoggerFactory.getLogger(classOf[QueryFailureRepository])

  @PersistenceContext
  val entityManager:EntityManager = null

  def queryFailure(startTime:LocalDateTime,endTime:LocalDateTime) = {
    var sql = "SELECT r.orderNo, r.amount, r.res, u.phone, u.unionId FROM billingRecord AS r LEFT JOIN userInfo AS u ON r.unionId = u.unionId "
    sql = sql + "WHERE json_extract(r.res, '$.code') is null AND r.createdAt >="
    sql = sql + "'"
    sql = sql + startTime.format(DateTimeHelper.default_format)
    sql = sql + "'"
    sql = sql + " AND r.createdAt <="
    sql = sql + "'"
    sql = sql + endTime.format(DateTimeHelper.default_format)
    sql = sql + "'" + " ORDER BY r.id DESC "
    log.info("sql is [{}]", sql)

    val query = entityManager.createNativeQuery(sql)
    query.getResultList.stream().map(item => {
      convertItemToFailureRecord(item)
    }).toList
  }

  private def convertItemToFailureRecord(item: AnyRef) = {
    val arr = item.asInstanceOf[Array[AnyRef]]
    val orderNo = arr(0).toString
    val amount = arr(1).asInstanceOf[Int]
    val res = arr(2).toString
    val phone = arr(3).toString
    val unionId = arr(4).toString

    val record = new FailureRecord
    record.orderNo = orderNo
    record.amount = amount
    record.res = res
    record.phone = phone
    record.unionId = unionId

    record
  }

}
