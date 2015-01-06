package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._

object Application extends Controller {

  val basePath = Play.current.configuration.getString("swagger.api.basepath").getOrElse("http://localhost:9000")

  def index = Action {
    Ok(views.html.index(basePath + "/api-docs"))
  }
}