package com.example.mp3player;

public class Option implements Comparable<Option> {
	
	private String name;
	private String data;
	private String path;
	@Override
	public int compareTo(Option o) {
		// TODO Auto-generated method stub
		if(this.name != null)
		{
			return this.name.toLowerCase().compareTo(o.getName().toLowerCase());
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	public Option(String n, String d, String p)
	{
		name = n;
		data = d;
		path = p;
	}
	
	public String getName()
	{
		return name;
	}
	public String getData()
	{
		return data;
	}
	public String getPath()
	{
		return path;
	}
	
	
}
