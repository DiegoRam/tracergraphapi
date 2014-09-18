package sandbox

import java.util.UUID


import com.ansvia.graph.ObjectConverter
import com.tinkerpop.blueprints.{Direction, Vertex}
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory
import com.tinkerpop.gremlin.java.GremlinPipeline
import org.diegoram.User
import org.specs2.mutable.Specification
import com.ansvia.graph.BlueprintsWrapper._
import scala.collection.JavaConversions._

/**
 * Created by diegoram on 9/17/14.
 */
class OrientDbTest extends Specification{

  val factory = new OrientGraphFactory("plocal:/Users/diegoram/dev/orientdata")
  implicit val db = factory.getNoTx

  val arjones = User(UUID.randomUUID().toString, "Gustavo Arjones").save
  val diegoram = User(UUID.randomUUID().toString, "Diego Ramirez").save


  "graph database" should {
    sequential
    "create a embedded database" in {
      db should not beNull
    }

    "create a simple vertex for arjones" in {
      val unmarshalledArjones = ObjectConverter.toCC[User](arjones).get
      unmarshalledArjones.screenName === "Gustavo Arjones"
    }

    "retrieve a recently created vertex" in {
      val unmashalledArjones = db.getVertex(arjones.getId)
      ObjectConverter.toCC[User](arjones).get.screenName == "Gustavo Arjones"
    }

    "create a simple relation arjones -> knows -> diegoram" in {
      val newEdge = arjones --> "knows" --> diegoram
      newEdge.isInstanceOf[VertexWrapper]
    }

    "retrieve known people by arjones" in {
      val knownByArjones = arjones.pipe.out("knows").toList
      knownByArjones.printDump("arjones knows", "screenName")
      knownByArjones.size must beEqualTo(1)
      ObjectConverter.toCC[User](knownByArjones.toList.head).get.screenName === "Diego Ramirez"
    }

    "retrieve known people arjones knows in a vertex list" in {
      val knowList = arjones.getVertices(Direction.OUT, "knows").iterator.toList
      knowList.size must beEqualTo(1)
      ObjectConverter.toCC[User](knowList.head).get.screenName == "Diego Ramirez"
    }
  }

}
