package project.capstone.com.matchingkak;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {
    private static String TAG_JSON="Success";
    private static String TAG="Matchingkak detail";
    private static String TAG_URL="http://matchingkak.com/mobile/detail.php";
    TextView textView;
    String mJsonString;
    private static  Info info;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        textView=(TextView)findViewById(R.id.detail_text);

        GetData task=new GetData();
        String gm_no=getIntent().getStringExtra("gm_no");

        task.execute(TAG_URL+"?gm_no="+gm_no);
     //  showInfo();


    }

    private void showInfo(){
        String domain="http://matchingkak.com/";
        imageView=(ImageView) findViewById(R.id.team_img);
       String imageUrl=domain+info.getString("tm_img");
       /* Glide.with(this)
                .load(domain+info.getString(Info.TM_IMG))
                .into(imageView);
*/

    }

    private class GetData extends AsyncTask<String,Void,String>{


        ProgressDialog progressDialog;
        String errorString=null;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=ProgressDialog.show(DetailActivity.this,"Please Wait",null,true,true);
        }

        @Override
        protected void onPostExecute(String s) {
                super.onPostExecute(s);

            progressDialog.dismiss();
            textView.setText(s);
            Log.d(TAG,"response-"+s);

            if(s==null){

                textView.setText(errorString);
            }
            else{
                mJsonString=s;
                showResult();
            }

        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL=params[0];

            try{

                URL url=new URL(serverURL);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();

                int responseStatusCode=httpURLConnection.getResponseCode();
                Log.d(TAG,"response code-"+responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode==HttpURLConnection.HTTP_OK){
                    inputStream=httpURLConnection.getInputStream();
                }else{
                    inputStream=httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader
                        =new InputStreamReader(inputStream,"UTF-8");
                BufferedReader br=new BufferedReader(inputStreamReader);
                StringBuilder sb=new StringBuilder();
                String line;
                while((line=br.readLine())!=null){
                    sb.append(line);
                }

                br.close();
                return sb.toString().trim();

            }catch (Exception e){

                Log.d(TAG,"InsertData:Error",e);
                errorString=e.toString();
                return null;

            }



        }
    }  //class Getdata

    private void showResult(){


    try{


        JSONObject jsonObject=new JSONObject(mJsonString);
        JSONArray jsonArray=jsonObject.getJSONArray(TAG_JSON);

        Log.d("show", ""+ jsonArray.length());
        for(int i=0;i<jsonArray.length();i++){


            JSONObject item=jsonArray.getJSONObject(i);
            Log.d("show",item.toString());
            this.info=new Info(item);
            String domain="http://matchingkak.com/";
            imageView=(ImageView) findViewById(R.id.team_img);

             Glide.with(this)
                .load(domain+info.getString(Info.TM_IMG))
                .into(imageView);




        }
    }catch(JSONException e){
        Log.d(TAG,"showResult:",e);
    }


}

     class Info{

      static final String GM_NO= "gm_no";
      static final String TM_NO="tm_no";
      static final String GM_DATA="gm_date";
      static final String GM_ADDR="gm_addr";
      static final String GM_GYM="gm_gym";
      static final String GM_STATE="gm_state";
      static final String GM_MATCH_MBNO="gm_match_mbno";
      static final String GM_GAME_TIME="gm_game_time";
      static final String GM_WRITE_DATE="gm_write_date";
      static final String GM_TITLE="gm_title";
      static final String GM_IMG="gm_img";
      static final String GM_COLOR="gm_color";
      static final String GM_MEMO="gm_memo";
      static final String MB_NICK="mb_nick";
      static final String TM_NAME="tm_name";
      static final String TM_INFO="tm_info";
      static final String TM_MATCH_COUNT="tm_match_count";
      static final String TM_SPORT="tm_sport";
      static final String TM_IMG="tm_img";

      private JSONObject item;
    Info(JSONObject item){
        this.item=item;
        Log.d("info",item.toString());

    }
    String getString(String tag){

        try {
            return item.getString(tag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
   public String toString(){

        return item.toString();
    }




  }


}

