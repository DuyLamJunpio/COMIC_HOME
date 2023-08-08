package com.example.asm_android_networking.Login;

import static com.example.asm_android_networking.RetrofitRequest.getRetrofit;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asm_android_networking.Comic.ComicActivity;
import com.example.asm_android_networking.R;
import com.example.asm_android_networking.databinding.FragmentLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LoginFragment extends Fragment {
    private LoginDAO loginDAO;

    private FragmentLoginBinding binding;

    public static String TOKEN;
    public static String ID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment using ViewBinding
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginDAO = getRetrofit().create(LoginDAO.class);


        binding.btnSignIn.setOnClickListener(v -> {
            String edUsernameLogin = binding.edUserNameLogin.getText().toString();
            String edPassLogin = binding.edPassLogin.getText().toString();
            Call<LoginModel> call = loginDAO.postData(new LoginModel(edUsernameLogin, edPassLogin));
            call.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    if (response.isSuccessful()) {
                        LoginModel loginResponse = response.body();
                        Log.d("TOKEN", loginResponse.toString());
                        if (loginResponse != null && loginResponse.getStatus() == 1) {
                            LoginModel.Data data = loginResponse.getData();
                            if (data != null) {
                                TOKEN = "Bearer "+data.getAccessToken();
                                ID = data.getObjU().get_id();
                                clearData();
                            } else {
                                Log.d("TOKEN", "Data is null");
                            }
                        } else {
                            Log.d("TOKEN", "Response data is null or status is not 1");
                        }
                    } else {
                        Log.d("TOKEN", "Response not successful");
                    }
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    Toast.makeText(getActivity(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                }
            });
        });

    }

    private void clearData() {
        binding.edUserNameLogin.setText("");
        binding.edPassLogin.setText("");
        Intent intent = new Intent(getActivity(), ComicActivity.class);
        startActivity(intent);
    }
}