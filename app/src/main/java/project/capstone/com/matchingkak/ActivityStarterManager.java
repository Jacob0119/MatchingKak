package project.capstone.com.matchingkak;

import android.content.Context;
import android.content.Intent;

import java.util.HashMap;

import project.capstone.com.matchingkak.detail_info.DetailActivity;
import project.capstone.com.matchingkak.list_game.ListGameActivity;

/**
 * Created by Lee on 2018-04-05.
 */

public class ActivityStarterManager {

    public static void StartGameDetailActivity(Context context,Object args){

        Intent intent=new Intent(context, DetailActivity.class);
        intent.putExtra(config.GM_NO,(String)args);
        context.startActivity(intent);
    }

    public static void StartListGameActivity(Context context, HashMap<String,String> parameters){

        Intent intent=new Intent(context, ListGameActivity.class);
        intent.putExtra(config.LIST_GAME_MAP,parameters);
        context.startActivity(intent);

    }
}
