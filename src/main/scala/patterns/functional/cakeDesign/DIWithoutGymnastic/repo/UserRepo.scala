package patterns.functional.cakeDesign.DIWithoutGymnastic.repo

import patterns.functional.cakeDesign.DIWithoutGymnastic.protocol.User
trait UserRepo {
  def create(user: User): Long

  def get(id: Long): User
}
