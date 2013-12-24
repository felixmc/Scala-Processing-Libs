package com.felixmilea.processing.models

import processing.core.PApplet

abstract class ProcessingModel( _canvas: PApplet ) {
  import com.felixmilea.processing.core.MathUtils
  val canvas: PApplet = _canvas
  def update()
  def draw()
}