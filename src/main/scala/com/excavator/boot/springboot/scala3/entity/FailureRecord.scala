package com.excavator.boot.springboot.scala3.entity

import scala.beans.BeanProperty

class FailureRecord {
  @BeanProperty
  var orderNo:String = null
  @BeanProperty
  var amount: Double = 0
  @BeanProperty
  var res:String = null
  @BeanProperty
  var phone:String = null
  @BeanProperty
  var unionId:String = null
}
