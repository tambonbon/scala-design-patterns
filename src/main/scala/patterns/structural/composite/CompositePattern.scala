package patterns.structural.composite

import scala.collection.mutable.ListBuffer

/** Used to describe group of objects that should be treated as a single one,
  * or, to compose objects into a tree structures to represent whole-part
  * hierachies. Useful for removing code duplication and avoiding error in cases
  * group of objects can be treated the same
  *
  * Think of the filesystem
  */
object CompositePattern {

  trait Node {
    def print(prefix: String): Unit
  }

  class Leaf(data: String) extends Node {
    def print(prefix: String): Unit = println(s"$prefix $data")
  }

  // Our composite, which contains either children tree, or just leaf nodes
  class Tree extends Node {
    private val children = ListBuffer.empty[Node]

    override def print(prefix: String): Unit = {
      System.out.println(s"${prefix}(")
      children.foreach(_.print(s"${prefix}${prefix}"))
      System.out.println(s"${prefix})")
    }

    def add(child: Node): Unit = {
      children += child
    }

    def remove(): Unit = {
      if (children.nonEmpty) {
        children.remove(0)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val tree = new Tree
    tree.add(new Leaf("leaf 1"))
    val subtree1 = new Tree
    subtree1.add(new Leaf("leaf 2"))
    val subtree2 = new Tree
    subtree2.add(new Leaf("leaf 3"))
    subtree2.add(new Leaf("leaf 4"))
    subtree1.add(subtree2)
    tree.add(subtree1)
    val subtree3 = new Tree
    val subtree4 = new Tree
    subtree4.add(new Leaf("leaf 5"))
    subtree4.add(new Leaf("leaf 6"))
    subtree3.add(subtree4)
    tree.add(subtree3)
    tree.print("-")

  }
}
