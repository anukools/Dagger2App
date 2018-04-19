package in.anukool.dagger2app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

import javax.inject.Inject;

import in.anukool.dagger2app.data.DataManager;
import in.anukool.dagger2app.data.model.User;
import in.anukool.dagger2app.di.component.ActivityComponent;
import in.anukool.dagger2app.di.component.DaggerActivityComponent;
import in.anukool.dagger2app.di.module.ActivityModule;

public class MainActivity extends AppCompatActivity {

    @Inject
    DataManager mDataManager;

    @Inject
    ActivityComponent activityComponent;

    private EditText mEtvUserName;
    private EditText mEtvUserAddress;
    private TextView mTvUserInfo;
    private TextView mTvRandomKey;

    private Button mSaveButton;
    private Button mGetButton;

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(Dagger2Application.get(this).getComponent())
                    .build();
        }
        return activityComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        mEtvUserName = (EditText) findViewById(R.id.etv_name);
        mEtvUserAddress = (EditText) findViewById(R.id.etv_address);
        mTvUserInfo = (TextView) findViewById(R.id.tv_user_info);
        mTvRandomKey = (TextView) findViewById(R.id.tv_sample_token);
        mGetButton = (Button) findViewById(R.id.fetch_button);
        mSaveButton = (Button) findViewById(R.id.save_button);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserDetails();
            }
        });

        mGetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserDetails();
            }
        });
    }

    private void saveUserDetails() {
        try {
            // mock to save the details in database
            mDataManager.createUser(new User(mEtvUserName.getText().toString(), mEtvUserAddress.getText().toString()));

            // mock to save the key in preference
            mDataManager.saveRandomKey(UUID.randomUUID().toString());

            Toast.makeText(this, "User details are saved!", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getUserDetails() {
        try {
            User user = mDataManager.getUser(1L);
            mTvUserInfo.setText(user.toString());

            String token = mDataManager.geSavedKey();
            if (token != null) {
                mTvRandomKey.setText(token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
