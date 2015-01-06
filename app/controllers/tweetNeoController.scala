package controllers


import org.diegoram.Tweet
import javax.ws.rs.{QueryParam, PathParam}
import com.wordnik.swagger.annotations._
import play.api.mvc.Action
import org.diegoram.utils

@Api(value = "/neo/tweets", description = "Operation with tweets over Neo4J")
object TweetNeoController extends BaseApiController{

  def getOptions(path: String) = Action {
    implicit request => JsonResponse(new utils.ApiResponse(200, "Ok"))
  }

  @ApiOperation(value = "Find tweet by Id", notes = "Return a tweet", response = classOf[Tweet], httpMethod = "GET",
    nickname = "getbyId")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid Id supplied"),
    new ApiResponse(code = 404, message = "User Not found")
  ))
  def getById(@ApiParam(value = "TweetId") @PathParam("id") id: Int) = Action{Ok}

  @ApiOperation(value = "Find tweets by UserId", notes = "Return a tweet list", response = classOf[Tweet], httpMethod = "GET",
    nickname = "gettweetsbyId")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid Id supplied"),
    new ApiResponse(code = 404, message = "User Not found")
  ))
  def getTweeetsByUserId(@ApiParam(value = "tweetId") @PathParam("id") id: Int,
                         @ApiParam(value = "UserId") @PathParam("id") userid: Int
                          ) = Action{Ok}

  @ApiOperation(value = "Create a given number of tweets", notes = "Return a tweets list", response = classOf[Tweet], httpMethod = "POST",
    nickname = "createtweets")
  @ApiResponses(Array(
    new ApiResponse(code = 500, message = "server error")
  ))
  def createTweets(@ApiParam(value = "Number of tweets") @PathParam("id") number: Int) = Action{Ok}

  @ApiOperation(value = "Delete all tweets", notes = "Bulk delete", response = classOf[Tweet], httpMethod = "DELETE",
    nickname = "deletetweets")
  @ApiResponses(Array(
    new ApiResponse(code = 500, message = "Server error")
  ))
  def deleteAllTweets = Action{Ok}

}
