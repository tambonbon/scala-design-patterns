package patterns.functional.lensDesign

/** Lens Design pattern was created for immutability purpose, and preserve code's readability This
  * example uses ScalaZ lens functionality
  */
object LensDesignPattern {

  // How to use our classes to modify some of their properties
  // Imagine an enterprise app with a class hierarchy
  case class Country(name: String, code: String)
  case class City(name: String, country: Country)
  case class Address(number: Int, street: String, city: City)
  case class Company(name: String, address: Address)
  case class User(name: String, company: Company, address: Address)

  def main(args: Array[String]): Unit = {
    // this is for ExampleNoLens
    val uk     = Country("United Kingdom", "uk") // problem lies here, with lower case 'uk'
    val london = City("London", uk)
    val buckinghamPalace = Address(1, "Buckingham Palace Road", london)
    val castleBuilders   = Company("Castle Builders", buckinghamPalace)
    val switzerland      = Country("Switzerland", "CH")
    val geneva           = City("geneva", switzerland)
    val genevaAddress    = Address(1, "Geneva Lake", geneva)
    val ivan             = User("Ivan", castleBuilders, genevaAddress)
    System.out.println(ivan)
    System.out.println("Capitalize UK code...")
    val ivanFixedNoLens = ivan.copy( // Too verbose.....
      company = ivan.company
        .copy( // We can make the fields in case class to be var, but that breaks immutability
          address = ivan.company.address.copy(
            city = ivan.company.address.city.copy(
              country = ivan.company.address.city.country.copy(
                code =
                  ivan.company.address.city.country.code.toUpperCase // wanna uppercase, but too verbose
              )
            )
          )
        )
    )
    print(ivanFixedNoLens)

    // Using the lens is not easy:
    val ivanFixedLens =
      User.userCompanyCountryCode.mod(_.toUpperCase, ivan)
    println(ivanFixedLens)
  }

  import scalaz.Lens
  // Lens let us get and set different properties of an object of 'x' type
  // --> in this case, we have to define different lenses for different properties we wanna set
  object User { // companion object of User
    /** Lens.lensu[A, B]
      * --> creates actual lenses so that for an object of A type, the calls GET & SET value of B
      * type
      */
    val userCompany = Lens.lensu[User, Company](
      (user, company) => user.copy(company = company),
      _.company
    )
    val userAddress = Lens.lensu[User, Address](
      (u, address) => u.copy(address = address),
      _.address
    )
    val companyAddress = Lens.lensu[Company, Address](
      (c, address) => c.copy(address = address),
      _.address
    )
    val addressCity = Lens.lensu[Address, City](
      (a, city) => a.copy(city = city),
      _.city
    )

    val cityCountry = Lens.lensu[City, Country](
      (c, country) => c.copy(country = country),
      _.country
    )
    val countryCode = Lens.lensu[Country, String](
      (c, code) => c.copy(code = code),
      _.code
    )
    val userCompanyCountryCode =
      userCompany >=> companyAddress >=> addressCity >=> cityCountry >=> countryCode
    // >=>: andThen
  }

}
