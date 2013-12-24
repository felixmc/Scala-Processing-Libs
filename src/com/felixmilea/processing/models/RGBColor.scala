package com.felixmilea.processing.models

class RGBColor( red: Float = 255, green: Float = 255, blue: Float = 255, alpha: Float = 255 ) extends Color {
  var r: Float = red
  var g: Float = green
  var b: Float = blue
  a = alpha

  def toInt(): Int = {
    callibrate()
    val ( rP, gP, bP, aP ) = ( Math.round( r ).toInt, Math.round( g ).toInt, Math.round( b ).toInt, Math.round( a ).toInt )
    return ( aP << 24 ) | ( rP << 16 ) | ( gP << 8 ) | bP
  }

  override def callibrate() {
    super.callibrate
    r = r % 256
    g = g % 256
    b = b % 256
  }

  def copy(): Color = {
    return new RGBColor( r, g, b, a )
  }

  def stringify(): String = s"rgba($r,$g,$b,$a)"
}

object RGBColor {
  implicit def RGBToHSB( color: RGBColor ): HSBColor = {
    color.callibrate()

    val ( rP, gP, bP ) = ( color.r / 255f, color.g / 255f, color.b / 255f )
    val cMax = Math.max( Math.max( rP, gP ), bP )
    val cMin = Math.min( Math.min( rP, gP ), bP )
    val delta = cMax - cMin

    val compareFactor = 0.0001f;

    return new HSBColor {
      h = 60 * ( cMax match {
        case c if Math.abs( c - rP ) < compareFactor => ( ( gP - bP ) / delta ) % 6
        case c if Math.abs( c - gP ) < compareFactor => ( ( bP - rP ) / delta ) + 2
        case c if Math.abs( c - bP ) < compareFactor => ( ( rP - gP ) / delta ) + 4
      } )
      b = ( cMax + cMin ) / 2f
      s = delta / ( 1 - Math.abs( ( 2f * b ) - 1 ) ) * 100f
      b = b * 200f
      a = color.a
    }
  }
}