package patterns.functional.lensDesign

/** This example uses Monocle lens functionality https://blog.rockthejvm.com/lens/
  */
object Lenses {
  def main(args: Array[String]): Unit = {
    // For lenses
    println("1. lens")
    val metallica = RockBand(
      "Metallica",
      1981,
      Guitarist("Kirk Hammett", Guitar("ESP", "M II"))
    )
    println(
      "We want to make changes to the database, and at the same time comply with database rules..."
    )
    val metallicaFixed = metallica.copy(
      leadGuitarist = metallica.leadGuitarist.copy(
        favoriteGuitar = metallica.leadGuitarist.favoriteGuitar.copy(
          model = metallica.leadGuitarist.favoriteGuitar.model.replace(" ", "-")
        )
      )
    )
    println(metallicaFixed) // which is a boilerplate...

    val kirksFavGuitar = Guitar("ESP", "M II")
    import monocle.Lens
    import monocle.macros.GenLens

    val guitarModelLensOnly: Lens[Guitar, String] = GenLens[Guitar](_.model)
    // inspecting
    val kirksGuitarModel = guitarModelLensOnly.get(kirksFavGuitar)
    println(kirksGuitarModel) // "M II"
    // modifying
    val formattedGuitar = guitarModelLensOnly.modify(_.replace(" ", "-"))(
      kirksFavGuitar
    )
    println(formattedGuitar) // Guitar("ESP", "M-II")
    // compose lenses
    val leadGuitaristLens = GenLens[RockBand](_.leadGuitarist)
    val guitarLens        = GenLens[Guitarist](_.favoriteGuitar)
    val guitarModelLens   = GenLens[Guitar](_.model)
    val composedLens      = leadGuitaristLens.andThen(guitarLens).andThen(guitarModelLens)
    // the composed Lens now has capacity to inspect and modify what we want
    val kirksGuitarModel2 = composedLens.get(metallica)
    println(kirksGuitarModel2)
    val metallicaFixed2 = composedLens.modify(_.replace(" ", "-"))(metallica)
    println(metallicaFixed2)
  }

  case class Guitar(make: String, model: String)
  case class Guitarist(name: String, favoriteGuitar: Guitar)
  case class RockBand(name: String, yearFormed: Int, leadGuitarist: Guitarist)
}
