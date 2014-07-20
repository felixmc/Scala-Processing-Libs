package com.felixmilea.processing.models

import scala.collection.mutable.ArrayBuffer
import scala.collection.TraversableOnce

object UpdatableManager extends Updateable {
  private val queue = new ArrayBuffer[Updateable]

  def +=(sub: Updateable) = queue += sub
  def ++=(subs: TraversableOnce[Updateable]) = queue ++= subs
  def -=(sub: Updateable) = queue -= sub

  override def update(dt: Float) = queue.foreach(u => u.update(dt))
}