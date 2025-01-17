package utils;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


//clase que retorna uma autenticacao para ser enviada e verificada pelo servidor smtp


public class SendMail {

    private String mailSMTPServer;
    private String mailSMTPServerPort;
    private String protocol;
    private boolean starttls;

    /*
     * caso queira mudar o servidor e a porta, so enviar para o contrutor
     * os valor como string
     */
    public SendMail(String mailSMTPServer, String mailSMTPServerPort, String protocol, boolean starttls) { //Para outro Servidor
        this.mailSMTPServer = mailSMTPServer;
        this.mailSMTPServerPort = mailSMTPServerPort;
        this.protocol = protocol;
        this.starttls = starttls;
    }

    public CompletableFuture<String> send(String to, String subject, String message, SimpleAuth simpleAuth) {
        CompletableFuture<String> future = new CompletableFuture<> ();
        
        Properties props = getProperties(simpleAuth.username);
        
        Session session = Session.getDefaultInstance(props, simpleAuth);
        session.setDebug(false);
        //Objeto que contém a mensagem
        Message msg = new MimeMessage(session);

        try {
            //Setando o destinatário
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //Setando a origem do email
            msg.setFrom(new InternetAddress(simpleAuth.username));
            //Setando o assunto
            msg.setSubject(subject);
            //Setando o conteúdo/corpo do email
            msg.setContent(message, "text/html");

        } catch (Exception e) {
            System.out.println(">> Erro: Completar Mensagem");
            e.printStackTrace();
        }

        //Objeto encarregado de enviar os dados para o email
        Transport tr;
        try {
            tr = session.getTransport(protocol); //define smtp para transporte
            /*
             *  1 - define o servidor smtp
             *  2 - seu nome de usuario do gmail
             *  3 - sua senha do gmail
             */
            tr.connect(mailSMTPServer, simpleAuth.username, simpleAuth.password);
            msg.saveChanges();
            //envio da mensagem
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
        } catch (Exception e) {
            System.out.println(">> Erro: Envio Mensagem");
            e.printStackTrace();
        }
        future.complete(simpleAuth.username);
        return future;
    }

    private Properties getProperties(String from) {
        Properties props = new Properties();

        // quem estiver utilizando um SERVIDOR PROXY descomente essa parte e atribua as propriedades do SERVIDOR PROXY utilizado
                /*
                props.setProperty("proxySet","true");
                props.setProperty("socksProxyHost","192.168.155.1"); // IP do Servidor Proxy
                props.setProperty("socksProxyPort","1080");  // Porta do servidor Proxy
                */
        props.put("mail.transport.protocol", protocol); //define protocolo de envio como SMTP
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.smtp.host", mailSMTPServer); //server SMTP do GMAIL
        props.put("mail.smtp.auth", "true"); //ativa autenticacao
        props.put("mail.smtp.user", from); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", mailSMTPServerPort); //porta
        props.put("mail.smtp.socketFactory.port", mailSMTPServerPort); //mesma porta para o socket
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        return props;
    }
}
