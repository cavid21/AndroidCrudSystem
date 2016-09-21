package crud.crudsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CrudActivity extends AppCompatActivity implements View.OnClickListener {

    Button bLogout;
    EditText etName , etAge , etUserName;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etUserName = (EditText) findViewById(R.id.etUsername);

        bLogout = (Button) findViewById(R.id.bLogout);

        bLogout.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(authenticate() == true){
            displayUserDetails();
        }else{
            startActivity(new Intent(CrudActivity.this , Login.class));
        }
    }

    private boolean authenticate(){ return userLocalStore.getUserLoggedIn(); }

    private void displayUserDetails(){
        User user = userLocalStore.getLoggedInUser();

        etUserName.setText(user.username);
        etName.setText(user.name);
        etUserName.setText(user.age + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bLogout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(this , Login.class));
                break;
        }
    }
}
