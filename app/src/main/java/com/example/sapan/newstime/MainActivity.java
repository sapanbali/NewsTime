package com.example.sapan.newstime;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.sapan.newstime.Adapter.ListSourceAdapter;
import com.example.sapan.newstime.Common.Common;
import com.example.sapan.newstime.Interface.NewsApi;
import com.example.sapan.newstime.Model.Website;
import com.google.gson.Gson;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
        NewsApi mService;
        ListSourceAdapter adapter;
        SpotsDialog dialog;
        SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem item=menu.findItem(R.id.menu_search);
        SearchView searchView=(SearchView)item.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paper.init(this);

        mService= Common.getNewsApi();

        dialog=new SpotsDialog(this);
        swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadwebsitesources(true);
            }
        });
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadwebsitesources(false);



    }

    private void loadwebsitesources(boolean isRefreshed) {

        if(!isRefreshed){
            String cache=Paper.book().read("cache");
            if(cache!=null && !cache.isEmpty() && !cache.equals(null)) {
                Website website=new Gson().fromJson(cache,Website.class);
                adapter=new ListSourceAdapter(getBaseContext(),website);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }else{

                    dialog.show();

                    mService.getSources().enqueue(new Callback<Website>() {
                    @Override
                    public void onResponse(Call<Website> call, Response<Website> response) {
                      adapter=new ListSourceAdapter(getBaseContext(),response.body());
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);



                        Paper.book().write("cache",new Gson().toJson(response.body()));
                        dialog.dismiss();

                    }

                    @Override
                    public void onFailure(Call<Website> call, Throwable t) {


                    }
                });
            }
        }
        else {

            swipeRefreshLayout.setRefreshing(true);

            mService.getSources().enqueue(new Callback<Website>() {
                @Override
                public void onResponse(Call<Website> call, Response<Website> response) {
                      adapter=new ListSourceAdapter(getBaseContext(),response.body());
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);

                    Paper.book().write("cache",new Gson().toJson(response.body()));

                    swipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<Website> call, Throwable t) {


                }
            });

        }

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
