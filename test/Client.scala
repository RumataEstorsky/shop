package controllers

import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.libs.ws.ahc.AhcCurlRequestLogger

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by rumata on 05/12/2016.
  */
class Client(ws: WSClient) {

  val baseUrl = "http://localhost:9000"

  def getSalesByPeriod = {
    val data = Json.obj(
      "from" -> 0,
      "to" -> System.currentTimeMillis()
    )
    val response = ws.url(baseUrl + "/test/get-sales-by-period")
      .withRequestFilter(AhcCurlRequestLogger())
      .post(data)

    Await.result(response, 20 seconds)

  }


  def getSalesByShop = {
    val data = Json.obj(
      "from" -> 0,
      "to" -> System.currentTimeMillis(),
      "shop" -> List(1, 2, 3)
    )
    val response = ws.url(baseUrl + "/test/get-sales-by-shop")
      .withRequestFilter(AhcCurlRequestLogger())
      .post(data)

    Await.result(response, 20 seconds)

  }

  def getSalesByShopProduct = {
    val data = Json.obj(
      "from" -> 0,
      "to" -> System.currentTimeMillis(),
      "shop" -> List(1, 2, 3),
      "products" -> List(1, 2)
    )
    val response = ws.url(baseUrl + "/test/get-sales-by-shop-product")
      .withRequestFilter(AhcCurlRequestLogger())
      .post(data)

    Await.result(response, 20 seconds)

  }

  def getSalesByShopPrice = {
    val data = Json.obj(
      "from" -> 0,
      "to" -> System.currentTimeMillis(),
      "shop" -> List(1, 2, 3),
      "price_from" -> 0.1,
      "price_to" -> 99.9
    )
    val response = ws.url(baseUrl + "/test/get-sales-by-shop-price")
      .withRequestFilter(AhcCurlRequestLogger())
      .post(data)

    Await.result(response, 20 seconds)

  }

}
