package controllers

import %%PACKAGE_NAME%%.models.Healthcheck
import %%PACKAGE_NAME%%.models.json._

import play.api._
import play.api.mvc._
import play.api.libs.json._

object Healthchecks extends Controller {

  private val HealthyJson = Json.toJson(Healthcheck(status = "healthy"))

  def getInternalAndHealthcheck() = Action { request =>
    Ok(HealthyJson)
  }

}
