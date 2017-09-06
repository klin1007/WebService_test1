package com.example.administrator.webservice_test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends Activity implements View.OnClickListener{

    private SoapObject soap,soapResult;

    private Button btn1,btn2;
    private TextView tv,tv2;
    private Handler mHandler;
    private List<String> params=new ArrayList<String>();
    private String URL="http://wh-srvcms-vm.wahhong.wahlee.com/MESws2005_WahHong/wsCMS/wsCMS.asmx?WSDL";
    private String NAMESPACE="http://microsoft.com/webservices/";
//    private String METHOD="LoadCompany";
    private String METHOD;
    private String SOAP_ACTION;
    private String Temp;
    private EditText edittext1;

    private SimpleAdapter simpleAdapter;

//    private List<String> companyList = new ArrayList<String>(); // 華宏集團公司名稱列表
//    private List<String> companyList = new ArrayList<String>(); // 華宏集團公司名稱列表
//    private ArrayList companyList = new ArrayList();
    private ArrayList<objloadcompany> companyList = new ArrayList<objloadcompany>();
//    private List<Map<String, Object>> companyList = new ArrayList <Map<String,Object>>(); // 華宏集團公司名稱列表

    //建立存放HashMap資訊的ArrayList物件
    private List<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();
//    private ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
//    private ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

    private ListView mCompanyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //main.xml的ListView
//        ListView mCompanyList = (ListView) findViewById(R.id.company_list);

        btn1 = (Button) findViewById(R.id.btnLoadCompany);
        btn1.setOnClickListener(this);
        METHOD = btn1.getText().toString();

        //main.xml的ListView
        mCompanyList = (ListView) findViewById(R.id.company_list);

//        tv=(TextView)findViewById(R.id.textView);
//        tv.setMovementMethod(new ScrollingMovementMethod());

        mHandler=new Handler()
        {
            public void handleMessage(Message msg)
            {
                switch(msg.what)
                {
                    case 1:
                        String result=msg.obj.toString();
//                        tv2.setText("華氏轉攝氏溫度");

//                        if (parseXML(result)) {
//                            try {
//                                //do something
//                            } catch (Exception e) {
//                                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
//                            }
//                        }

                        if (parseXML(result)) {
                            try {
                                //將資料轉換成HashMap型態存進ListView裡
//                                List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();

                                for(int i = 0; i < companyList.size(); i++){


                                    HashMap<String, Object> map = new HashMap<String, Object>();
//                                        HashMap<String, String> map = new HashMap<String, String>();

//                                        map.put("COMP_CODE", companyList.get(i));//COMP_CODE
//                                        map.put("COMP_CODE", companyList.get(0).equals("COMP_CODE"));//COMP_CODE
                                    map.put("COMP_CODE", companyList.get(i).getcomp_code().toString());//COMP_CODE

//                                        map.put("BUTXT", (companyList.get(i)));//BUTXT
                                    map.put("BUTXT", companyList.get(i).getbutxt().toString());//BUTXT

                                    listItem.add(map);

                                }

//                                simpleAdapter = new SimpleAdapter(MainActivity.this,
//                                        listItem, R.layout.item_layout, new String[]{"COMP_CODE","BUTXT"},
//                                        new int[]{R.id.txtcompcode, R.id.txtbutxt});

                                simpleAdapter = new SimpleAdapter(MainActivity.this,
                                        listItem, R.layout.item_layout, new String[]{"COMP_CODE","BUTXT"},
                                        new int[]{R.id.txtcompcode, R.id.txtbutxt});

//                                // 清單陣列
//                                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
//                                        android.R.layout.simple_list_item_1);
//                                adapter.add("紅豆");
//                                adapter.add("黑豆");
//                                adapter.add("綠豆");
//                                adapter.add("花豆");
//                                adapter.add("毛豆");
//                                adapter.add("土豆");
//                                adapter.add("芋頭");
//                                adapter.add("地瓜");
//
//                                //執行SimpleAdapter
//                                mCompanyList.setAdapter(adapter);

//                                //main.xml的ListView
//                                mCompanyList = (ListView) findViewById(R.id.company_list);

                                //執行SimpleAdapter
                                mCompanyList.setAdapter(simpleAdapter);

                                //do something
//                                    if (result != null) {
//                                        companyList = li;
//                                simpleAdapter = new SimpleAdapter(MainActivity.this,
//                                        companyList, R.layout.item_layout, new String[]{"COMP_CODE", "BUTXT"},
//                                        new int[]{R.id.txtcompcode, R.id.txtbutxt});
//                                    mCompanyList.setAdapter(simpleAdapter);
//                                    mCompanyList.setAdapter(new ArrayAdapter<String>(
//                                                MainActivity.this,
//                                                android.R.layout.simple_list_item_1, companyList));
//                                    } else {
//                                        Toast.makeText(MainActivity.this, "?取WebService?据??",
//                                                Toast.LENGTH_SHORT).show();
//                                    }
                            } catch (Exception e) {
                                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

//                         tv.setText(result);
                        break;
//                    case 2:
//                        result=msg.obj.toString();
//                        tv2.setText("攝氏轉華氏溫度");
//                        tv.setText(result);
//                        break;
                }
            }
        };

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnLoadCompany:
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
//
//                        // creating new SoapObject
////                        soap = GetSoapObject(method_name);
//
//                        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//
//                        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
//
////                        androidHttpTransport.call(NAMESPACE + method_name, envelope);
////
////                        soap = (SoapObject) envelope.getResponse();
//
//
//                        soap = (SoapObject) envelope.bodyIn;
//                        soapResult = (SoapObject)soap.getProperty(0);
//                        for(int i=0;i<soapResult.getPropertyCount();i++)
//                        {
//                            SoapObject so = (SoapObject) soapResult.getProperty(i);
//                            //here, you can get data from xml using so.getProperty("PublicationID")
//                            //or the other tag in xml file.
//                        }
                        SoapObject request=new SoapObject(NAMESPACE,METHOD);
                        Temp="<request><identity><computername>TN-MIS-KKLIN</computername><curuserno>IT001</curuserno><sendtime>2017/08/21 下午 04:35:45</sendtime></identity></request>";
                        request.addProperty("InXml", Temp);
                        SoapSerializationEnvelope env=new SoapSerializationEnvelope(SoapEnvelope.VER11);
//                        env.bodyOut=request;
						env.setOutputSoapObject(request);
                        env.dotNet=true;
                        HttpTransportSE htc=new HttpTransportSE(URL);
//                        AndroidHttpTransport htc = new AndroidHttpTransport(URL);
                        try
                        {
                            SOAP_ACTION=NAMESPACE+METHOD;
                            htc.call(SOAP_ACTION, env);
//							String result=env.
                            String result=env.getResponse().toString();

                            Message msg=new Message();
                            msg.what=1;
                            msg.obj=result;
                            mHandler.sendMessage(msg);

//                            final ListView mCompanyList = (ListView) findViewById(R.id.company_list);
//                            if (parseXML(result)) {
//                                try {
//                                    //將資料轉換成HashMap型態存進ListView裡
//
//                                    for(int i = 0; i < companyList.size(); i++){
//
//                                        HashMap<String, Object> map = new HashMap<String, Object>();
////                                        HashMap<String, String> map = new HashMap<String, String>();
//
////                                        map.put("COMP_CODE", companyList.get(i));//COMP_CODE
////                                        map.put("COMP_CODE", companyList.get(0).equals("COMP_CODE"));//COMP_CODE
//                                        map.put("COMP_CODE", companyList.get(i).getcomp_code().toString());//COMP_CODE
//
////                                        map.put("BUTXT", (companyList.get(i)));//BUTXT
//                                        map.put("BUTXT", companyList.get(i).getbutxt().toString());//BUTXT
//
//                                        listItem.add(map);
//
//                                    }
//
//                                simpleAdapter = new SimpleAdapter(MainActivity.this,
//                                        listItem, R.layout.item_layout, new String[]{"COMP_CODE", "BUTXT"},
//                                        new int[]{R.id.txtcompcode, R.id.txtbutxt});
//
//                                    //main.xml的ListView
//                                    ListView mCompanyList = (ListView) findViewById(R.id.company_list);
//
//                                    //執行SimpleAdapter
//                                    mCompanyList.setAdapter(simpleAdapter);
//
//                                    //do something
////                                    if (result != null) {
////                                        companyList = li;
////                                simpleAdapter = new SimpleAdapter(MainActivity.this,
////                                        companyList, R.layout.item_layout, new String[]{"COMP_CODE", "BUTXT"},
////                                        new int[]{R.id.txtcompcode, R.id.txtbutxt});
////                                    mCompanyList.setAdapter(simpleAdapter);
////                                    mCompanyList.setAdapter(new ArrayAdapter<String>(
////                                                MainActivity.this,
////                                                android.R.layout.simple_list_item_1, companyList));
////                                    } else {
////                                        Toast.makeText(MainActivity.this, "获取WebService数据错误",
////                                                Toast.LENGTH_SHORT).show();
////                                    }
//                                } catch (Exception e) {
//                                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                            if (result != null) {
////                                List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
//                                companyList = parseXML(result);
//                                mCompanyList.setAdapter(new ArrayAdapter<String>(
//                                        MainActivity.this,
//                                        android.R.layout.simple_list_item_1, companyList));
////                                List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
////                                for (int i = 0; i < companyList.size(); i++) {
////                                    Map<String, Object> item = new HashMap<String, Object>();
////                                    item.put("image", image[i]);
////                                    item.put("text", imgText[i]);
////                                    items.add(item);
////                                }
////                                simpleAdapter = new SimpleAdapter(MainActivity.this,
////                                        companyList, R.layout.item_layout, new String[]{"COMP_CODE", "BUTXT"},
////                                        new int[]{R.id.txtcompcode, R.id.txtbutxt});
//////                                simpleAdapter = new SimpleAdapter(this,
//////                                        items, R.layout.item_layout, new String[]{"image", "text"},
//////                                        new int[]{R.id.image, R.id.text});
////                                mCompanyList.setAdapter(simpleAdapter);
//
//                            } else {
//                                Toast.makeText(MainActivity.this, "獲取WebService資料錯誤",
//                                        Toast.LENGTH_SHORT).show();
//                            }
////                            Message msg=new Message();
////                            msg.what=1;
////                            msg.obj=result;
////                            mHandler.sendMessage(msg);
                        }catch(Exception e){
                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        }

//                        try {
//                            androidHttpTransport.call(SOAP_ACTION, envelope);
//
//                            SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
//
//                            TextView showMeTV = (TextView) findViewById(R.id.tv_showme);
//                            showMeTV.setText(resultsRequestSOAP.toString());
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }

                    }
                });

                t.start();
                break;

        }
    }

    /**
     * 解析SoapObject对象 - 公司代碼名稱
     *
     * @param XML
     * @return
     */
