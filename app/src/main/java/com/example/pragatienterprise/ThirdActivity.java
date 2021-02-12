package com.example.pragatienterprise;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pragatienterprise.adapter.PostsAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;




public class  ThirdActivity extends AppCompatActivity {

   Button btnhome,btnadd,btnprofile;
    @BindView(R.id.search)
    EditText searchET;

   // private AdView mAdView;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private DatabaseReference postRef;
    private FirebaseRecyclerAdapter<User, PostsViewHolder> adapter;
    private FirebaseRecyclerOptions<User> options;
    private InterstitialAd mInterstitialAd;
    private ArrayList<User> postsArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        ButterKnife.bind(this);





        //code for bottom  buttons

        btnadd=findViewById(R.id.btnadd);
        btnprofile=findViewById(R.id.btnprofile);
        btnhome=findViewById(R.id.btnhome);


        btnadd.setOnClickListener(view -> startActivity(new Intent(ThirdActivity.this, MainActivity3.class)));
        btnprofile.setOnClickListener(view -> startActivity(new Intent(ThirdActivity.this, update_profile.class)));
        grnrtateAdd();//this line for Advertise


        postRef = FirebaseDatabase.getInstance().getReference().child("Advertise");

        postsArrayList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);


        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    searchPosts(s.toString());
                } else {
                    fillRecyclerViewData();
                }
            }
        });
        fillRecyclerViewData();


    }

    //create add
public void grnrtateAdd(){
    MobileAds.initialize(this,
            "ca-app-pub-3940256099942544~3347511713");

    mInterstitialAd = new InterstitialAd(this);
    mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
    mInterstitialAd.loadAd(new AdRequest.Builder().build());

    mInterstitialAd.setAdListener(new AdListener(){
        @Override
        public void onAdClosed(){
            super.onAdClosed();
        }
         @Override
        public void onAdFailedToLoad(LoadAdError loadAdError){
            super.onAdFailedToLoad(loadAdError);
         }

         @Override
        public void onAdLoaded(){
            super.onAdLoaded();
            if (mInterstitialAd.isLoaded())
                mInterstitialAd.show();

         }
    });
}

    private void searchPosts(String searchString) {
        Query query = postRef.orderByChild("content")
                .startAt(searchString)
                .endAt(searchString + "/uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    postsArrayList.clear();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        final User posts = data.getValue(User.class);
                        postsArrayList.add(posts);
                    }

                    PostsAdapter postsAdapter = new PostsAdapter(getApplicationContext(), postsArrayList);
                    recyclerView.setAdapter(postsAdapter);
                    postsAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void fillRecyclerViewData() {

        options = new FirebaseRecyclerOptions.Builder<User>()
                .setQuery(postRef, User.class).build();

        adapter = new FirebaseRecyclerAdapter<User, PostsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PostsViewHolder holder, int i, @NonNull User model) {
               holder.textView1.setText(model.getContent());
                holder.textView.setText(model.getName());
            }

            @NonNull
            @Override
            public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
                return new PostsViewHolder(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.startListening();
        }
    }




}


