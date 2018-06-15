package com.hebut.triptogether.UI.PersonalInfor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.hebut.triptogether.R;

import java.util.Random;

public class PhotoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.photo_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i ;
        Random random = new Random();
        switch (item.getItemId()){
            case R.id.add_item:
                i=random.nextInt(5)+1;
                switch (i){
                    case 1:
                        ImageView imageenew1 = findViewById(R.id.image1);
                        imageenew1.setImageResource(R.mipmap.yueyanglou);
                        break;
                    case 2:
                        ImageView imageenew2 = findViewById(R.id.image2);
                        imageenew2.setImageResource(R.mipmap.changcheng);
                        break;
                    case 3:
                        ImageView imageenew3 = findViewById(R.id.image3);
                        imageenew3.setImageResource(R.mipmap.xihu);
                        break;
                    case 4:
                        ImageView imageenew4 = findViewById(R.id.image4);
                        imageenew4.setImageResource(R.mipmap.tianjinzhiyan);
                        break;
                    case 5:
                        ImageView imageenew5 = findViewById(R.id.image5);
                        imageenew5.setImageResource(R.mipmap.yiheyuan);
                        break;
                    default:
                        break;
                }

                break;
            case R.id.remove_item:
                i=random.nextInt(5)+1;
                switch (i){
                    case 1:
                        ImageView imageenew1 = findViewById(R.id.image1);
                        imageenew1.setImageDrawable(null);
                        break;
                    case 2:
                        ImageView imageenew2 = findViewById(R.id.image1);
                        imageenew2.setImageDrawable(null);
                        break;
                    case 3:
                        ImageView imageenew3 = findViewById(R.id.image2);
                        imageenew3.setImageDrawable(null);
                        break;
                    case 4:
                        ImageView imageenew4 = findViewById(R.id.image3);
                        imageenew4.setImageDrawable(null);
                        break;
                    case 5:
                        ImageView imageenew5 = findViewById(R.id.image4);
                        imageenew5.setImageDrawable(null);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return true;
    }
}
