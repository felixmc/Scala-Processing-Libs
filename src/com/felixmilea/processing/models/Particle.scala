package com.felixmilea.processing.models

import processing.core.PVector
import processing.core.PApplet

class Particle() extends ProcessingModel {
  var position, velocity, acceleration = (new PVector())
  var size = 10f
  var lifetime = 100
  var color: Color = new RGBColor

  override def update(dt: Float) {
    position.add(velocity);
    velocity.add(acceleration);

    lifetime = lifetime - 1
  }

  override def draw(canvas: PApplet) {
    canvas.noStroke()
    canvas.fill(color)

    canvas.ellipse(position.x, position.y, size, size)
    //canvas.rect( position.x, position.y, size, size )
  }

  def isDead(): Boolean = {
    return lifetime <= 0
  }
}