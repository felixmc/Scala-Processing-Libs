package com.felixmilea.processing.models

import scala.collection.mutable.ArrayBuffer
import processing.core.PVector
import processing.core.PApplet
import scala.util.Random
import com.felixmilea.processing.core.ProcessingMath

class ParticleSystem extends ProcessingModel {
  private val particles: ArrayBuffer[Particle] = new ArrayBuffer[Particle]()

  var particleCount = 100
  var particleColor: Color = new RGBColor
  var onParticleCreate: (Particle => Unit) = p => {}
  var onParticleUpdate: (Particle => Unit) = p => {}

  var particleOrigin = new PVector
  var minParticleOriginOffset = new PVector
  var maxParticleOriginOffset = new PVector

  var minParticleLifetime = 60
  var maxParticleLifetime = 120

  var minParticleSize = 10f
  var maxParticleSize = 20f

  var minParticleVelocity = new PVector(-1, -1)
  var maxParticleVelocity = new PVector(1, 1)

  var particleAcceleration = new PVector

  private def addParticle() {
    val particle = new Particle() {
      position = particleOrigin.get
      position.add(new PVector(ProcessingMath.random(minParticleOriginOffset.x, maxParticleOriginOffset.x),
        ProcessingMath.random(minParticleOriginOffset.y, maxParticleOriginOffset.y)))
      lifetime = ProcessingMath.random(minParticleLifetime, maxParticleLifetime).toInt
      color = particleColor.copy
      size = ProcessingMath.random(minParticleSize, maxParticleSize)
      velocity = new PVector(ProcessingMath.random(minParticleVelocity.x, maxParticleVelocity.x),
        ProcessingMath.random(minParticleVelocity.y, maxParticleVelocity.y))
      acceleration = particleAcceleration.get
    }

    onParticleCreate(particle)
    particles += particle
  }

  override def update(dt: Float) {
    particles --= particles.filter(p => p.isDead)

    particles.foreach(p => {
      p.update(dt)
      onParticleUpdate(p)
    })

    var generationQuota: Int = Math.ceil(particleCount.asInstanceOf[Double] / minParticleLifetime).asInstanceOf[Int]
    var generatedParticles = 0

    while ((particles.length < particleCount && generatedParticles < generationQuota)) {
      addParticle
      generatedParticles += 1
    }
  }

  override def draw(canvas: PApplet) {
    particles.foreach(p => p.draw(canvas))
  }

}