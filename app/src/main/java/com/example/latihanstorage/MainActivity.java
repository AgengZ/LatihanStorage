package com.example.latihanstorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String FILENAME = "NamaFile.txt";
    Button buat, baca, ubah, hapus;
    TextView hasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buat = findViewById(R.id.btnCreate);
        baca = findViewById(R.id.btnRead);
        ubah = findViewById(R.id.btnUpdate);
        hapus = findViewById(R.id.btnDelete);
        hasil = findViewById(R.id.txtHasil);

        buat.setOnClickListener(this);
        baca.setOnClickListener(this);
        ubah.setOnClickListener(this);
        hapus.setOnClickListener(this);
    }

    public void setBuat() {
        String isiFile = "Isi dari Data file teks";
        File file = new File(getFilesDir(),FILENAME);
        FileOutputStream outputStream = null;
        try{
            file.createNewFile();
            outputStream = new FileOutputStream(file,true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setBaca() {
        File sdcard = getFilesDir();
        File file = new File(sdcard,FILENAME);
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line!=null){
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            }catch (Exception e){
                System.out.println("Error "+e.getMessage());
            }
            hasil.setText(text.toString());
        }
    }

    public void setUbah() {
        String ubah = "Update dari file yg akan diubah";
        File file = new File(getFilesDir(),FILENAME);
        FileOutputStream outputStream = null;
        try{
            file.createNewFile();
            outputStream = new FileOutputStream(file,false);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setHapus() {
        File file = new File(getFilesDir(),FILENAME);
        if (file.exists()){
            file.delete();
        }
    }

    @Override
    public void onClick(View v) {
        jalankanPerintah(v.getId());
    }
    public void jalankanPerintah(int id){
        switch (id){
            case R.id.btnCreate:
                setBuat();
                break;
            case R.id.btnRead:
                setBaca();
                break;
            case R.id.btnUpdate:
                setUbah();
                break;
            case R.id.btnDelete:
                setHapus();
                break;
        }
    }
}