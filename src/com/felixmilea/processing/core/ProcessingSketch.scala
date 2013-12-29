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
  def main( args: Array[String] ) {
    new SketchFrame( this )
  }

  /**
   * Called when sketch starts to setup environment properties. Corresponds to the setup method of a regular Processing sketch.
   *
   * If overridden, call super.setup() to ensure proper sketch functionality.
   * @see <a href="http://processing.org/reference/setup_.html">Processing docs</a>
   */
  override def setup() {
    size( config.dimensions.width, config.dimensions.height )
    background( 0 )
    frameRate( config.frameRate )
    if ( config.smooth )
      smooth()
  }

  /**
   * Corresponds to the draw method of a regular Processing sketch.
   *
   * @see <a href="http://processing.org/reference/draw_.html">Processing docs</a>
   */
  def draw()
}