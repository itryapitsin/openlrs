package org.openlrs

/**
 * Created by Iliya Tryapitsin on 20.05.15.
 */
case class PartialSeq[T](seq:    Seq[T],
                         isFull: Boolean)