package com.hebut.triptogether.UI.PersonalInfor;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.hebut.triptogether.R;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class PhotoActivity extends AppCompatActivity {

    List<MyImage> myImageList=new List<MyImage>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<MyImage> iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] ts) {
            return null;
        }

        @Override
        public boolean add(MyImage myImage) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends MyImage> collection) {
            return false;
        }

        @Override
        public boolean addAll(int i, @NonNull Collection<? extends MyImage> collection) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public MyImage get(int i) {
            return null;
        }

        @Override
        public MyImage set(int i, MyImage myImage) {
            return null;
        }

        @Override
        public void add(int i, MyImage myImage) {

        }

        @Override
        public MyImage remove(int i) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @NonNull
        @Override
        public ListIterator<MyImage> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<MyImage> listIterator(int i) {
            return null;
        }

        @NonNull
        @Override
        public List<MyImage> subList(int i, int i1) {
            return null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        init();
        show();
    }

    private void show() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        MyImageAdapter adapter = new MyImageAdapter(myImageList);
        recyclerView.setAdapter(adapter);
    }

    private void init() {
        for (int i = 0; i < 4; i++) {
            MyImage imagee1 = new MyImage(R.mipmap.imagetest);
            myImageList.add(imagee1);
            MyImage imagee2 = new MyImage(R.mipmap.imagetest);
            myImageList.add(imagee2);
            MyImage imagee3 = new MyImage(R.mipmap.imagetest);
            myImageList.add(imagee3);
            MyImage imagee4 = new MyImage(R.mipmap.imagetest);
            myImageList.add(imagee4);

        }
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
                        MyImage imageenew1 = new MyImage(R.mipmap.imagetest);
                        myImageList.add(imageenew1);
                        break;
                    case 2:
                        MyImage imageenew2 = new MyImage(R.mipmap.imagetest);
                        myImageList.add(imageenew2);
                        break;
                    case 3:
                        MyImage imageenew3 = new MyImage(R.mipmap.imagetest);
                        myImageList.add(imageenew3);
                        break;
                    case 4:
                        MyImage imageenew4 = new MyImage(R.mipmap.imagetest);
                        myImageList.add(imageenew4);
                        break;
                    case 5:
                        MyImage imageenew5 = new MyImage(R.mipmap.imagetest);
                        myImageList.add(imageenew5);
                        break;
                    default:
                        break;
                }
                show();
                break;
            case R.id.remove_item:
                i = random.nextInt(myImageList.size());
                myImageList.remove(i);
                show();
                break;
            default:
                break;
        }
        return true;
    }
}
