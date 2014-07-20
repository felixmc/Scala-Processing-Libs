package com.felixmilea.processing.models

class Interpolation(start: Float, end: Float, duration: Float = 120f, fn: (Float, Float, Float, Float) => Float = Interpolation.linear,
                    direction: Interpolation.Direction.Direction = Interpolation.Direction.Normal, repeat: Float = Float.PositiveInfinity) extends Updateable {
  private var currentTime = 0
  private var its = 0
  private var _value = start

  def value = _value

  override def update(dt: Float) = if (its < repeat) {
    val (val0, val1): (Float, Float) = direction match {
      case Interpolation.Direction.Normal           => (start, end)
      case Interpolation.Direction.Reverse          => (end, start)
      case Interpolation.Direction.Alternate        => if (its % 2 == 0) (start, end) else (end, start)
      case Interpolation.Direction.AlternateReverse => if (its % 2 == 0) (end, start) else (start, end)
    }

    val mu = currentTime / duration
    _value = fn(currentTime, val0, val1 - val0, duration)

    if (currentTime >= duration) {
      its = its + 1
      currentTime = 0
    } else {
      currentTime = currentTime + 1
    }

  }
}

object Interpolation {

  object Direction extends Enumeration {
    type Direction = Value
    val Normal, Reverse, Alternate, AlternateReverse = Value
  }

  def linear(t: Float, b: Float, c: Float, d: Float): Float = {
    return (c * (t / d)) + b
  }

  def easeInQuad(t: Float, b: Float, c: Float, d: Float): Float = {
    val mu = t / d
    return (c * mu * mu) + b
  }

  def easeOutQuad(t: Float, b: Float, c: Float, d: Float): Float = {
    val mu = t / d
    return (-c * mu * (mu - 2)) + b
  }

  def easeInOutQuad(t: Float, b: Float, c: Float, d: Float): Float = {
    var mu = t / (d / 2)
    if (mu < 1) return ((c / 2) * mu * mu) + b
    mu = mu - 1
    return ((-c / 2) * ((mu * (mu - 2)) - 1)) + b
  }

  def easeInCubic(t: Float, b: Float, c: Float, d: Float): Float = {
    val mu = t / d
    return (c * mu * mu * mu) + b
  }

  def easeOutCubic(t: Float, b: Float, c: Float, d: Float): Float = {
    val mu = (t / d) - 1
    return (c * ((mu * mu * mu) + 1)) + b
  }

  def easeInOutCubic(t: Float, b: Float, c: Float, d: Float): Float = {
    var mu = t / (d / 2)
    if (mu < 1) return ((c / 2) * (mu * mu * mu)) + b
    mu = mu - 2
    return ((c / 2) * ((mu * mu * mu) + 2)) + b
  }

  def easeInQuart(t: Float, b: Float, c: Float, d: Float): Float = {
    val mu = t / d
    return (c * mu * mu * mu * mu) + b
  }

  def easeOutQuart(t: Float, b: Float, c: Float, d: Float): Float = {
    val mu = (t / d) - 1
    return (-c * ((mu * mu * mu * mu) - 1)) + b
  }

  def easeInOutQuart(t: Float, b: Float, c: Float, d: Float): Float = {
    var mu = t / (d / 2)
    if (mu < 1) return ((c / 2) * (mu * mu * mu * mu)) + b
    mu = mu - 2
    return ((c / -2) * ((mu * mu * mu * mu) - 2)) + b
  }

  def easeInQuint(t: Float, b: Float, c: Float, d: Float): Float = {
    val mu = t / d
    return (c * mu * mu * mu * mu * mu) + b
  }

  def easeOutQuint(t: Float, b: Float, c: Float, d: Float): Float = {
    val mu = (t / d) - 1
    return (c * ((mu * mu * mu * mu * mu) + 1)) + b
  }

  def easeInOutQuint(t: Float, b: Float, c: Float, d: Float): Float = {
    var mu = t / (d / 2)
    if (mu < 1) return ((c / 2) * (mu * mu * mu * mu * mu)) + b
    mu = mu - 2
    return ((c / 2) * ((mu * mu * mu * mu * mu) + 2)) + b
  }

