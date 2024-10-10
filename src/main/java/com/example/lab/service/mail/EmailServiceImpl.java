package com.example.lab.service.mail;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.example.lab.config.Config;
import com.example.lab.dto.BuddyInfoDto;
import com.example.lab.service.securityServices.UserService;

@Component
public class EmailServiceImpl {

	@Autowired
	private JavaMailSender emailSender;
	@Autowired
	private UserService userService;

	public void sendRegisterEmail(String to, String username, String token, String uri,String email,String accType) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@test.com");
		message.setTo(to);
		message.setSubject("[LAB][Đăng ký tài khoản]: "+username);
		message.setText("Đăng ký tạo tài khoản :\n "
						+ "Username: " +username+ " \n"
						+ "Địa chỉ email: " +email+ "\n"
						+ "Loại tài khoản: " +accType+ "\n"
						+ "Click link dưới đây xác nhận đăng ký tài khoản\n "
						+ "Link này có thời hạn 24h : "+uri+"account/register/active?token="+token );
		emailSender.send(message);
	}

	public void sendChemicalStatusAlertEmail(String to,String cc, String chemical, String user,BigDecimal remain) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@test.com");
		message.setTo(to);
		message.setCc(cc);
		message.setSubject("[LAB][Sử dụng hóa chất]["+chemical+"]");
		message.setText("Hóa chất "+chemical+" mới được sử dụng bới người dùng "+user+" \n "
				+ " Trạng thái hóa chất : Hết/Sắp hết \n "
				+ " Lượng hóa chất còn lại: "+remain+" (g/ml)\n"
				+ " Xin kiểm tra lại thông tin hóa chất trên hệ thống! \n\n "
				+ "Lab develop team" );
		emailSender.send(message);
	}

	public void sendActiveAccountEmail(String to, String username) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@test.com");
		message.setTo(to);
		message.setSubject("[LAB][Đăng ký tài khoản]: " + username);
		message.setText(
				"Tài khoản :" + username + " đã được đăng ký hoạt động\n" + "Đăng nhập: " + Config.CORS_DOMAIN + " \n");
		emailSender.send(message);
	}
	
	public void sendMailBuddyReportDeviceStatus(Long userId,String report,String deviceName)
	{
		if(userId==null)return;
		BuddyInfoDto dto = userService.findBuddyInfo(userId);
		if(dto.getBuddyMail()==null)return;
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@test.com");
		message.setTo(dto.getBuddyMail());
		message.setCc(dto.getStudentMail());
		message.setSubject("[LAB][Trạng thái thiết bị]");
		message.setText(
				"Thiết bị :" + deviceName + "\n"
				+"Trạng thái: "+report+"\n" 
				+"Sinh viên báo cáo: " + dto.getStudentName()+ " \n");
		emailSender.send(message);
	}
//	@Scheduled(cron = "2 * * * * *")
//	public void test() {
//		sendChemicalStatusAlertEmail("hust.dientran@gmail.com", "hust.dientran@gmail.com", "a", "b", BigDecimal.ONE);
//	}
}