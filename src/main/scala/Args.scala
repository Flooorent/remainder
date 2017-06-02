import scala.util.{Failure, Success, Try}


object Args {

  case class Config(
    to: String = "",
    from: String = "",
    password: String = "",
    language: String = "en"
  )


  val usage = """sbt "run --to $TO --from $FROM --password $PASSWORD --language en""""


  val parser = new scopt.OptionParser[Config](usage) {
    opt[String]("to")
      .required()
      .action{ (value, conf) => conf.copy(to = value)}
      .text("Recipient's email address.")

    opt[String]("from")
      .required()
      .action{ (value, conf) => conf.copy(from = value)}
      .text("Sender's email address.")

    opt[String]("password")
      .required()
      .action{ (value, conf) => conf.copy(password = value)}
      .text("Sender's email password.")

    opt[String]("language")
      .action{ (value, conf) => conf.copy(language = value)}
      .text("Email's language. Only english and french are available at the moment.")
  }


  /** Get parameters configuration with scopt.
    *
    * @param args: command line arguments for scopt
    */
  def getConfig(args: Array[String]): Try[Config] =
    parser.parse(args, Config()) match {
      case None => Failure(new IllegalArgumentException(parser.usage))
      case Some(conf) => Success(conf)
    }
}
