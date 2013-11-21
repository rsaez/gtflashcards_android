package com.example.gtflashcards.http_request;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;

import com.example.gtflashcards.convertor.OutputConvertor;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;

public class AsyncPOSTRequest extends AsyncTask<String, Void, String> {
	private List<String> datas;
	private OutputConvertor convertor;
	private final ProgressDialog dialog;
	
	public AsyncPOSTRequest(Context contex, List<String> ds, OutputConvertor cvt) {
		datas = ds;
		convertor = cvt;
		dialog = new ProgressDialog(contex);
	}
    
    @Override
    protected void onPostExecute(String result) {            
        super.onPostExecute(result);
        dialog.dismiss();
    }

    @Override
    protected void onPreExecute() {        
        super.onPreExecute();
        dialog.setMessage("Wait ...");
        dialog.show();
    }
	
	@Override
	protected String doInBackground(String... params) {
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		try {
            URL u = new URL(params[0]);

            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            conn.setDoInput(true);
            conn.setDoOutput(true);
            
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            
            writer.write(convertor.outputConvert(datas));
            writer.flush();
            writer.close();
            os.close();

            conn.connect();

            return "Done!";
        }
        
        catch(Throwable t) {
            t.printStackTrace();
        }
		return null;
	}

}
