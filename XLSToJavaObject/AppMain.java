package com.rzt.main;

import java.util.ArrayList;
import java.util.List;
import com.rzt.pojo.Music;
import com.rzt.pojo.Song;
import com.rzt.service.ObjectToXLS;
import com.rzt.service.XLSToJavaObject;
import com.rzt.utils.ObjectHelper;

public class AppMain {

	static String HEADER_COLOR = "yellow";
	static String FONT_COLOR = "green";
	static String filePath = "/home/muthamizan/projects/music.xlsx";
	static String sheetName = "TestSheet";

	public static void main( String[] args ) throws Exception
	{

		List<Music> musicObjets = (List<Music>) XLSToJavaObject.getObjectsForXLSSheet(filePath, sheetName, new Music());
		List<Song> SongObjets = (List<Song>) XLSToJavaObject.getObjectsForXLSSheet(filePath, sheetName, new Song());

		System.out.println("------------Music Object-----------------");
		System.out.println(ObjectHelper.parseObject(musicObjets));

		System.out.println("-------------Song Object----------------");
		System.out.println(ObjectHelper.parseObject(SongObjets));

		ObjectToXLS.generateXLSSheet(createObject(), "music.xlsx", HEADER_COLOR, FONT_COLOR);
		System.out.println("-----done-----");
	}

	public static List<Object> createObject()
	{

		List<Object> list = new ArrayList<Object>();
		List<String> singers = new ArrayList<String>();

		Song song = new Song();
		song.setMusicDirector("AR Rahman");
		song.setType("song");
		song.setCategory("Romantic");
		song.setSongName("Bla Bla");

		singers.add("AR");
		singers.add("Anirudh");

		song.setSingers(singers);

		singers = new ArrayList<String>();

		Song song1 = new Song();
		song1.setMusicDirector("AR Rahman");
		song1.setType("song");
		song1.setCategory("pop");
		song1.setSongName("Ho Hoo");

		singers.add("Justin Biber");
		singers.add("Michael Jackson");

		song1.setSingers(singers);

		list.add(song);
		list.add(song1);

		return list;
	}

}
