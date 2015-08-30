package controllers

import %%PACKAGE_NAME%%.models.Healthcheck
import %%PACKAGE_NAME%%.models.json._

import play.api._
import play.api.mvc._

object HealthchecksController extends Controller {

  def index() = Action { implicit request =>
    Ok(views.html.healthchecks.index("healthy"))
  }

}
