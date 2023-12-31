package com.example.cityb;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.parseXML).setOnClickListener(v -> {
            try {
                XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = parserFactory.newPullParser();
                InputStream is = getAssets().open("data.xml");
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(is, null);

                String xmltext = "XML DATA\n\n\n";
                for (int eventType = parser.getEventType(); eventType != XmlPullParser.END_DOCUMENT; eventType = parser.next())
                    if (eventType == XmlPullParser.START_TAG) {
                        String tag = parser.getName();
                        if (!"value".equals(tag))
                            xmltext += tag + " : " + parser.nextText() + "\n";
                    }
                ((TextView) findViewById(R.id.RXML)).setText(xmltext);
                findViewById(R.id.parseXML).setVisibility(View.GONE);
                is.close();
            } catch (XmlPullParserException | IOException ignored) {
            }
        });

        findViewById(R.id.parseJSON).setOnClickListener(v -> {
            try {
                InputStream is = getAssets().open("data.json");

                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                is.close();

                JSONObject obj = new JSONArray(new String(buffer)).getJSONObject(0);
                String jsontext = "JSON DATA\n\n\n";
                Iterator itr = obj.keys();
                while (itr.hasNext()){
                    String key = (String) itr.next();
                    jsontext += key + " : " + obj.getString(key) + "\n";
                }
                ((TextView) findViewById(R.id.RJSON)).setText(jsontext);

                findViewById(R.id.parseJSON).setVisibility(View.GONE);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
    }
}
