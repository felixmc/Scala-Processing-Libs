package com.felixmilea.processing.core

import java.awt.Toolkit
import java.awt.Dimension
import java.awt.image.BufferedImage
import java.awt.Robot
import java.awt.Rectangle

object Screen {

  def getScreenSize(): Dimension = Toolkit.getDefaultToolkit.getScreenSize

  def getScreenCapture(): BufferedImage = new Robot().createScreenCapture( new Rectangle( getScreenSize ) )

}