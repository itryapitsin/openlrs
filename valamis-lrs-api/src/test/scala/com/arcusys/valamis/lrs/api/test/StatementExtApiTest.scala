package com.arcusys.valamis.lrs.api.test

import java.net.URI

import com.arcusys.json.JsonHelper
import com.arcusys.valamis.lrs._
import com.arcusys.valamis.lrs.api.valamis.{StatementExtApi, ScaleApi}
import org.openlrs.serializer.AgentSerializer
import org.openlrs.xapi.Agent
import org.scalatest.FeatureSpec

import scala.util._

/**
 * Created by Iliya Tryapitsin on 16.06.15.
 */
class StatementExtApiTest extends FeatureSpec with BaseFeatureTests {
  ignore("Get statement count") {
    val statementExt = new StatementExtApi
    val agent = Agent (mBox = "mailto:tincanpython@tincanapi.com" ?)

    val verbs  = Seq(
      new URI ("http://adlnet.gov/expapi/verbs/experienced")
    )

    val scale = statementExt.getByParamsCount(agent, verbs) match {
      case Success(v) => v
      case Failure(e) => fail(e)
    }
  }
}
