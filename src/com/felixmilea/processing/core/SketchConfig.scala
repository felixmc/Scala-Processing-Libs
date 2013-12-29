package com.felixmilea.processing.core

import java.awt.Dimension

/** Configures the behavior of an instance of ProcessingSketch */
class SketchConfig {
  var windowTitle = "Processing Sketch"
  var fullScreen = true
  var resizeable = false
  var smooth = true
  var frameRate = 60
  var dimensions = new Dimension( 800, 800 )
}