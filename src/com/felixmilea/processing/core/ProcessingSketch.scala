package com.felixmilea.processing.core

import java.awt.Dimension
import processing.core._

abstract class ProcessingSketch extends PApplet {
  import com.felixmilea.processing.core.MathUtils

  val config = new SketchConfig

  def main( args: Array[String] ) {
    new SketchFrame( this )
  }

  override def setup() {
    size( config.dimensions.width, config.dimensions.height )
    background( 0 )
    frameRate( config.frameRate )
    if ( config.smooth )
      smooth()
  }

  def draw()
}