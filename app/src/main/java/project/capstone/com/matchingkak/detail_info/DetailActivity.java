package project.capstone.com.matchingkak.detail_info;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import project.capstone.com.matchingkak.Message.Message2Activity;
import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.config;
import retrofit2.Call;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {


    String mJsonString;
    private static  Info info;
    ImageView imageView;
    private MapView mMapView;
    private State state;
    private StateManager stateManager;
private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        stateManager=StateManager.getInstance();


         toolbar=findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        GetData task=new GetData();
        String gm_no=getIntent().getStringExtra(config.GM_NO);
        task.execute(gm_no);
        //getSupportActionBar().setTitle(info.getString(Info.GM_TITLE));




     //  showInfo();

    }

    private void  initMap(final String addr){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {

                MapPoint point;
                // MapView 객체생성 및 API Key 설정

                mMapView=new MapView(DetailActivity.this);
                mMapView.setMapType(MapView.MapType.Standard);
                ViewGroup mapViewContainer=(ViewGroup) findViewById(R.id.map_view);

                Geocoder coder=new Geocoder(DetailActivity.this);
                List<Address> addressList=null;
                try {
                    addressList=coder.getFromLocationName(addr,1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(addr!=null){


                        Address location=addressList.get(0);
                        double lat=location.getLatitude();
                        double lon=location.getLongitude();
                        point= MapPoint.mapPointWithGeoCoord(lat,lon);

                        mMapView.setMapCenterPoint(point, true);


                    MapPOIItem marker = new MapPOIItem();
                    marker.setItemName("Default Marker");
                    marker.setTag(0);
                    marker.setMapPoint(point);
                    marker.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
                    marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

                    mMapView.addPOIItem(marker);
                }



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
                Intent intent=new Intent(this,Message2Activity.class);
                intent.putExtra(config.MB_NICK,info.getMbNick());
                startActivity(intent);
                //Toast.makeText(this,"message",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu,menu);
    return true;
    }




    private void showResult(){


            //Gson gson =new Gson();
          //  Info info=gson.fromJson(mJsonString,Info.class );

        String domain="http://matchingkak.com/";
        int btnState=Integer.parseInt(info.getBtnState().getBtnState());
        Log.d("btnState",btnState+"");
        stateManager.setState(btnState,info,this);
        Button submit= findViewById(R.id.Detail_submit_btn);
        stateManager.setBtn(submit);

        toolbar.setTitle(info.getGmTitle());

        TextView gm_memo= findViewById(R.id.Detail_gm_memo);
        gm_memo.setText(info.getGmMemo());
        TextView gm_date= findViewById(R.id.Detail_gm_date );
        gm_date.setText(info.getGmDate());
        TextView gm_gym=findViewById(R.id.Detail_gm_gym);
        gm_gym.setText(info.getGmGym());
        TextView gm_color=findViewById(R.id.Detail_gm_color);
        gm_color.setText(info.getGmColor());
        TextView tm_name=findViewById(R.id.Detail_tm_name);
        tm_name.setText(info.getTmName());
        TextView mb_nick=findViewById(R.id.Detail_mb_nick);
        mb_nick.setText(info.getMbNick());
        TextView match_count=findViewById(R.id.Detail_match_count);
        match_count.setText(info.getTmMatchCount());

        imageView= findViewById(R.id.Detail_team_img);
             Glide.with(this)
                .load(domain+info.getTmImg())
                .into(imageView);

        initMap(info.getGmAddr());





}

    class GetData extends AsyncTask<String,Void,String> {


        String errorString=null;





        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




                if(info!=null){
                  showResult();
                }
            else{

                }



        }

        @Override
        protected String doInBackground(String... params) {
           String gm_no=params[0];
            Log.d("doIn",gm_no);
         Call<Info> call=   DetailService.getRetrofit().requestInfo(gm_no);

            try {
                Response<Info> response=call.execute();
                info=response.body();
                return response.body().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }


           /*
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
                if(responseStatusCode== HttpURLConnection.HTTP_OK){
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
                Log.d(TAG,sb.toString());
                return sb.toString().trim();

            }catch (Exception e){

                Log.d(TAG,"InsertData:Error",e);
                errorString=e.toString();
                return null;

            }



*/
       return null;
        }
    }  //class Getdata




}

