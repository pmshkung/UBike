package ubike;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Webb
 */
public class UBike {
    private static final int TIME_OUT = 120000;
    private static final String ACCEPT_ENCODING = "gzip, deflate, br";
    private static final String HOST = "apis.youbike.com.tw";
    private static final String ACCEPT = "*/*";
    private static final String ACCEPT_LANGUAGE = "zh-TW,zh;q=0.8,en-US;q=0.5,en;q=0.3";
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:76.0) Gecko/20100101 Firefox/76.0";
    
    private static boolean jsonToData(final String jsonData) {
        boolean result = false;        
        
        if (null != jsonData) {
            String retCode, retMsg;
            try {
                JSONObject object = new JSONObject(jsonData);
                if (null != object) {
                    retCode = object.get(UBikeJson.UBikeRetCode).toString();
                    retMsg = object.get(UBikeJson.UBikeRetMsg).toString();
                    
                    if (null != retMsg && retMsg.equals("查詢場站資料成功")) {
                        JSONArray array = object.getJSONArray(UBikeJson.UBikeRetVal);
                        int length;
                        if (null != array) {
                            length = array.length();
                            System.out.print("All UBike Stop Numbers: ");
                            System.out.println(length);
                            
                            for (int i = 0; i < length; i++) {
                                JSONObject jsonObject = array.getJSONObject(i);
                                UBikeData data = new UBikeData();
                                
                                data.mCountryCode = jsonObject.isNull(UBikeJson.UBikeStop[0]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[0]).toString();
                                data.mAreaCode = jsonObject.isNull(UBikeJson.UBikeStop[1]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[1]).toString();
                                data.mType = jsonObject.isNull(UBikeJson.UBikeStop[2]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[2]).toString();
                                data.mStatus = jsonObject.isNull(UBikeJson.UBikeStop[3]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[3]).toString();
                                data.mStationNo = jsonObject.isNull(UBikeJson.UBikeStop[4]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[4]).toString();
                                data.mNameTw = jsonObject.isNull(UBikeJson.UBikeStop[5]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[5]).toString();
                                data.mDistrictTw = jsonObject.isNull(UBikeJson.UBikeStop[6]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[6]).toString();
                                data.mAddressTw = jsonObject.isNull(UBikeJson.UBikeStop[7]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[7]).toString();
                                data.mNameEn = jsonObject.isNull(UBikeJson.UBikeStop[8]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[8]).toString();
                                data.mDistrictEn = jsonObject.isNull(UBikeJson.UBikeStop[9]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[9]).toString();
                                data.mAddressEn = jsonObject.isNull(UBikeJson.UBikeStop[10]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[10]).toString();
                                data.mNameCn = jsonObject.isNull(UBikeJson.UBikeStop[11]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[11]).toString();
                                data.mDistrictCn = jsonObject.isNull(UBikeJson.UBikeStop[12]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[12]).toString();
                                data.mAddressCn = jsonObject.isNull(UBikeJson.UBikeStop[13]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[13]).toString();
                                data.mParkingSpaces = jsonObject.isNull(UBikeJson.UBikeStop[14]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[14]).toString();
                                data.mAvailableSpaces = jsonObject.isNull(UBikeJson.UBikeStop[15]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[15]).toString();
                                data.mEmptySpaces = jsonObject.isNull(UBikeJson.UBikeStop[16]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[16]).toString();
                                data.mForbiddenSpaces = jsonObject.isNull(UBikeJson.UBikeStop[17]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[17]).toString();
                                data.mLat = jsonObject.isNull(UBikeJson.UBikeStop[18]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[18]).toString();
                                data.mLng = jsonObject.isNull(UBikeJson.UBikeStop[19]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[19]).toString();                                
                                data.mImg = jsonObject.isNull(UBikeJson.UBikeStop[20]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[20]).toString();
                                data.mUpdatedAt = jsonObject.isNull(UBikeJson.UBikeStop[21]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[21]).toString();
                                data.mTime = jsonObject.isNull(UBikeJson.UBikeStop[22]) ? "null" : jsonObject.get(UBikeJson.UBikeStop[22]).toString();
                                                                                                
                                System.out.println("Stop: " + (i + 1));
                                System.out.println(data);
                            }
                            
                            result = true;
                        }
                    }
                    else {
                        System.out.println("Search Data Error");
                    }
                }
                
                
            } catch (JSONException e) {
                System.out.println(e.toString());
            }
            
        }
        
        return result;
    }
    
    private static String getUBike(HttpURLConnection conn, String type) throws IOException {                
        String result = "", encoding, line;
        boolean gzipped;
        Reader reader;
        StringBuilder response;
        BufferedReader br;
        
        String mUrl = "https://apis.youbike.com.tw/api/front/station/all?lang=tw&type=[TYPE]";
        mUrl = mUrl.replace("[TYPE]", type);
        
        conn = null;       
        try {
            URL url = new URL(mUrl);
            if (url.getProtocol().toLowerCase().equals("https")) {
                SSLSocketFactory.trustAllHosts();
                HttpsURLConnection https = (HttpsURLConnection)url.openConnection();

                if ( null != https ) {
                    https.setHostnameVerifier(SSLSocketFactory.DO_NOT_VERIFY);
                    conn = https;
                }
            } else {
                conn = (HttpURLConnection)url.openConnection();
            }

            if (null != conn) {
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setConnectTimeout(TIME_OUT);
                conn.setReadTimeout(TIME_OUT);
                conn.setRequestProperty("Accept-Encoding", ACCEPT_ENCODING);
                conn.setRequestProperty("host", HOST);
                conn.setRequestProperty("Accept", ACCEPT);
                conn.setRequestProperty("Accept-Language", ACCEPT_LANGUAGE);
                conn.setRequestProperty("User-Agent", USER_AGENT);
            
                conn.connect();
            
                encoding = conn.getHeaderField("Content-Encoding");
                gzipped = ((encoding != null) && (encoding.toLowerCase().contains("gzip")));    
                if (conn.getResponseCode() == 200) {
                    System.out.println("Get UBike Response Code OK.");
                    response = new StringBuilder();
                    if (gzipped) {
                        reader = new InputStreamReader(new GZIPInputStream(conn.getInputStream()), "UTF-8");
                    } else {
                        reader = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    }
                
                    br = new BufferedReader(reader);
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                
                    reader.close();
                    br.close();
        
                    result = response.toString();
                }
            }
        }
         catch (Exception ex) {
            System.out.println("Error Happen");
        }
        finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        
        return result;
    }
    

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO code application logic here
        String ubikeJsonData;
        HttpURLConnection myConn = null;
        ubikeJsonData = getUBike(myConn, "1");
        
        if (null != ubikeJsonData) {
            System.out.print("UBike Json Data: ");
            System.out.println(ubikeJsonData);
            
            jsonToData(ubikeJsonData);
        }
    }    
}
