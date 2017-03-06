package com.charon.www.younghawkdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.charon.www.younghawkdemo.Fragments.ManageFragment;

public class MainActivity extends AppCompatActivity {
    private TextView mPhotograph,mBook,mProfessor,mStudent,mTable,mMailList,mMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setOnclick();
    }
    private void init(){
        mPhotograph = (TextView) findViewById(R.id.main_photograph);
        mBook = (TextView) findViewById(R.id.main_book);
        mProfessor = (TextView) findViewById(R.id.main_professor);
        mStudent = (TextView) findViewById(R.id.main_student);
        mTable = (TextView) findViewById(R.id.main_table);
        mMailList = (TextView) findViewById(R.id.main_mailList);
        mMore = (TextView) findViewById(R.id.main_more);
    }

    private void setOnclick(){
        PhotoGraphListener photoGraphListener = new PhotoGraphListener();
        BookListener bookListener = new BookListener();
        ProfessorListener professorListener = new ProfessorListener();
        StudentListener studentListener = new StudentListener();
        TableListener tableListener = new TableListener();
        MailListListener mailListListener = new MailListListener();
        MoreListener moreListener = new MoreListener();
        mPhotograph.setOnClickListener(photoGraphListener);
        mBook.setOnClickListener(bookListener);
        mProfessor.setOnClickListener(professorListener);
        mStudent.setOnClickListener(studentListener);
        mTable.setOnClickListener(tableListener);
        mMailList.setOnClickListener(mailListListener);
        mMore.setOnClickListener(moreListener);
    }

    private class PhotoGraphListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "暂时未开放此功能", Toast.LENGTH_SHORT).show();
        }
    }
    private class BookListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, ManageFragment.class);
            startActivity(intent);
        }
    }//done
    private class ProfessorListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
           /* Intent intent = new Intent();
            intent.setClass(MainActivity.this, ProfessorActivity.class);
            startActivity(intent);*/
        }
    }//done
    private class StudentListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "暂时未开放此功能", Toast.LENGTH_SHORT).show();
        }
    }
    private class TableListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "暂时未开放此功能", Toast.LENGTH_SHORT).show();
        }
    }
    private class MailListListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, MailListActivity.class);
            startActivity(intent);
        }
    }
    private class MoreListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "暂时未开放此功能", Toast.LENGTH_SHORT).show();
        }
    }

}
