package patterns.structural.bridge

import org.apache.commons.codec.binary.Hex

object BridgeForScalaPattern {
  // Now, we keep the Hasher trait, but the implementations become traits instead of classes
  import BridgePattern.Hasher

  trait Sha1Hasher extends Hasher { // having traits allows to mix in later
    override def hash(data: String): String =
      new String(Hex.encodeHex(getDigest("SHA-1", data).digest()))
  }
  trait Sha256Hasher extends Hasher {
    override def hash(data: String): String =
      new String(Hex.encodeHex(getDigest("SHA-256", data).digest()))
  }
  trait Md5Hasher extends Hasher {
    override def hash(data: String): String =
      new String(Hex.encodeHex(getDigest("MD5", data).digest()))
  }

  // The abstraction side now uses self type
  abstract class PasswordConverterBase {
    self: Hasher => // this tells the compiler that ..
    // .. whenever we use this base class, we need Hasher trait to mix in
    def convert(password: String): String
  }
  // similar implementations
  class SimplePasswordConverterBase extends PasswordConverterBase {
    self: Hasher =>
    def convert(password: String): String = self.hash(password)
  }
  class SaltedPasswordConverterBase extends PasswordConverterBase {
    self: Hasher =>
    def convert(password: String): String = self.hash(s"salted$password")
  }

  // Now the clients use
  def main(args: Array[String]): Unit = {
    val pass1 = new SimplePasswordConverterBase with Sha1Hasher
    val pass2 = (new SimplePasswordConverterBase with Md5Hasher) convert("Pa55w0rd")
    val pass3 = (new SaltedPasswordConverterBase with Sha1Hasher).convert("Pa55w0rd")

    println(s"Simple password converter to SHA256 for Pa55w0rd is ${pass1.convert("pa55w0rd")}")
  }
}
