package ua.study.english.other;

import java.util.ArrayList;

	//оголошені всі необхідні методи для роботи з базою
public interface DbInterface {
	public void add_rezults(RezultEnt rezult);
	public ArrayList<RezultEnt> user_get_all_rezult(String user);
	public void user_clear_statistics();
	public void clear_all_data();
	public ArrayList<RezultEnt> day_get_middle_rezult(String user, ArrayList<String> date);
	public ArrayList<String> get_all_diferent_data();
	public ArrayList<WordTranslEnt> get_all_words(String user);
	public void add_word_translate (WordTranslEnt word_ent);

}
