package com.felixmilea.processing.models

import processing.core.PApplet

trait Drawable {
  def draw(canvas: PApplet)
}