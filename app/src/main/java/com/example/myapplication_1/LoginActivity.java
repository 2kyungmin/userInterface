import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication_1.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnOption1 = findViewById(R.id.btn_option1);
        Button btnOption2 = findViewById(R.id.btn_option2);

        // 버튼 클릭 리스너 추가
        btnOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 옵션 1 처리
            }
        });

        btnOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 옵션 2 처리
            }
        });
    }
}
