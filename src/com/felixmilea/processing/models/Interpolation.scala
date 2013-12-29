package com.felixmilea.processing.models

class Interpolation( start: Float, end: Float, speed: Float = .01f, fn: ( Float, Float, Float ) => Float = Interpolation.linear,
  repeat: Float = 1, direction: Interpolation.Direction.Direction = Interpolation.Direction.Normal ) extends Updateable {

  private var its = 0
  private var mu = 0f
  var value = start

  def update() = if ( its < repeat ) {

    val ( val0, val1 ): ( Float, Float ) = direction match {
      case Interpolation.Direction.Normal => ( start, end )
      case Interpolation.Direction.Reverse => ( end, start )
      case Interpolation.Direction.Alternate => if ( its % 2 == 0 ) ( start, end ) else ( end, start )
      case Interpolation.Direction.AlternateReverse => if ( its % 2 == 0 ) ( end, start ) else ( start, end )
    }

    mu = Math.max( 0, Math.min( 1, mu + speed ) )
    value = fn( val0, val1, mu )

    if ( mu == 1 ) {
      its = its + 1
      mu = 0
    }
  }
}

object Interpolation {
  def linear( start: Float, end: Float, mu: Float ): Float = {
    return start + ( ( end - start ) * mu )
  }

  object Direction extends Enumeration {
    type Direction = Value
    val Normal, Reverse, Alternate, AlternateReverse = Value
  }
}