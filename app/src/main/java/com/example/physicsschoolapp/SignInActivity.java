package com.example.physicsschoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.physicsschoolapp.databinding.ActivitySignInBinding;
import com.example.physicsschoolapp.datas.User;
import com.example.physicsschoolapp.datas.enums.ROLE_ENUM;
import com.example.physicsschoolapp.singleton.CurrentUser;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {
    private TextInputEditText et_email, et_password;
    private ActivitySignInBinding binding;
    private static FirebaseDatabase instance = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        et_email = binding.etEmail;
        et_password = binding.etPassword;

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        initialData();
        binding.btnLogin.setOnClickListener(view -> {
            signIn();


        });
    }

    private static void initialData() {
        DatabaseReference db = instance.getReference("Users");
        User user = new User("Jafarbek", "Sayfiddinov", "jafarbeksayfiddinov@gmail.com", "123456",ROLE_ENUM.STUDENT);

        db.child(user.getId().toString()).setValue(user);
    }

    private void signIn() {
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        DatabaseReference database = instance.getReference("Users");
        database.orderByChild("email").equalTo(email)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.exists()) {
                            Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        boolean found = false;
                        for (DataSnapshot userSnap : snapshot.getChildren()) {
                            String dbPassword = userSnap.child("password").getValue(String.class);
                            if (password.equals(dbPassword)) {
                                User value = userSnap.getValue(User.class);
                                CurrentUser.setUser(value);
                                Intent intent=new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(intent);
                                return;
                            }
                        }

                        if (!found) {
                            Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}