package com.example.mp3player;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	private File currentDir;
	private String song_path;
	private FileArrayAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		currentDir = new File("/sdcard/");
		fill(currentDir);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private void fill(File f)
	{
		File dirs[] = f.listFiles();
		this.setTitle("Current Directory" + f.getName());
		List<Option> dir = new ArrayList<Option>();
		List<Option> fls = new ArrayList<Option>();
		try
		{
			for(File ff: dirs)
			{
				if(ff.isDirectory())
				{
					dir.add(new Option(ff.getName(), "Folder", ff.getAbsolutePath()));
				}
				else
				{
					if(ff.getName().endsWith(".mp3"))
					fls.add(new Option(ff.getName(),"File Size " + ff.length(), ff.getAbsolutePath()));
				}
			}
		}
		catch( Exception e)
		{
			
		}
		Collections.sort(dir);
		Collections.sort(fls);
		dir.addAll(fls);
		/*if(!f.getName().equalsIgnoreCase("sdcard"))
		{
			dir.add(new Option("..", "Parent Directory", f.getParent()));
		}*/
		adapter = new FileArrayAdapter(MainActivity.this, R.layout.file_view, dir);
		this.setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Option o = adapter.getItem(position);
		Log.d("Hi THere", o.getPath());
		if(o.getData().equalsIgnoreCase("folder")||o.getData().equalsIgnoreCase("parent directory")){
				currentDir = new File(o.getPath());
				fill(currentDir);
		}
		else 
		{
			//if(o.getName().endsWith(".mp3"))
			//{
				song_path = o.getPath();
				Log.d("hi",song_path);
				Intent returnIntent = new Intent();
				returnIntent.putExtra("LeftSong", song_path);
				setResult(RESULT_OK, returnIntent);
				finish();
			//}
			/*else 
			{
				Toast.makeText(this, "File Clicked: "+o.getName(), Toast.LENGTH_SHORT).show();
			}*/
		}
	}
	public void onBackPressed()
	{
		if(currentDir.getParent().equalsIgnoreCase("/"))
		{
			Intent PlayListIntent =new Intent();
			setResult(RESULT_OK,PlayListIntent);
			finish();
			}
		else
		{
			currentDir=new File(currentDir.getParent());
			fill(currentDir);
		}
	}

}
