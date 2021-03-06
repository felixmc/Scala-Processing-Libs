package com.felixmilea.processing.examples

import com.felixmilea.processing.core.ProcessingSketch
import com.felixmilea.processing.models.ParticleSystem
import com.felixmilea.processing.models.HSBColor
import processing.core.PVector

object ParticleSketch extends ProcessingSketch {
  var ps: ParticleSystem = null

  config.fullScreen = false

  override def setup() {
    super.setup

    ps = new ParticleSystem() {
      particleCount = 1000
      minParticleVelocity = new PVector(-1f, -1f)
      maxParticleVelocity = new PVector(1f, 1f)
      minParticleOriginOffset = new PVector(-30, -30)
      maxParticleOriginOffset = new PVector(30, 30)
      particleColor = new HSBColor(hue = 173, alpha = 180)
      minParticleLifetime = 100
      maxParticleLifetime = 200
      minParticleSize = 2
      maxParticleSize = 7
      onParticleCreate = p => {
        // offset hue of newly created particles
        p.color.asInstanceOf[HSBColor].transform(random(-15, 15))
      }
      onParticleUpdate = p => {
        // on mouse pressed..
        if (ParticleSketch.this.mousePressed) {
          // decrease particle size..
          p.size = p.size - .05f

          // and decrease particle opacity
          p.color.asInstanceOf[HSBColor].transform(alpha = -1.5f)

          // remove particle if size or opacity are less than 0
          if (p.color.a <= 0 || p.size <= 0)
            p.lifetime = 0
        }
      }
    }
  }

  override def draw(dt: Float) {
    background(0)

    // transform hue of base particle color
    ps.particleColor.asInstanceOf[HSBColor].transform(1f)

    // update origin to current mouse position
    ps.particleOrigin = new PVector(mouseX, mouseY)

    println(dt)

    // update/draw particle system
    ps.update(dt)
    ps.draw(this)
  }

  // on key press, if key is the space key, add gravity to particle system
  override def keyPressed() {
    if (keyCode == ' ')
      ps.particleAcceleration = new PVector(0, .15f)
  }

  // on key release, if key is the space key, remove gravity from particle system
  override def keyReleased() {
    if (keyCode == ' ')
      ps.particleAcceleration = new PVector
  }
}