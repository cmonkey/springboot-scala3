package com.excavator.boot.springboot.scala3.controller

import com.excavator.boot.springboot.scala3.service.QueryFailureService
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}
import reactor.core.publisher.Flux

import java.time.LocalDate

@RestController
@RequestMapping(value = Array("/query/failure/v1"))
class QueryFailureController(val queryFailureService: QueryFailureService) {

  @GetMapping(value = Array("/record/{time}"))
  def queryFailureRecord(@PathVariable("time") time:String) = {
    val localDate = LocalDate.parse(time)
    val r = queryFailureService.queryFailure(localDate)
    Flux.just(r.toArray():_*)
  }

}
