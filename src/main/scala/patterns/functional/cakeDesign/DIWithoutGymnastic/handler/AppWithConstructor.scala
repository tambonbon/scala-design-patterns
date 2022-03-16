package patterns.functional.cakeDesign.DIWithoutGymnastic.handler

import patterns.functional.cakeDesign.DIWithoutGymnastic.protocol._
import patterns.functional.cakeDesign.DIWithoutGymnastic.repo._

object AppWithConstructor {
  def main(args: Array[String]): Unit = {
    // dependencies
    val userRepo = new UserRepoImpl
    val permRepo = new PermissionRepoImpl
    val repo     = Repo(userRepo, permRepo)

    // create user
    val createUserResp = UserHandlerWithConstructor.createUser(repo, User(1001, "lambda", "admin"))
    println(createUserResp)

    // output
    // 1001

    // get user
    val getUserResp = UserHandlerWithConstructor.getUser(repo, 1001)
    println(getUserResp)

    // output
    // User(1001,lambda1001,admin)

    // create permission
    val createPermResp =
      PermissionHandlerWithConstructor.createPermission(repo, Permission(2001, "admin", "use_auth"))
    println(createPermResp)

    // output
    // 2001

    // get permission
    val getPermResp = PermissionHandlerWithConstructor.getPermission(repo, 1001)
    println(getPermResp)

    // output
    // Permission(1001,legal_officer,use_archive)
  }
}
