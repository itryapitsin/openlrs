package org.openlrs.serializer

import org.json4s.JsonAST.JValue
import org.json4s.jackson.JsonMethods._
import org.json4s.{CustomSerializer, Extraction}
import org.openlrs.xapi.Constants.Tincan
import org.openlrs.xapi.Constants.Tincan.Field._
import org.openlrs.xapi.{Constants, Result, Score}
import org.openlrs.validator.ResultValidator

/**
 * Created by Iliya Tryapitsin on 29/12/14.
 */
object ResultSerializer extends CustomSerializer[Result](format => ( {
  case jValue: JValue =>
    implicit val jsonFormats = resultSerializers

    ResultValidator checkNotNull jValue

    Result(
      jValue.\(Tincan.Score)     .extractOpt[Score],
      jValue.\(success)   .extractOpt[Boolean],
      jValue.\(completion).extractOpt[Boolean],
      jValue.\(response)  .extractOpt[String].trimAll(),
      jValue.\(duration)  .extractOpt[String],
      jValue.\(extensions).extractOpt[Map[String, String]]
    )
}, {
  case r: Result => render(Extraction.decompose(r)(resultSerializers))
}))
