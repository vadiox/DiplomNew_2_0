package ua.study.english.other;

public class WordTranslEnt {
	
	String word;
	String translate;
	int id;
	String user;
	
	//створили свій шаблон слів з їх перекладами та методи роботи з ними
	//word				слово на англійскій мові
	//translate			його переклад
	//id				номер слова у загальному масиві слів
	
	//приклад
	//word				absolutely
	//translate			совершенно, безусловно
	//id				13
	//user				Test User
	
	public WordTranslEnt(){}
	
	public WordTranslEnt(String mWord, String mTranslate, String mUser, int mId){
		this.word=mWord;
		this.translate=mTranslate;
		this.id=mId;
		this.user = mUser;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getTranslate() {
		return translate;
	}

	public void setTranslate(String translate) {
		this.translate = translate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
