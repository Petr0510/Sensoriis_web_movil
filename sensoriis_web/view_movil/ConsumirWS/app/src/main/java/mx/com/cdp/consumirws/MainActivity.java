package mx.com.cdp.consumirws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText txtid, txtTemp, txtHum;
    TextView textView, textView2, textView3;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtid = findViewById(R.id.txtid);
        txtTemp = findViewById(R.id.txtTemp);
        txtHum = findViewById(R.id.txtHum);
        btnEnviar = findViewById(R.id.btnEnviar);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LeerWs();
            }
        });

    }

    private void LeerWs() {

        String url = "https://my-json-server.typicode.com/Petr0510/test_sensor/sensores";

        StringRequest postResquest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    txtid.setText(jsonObject.getString("Sensor_Id"));
                    System.out.println(jsonObject.getString("Sensor_Id"));
                    System.out.println("Enviando Datos");
                    String temp = jsonObject.getString("Temperature");
                    txtTemp.setText(temp.substring(0,4) + " ° C");
                    String hum = jsonObject.getString("Humidity");
                    txtHum.setText(hum.substring(0,4)  + " %");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
            }
        });
        Volley.newRequestQueue(this).add(postResquest);
    }

}