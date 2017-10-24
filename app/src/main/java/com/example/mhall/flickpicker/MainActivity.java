package com.example.mhall.flickpicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


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
                // add new item to arraylist (as long as it's not blank)
                if (!movie.equals("")) {
                    movieList.add(movie);
                    // notify listview of data changed
                    adapter.notifyDataSetChanged();

                    //clear input box
                    inputBox.setText("");
                }
            }
        });
    }


    public void pickMovie(View v){
        //check to see if movies have been added
        if (movieList.get(0).contains("No movies yet") || (movieList.size() <= 1)) {
            Toast.makeText(this, "Add movies first!", Toast.LENGTH_SHORT).show();
            return;
        }
        int numMovies = movieList.size();
        int selectedMovie = (int) Math.floor(Math.random() * numMovies);

        displaySelectedMovie(selectedMovie);
    }

    public void displaySelectedMovie(int selectedMovie){
        String selectedMovieName = movieList.get(selectedMovie);
        Toast.makeText(this, "FlickPicker selected " + selectedMovieName + ". Enjoy!", Toast.LENGTH_LONG).show();

    }

}