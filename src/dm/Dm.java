package dm;

import com.dm.helpers.DStatus;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author Anique Tahir<aniquetahir@gmail.com>
 */
public class Dm {
    private static final int DOWNLOAD_UNIT = 102400;//100kB
    public static void main(String[] args) {
        DStatus dstats = DStatus.getConfig();
        if(dstats == null){
            dstats = new DStatus();
        }
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://37.media.tumblr.com/avatar_89bb09c35e4d_128.png");
        try{
            CloseableHttpResponse response = client.execute(httpGet);
            StringEntity myEntity = new StringEntity("");
            HttpEntity respEntity = response.getEntity();
            Header[] responseHeaders = response.getAllHeaders();
            
            for(Header responseHeader:responseHeaders){
                System.out.println(responseHeader.getName()+":"+responseHeader.getValue());
            }
            
            long responseLength = respEntity.getContentLength();
            InputStream respInput = respEntity.getContent();
            
            
            
            ArrayList<byte[]> bufferList = new ArrayList<>();
            while(true){
                byte[] buffer = new byte[DOWNLOAD_UNIT];
                //Read DOWNLOAD_UNIT number of bytes
                int readResp = 0;
                int offset = 0;
                while(offset<DOWNLOAD_UNIT){
                    readResp = respInput.read(buffer, offset, DOWNLOAD_UNIT-offset);
                    offset+=readResp;
                    if(readResp==-1){
                        break;
                    }
                }
                if(readResp==-1){
                    byte[] lastBuffer = new byte[offset+1];
                    System.arraycopy(buffer, 0, lastBuffer, 0, offset+1);
                    bufferList.add(lastBuffer);
                    break;
                }else{
                    bufferList.add(buffer);
                }
            }
            
            int contentLength = ((bufferList.size()-1)*DOWNLOAD_UNIT) + bufferList.get(bufferList.size()-1).length;
            byte[] mainBuffer = new byte[contentLength];
            for(int j=0;j<bufferList.size();j++){
                byte[] tBuffer = bufferList.get(j);
                System.arraycopy(tBuffer, 0, mainBuffer, j * DOWNLOAD_UNIT, tBuffer.length);
            }
            
            System.out.println(new String(mainBuffer,"UTF-8"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        dstats.close();
    }
    
}
