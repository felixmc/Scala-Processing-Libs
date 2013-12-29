package com.felixmilea.processing.models

import processing.core.PApplet

abstract class ProcessingModel( _canvas: PApplet ) {
  val canvas: PApplet = _canvas
  def update()
  def draw()
}