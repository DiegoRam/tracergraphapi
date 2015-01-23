package sandbox

import java.util.{Date, UUID}

import com.tinkerpop.blueprints.Direction
import com.ansvia.graph.BlueprintsWrapper._
import scala.collection.JavaConversions._
import org.diegoram.{Tweet, User}
import org.specs2.mutable.Specification
import com.tinkerpop.blueprints.impls.neo4j2.Neo4j2Graph


class NeoPerformanceTest extends Specification{
  implicit val graph  = new Neo4j2Graph("/tmp/neo4j")

  val diegoram = User(UUID.randomUUID().toString, "DiegoTest").save()

  "neo4j db test" should {
    sequential
    "create Database" in {
      graph should not beNull
    }


    "create a new database" >> {
      (new  Neo4j2Graph("/tmp/neo4jTest")) should not beNull
    }

    "create 1000 nodes" >> {
      val userList = for(i <- 1 to 1000) yield (
        User(UUID.randomUUID().toString, "Diego_" + i.toString).save
        )
      userList.size should beEqualTo(1000)
    }
    "create 10K nodes" >> {
      val userList = for(i <- 1 to 10000) yield (
        User(UUID.randomUUID().toString, "Diego_" + i.toString).save
        )
      userList.size should beEqualTo(10000)

    }

    "create 100 edges form a given Vertex" >> {
      (for(i <- 1 to 100) yield {
        diegoram --> "own" --> Tweet(UUID.randomUUID().toString, "text " + i.toString, new Date().getTime).save() <()
      }).size should beEqualTo(100)
    }

    "create 100K nodes" >> {
      val userList = for(i <- 1 to 100000) yield (
        User(UUID.randomUUID().toString, "Diego_" + i.toString).save
        )
      userList.size should beEqualTo(100000)

    }

    "retrieve a single Node by id" >> {
      graph.getVertex(diegoram.getId).toCC[User].get.screenName must contain("Diego")
    }


    "retrieve a list of node by a given Node (related ones)" in {
      diegoram.getVertices(Direction.OUT, "own").iterator.toList.size should beEqualTo(100)
    }
    "retrieve a list of nodes by a given query" in {
      diegoram.pipe.out("own").toList.size should beEqualTo(100)
    }
  }



}
