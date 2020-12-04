package me.pedro.mastermail.util;

import org.simplejavamail.api.email.CalendarMethod
import org.simplejavamail.api.email.Email
import org.simplejavamail.api.mailer.config.TransportStrategy
import org.simplejavamail.email.EmailBuilder
import org.simplejavamail.mailer.MailerBuilder
import org.simplejavamail.mailer.MailerBuilder.withSMTPServer
import javax.mail.Message.RecipientType.BCC


class Mailer {

    fun test(){
        var email = EmailBuilder.startingBlank()
            .from("lollypop", "pedroeugeniocavalcanti@gmail.com")
            .to("C. Cane", "pedroeugeniocavalcanti@gmail.com")
            .cc("C. Bo <chocobo@candyshop.org>")
            .withSubject("hey")
            .withPlainText("We should meet up! ;)")
            .buildEmail();

        var mailer = withSMTPServer("smtp.gmail.com", 465, "pedroeugeniocavalcanti@gmail.com", "PEdro1414")
            .withTransportStrategy(TransportStrategy.SMTP)
            .withSessionTimeout(10 * 1000)
            .clearEmailAddressCriteria()
            .withDebugLogging(true)
            .async()
            .buildMailer();

        mailer.sendMail(email)
    }

}