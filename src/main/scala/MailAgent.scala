import javax.mail.{Address, Authenticator, Message, MessagingException, PasswordAuthentication, Session, Transport}
import javax.mail.internet.{AddressException, InternetAddress, MimeMessage}
import java.util.Date
import java.util.Properties

import scala.collection.JavaConversions._


// taken from http://alvinalexander.com/source-code/scala/scala-send-email-class-uses-javamail-api
class MailAgent(
    to: String,
    cc: String,
    bcc: String,
    from: String,
    password: String,
    subject: String,
    content: String){

  var message: Message = null

  message = createMessage
  message.setFrom(new InternetAddress(from))
  setToCcBccRecipients()

  message.setSentDate(new Date())
  message.setSubject(subject)
  message.setText(content)


  @throws(classOf[MessagingException])
  def sendMessage(): Unit = {
    Transport.send(message)
  }


  def createMessage: Message = {
    val properties = new Properties()
    properties.put("mail.smtp.auth", "true")
    properties.put("mail.smtp.starttls.enable", "true")
    properties.put("mail.smtp.host", "smtp.gmail.com")
    properties.put("mail.smtp.port", "587")

    val session = Session.getDefaultInstance(properties, new GmailAuthenticator(from, password))
    new MimeMessage(session)
  }


  @throws(classOf[AddressException])
  @throws(classOf[MessagingException])
  def setToCcBccRecipients(): Unit = {
    setMessageRecipients(to, Message.RecipientType.TO)
    if (cc != null)
      setMessageRecipients(cc, Message.RecipientType.CC)
    if (bcc != null)
      setMessageRecipients(bcc, Message.RecipientType.BCC)
  }

  @throws(classOf[AddressException])
  @throws(classOf[MessagingException])
  def setMessageRecipients(recipient: String, recipientType: Message.RecipientType): Unit = {
    // had to do the asInstanceIf[...] call here to make scala happy
    val addressArray = buildInternetAddressArray(recipient).asInstanceOf[Array[Address]]
    if ((addressArray != null) && (addressArray.length > 0))
      message.setRecipients(recipientType, addressArray)
  }


  @throws(classOf[AddressException])
  def buildInternetAddressArray(address: String): Array[InternetAddress] = {
    // could test for a null or blank String but I'm letting parse just throw an exception
    InternetAddress.parse(address)
  }

}


class GmailAuthenticator(username: String, password: String) extends Authenticator {
  override def getPasswordAuthentication(): PasswordAuthentication = new PasswordAuthentication(username, password)
}
