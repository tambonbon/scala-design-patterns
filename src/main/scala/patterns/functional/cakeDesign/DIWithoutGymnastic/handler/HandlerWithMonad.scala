package patterns.functional.cakeDesign.DIWithoutGymnastic.handler

import patterns.functional.cakeDesign.DIWithoutGymnastic.protocol._
import cats.data.Reader
import cats.data.Kleisli
import cats.Id

/**
 * 
 */
object UserHandlerWithMonad {
  def createUser(user: User): Reader[Repo, Long] = {
    Reader( // `Reader Monad` wraps functions with `Reader`, where we pass `Repo` in run time..
      (repo: Repo) => {
        repo.userRepo.create(user)
      } // .. return type is `Reader[Repo, _]`
    )
  }

  def getUser(id: Long): Reader[Repo, User] = {
    Reader(
      (repo: Repo) => {
        repo.userRepo.get(id)
      }
    )
  }
}


object PermissionHandlerWithMonad {
  def createPermission(permission: Permission): Reader[Repo, Long] = {
    Reader(
      (repo: Repo) => {
        repo.permRepo.create(permission)
      }
    )
  }

  def getPermission(id: Long): Reader[Repo, Permission] = {
    Reader(
      (repo: Repo) => {
        repo.permRepo.get(id)
      }
    )
  }

  def searchPermissions(role: String): Reader[Repo, List[Permission]] = {
    Reader(
      (repo: Repo) => {
        repo.permRepo.search(role)
      }
    )
  }
}


object UserPermissionHandlerWithMonad { // we can even compose objects --> power of monad
  def getUserPermissions(id: Long): Kleisli[Id, Repo, List[Permission]] = {
    for {
      u <- UserHandlerWithMonad.getUser(id)
      p <- PermissionHandlerWithMonad.searchPermissions(u.role)
    } yield p
  }
}
