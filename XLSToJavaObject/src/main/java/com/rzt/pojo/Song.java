package com.rzt.pojo;

import java.util.List;
import org.javafunk.excelparser.annotations.ExcelField;
import org.javafunk.excelparser.annotations.ExcelObject;
import org.javafunk.excelparser.annotations.ParseType;

@ExcelObject( parseType = ParseType.ROW, start = 2, end = 3 )
public class Song extends Music {

	@ExcelField( position = 1 )
	private String songName;
	@ExcelField( position = 2 )
	private String musicDirector;
	@ExcelField( position = 3 )
	private String singersList;

	public String getSingersList()
	{
		return singersList;
	}

	public void setSingersList( String singersList )
	{
		this.singersList = singersList;
	}

	private List<String> singers;

	public String getSongName()
	{
		return songName;
	}

	public void setSongName( String songName )
	{
		this.songName = songName;
	}

	public String getMusicDirector()
	{
		return musicDirector;
	}

	public void setMusicDirector( String musicDirector )
	{
		this.musicDirector = musicDirector;
	}

	public List<String> getSingers()
	{
		return singers;
	}

	public void setSingers( List<String> singers )
	{
		this.singers = singers;
	}

}
