package com.felixmilea.processing.core

import java.awt.Dimension
import processing.core._

/** A Processing sketch */
abstract class ProcessingSketch extends PApplet {
  val config = new SketchConfig

  /**
   * Runs the sketch by creating a SketchFrame (a specialized JFrame) and rendering the sketch in it based on the sketch's config property.
   *
   * Override this method to change how the sketch is ran.
   */
  def main(args: Array[String]) {
    new SketchFrame(this)
  }

  /**
   * Called when sketch starts to setup environment properties. Corresponds to the setup method of a regular Processing sketch.
   *
   * If overridden, call super.setup() to ensure proper sketch functionality.
   * @see <a href="http://processing.org/reference/setup_.html">Processing docs</a>
   */
  override def setup() {
    size(config.dimensions.width, config.dimensions.height)
    background(0)
    frameRate(config.frameRate)
    if (config.smooth)
      smooth()
  }

  private var lastTime: Float = 0

  final override def draw() {
    val now: Float = System.nanoTime()
    draw(if (lastTime == 0) lastTime else now - lastTime)
    lastTime = now
  }

  /**
   * Corresponds to the draw method of a regular Processing sketch.
   *
   * @param dt is a float representing the delta time since draw method was last called in seconds
   *
   * @see <a href="http://processing.org/reference/draw_.html">Processing docs</a>
   */
  def draw(dt: Float)
}