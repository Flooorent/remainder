object Main {

  /** args(0): to (gmail)
    * args(1): from (gmail)
    * args(2): password
    */
  def main(args: Array[String]): Unit = {

    val content: String = "test send email localhost address"

    val mail = new MailAgent(
      to = args(0),
      cc = null,
      bcc = null,
      from = args(1),
      password = args(2),
      subject = "TEST LOCALHOST",
      content = content)

    mail.sendMessage()

    println("done")
  }
}
