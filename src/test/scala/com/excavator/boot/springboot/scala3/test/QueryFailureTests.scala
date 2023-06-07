package com.excavator.boot.springboot.scala3.test

import com.excavator.boot.springboot.scala3.Scala3Application
import com.excavator.boot.springboot.scala3.entity.FailureRecord
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.Resource
import net.bytebuddy.asm.Advice.Exit
import org.junit.jupiter.api.Assertions.{assertNotNull, assertTrue}
import org.junit.jupiter.api.{DisplayName, Test}
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.{HttpEntity, HttpHeaders, HttpMethod, MediaType}
import org.springframework.test.context.junit.jupiter.SpringExtension

import java.util
import java.util.{List => JList}
import java.util.{ArrayList => JArrayList}

@SpringBootTest(classes = Array(classOf[Scala3Application]),webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(Array(classOf[SpringExtension]))
class QueryFailureTests {
  @Resource
  val testRestTemplate: TestRestTemplate = null
  @LocalServerPort
  var port: Int = 0

  @Test
  @DisplayName("test query failure record")
  def testQueryFailureRecord():Unit = {

    val url = buildUrl("/query/failure/v1/record/"+"2023-05-05")
    val headers = new HttpHeaders()
    headers.set("me", "cmonkey")
    headers.set("content-type", "application/json")
    headers.setAccept(util.Arrays.asList(MediaType.APPLICATION_JSON))
    val httpEntity = new HttpEntity[String]("", headers)

    val response = testRestTemplate.exchange(url, HttpMethod.GET, httpEntity, classOf[JArrayList[FailureRecord])
    val is2xxSuccessful = response.getStatusCode.is2xxSuccessful()
    assertTrue(is2xxSuccessful)
    val body = response.getBody
    //body elem is map type , why elem type is not FailureRecord?
    assertNotNull(body)
  }

  def buildUrl(url:String) = {
    s"http://localhost:$port" + url
  }

}
