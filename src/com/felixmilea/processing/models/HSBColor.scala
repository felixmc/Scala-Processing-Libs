package com.felixmilea.processing.models

class HSBColor( hue: Float = 360, saturation: Float = 100, brightness: Float = 100, alpha: Float = 255 ) extends Color {
  var h: Float = hue
  var s: Float = saturation
  var b: Float = brightness
  a = alpha

  def toInt(): Int = {
    return HSBColor.HSBToRGB( this )
  }

  override def callibrate() {
    super.callibrate
    h = h % 360
    s = s % 101
    b = b % 101

    while ( h < 0 ) {
      h = h + 360
    }
  }

  def copy(): Color = {
    return new HSBColor( h, s, b, a )
  }

  def transform( hue: Float = 0, sat: Float = 0, bri: Float = 0, alpha: Float = 0 ) {
    h = h + hue
    s = s + sat
    b = b + bri
    a = a + alpha
    callibrate()
  }

  def stringify(): String = s"hsba($h,$s,$b,$a)"
}

object HSBColor {
  implicit def HSBToRGB( color: HSBColor ): RGBColor = {
    color.callibrate()

    val ( sP, brP ) = ( color.s / 100f, color.b / 100f )
    val c = brP * sP
    val hP = color.h / 60f
    val x = c * ( 1 - Math.abs( ( hP % 2 ) - 1 ) )
    val m = brP - c

    val ( rP, gP, bP ): ( Float, Float, Float ) = color.h.toInt match {
      case h if 0 until 60 contains h => ( c, x, 0 )
      case h if 60 until 120 contains h => ( x, c, 0 )
      case h if 120 until 180 contains h => ( 0, c, x )
      case h if 180 until 240 contains h => ( 0, x, c )
      case h if 240 until 300 contains h => ( x, 0, c )
      case h if 300 until 360 contains h => ( c, 0, x )
    }

    return new RGBColor {
      r = ( rP + m ) * 255
      g = ( gP + m ) * 255
      b = ( bP + m ) * 255
      a = color.a
    }
  }
}