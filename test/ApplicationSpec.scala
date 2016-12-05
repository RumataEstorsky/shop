import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.mvc.{Action, EssentialAction, Results}
import play.api.test.Helpers._
import play.api.test._
import play.test.WithApplication

class ApplicationSpec extends PlaySpec with OneAppPerTest with Results {

  "An essential action" should {
    "can parse a JSON body" in new WithApplication() {
      val action: EssentialAction = Action { request =>
        val value = (request.body.asJson.get \ "data")
        println(value)
        Ok(value)
      }

      val data = Json.obj(
        "from" -> 0,
        "to" -> System.currentTimeMillis()
      )

      val request = FakeRequest(GET, "/test/get-sales-by-period").withJsonBody(data)
      val result = call(action, request)

      status(result) mustEqual OK
      contentAsString(result) mustEqual "value"

    }
  }


}
