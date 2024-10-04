package com.example.lab.common.message;

public class ErrorMessage {
	// ==========Auth=================
	public static final String AUTH_INVALID_USERNAME_PASSWORD = "Tên đăng nhập hoặc mật khẩu không chính xác!";
	public static final String AUTH_INVALID_EMAIL_REGISTER = "Email không đúng hoặc có vấn đề khi gửi mail!Hãy thử lại";

	public static final String AUTH_DUPLICATED_EMAIL_REGISTER = "Email đã được đăng ký trên hệ thống";
	public static final String AUTH_DUPLICATED_USERNAME_REGISTER = "Username đã được sử dụng";
	public static final String AUTH_NOT_ACTIVE_USER = "Tài khoản chưa đăng ký hoạt dộng";
	// ==============Chemical=============================
	public static final String CHEMICAL_IMPORTED_CHEMICAL_MESSAGE = "Hóa chất đã được nhập trước đó";
	public static final String CHEMICAL_CANNOT_IMPORT_CHEMICAL_MESSAGE = "Hóa chất chưa được nhập";
	public static final String CHEMICAL_BARCODE_IS_EMPTY_MESSAGE = "Chưa nhập mã barcode";
	public static final String CHEMICAL_CANNOT_ADD_CHEMICAL = "Hóa chất chưa được đăng ký";
	public static final String CHEMICAL_CHEMICAL_DUPLICATED = "Hóa chất đã được đăng ký trước đó";
	public static final String CHEMICAL_CANNOT_GET_CHEMICAL_INFO = "Không tìm thấy thông tin hóa chất hoặc hóa chất đã hết";
	public static final String CHEMICAL_CANNOT_USE_CHEMICAL = "Giá trị sử dụng nhiều hơn số lượng hóa chất còn lại hoặc có lỗi trong quá trình đăng ký sử dụng hóa chất";
	public static final String CHEMICAL_CANNOT_DELETED = "Không thể xóa hóa chất hoặc hóa chất đã được xóa trước đó";
	public static final String CHEMICAL_INFO_CANNOT_DELETED = "Không thể xóa thông tin cơ bản hóa chất hoặc thông tin hóa chất đã được xóa trước đó";
	public static final String CHEMICAL_CANNOT_UPDATE = "Không thể cập nhật thông tin cơ bản hóa chất hoặc thông tin hóa chất đã được xóa trước đó";
	// =====================Brand==========================

	public static final String BRAND_CANNOT_ADD = "Đăng ký nơi sản xuất thất bại hoặc trùng lặp thông tin đăng ký";
	public static final String BRAND_INFO_CANNOT_DELETED = "Không thể xóa thông tin nơi sản xuất hoặc thông tin hóa chất đã được xóa trước đó";
	// =====================Position==========================

	public static final String POSITION_CANNOT_ADD = "Đăng ký nơi đặt hóa chất thất bại hoặc trùng lặp thông tin đăng ký";
	public static final String POSITION_INFO_CANNOT_DELETED = "Không thể xóa thông tin nơi đặt hóa chất hoặc thông tin hóa chất đã được xóa trước đó";
	//======================Device=====================
	public static final String DEVICE_CANNOT_ADD = "Đăng ký thông tin thiết bị thất bại hoặc trùng lặp thông tin đăng ký";
}
