package project.capstone.com.matchingkak;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import net.daum.mf.map.api.MapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private static String TAG_JSON="Success";
    private static String TAG="Matchingkak detail";
    private static String TAG_URL="http://matchingkak.com/mobile/detail.php";
    TextView textView;
    String mJsonString;
    private static  Info info;
    ImageView imageView;
    private MapView mMapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView=(TextView)findViewById(R.id.detail_text);

        Toolbar toolbar=(Toolbar)findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        GetData task=new GetData();
        String gm_no=getIntent().getStringExtra("gm_no");
        task.execute(TAG_URL+"?gm_no="+gm_no);
        //getSupportActionBar().setTitle(info.getString(Info.GM_TITLE));
      initMap();



     //  showInfo();

    }
    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.d("Hash key", something);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
    }
    private void  initMap(){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(DetailActivity.this, "권한 허가", Toast.LENGTH_SHORT).show();

                // MapView 객체생성 및 API Key 설정

                mMapView=new MapView(DetailActivity.this);
                mMapView.setMapType(MapView.MapType.Standard);
                ViewGroup mapViewContainer=(ViewGroup) findViewById(R.id.map_view);

                mapViewContainer.addView(mMapView);


                // 중심점 변경
              //  mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), true);

                // 마커 생성 및 설정

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(DetailActivity.this, "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }

        };


                 TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("지도 서비스를 사용하기 위해서는 위치 접근 권한이 필요해요")
                .setDeniedMessage("왜 거부하셨어요...\n하지만 [설정] > [권한] 에서 권한을 허용할 수 있어요.")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .check();




    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.detail_message:
                Toast.makeText(this,"message",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu,menu);
    return true;
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
            textView.setText(s+"0000000000000000\n00000000000000000000\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
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

