package com.felixmilea.processing.examples

import com.felixmilea.processing.core.ProcessingSketch
import processing.core.PImage
import com.felixmilea.processing.core.Screen
import com.felixmilea.processing.models.ParticleSystem
import processing.core.PVector
import com.felixmilea.processing.models.HSBColor
import com.felixmilea.processing.models.Interpolation

object ScreenshotSketch extends ProcessingSketch {

  var screen: PImage = new PImage(Screen.getScreenCapture)
  val hueEasing = new Interpolation(start = 180, end = 270, duration = 320, fn = Interpolation.easeInOutSine, direction = Interpolation.Direction.Alternate)
  var ps: ParticleSystem = null

  override def setup() {
    super.setup

    ps = new ParticleSystem() {
      particleCount = 1000
      minParticleVelocity = new PVector(-2f, -2f)
      maxParticleVelocity = new PVector(2f, 2f)
      minParticleOriginOffset = new PVector(-50, -50)
      maxParticleOriginOffset = new PVector(50, 50)
      particleColor = new HSBColor(hue = hueEasing.value, alpha = 150)
      minParticleLifetime = 100
      maxParticleLifetime = 200
      minParticleSize = 8
      maxParticleSize = 20
      onParticleCreate = p => {
        // offset hue of newly created particles
        p.color.asInstanceOf[HSBColor].transform(random(-20, 20))
      }
      onParticleUpdate = p => {
        // on mouse pressed..
        if (ScreenshotSketch.this.mousePressed) {
          // decrease particle size..
          p.size = p.size - .1f

          // remove particle if size or opacity are less than 0
          if (p.size <= 0)
            p.lifetime = 0
        }
      }
    }
  }

  override def draw(dt: Float) {
    image(screen, 0, 0)

    ps.particleColor = new HSBColor(hue = hueEasing.value, alpha = 100)
    ps.particleOrigin = new PVector(mouseX, mouseY)

    hueEasing.update(dt)
    ps.update(dt)
    ps.draw(this)
  }

}