//    private List<String> parseXML(String XML){
     private boolean parseXML(String XML){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = null;

            StringReader sr = new StringReader(XML);
            InputSource is = new InputSource(sr);
            try{doc=(Document) builder.parse(is);}
            catch(IOException e){}
            catch (SAXException e){}

            NodeList list = doc.getElementsByTagName("LoadCompany");
//            ArrayList li = new ArrayList();
//            Map<String, Object> item = new HashMap<String, Object>();
            for (int i = 0; i < list.getLength(); i++) {
                objloadcompany obj = new objloadcompany();
                Element element = (Element) list.item(i);
                obj.setComp_code(element.getElementsByTagName("COMP_CODE")
                        .item(0).getFirstChild().getNodeValue());
                obj.setButxt(element.getElementsByTagName("BUTXT")
                        .item(0).getFirstChild().getNodeValue());
//                obj.setDate(element.getElementsByTagName("DATE")
//                        .item(0).getFirstChild().getNodeValue());
//                item.put("COMP_CODE",obj.setComp_code(element.getElementsByTagName("COMP_CODE")
//                        .item(0).getFirstChild().getNodeValue()));
//                obj.setButxt(element.getElementsByTagName("BUTXT")
//                        .item(0).getFirstChild().getNodeValue());
//                li.add(obj);
                companyList.add(obj);
            }

//            Map<String, Object> item = new HashMap<String, Object>();
//            item.put("image", image[i]);
//            item.put("text", imgText[i]);
//            items.add(item);

            return true;
//            return li;
        }catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
