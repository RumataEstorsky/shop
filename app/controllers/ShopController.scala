package controllers

import javax.inject._

import controllers.forms._
import models.Tables._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.json.Json
import play.api.libs.json.Json.toJson
import play.api.libs.ws.WSClient
import play.api.mvc._
import slick.driver.JdbcProfile
import slick.driver.PostgresDriver.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class ShopController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends Controller with HasDatabaseConfigProvider[JdbcProfile] {
  val InvalidJsonFuture = Future.successful(BadRequest(Json.obj("error" -> "Invalid JSON")))

  def getSales = Action.async(parse.json) { implicit request =>
    val l = slick.lifted.LiteralColumn(true)


    request.body.validate[SalesRequest].map { form =>

      val q = Sales.filter(s =>
        (s.saleDate >= form.dateFrom && s.saleDate <= form.dateTo)
          && form.shopIds.map(shops => s.shopId inSet shops).getOrElse(l)
          && form.productIds.map(products => s.productId inSet products).getOrElse(l)
          && form.priceFrom.flatMap(priceFrom => form.priceTo.map(priceTo =>
          s.price >= priceFrom && s.price <= priceTo)).getOrElse(l)
      )

      db.run(q.result) map { result =>
        Ok(Json.obj("data" -> toJson(result)))
      }

    }.getOrElse(InvalidJsonFuture)

  }


}
