package com.example.lab.common.message;

public class ErrorMessage {
	//==========Auth=================
	public static final String AUTH_INVALID_USERNAME_PASSWORD = "Tên đăng nhập hoặc mật khẩu không chính xác!";
	public static final String AUTH_INVALID_EMAIL_REGISTER = "Email không đúng hoặc có vấn đề khi gửi mail!Hãy thử lại";

	public static final String AUTH_DUPLICATED_EMAIL_REGISTER = "Email đã được đăng ký trên hệ thống";
	public static final String AUTH_DUPLICATED_USERNAME_REGISTER = "Username đã được sử dụng";
	
	//==============Chemical=============================
	public static final String CHEMICAL_IMPORTED_CHEMICAL_MESSAGE = "Hóa chất đã được nhập trước đó";
	public static final String CHEMICAL_CANNOT_IMPORT_CHEMICAL_MESSAGE = "Hóa chất chưa được nhập";
	public static final String CHEMICAL_BARCODE_IS_EMPTY_MESSAGE = "Chưa nhập mã barcode";
	public static final String CHEMICAL_CANNOT_ADD_CHEMICAL = "Hóa chất chưa được đăng ký";
	public static final String CHEMICAL_CHEMICAL_DUPLICATED = "Hóa chất đã được đăng ký trước đó";
	public static final String CHEMICAL_CANNOT_GET_CHEMICAL_INFO = "Không tìm thấy thông tin hóa chất hoặc hóa chất đã hết";
	public static final String CHEMICAL_CANNOT_USE_CHEMICAL = "Đăng ký sử dụng hóa chất thất bại";
}
