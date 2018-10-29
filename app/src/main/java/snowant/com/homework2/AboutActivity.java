package snowant.com.homework2;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtMessage;
    private static final String OK_URL="https://ok.ru/profile/540950179396";

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, AboutActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSend = findViewById(R.id.btn_send);
        btnSend.setOnClickListener(this);
        edtMessage = findViewById(R.id.edt_message);
        ImageView imgOk = findViewById(R.id.img_ok);
        imgOk.setOnClickListener(this);

        createdTextView();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_send:
                sendMessage();
                break;
            case R.id.img_ok:
                openOk();
                break;
        }
    }

    private void sendMessage() {

        String uriText =
                "mailto:pepelanton@gmail.com" +
                        "?subject=" + Uri.encode(getString(R.string.hello)) +
                        "&body=" + Uri.encode(edtMessage.getText().toString());
        Uri uri = Uri.parse(uriText);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                Toast.makeText(this, getString(R.string.not_found_email), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void openOk() {

        Uri address = Uri.parse(OK_URL);
        Intent openOk = new Intent(Intent.ACTION_VIEW, address);

        startActivity(openOk);
    }

    private void createdTextView() {

        RelativeLayout relLayout = findViewById(R.id.rel_layout);
        TextView txtName = new TextView(this);
        txtName.setText(getString(R.string.bottom_name));
        txtName.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_name));

        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        relativeLayoutParams.setMarginEnd((int) getResources().getDimension(R.dimen.standard_margin));
        txtName.setLayoutParams(relativeLayoutParams);
        relLayout.addView(txtName);


    }
}
