package ua.study.english.other;

public class RezultEnt {
	String user;
	String date;
	int rezult;
	
	public RezultEnt(){}
	
	//�������� ��� ������ ��������� �� ������ ������ � ����
	// user   			������ ����� �� ������� �����
	// date				���� ����������� ����� �� ����� ����������� ������
	// rezult			������� ����� �������� �����������
	
	//�������--------
	// user					Test
	// date					28/05/14 		dd/MM/yy
	// rezult				5  (min 0, max 10)
	
	public RezultEnt(String mUser, String mDate, int mRezult){
		this.user=mUser;
		this.date=mDate;
		this.rezult=mRezult;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getRezult() {
		return rezult;
	}

	public void setRezult(int rezult) {
		this.rezult = rezult;
	}

}
