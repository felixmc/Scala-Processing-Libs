package com.felixmilea.processing.core

import javax.swing.JFrame
import java.awt.Dimension
import java.awt.Frame
import java.awt.Toolkit
import java.awt.BorderLayout

/** A specialized JFrame that displays a ProcesingSketch */
class SketchFrame( sketch: ProcessingSketch ) extends JFrame {
  setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE )
  setLayout( new BorderLayout() )
  setMinimumSize( new Dimension( 200, 200 ) )

  setTitle( sketch.config.windowTitle )
  setResizable( sketch.config.resizeable )
  setUndecorated( sketch.config.fullScreen )

  if ( sketch.config.fullScreen ) {
    sketch.config.dimensions = Toolkit.getDefaultToolkit.getScreenSize
    setExtendedState( Frame.MAXIMIZED_BOTH )
  } else {
    setSize( sketch.config.dimensions.width, sketch.config.dimensions.height )
    setLocationRelativeTo( null )
  }

  if ( sketch.config.resizeable && !sketch.config.fullScreen )
    sketch.config.dimensions = Toolkit.getDefaultToolkit.getScreenSize

  getContentPane.add( sketch, BorderLayout.CENTER )

  sketch.init()
  setVisible( true )
}