  def easeInSine(t: Float, b: Float, c: Float, d: Float): Float = {
    return (-c * Math.cos((t / d) * (Math.PI / 2)).asInstanceOf[Float]) + c + b
  }

  def easeOutSine(t: Float, b: Float, c: Float, d: Float): Float = {
    return (c * Math.sin((t / d) * (Math.PI / 2)).asInstanceOf[Float]) + b
  }

  def easeInOutSine(t: Float, b: Float, c: Float, d: Float): Float = {
    return ((-c / 2) * (Math.cos(Math.PI * (t / d)) - 1).asInstanceOf[Float]) + b
  }

  def easeInExpo(t: Float, b: Float, c: Float, d: Float): Float = {
    return (c * Math.pow(2, 10 * ((t / d) - 1)).asInstanceOf[Float]) + b
  }

  def easeOutExpo(t: Float, b: Float, c: Float, d: Float): Float = {
    return (c * (-Math.pow(2, -10 * (t / d)).asInstanceOf[Float] + 1)) + b
  }

  def easeInOutExpo(t: Float, b: Float, c: Float, d: Float): Float = {
    var mu = t / (d / 2)
    if (mu < 1) return ((c / 2) * Math.pow(2, 10 * (mu - 1)).asInstanceOf[Float]) + b
    mu = mu - 1
    return ((c / 2) * (-Math.pow(2, -10 * mu).asInstanceOf[Float] + 2)) + b
  }

  def easeInCirc(t: Float, b: Float, c: Float, d: Float): Float = {
    val mu = t / d
    return (-c * (Math.sqrt(1 - (mu * mu)) - 1).asInstanceOf[Float]) + b
  }

  def easeOutCirc(t: Float, b: Float, c: Float, d: Float): Float = {
    val mu = (t / d) - 1
    return (c * Math.sqrt(1 - (mu * mu)).asInstanceOf[Float]) + b
  }

  def easeInOutCirc(t: Float, b: Float, c: Float, d: Float): Float = {
    var mu = t / (d / 2)
    if (mu < 1) return ((c / -2) * (Math.sqrt(1 - (mu * mu)).asInstanceOf[Float] - 1)) + b
    mu = mu - 2
    return ((c / 2) * (Math.sqrt(1 - (mu * mu)).asInstanceOf[Float] + 1)) + b
  }

  def easeInBack(t: Float, b: Float, c: Float, d: Float): Float = {
    val mu = t / d
    val s = 1.70158f
    return (c * mu * mu * (((s + 1) * mu) - s)) + b
  }

  def easeOutBack(t: Float, b: Float, c: Float, d: Float): Float = {
    val mu = (t / d) - 1
    val s = 1.70158f
    return (c * (mu * mu * (((s + 1) * mu) + s) + 1)) + b
  }

  def easeInOutBack(t: Float, b: Float, c: Float, d: Float): Float = {
    var mu = t / (d / 2)
    val s = 1.70158f * 1.525f
    if (mu < 1) return ((c / 2) * (mu * mu * (((s + 1) * mu) - s))) + b
    mu = mu - 2
    return ((c / 2) * (mu * mu * (((s + 1) * mu) + s) + 2)) + b
  }

  def easeInBounce(t: Float, b: Float, c: Float, d: Float): Float = {
    return c - easeOutBounce(d - t, 0, c, d) + b
  }

  def easeOutBounce(t: Float, b: Float, c: Float, d: Float): Float = {
    var mu = t / d
    if (mu < (1 / 2.75)) {
      return c * (7.5625f * mu * mu) + b
    } else if (t < (2 / 2.75)) {
      mu = mu - (1.5f / 2.75f)
      return c * (7.5625f * mu * mu + .75f) + b
    } else if (t < (2.5 / 2.75)) {
      mu = mu - (2.25f / 2.75f)
      return c * (7.5625f * mu * mu + .9375f) + b
    } else {
      mu = mu - (2.625f / 2.75f)
      return c * (7.5625f * mu * mu + .984375f) + b
    }
  }

  def easeInOutBounce(t: Float, b: Float, c: Float, d: Float): Float = {
    if (t < d / 2) return easeInBounce(t * 2, 0, c, d) / 2 + b
    return (easeOutBounce(t * 2 - d, 0, c, d) / 2) + (c / 2) + b
  }
}