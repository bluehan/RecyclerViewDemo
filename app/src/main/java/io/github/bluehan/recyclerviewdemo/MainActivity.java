package io.github.bluehan.recyclerviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<String> mDatas;
    private SimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDatas();

        mAdapter = new SimpleAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);

        // 设置RecyclerView布局管理
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        // 设置RecyclerView分隔线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // 设置默认的增删动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new SimpleAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(MainActivity.this, "click:" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "long clickL" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add((char) i + "");
        }
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_listview: {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            }
            case R.id.action_gridview: {
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
                break;
            }
            case R.id.action_hor_gridview: {
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, GridLayoutManager.HORIZONTAL));
                break;
            }
            case R.id.action_staggered: {
                startActivity(new Intent(this, StaggeredActivity.class));
                break;
            }
            case R.id.action_add: {
                mAdapter.addData(1);
                break;
            }
            case R.id.action_delete: {
                mAdapter.deleteData(1);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
