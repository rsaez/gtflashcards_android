package com.example.gtflashcards.http_request;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import edu.gatech.adapter.CustomAdapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AsyncListViewLoader<T> extends AsyncTask<String, Void, List<T>> {
	private Context context;
	private ListView listView;
	private CustomAdapter<T> adapter;
	private InputConvertor<T> convertor;
	private final ProgressDialog dialog;
	
	public AsyncListViewLoader(Context ctx, ListView lv, CustomAdapter<T> adpt, InputConvertor<T> cvt) {
		context = ctx;
		listView = lv;
		adapter = adpt;
		convertor = cvt;
		dialog = new ProgressDialog(context);
	}

    @Override
    protected void onPostExecute(List<T> result) {            
        super.onPostExecute(result);
        dialog.dismiss();
        adapter.setItemList(result);
        listView.setAdapter(adapter);   
    }

    @Override
    protected void onPreExecute() {        
        super.onPreExecute();
        dialog.setMessage("Downloading courses...");
        dialog.show();
    }
    
	@Override
	protected List<T> doInBackground(String... params) {
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 
		
        List<T> result = new ArrayList<T>();

        try {
            URL u = new URL(params[0]);

            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");

            conn.connect();
            InputStream is = conn.getInputStream();

            // Read the stream
            byte[] b = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while ( is.read(b) != -1 )
                baos.write(b);

            String JSONResp = new String(baos.toByteArray());

            JSONArray arr = new JSONArray(JSONResp);
            
            for (int i=0; i < arr.length(); i++) {
                result.add(convertor.inputConvert(arr.getJSONObject(i)));
            }

            return result;
        }
        
        catch(Throwable t) {
            t.printStackTrace();
        }
		
		return null;
	}

}
