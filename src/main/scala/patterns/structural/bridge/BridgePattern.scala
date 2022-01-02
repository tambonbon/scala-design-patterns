package patterns.structural.bridge

import java.security.MessageDigest
import org.apache.commons.codec.binary.Hex

/** Sometimes, a single functionality requires multiple implementations.. .. and
  * some more implementations might arise through out the life cycle of the
  * application. In these cases, it's better to decouple some parts of our code,
  * otherwise we're in danger of code explosion
  * ---> Bridge pattern: decouple abstraction from its implementation, so that
  * the two can vary independently
  *
  * Bridge is very similar to adapter
  *   - Bridge: used when we design our app
  *   - Adapter: used in 3rd-party app or library
  */
object BridgePattern {
  // Suppose we're writing a library that makes hash for storing passwords
  // There are several hash methods, i.e. SHA1, SHA256..., and there might be even more in the future

  // First, focus on the implementation side of Hasher trait
  trait Hasher {
    def hash(data: String): String
    protected def getDigest(algorithm: String, data: String) = {
      val crypt = MessageDigest.getInstance(algorithm)
      crypt.reset()
      crypt.update(data.getBytes("UTF-8"))
      crypt
    }
  }
  // Now, we have 3 classes implementing the trait. They yield different results but look quite similar
  class Sha1Hasher extends Hasher {
    override def hash(data: String): String =
      new String(Hex.encodeHex(getDigest("SHA-1", data).digest()))
  }
  class Sha256Hasher extends Hasher {
    override def hash(data: String): String =
      new String(Hex.encodeHex(getDigest("SHA-256", data).digest()))
  }
  class Md5Hasher extends Hasher {
    override def hash(data: String): String =
      new String(Hex.encodeHex(getDigest("MD5", data).digest()))
  }

  // Now, the abstraction side of things, which the clients will use
  abstract class PasswordConverter(hasher: Hasher) { // note the param, which is the trait
    def convert(password: String): String
  }
  // Then, we have chosen to provide 2 different implementations here
  class SimplePasswordConverter(hasher: Hasher) extends PasswordConverter(hasher) {
    def convert(password: String): String = hasher.hash(password)
  }
  class SaltedPasswordConverter(hasher: Hasher) extends PasswordConverter(hasher) {
    def convert(password: String): String = hasher.hash(s"salt$password")
  }

  // Now our client will use the code
  def main(args: Array[String]): Unit = {
    val pass1 = new SimplePasswordConverter(new Sha256Hasher).convert("Pa55w0rd")
    val pass2 = new SimplePasswordConverter(new Md5Hasher).convert("Pa55w0rd")
    val pass3 = new SaltedPasswordConverter(new Sha1Hasher).convert("Pa55w0rd")

    println(s"Simple password converter to SHA256 for Pa55w0rd is $pass1")
  }

  // Remarks:
  // If we hadn't used this pattern, we'd have probably implemented the converters for each different algorithm
}
