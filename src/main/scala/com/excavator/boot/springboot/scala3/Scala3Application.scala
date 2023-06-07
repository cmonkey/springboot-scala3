package com.excavator.boot.springboot.scala3

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Scala3Application{

}

object Scala3Application{
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Scala3Application], args:_*)
  }
}