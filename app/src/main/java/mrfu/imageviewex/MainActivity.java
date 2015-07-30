package mrfu.imageviewex;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import mrfu.imageviewex.lib.ImageViewEx;
import mrfu.imageviewex.lib.RoundImageView;


public class MainActivity extends Activity {


    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTitleBar();
        initViewAndData();
    }

    private void initTitleBar() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("ImageView Load");
        toolbar.setLogo(R.mipmap.logo);
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
               @Override
               public boolean onMenuItemClick(MenuItem item) {
                 Uri uri = Uri.parse("https://github.com/MrFuFuFu/ImageViewEx");
                 Intent i = new Intent(Intent.ACTION_VIEW, uri);
                 i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 startActivity(i);
                 return false;
               }
           }
        );
    }

    private void initViewAndData() {
        ImageViewEx imageviewex = (ImageViewEx)findViewById(R.id.imageview);
        RoundImageView roundimageview1 = (RoundImageView)findViewById(R.id.roundimageview1);
        RoundImageView roundimageview2 = (RoundImageView)findViewById(R.id.roundimageview2);
        imageviewex.loadImage("http://f.hiphotos.baidu.com/image/pic/item/ae51f3deb48f8c5471a15c2e38292df5e0fe7f45.jpg");
        roundimageview1.loadImage("http://f.hiphotos.baidu.com/image/pic/item/ae51f3deb48f8c5471a15c2e38292df5e0fe7f45.jpg");
        roundimageview2.setConer(10, 10);//设置圆角图片
        roundimageview2.loadImage("http://f.hiphotos.baidu.com/image/pic/item/ae51f3deb48f8c5471a15c2e38292df5e0fe7f45.jpg");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
