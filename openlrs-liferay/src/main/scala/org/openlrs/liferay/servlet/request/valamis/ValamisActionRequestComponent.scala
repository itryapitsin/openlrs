package org.openlrs.liferay.servlet.request.valamis

import org.openlrs.liferay.servlet.request.BaseLrsRequest

/**
 * Created by Iliya Tryapitsin on 17.06.15.
 */
trait ValamisActionRequestComponent {
  r: BaseLrsRequest =>

  def action = require(Action)

  val Action = "action"
}
