package com.lee.oneweekonebook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class DoneDetailActivity extends AppCompatActivity {
   
    ImageView img_cover;
    SQLiteDatabase database;

    int idSelected = 1;
    String mCurrentPhotoPath;
    TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_detail);

        database = openOrCreateDatabase("book.db", MODE_PRIVATE, null);

        img_cover = (ImageView) findViewById(R.id.img_cover);

        setInit();
    }

    public void setInit(){
        if(database != null){
            String sql = "select pic_cover from " + "read" + " where _id=" + idSelected;

            if(database != null) {
                Cursor cursor = database.rawQuery(sql, null);

                cursor.moveToNext();
                mCurrentPhotoPath = cursor.getString(0);

                File imgFile = new  File(mCurrentPhotoPath);
                if(imgFile.exists()){
                    Uri uri = Uri.fromFile(new File(mCurrentPhotoPath));
                    setTitleImage(uri, mCurrentPhotoPath);                }
                //저장된 표지 사진이 없으면 일반사진으로 대체
                else{
                    img_cover.setImageResource(R.drawable.ic_book);
                }
            }
        }
    }

    //사진 뷰에 띄운다 (회전 후)
    public void setTitleImage(Uri uri, String path){
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);
        Bitmap bitmap;
        Bitmap bmRotated;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            //회전된 사진을 보정한다
            bmRotated = rotateBitmap(bitmap, orientation);
            img_cover.setImageBitmap(bmRotated);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //회전된 사진을 보정한다
    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {
        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        }
        catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }
}
