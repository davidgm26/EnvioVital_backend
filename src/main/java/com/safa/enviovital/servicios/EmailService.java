package com.safa.enviovital.servicios;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     *  Metodo para enviar el correo de bienvenida a un usuario.
     * @param toEmail
     * @param name
     */
    public void sendRegistrationEmail(String toEmail, String name) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("pablopadillaarroyo@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject("Registro exitoso");

            String htmlContent =
                    "<!DOCTYPE html>" +
                            "<html lang=\"es\">" +
                            "<head>" +
                            "  <meta charset=\"UTF-8\">" +
                            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                            "  <title>Registro Exitoso</title>" +
                            "</head>" +
                            "<body style=\"background-color: #f9f9f9; font-family: Arial, sans-serif; margin: 0; padding: 0;\">" +
                            "  <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color: #f9f9f9; padding: 20px 0;\">" +
                            "    <tr>" +
                            "      <td align=\"center\">" +
                            "        <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" style=\"background: #ffffff; border-radius: 8px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); overflow: hidden;\">" +
                            "          <tr>" +
                            "            <td style=\"background: #3b82f6; color: white; padding: 20px; text-align: center; font-size: 24px; font-weight: bold;\">" +
                            "              Registro Exitoso en EnvioVital" +
                            "            </td>" +
                            "          </tr>" +
                            "          <tr>" +
                            "            <td style=\"padding: 20px; text-align: center;\">" +
                            "              <h1 style=\"font-size: 22px; color: #333333; margin: 0;\">¡Hola, " + name + "!</h1>" +
                            "              <p style=\"font-size: 16px; color: #555555; line-height: 1.6; margin: 10px 0;\">" +
                            "                Gracias por registrarte en EnvioVital. Tu registro ha sido exitoso. 🎉" +
                            "              </p>" +
                            "              <a href=\"http://localhost:4200/main\" style=\"display: inline-block; margin-top: 20px; padding: 10px 20px; background: #3b82f6; color: white; text-decoration: none; font-size: 16px; font-weight: bold; border-radius: 4px;\">" +
                            "                Ir a EnvioVital" +
                            "              </a>" +
                            "            </td>" +
                            "          </tr>" +
                            "          <tr>" +
                            "            <td style=\"padding: 20px; text-align: center; background: #f4f4f4; font-size: 14px; color: #888888;\">" +
                            "              Si tienes alguna pregunta, no dudes en contactarnos en <a href=\"mailto:soporte@enviovital\" style=\"color: #3b82f6; text-decoration: none;\">soporte@enviovital</a>." +
                            "            </td>" +
                            "          </tr>" +
                            "        </table>" +
                            "      </td>" +
                            "    </tr>" +
                            "  </table>" +
                            "</body>" +
                            "</html>";



            helper.setText(htmlContent, true); // El segundo parámetro indica que es HTML
            mailSender.send(message);
        } catch (MessagingException e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}
