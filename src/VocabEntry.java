public class VocabEntry
{
	private String kana;
	private String kanji;
	private String english;
	
	public VocabEntry(String kana, String kanji, String english)
	{
		this.kana = kana.replaceAll("〜", "~");
		this.kanji = kanji.replaceAll("〜", "~");
		this.english = english.replaceAll("\"", "");
	}
	
	public static VocabEntry parseCSVLine(String line)
	{
		String[] parts = line.split(",");
			
		return new VocabEntry(parts[0], parts[1], parts[2]);	
	}
	
	public String toString()
	{
		return this.kana + "," + this.kanji + "," + this.english;
	}
	
	public String getKanji() { return this.kanji; }
	public String getKana() { return this.kana; }
	public String getEnglish() { return this.english; }
}
