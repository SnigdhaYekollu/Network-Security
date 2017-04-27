package DBfunctions;

public class DBstore {
	

		boolean pwdExist;
		String fileName;
		byte[] fileEncKey;
		byte[] hashEncKey;
		String hashValue;
		String password;
		
		public boolean getPasswordExist() {
			return pwdExist;
		}
		public void setPasswordExist(boolean pwdExist) {
			this.pwdExist = pwdExist;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public byte[] getFileEncKey() {
			return fileEncKey;
		}
		public void setFileEncKey(byte[] fileEncKey) {
			this.fileEncKey = fileEncKey;
		}
		public byte[] getHashEncKey() {
			return hashEncKey;
		}
		public void setHashEncKey(byte[] hashEncKey) {
			this.hashEncKey = hashEncKey;
		}
		public String getHashValue() {
			return hashValue;
		}
		public void setHashValue(String hashValue) {
			this.hashValue = hashValue;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
}
