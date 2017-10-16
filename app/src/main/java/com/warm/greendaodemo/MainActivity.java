package com.warm.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.warm.greendaodemo.dao.entity.Shop;
import com.warm.greendaodemo.dao.entity.ShopDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity11";

    @BindView(R.id.bt_add)
    Button btAdd;
    @BindView(R.id.bt_detele)
    Button btDetele;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.bt_refresh)
    Button btRefresh;
    @BindView(R.id.bt_query)
    Button btQuery;

    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        List<Shop> shops= MyApp.getDaoSession().getShopDao().queryBuilder().build().list();
        i = shops.size();
        ll.removeAllViews();
        for (int i=0;i<shops.size();i++) {
            ll.addView(addView(shops.get(i)));
        }
    }

    @OnClick({R.id.bt_add, R.id.bt_detele,R.id.bt_refresh,R.id.bt_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_add:
                i++;
                final Shop shop = new Shop( "商品" + i, "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png", 3.44f + i, i, "老板" + i,Shop.COLLECT);

               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       Long time=System.currentTimeMillis();
                       Log.d(TAG, "onViewClicked: 1");

                       for (int j=0;j<10000;j++) {
                           MyApp.getDaoSession().getShopDao().insert(shop);
                       }
                       Log.d(TAG, "onViewClicked: 2"+String.valueOf(System.currentTimeMillis()-time));

                   }
               }).start();


                break;
            case R.id.bt_detele:
                MyApp.getDaoSession().getShopDao().deleteAll();
                break;
            case R.id.bt_refresh:
                ll.removeAllViews();
                List<Shop> shops=MyApp.getDaoSession().getShopDao().queryBuilder().build().list();
                for (int i=0;i<shops.size();i++) {
                    ll.addView(addView(shops.get(i)));
                }

                break;
            case R.id.bt_query:
                Shop shop1=MyApp.getDaoSession().getShopDao().loadByRowId(1);
                shop1.setShoper("这是老板改的，不关我事");
                shop1.setType(Shop.BUY);
                MyApp.getDaoSession().getShopDao().update(shop1);

                List<Shop> qu=MyApp.getDaoSession().getShopDao().queryBuilder().where(ShopDao.Properties.Type.eq(Shop.BUY)).build().list();
                Toast.makeText(this, shop1.getShoper()+qu.size(), Toast.LENGTH_SHORT).show();

                break;
        }
    }


    private View addView(Shop shop) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(this).inflate(R.layout.item_ll, ll, false));
        Glide.with(this).load(shop.getPic()).asBitmap().fitCenter().into(holder.iv);
        holder.tv_name.setText(shop.getName());
        holder.tv_info.setText("单价=" + shop.getPrice() + "数量=" + shop.getNum() + "卖家=" + shop.getShoper());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shop shop = new Shop( "更新商品" + i, "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png", 3.44f + i, i, "老板" + i,Shop.COLLECT);
                MyApp.getDaoSession().getShopDao().update(shop);
            }
        });

        return holder.view;
    }



    class ViewHolder {
        private View view;

        ImageView iv;
        TextView tv_name;
        TextView tv_info;

        public ViewHolder(View view) {
            this.view = view;
            iv = (ImageView) view.findViewById(R.id.iv);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_info = (TextView) view.findViewById(R.id.tv_info);
        }


    }

}
