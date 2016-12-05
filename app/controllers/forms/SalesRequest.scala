package controllers.forms

import play.api.data.Forms._
import play.api.data._
import play.api.data.format.Formats._
import play.api.libs.json.{JsPath, Reads}
import play.api.libs.functional.syntax._


case class SalesRequest(dateFrom: Long,
                        dateTo: Long,
                        shopIds: Option[Seq[Int]],
                        productIds: Option[Seq[Int]],
                        priceFrom: Option[Double],
                        priceTo: Option[Double]
                       )

/**
  * Created by rumata on 05/12/2016.
  */
object SalesRequest {
  val userForm = Form(
    mapping(
      "dateFrom" -> longNumber,
      "dateTo" -> longNumber,
      "shopIds" -> optional(default(seq(number), Seq[Int]())),
      "productIds" -> optional(default(seq(number), Seq[Int]())),
      "price_from" -> optional(of[Double]),
      "price_to" -> optional(of[Double])
    )(SalesRequest.apply)(SalesRequest.unapply)
  )

//  implicit val format = Json.format[SalesRequest]
implicit val residentReads: Reads[SalesRequest] = (
  (JsPath \ "from").read[Long] and
  (JsPath \ "to").read[Long] and
  (JsPath \ "shop").readNullable[Seq[Int]] and
  (JsPath \ "products").readNullable[Seq[Int]] and
  (JsPath \ "priceFrom").readNullable[Double] and
  (JsPath \ "priceTo").readNullable[Double]
  )(SalesRequest.apply _)
}




