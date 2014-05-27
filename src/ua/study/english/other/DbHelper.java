package ua.study.english.other;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
	
	//������� �� ������ � ����� �����
public class DbHelper extends SQLiteOpenHelper implements DbInterface {
	
	/**Databases instants**/
	private static final int DATABASE_VERSION = 1;			//����� ����
    private static final String DATABASE_NAME = "english_db";	//����� ���� �����
    
    //����� ������� � ���
    static final String TABLE_REZULT = "tbl_rezult";
    static final String TABLE_WORD_TRANSLATE = "tbl_word_translate";
     
    //������� ���������� �� �� ����
    private static final String REZULT_USER = "_user";
    private static final String REZULT_DATE = "_date";
    private static final String REZULT_REZULT = "rezult";
    private static final String REZULT_ID = "_id";
    
    //������� �������� ��� �� �� ����
    private static final String WT_WORD = "_word";
    private static final String WT_TRANSLATE = "_translate";
    private static final String WT_USER = "_user";
    private static final String WT_ID = "_wt_id";
    private static final String ID = "_id";
    
    SQLiteDatabase db; 
    
    public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void add_rezults(RezultEnt rezult) { //��������� ���������� � �������
		db = this.getWritableDatabase();
		Log.e("add", "rezult");
        ContentValues values = new ContentValues();
        values.put(REZULT_USER, rezult.getUser());
        values.put(REZULT_DATE, rezult.getDate());
        values.put(REZULT_REZULT, rezult.getRezult());
        db.insert(TABLE_REZULT, null, values);
        db.close();
	}

	@Override
	public ArrayList<RezultEnt> user_get_all_rezult(String user) {	//���������� ��� ���������� ����������� 
		ArrayList<RezultEnt> list = new ArrayList<RezultEnt>();
        String selectQuer = "SELECT * FROM "+TABLE_REZULT+" WHERE "+REZULT_USER+"='"+user+"';";
        db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuer,null);
        if (cursor.moveToFirst()){
            do{
            	RezultEnt rezult = new RezultEnt();
            	rezult.setUser(cursor.getString(cursor.getColumnIndex(REZULT_USER )));
            	rezult.setDate(cursor.getString(cursor.getColumnIndex(REZULT_DATE)));
            	rezult.setRezult(cursor.getInt(cursor.getColumnIndex(REZULT_REZULT)));
            	list.add(rezult);
            }while(cursor.moveToNext());
        }else{
        	return list;	
        }
        cursor.close();
        return list;
	}

	@Override
	public void user_clear_statistics() {		//��������� ������� � ������������
		db.delete(TABLE_REZULT, null, null);	
	}

	@Override
	public void clear_all_data() {				//��������� �� ��� (���������� �� ������ �����)
		db.delete(TABLE_REZULT, null, null);
		db.delete(TABLE_WORD_TRANSLATE, null, null);	
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e("greate", "SQ Lite");

		String CREATE_TABLE_REZULT = "CREATE TABLE " + TABLE_REZULT //���� ��������� ������� ����������
				+ "("
                + REZULT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + REZULT_REZULT + " INTEGER,"
				+ REZULT_USER + " TEXT,"
                + REZULT_DATE + " TEXT" 
				+");";
		
		String CREATE_TABLE_WT = "CREATE TABLE " + TABLE_WORD_TRANSLATE //���� ��������� ������� �������� ���
				+ "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + WT_USER + " TEXT,"
                + WT_ID + " INTEGER,"
				+ WT_WORD + " TEXT,"
                + WT_TRANSLATE + " TEXT" 
				+");";
        db.execSQL(CREATE_TABLE_REZULT);		//��������� ������� ����������, ���� ���� �������
        db.execSQL(CREATE_TABLE_WT);			//��������� ������� ���, ���� ���� �������	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {	//��������� �������
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_REZULT);		//��������� ������� ����������
		db.execSQL(TABLE_WORD_TRANSLATE);						//��������� ������� ���
		onCreate(db);											//��������� ���� ����
	}

	@Override
	public ArrayList<RezultEnt> day_get_middle_rezult(String user, ArrayList<String> date) {
		db= this.getReadableDatabase();
		ArrayList<RezultEnt> rez = new ArrayList<RezultEnt>();
		for (int i=0;i<date.size();i++){
			int count = 0; 							//��������� ������� ���������� �� ����
			RezultEnt rez_ent = new RezultEnt();
			int total_score =0;						//��������� ���� ��� ���������� �� ����
			
			//�������� �� ���������� ����������� �� ���������� ����
			String selectQuer = "SELECT * FROM " + TABLE_REZULT+" WHERE "+REZULT_USER+"='"+user+"' AND " + REZULT_DATE + "='" + date.get(i)+"';";
	        Cursor cursor = db.rawQuery(selectQuer, null);    
	        if (cursor.moveToFirst()){	//���� � ����������� � ���������� �� �������� ����, �� 
	            do{
	            	count++;		//�������� ������� ���������� �� 1
	            	total_score = total_score + cursor.getInt(cursor.getColumnIndex(REZULT_REZULT));		//��������� �������� ��������� �� ����
	            }while(cursor.moveToNext());
	            rez_ent.setDate(date.get(i));	//���������� ���������� ������� ����
	            rez_ent.setRezult(total_score/count);	//���������� ���������� ������� ��������� �� ����
	            rez_ent.setUser(user);					//���������� ���������� ��� �����������
	            rez.add(rez_ent);						//������ ���� � �����
	        } 
		}
		
		return rez;
	}

	@Override
	public ArrayList<String> get_all_diferent_data() {		//������� �� ��� ���� �� ���� � ���� � ���� ���������
		ArrayList<String> data_list = new ArrayList<String>();
		db= this.getReadableDatabase();
		String selectQuer = "SELECT DISTINCT " + REZULT_DATE+" FROM " + TABLE_REZULT+";";
        Cursor cursor = db.rawQuery(selectQuer, null);    
        if (cursor.moveToFirst()){
            do{
            	data_list.add(cursor.getString(cursor.getColumnIndex(REZULT_DATE)));   	
            }while(cursor.moveToNext());
        }	
		return data_list;
	}

	@Override
	public ArrayList<WordTranslEnt> get_all_words(String user) {
		ArrayList<WordTranslEnt> list_words = new ArrayList<WordTranslEnt>();
		db= this.getReadableDatabase();
		String selectQuer = "SELECT * FROM "  + TABLE_WORD_TRANSLATE +" WHERE " + WT_USER+"='" + user +  "';";
        Cursor cursor = db.rawQuery(selectQuer, null);    
        if (cursor.moveToFirst()){
            do{
            	Log.e("get word", cursor.getString(cursor.getColumnIndex(WT_WORD)));
            	WordTranslEnt word = new WordTranslEnt();
	            	word.setWord(cursor.getString(cursor.getColumnIndex(WT_WORD)));
	            	word.setUser(cursor.getString(cursor.getColumnIndex(WT_USER)));
	            	word.setTranslate(cursor.getString(cursor.getColumnIndex(WT_TRANSLATE)));
	            	word.setId(cursor.getInt(cursor.getColumnIndex(WT_ID)));
            	list_words.add(word);
            }while(cursor.moveToNext());
        }
		return list_words;
	}

	@Override
	public void add_word_translate(WordTranslEnt word_ent) {
		db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WT_WORD, word_ent.getWord());
        values.put(WT_TRANSLATE, word_ent.getTranslate());
        values.put(WT_USER, word_ent.getUser());
        values.put(WT_ID, word_ent.getId());
        db.insert(TABLE_WORD_TRANSLATE, null, values);
        db.close();
	}
}
