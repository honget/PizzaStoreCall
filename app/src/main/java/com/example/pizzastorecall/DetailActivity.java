package com.example.pizzastorecall;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.pizzastorecall.databinding.ActivityDetailBinding;
import com.example.pizzastorecall.datas.Store;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding act;

    Store storeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setValue();
        setEvent();

    }

    void setEvent(){

        act.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        Uri uri = Uri.parse(String.format("tel:%s", storeData.getCallNum()));

                        Intent intent = new Intent(Intent.ACTION_CALL, uri);

                        startActivity(intent);

                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {

                    }
                };

                TedPermission.with(DetailActivity.this)
                        .setPermissionListener(permissionlistener)
                        .setDeniedMessage("설정에서 승인을 하지 않으면 사용 할수 없습니다.")
                        .setPermissions(Manifest.permission.INTERNET, Manifest.permission.CALL_PHONE)
                        .check();
            }
        });

        act.okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //메인으로 돌아가기
                Intent resultIntent = new Intent();

                resultIntent.putExtra("PIZZA_NM",act.pizzaItem.getSelectedItem().toString());
                resultIntent.putExtra("STORE_NM",storeData.getName());


                /** finish 하니 전 결과를 설정
                 *  완료값
                 *  전달값
                 */
                setResult(RESULT_OK, resultIntent);

                finish();

            }
        });
    }

    void setValue(){

        storeData = (Store) getIntent().getSerializableExtra("STORE");
        setTitle(String.format("%s 상세정보", storeData.getName()));
        act = DataBindingUtil.setContentView(this, R.layout.activity_detail);


        Glide.with(DetailActivity.this).load(storeData.getLogoImgUrl()).into(act.logoImg);

        act.storeNmTxt.setText(storeData.getName());
        act.callNum.setText(storeData.getCallNum());
    }
}
