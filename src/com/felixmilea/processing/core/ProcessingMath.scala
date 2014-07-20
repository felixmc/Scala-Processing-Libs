package com.felixmilea.processing.core

object ProcessingMath {

  def random(min: Float, max: Float): Float = {
    return ((Math.random() * (max - min)) + min).toFloat;
  }

}