package com.felixmilea.processing.models

abstract class Color {
  var a = 255f

  def callibrate() {
    a = Math.max( a, 0 ) % 256f
  }

  def toInt(): Int
  def stringify(): String
  def copy(): Color
  override def toString() = stringify()
}

object Color {
  implicit def colorToInt( c: Color ): Int = c.toInt
}