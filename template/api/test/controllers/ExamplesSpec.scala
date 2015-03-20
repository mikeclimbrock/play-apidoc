package controllers

import play.api.test._
import play.api.test.Helpers._
import org.scalatestplus.play._

class ExamplesSpec extends PlaySpec with OneServerPerSuite {

  import scala.concurrent.ExecutionContext.Implicits.global

  implicit override lazy val port = 9010
  implicit override lazy val app: FakeApplication = FakeApplication()

  lazy val client = new com.gilt.xxx.v0.Client(s"http://localhost:$port")

  "POST /examples" in new WithServer {
    // TODO
    await(client.organizations.deleteByKey(org.key)) must be(Some(()))
  }

  "POST /examples validates key is valid" in new WithServer {
    intercept[ErrorsResponse] {
      sys.error("TODO")
    }.errors.map(_.message) must be(Seq(s"TODO"))
  }

}
