package com.felixmilea.processing.models

import processing.core.PApplet

abstract class ProcessingModel( _canvas: PApplet ) extends Updateable with Drawable {
  val canvas: PApplet = _canvas
}