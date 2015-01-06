package controllers

import org.diegoram.models.User
import javax.ws.rs.{QueryParam, PathParam}
import com.wordnik.swagger.annotations._
import play.api.mvc.Action
import org.diegoram.utils

@Api(value = "/users", description = "Operation with Users")
object UserController extends BaseApiController{

  def getOptions(path: String) = Action {
    implicit request => JsonResponse(new utils.ApiResponse(200, "Ok"))
  }

  @ApiOperation(value = "Find user by Id", notes = "Return a user", response = classOf[User], httpMethod = "GET",
  nickname = "getbyId")
  @ApiResponses(Array(
  new ApiResponse(code = 400, message = "Invalid Id supplied"),
  new ApiResponse(code = 404, message = "User Not found")
  ))
  def getById(@ApiParam(value = "UserId") @PathParam("id") id: Int) = Action{Ok}

  @ApiOperation(value = "Find followers by UserId", notes = "Return a users list", response = classOf[User], httpMethod = "GET",
    nickname = "getfollowersbyId")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid Id supplied"),
    new ApiResponse(code = 404, message = "User Not found")
  ))
  def getFollowersById(@ApiParam(value = "UserId") @PathParam("id") id: Int) = Action{Ok}

  @ApiOperation(value = "Create a given number of users", notes = "Return a users list", response = classOf[User], httpMethod = "POST",
    nickname = "createUsers")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid Id supplied"),
    new ApiResponse(code = 404, message = "User Not found")
  ))
  def createUsers(@ApiParam(value = "Number of users") @PathParam("id") number: Int) = Action{Ok}

  @ApiOperation(value = "Delete all users", notes = "Bulk delete", response = classOf[User], httpMethod = "DELETE",
    nickname = "deleteUsers")
  @ApiResponses(Array(
    new ApiResponse(code = 500, message = "Server error")
  ))
  def deleteAllUsers = Action{Ok}

}
