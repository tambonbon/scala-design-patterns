package patterns.functional.cakeDesign.DIWithoutGymnastic.protocol

import patterns.functional.cakeDesign.DIWithoutGymnastic.repo._
case class Repo(userRepo: UserRepo, permRepo: PermissionRepo)