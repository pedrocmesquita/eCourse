import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HttpRequestThread extends Thread{

    String baseFolder;
    Socket sock;
    DataInputStream inS;
    DataOutputStream outS;
    HttpServerAjax httpServerAjax;
    //Gson json;

    public HttpRequestThread(Socket httpCliSock, String folder, HttpServerAjax httpServerAjax) {
        this.baseFolder = folder;
        this.sock = httpCliSock;
        //this.json = new Gson();
        this.httpServerAjax = httpServerAjax;
    }

    @Override
    public void run() {
        try{
            outS = new DataOutputStream(sock.getOutputStream());
            inS = new DataInputStream(sock.getInputStream());
        } catch (IOException ex){
        }

        try{
            HttpMessage request = new HttpMessage(inS);
            HttpMessage response = new HttpMessage();

            if(request.getMethod().equals("GET")){

                if(request.getURI().equals("/test_request")){
                    try{
                        response.setContentFromString("EXAMPLE RESPONSE",
                                                    "text");

                        response.setResponseStatus("200 ok");
                    }catch (Exception e){
                        response.setContentFromString("BAD REQUEST", "text");

                        response.setResponseStatus("400 BAD REQUEST");
                    }

                    response.send(outS);
                }

            }

            if(request.getMethod().equals("POST")){

            }

            if(request.getMethod().equals("PUT")){

            }

            if(request.getMethod().equals("DELETE")){

            }
        }catch(Exception e){

        }

        try{
            sock.close();
        }catch(Exception e){
        }
    }
}
