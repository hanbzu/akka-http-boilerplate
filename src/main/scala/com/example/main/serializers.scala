package com.example

import org.joda.time.{LocalTime, LocalDate}
import org.joda.time.format.DateTimeFormat
import org.json4s.JsonAST.JString
import org.json4s._
import org.json4s.reflect.TypeInfo


object exampleCustomSerializers {
  def all = List(LocalDateSerializer, LocalTimeSerializer)
}


// I followed this example to build a serializer:
// https://gist.github.com/casualjim/5130756

object LocalDateSerializer extends Serializer[LocalDate] {
  private val LocalDateClass = classOf[LocalDate]

  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), LocalDate] = {
    case (TypeInfo(LocalDateClass, _), json) => json match {
      case x => throw new MappingException("Can't convert " + x + " to MyClass")
    }
  }

  def serialize(implicit formats: Formats): PartialFunction[Any, JValue] = {
    case x: LocalDate =>
      val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")
      new JString(formatter print x)
  }
}

object LocalTimeSerializer extends Serializer[LocalTime] {
  private val LocalTimeClass = classOf[LocalTime]

  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), LocalTime] = {
    case (TypeInfo(LocalTimeClass, _), json) => json match {
      case x => throw new MappingException("Can't convert " + x + " to MyClass")
    }
  }

  def serialize(implicit formats: Formats): PartialFunction[Any, JValue] = {
    case x: LocalTime =>
      val formatter = DateTimeFormat.forPattern("HH:mm")
      new JString(formatter print x)
  }
}
