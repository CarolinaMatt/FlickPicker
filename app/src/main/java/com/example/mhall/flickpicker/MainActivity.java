package com.example.mhall.flickpicker;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import static android.R.id.edit;
import static android.R.id.list;
import static com.example.mhall.flickpicker.R.id.addMovie;
import static com.example.mhall.flickpicker.R.id.movieInput;


public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    EditText inputBox;
    ArrayList<String> movieList;
    ListView listV;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] items={"No movies yet."};
        movieList = new ArrayList<String>(Arrays.asList(items));
        adapter=new ArrayAdapter<String>(this,R.layout.list,R.id.movieTextView,movieList);
        listV = (ListView)findViewById(R.id.list);
        listV.setAdapter(adapter);
        inputBox = (EditText)findViewById(R.id.movieInput);
        Button addMovieBtn =(Button)findViewById(R.id.addMovie);

        addMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movieList.get(0).contains("No movies yet")){
                    movieList.remove(0);
                }
                String movie = inputBox.getText().toString();
                // add new item to arraylist
                movieList.add(movie);
                // notify listview of data changed
                adapter.notifyDataSetChanged();

                //clear input box
                inputBox.setText("");
            }
        });
    }


    public void pickMovie(View v){
        int numMovies = movieList.size();
        int selectedMovie = (int) Math.floor(Math.random() * numMovies);

        displaySelectedMovie(selectedMovie);
    }

    public void displaySelectedMovie(int selectedMovie){
        String selectedMovieName = movieList.get(selectedMovie);
        Toast.makeText(this, "FlickPicker selected " + selectedMovieName + ". Enjoy!", Toast.LENGTH_LONG).show();

    }

}