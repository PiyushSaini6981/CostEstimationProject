package com.solitera.test_estimation.usermgmt.utils.mail;

import java.io.ByteArrayOutputStream;
import java.util.Optional;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;
import com.solitera.test_estimation.usermgmt.exception.NotFoundException;
import com.solitera.test_estimation.usermgmt.model.UserModel;
import com.solitera.test_estimation.usermgmt.repository.UserRepository;
import com.solitera.test_estimation.usermgmt.utils.UtilsFunc;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class Emailsender {

	 @Autowired
	 private UserRepository repository; 
	 
	 @Autowired
	 private JavaMailSender javaMailSender;

	 @Value("${spring.mail.username}") 
	 private String sender;
	 
	 @Value("${app.base-url}")
	 private String baseUrl;
	 
	 public void sendOtp(String email) {
		    Optional<UserModel> findEmail = repository.findByEmail(email);
		    if (findEmail.isPresent()) {
		        UserModel model = findEmail.get();
		        String otp = UtilsFunc.otpGenerator();
		        String token = UUID.randomUUID().toString();
		        
		        String verificationUrl = baseUrl + "?token=" + token;
		        
		        String emailText = "Email Verification OTP, valid for 15 minutes: " + otp + "\n\n" +
		                "<div style=\"text-align: center; margin-top: 100px;\">\r\n"
		                + "    <a style=\"background:#1976d2;\r\n"
		                + "        color:white;\r\n"
		                + "        border: none;\r\n"
		                + "        padding: 10px 16px;\r\n"
		                + "        text-align: center;\r\n"
		                + "        text-decoration: none;\r\n"
		                + "        display: inline-block;\r\n"
		                + "        font-size: 30px;\r\n"
		                + "        margin: 4px 2px;\r\n"
		                + "        cursor: pointer;\r\n"
		                + "        border-radius: 4px;\r\n"
		                + "        font-size: 1rem;\r\n"
		                + "        width: auto;\r\n"
		                + "        min-width: 64px;\r\n"
		                + "        font-weight: 500;\"\r\n"
		                + "        href=\"" + verificationUrl + "\">\r\n"
		                + "        VERIFY EMAIL\r\n"
		                + "    </a>\r\n"
		                + "</div>";		
		        try {
			        MimeMessage mailmessage = javaMailSender.createMimeMessage();
					MimeMessageHelper message = new MimeMessageHelper(mailmessage,true);
			        message.setFrom(sender);
			        message.setTo(email);
			        message.setSubject("Email Verification");
			        message.setText(emailText,true);
			        model.setOtp(otp);
			        model.setToken(token);
			        model.setTimestamp(System.currentTimeMillis());
			        repository.save(model);
			        javaMailSender.send(mailmessage);
		        }catch (MessagingException e) {
					log.info("Token not send : "  + e.getMessage());					
				}
		    } else {
		        throw new NotFoundException("Otp not sent to Email");
		    }
		}
	
	public void sendVerificationToken(String email,String token) {
		try {
			MimeMessage mailmessage = javaMailSender.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(mailmessage,true);
	        String verificationUrl = baseUrl + "/resetPassword?token=" + token;
			message.setFrom(sender);
			message.setTo(email);
			message.setSubject("Forgot password Token");
			String emailText ="\nPlease click the link below to verify your email address:\n" + verificationUrl;
			message.setText(emailText,true);
			javaMailSender.send(mailmessage);
		}catch (MessagingException e) {
			log.info("Token not send : "  + e.getMessage());					
		}
	}
	
	public void sendEstimationDetailsToMail(String email,String data) throws DocumentException, MessagingException{
		Optional<UserModel> findEmail = repository.findByEmail(email);
		if(findEmail.isPresent()) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.open();
			Chunk chunk = new Chunk(data);
			chunk.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 12)); // adjust font size
			Phrase phrase = new Phrase();
			phrase.add(chunk);
			ColumnText ct = new ColumnText(writer.getDirectContent());
			ct.setSimpleColumn(phrase, 100, 100, 500, 700, 20, Element.ALIGN_LEFT);
			int status = ct.go();
			while (ColumnText.hasMoreText(status)) {
			    document.newPage();
			    status = ct.go();
			}
			document.close();
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(sender);
			helper.setTo(email);
			helper.setSubject("Estimation Data");
			helper.setText("Estimation Data : ");
			helper.addAttachment("EstimationDataPdf.pdf", new ByteArrayResource(baos.toByteArray()));
			javaMailSender.send(message);
			baos.reset();
		}else {
			throw new NotFoundException("Email not Found");
		}
	}
}

