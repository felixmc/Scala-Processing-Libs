package com.felixmilea.processing.models

import scala.collection.mutable.ArrayBuffer
import scala.collection.TraversableOnce

object UpdatableManager {
  private val queue = new ArrayBuffer[Updateable]

  def +=( sub: Updateable ) = queue += sub
  def ++=( subs: TraversableOnce[Updateable] ) = queue ++= subs
  def -=( sub: Updateable ) = queue -= sub

  def update() = queue.foreach( u => u.update )
}