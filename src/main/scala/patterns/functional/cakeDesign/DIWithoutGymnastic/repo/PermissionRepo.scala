package patterns.functional.cakeDesign.DIWithoutGymnastic.repo

import patterns.functional.cakeDesign.DIWithoutGymnastic.protocol.Permission
trait PermissionRepo {
  def create(permission: Permission): Long

  def get(id: Long): Permission

  def search(role: String): List[Permission]
}
