package patterns.functional.cakeDesign.DIWithoutGymnastic.handler

import patterns.functional.cakeDesign.DIWithoutGymnastic.protocol._
import patterns.functional.cakeDesign.DIWithoutGymnastic.repo._

object AppWithMonad {
  def main(args: Array[String]): Unit = {
    // dependencies
    val userRepo = new UserRepoImpl
    val permRepo = new PermissionRepoImpl
    val repo     = Repo(userRepo, permRepo)

    // create user
    val createUserResp = UserHandlerWithMonad.createUser(User(1001, "lambda", "admin")).run(repo) // NOTE: run method
    println(createUserResp)

    // output
    // 1001

    // get user
    val getUserResp = UserHandlerWithMonad.getUser(1001).run(repo)
    println(getUserResp)

    // output
    // User(1001,lambda1001,admin)

    // create permission
    val createPermResp =
      PermissionHandlerWithMonad.createPermission(Permission(2001, "admin", "use_auth")).run(repo)
    println(createPermResp)

    // output
    // 2001

    // get permission
    val getPermResp = PermissionHandlerWithMonad.getPermission(1001).run(repo)
    println(getPermResp)

    // output
    // Permission(1001,legal_officer,use_archive)

    // get permissions of user
    val permissions = UserPermissionHandlerWithMonad.getUserPermissions(1001).run(repo)
    println(permissions)

    // output
    // List(Permission(1001,user,user_archive), Permission(1002,user,use_doc_storage), Permission(1005,user,use_auth))

  }
}
