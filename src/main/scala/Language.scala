import java.util.Locale
import enumeratum.{Enum, EnumEntry}


sealed abstract class Language(
  override val entryName: String,
  val locale: Locale,
  val thisDay: String,
  val thisWeek: String,
  val thisMonth: String,
  val nobody: String) extends EnumEntry


object Language extends Enum[Language] {
  val values = findValues

  case object English extends Language(
    entryName = "en",
    locale = new Locale("en"),
    thisDay = "This day",
    thisWeek = "This week",
    thisMonth = "This month",
    nobody = "nobody")

  case object French extends Language (
    entryName = "fr",
    locale = new Locale("fr"),
    thisDay = "Aujourd'hui",
    thisWeek = "Cette semaine",
    thisMonth = "Ce mois",
    nobody = "personne")
}
