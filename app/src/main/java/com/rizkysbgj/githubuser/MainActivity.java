package com.rizkysbgj.githubuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rizkysbgj.githubuser.Adapters.UserAdapter;
import com.rizkysbgj.githubuser.Classes.EndlessOnScrollListener;
import com.rizkysbgj.githubuser.Classes.GetUser;
import com.rizkysbgj.githubuser.Classes.User;
import com.rizkysbgj.githubuser.Rest.ApiClient;
import com.rizkysbgj.githubuser.Rest.ApiInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText edtSearch;

    UserAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView rvUsers;

    private ArrayList<User> userList = new ArrayList<>();
    private String query;

    private EndlessOnScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvUsers = findViewById(R.id.rv_users);
        adapter = new UserAdapter(userList, this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        edtSearch = findViewById(R.id.edt_search);

        scrollListener = new EndlessOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(final int page, int totalItemsCount, RecyclerView view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadUrlData(query, page);
                    }
                }, 1000);

            }
        };

        rvUsers.setLayoutManager(linearLayoutManager);
        rvUsers.setItemAnimator(new DefaultItemAnimator());
        rvUsers.setAdapter(adapter);
        rvUsers.addOnScrollListener(scrollListener);

        edtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == keyEvent.ACTION_DOWN) && i == keyEvent.KEYCODE_ENTER) {
                    InputMethodManager inputMethodManager = (InputMethodManager) view.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    Log.e("TEXT", edtSearch.getText().toString());
                    query = edtSearch.getText().toString();
                    scrollListener.resetState();
                    userList.clear();
                    adapter.notifyDataSetChanged();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadUrlData(query,0);
                        }
                    }, 1000);
                }
                return false;
            }
        });
    }

    private void  loadUrlData(String query, final int page) {
        Log.d("Request", "Sent");
        ApiClient.user().getItems(query, page).enqueue(new retrofit2.Callback<GetUser>() {
            @Override
            public void onResponse(Call<GetUser> call, Response<GetUser> response) {
                if (response.body() != null) {
                    ArrayList<User> newUserList = response.body().getItems();
                    Log.d(TAG, call.request().toString());
                    adapter.addAll(newUserList);
                }
            }

            @Override
            public void onFailure(Call<GetUser> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
